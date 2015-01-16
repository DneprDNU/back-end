package org.dnu.filestorage.controller;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Throwables;
import org.apache.commons.beanutils.BeanUtils;
import org.dnu.filestorage.model.Category;
import org.dnu.filestorage.model.Resource;
import org.dnu.filestorage.model.Subject;
import org.dnu.filestorage.search.ResourceSearchRepository;
import org.dnu.filestorage.service.dao.CategoryDAO;
import org.dnu.filestorage.service.dao.ResourceDAO;
import org.dnu.filestorage.service.dao.SubjectDAO;
import org.dnu.filestorage.utils.FileUploader;
import org.dnu.filestorage.utils.HibernateAwareObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomCollectionEditor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author demyura
 * @since 15.10.14
 */
@Controller
@RequestMapping("/rest/resource")
public class ResourceController {

    @Autowired
    ResourceSearchRepository resourceSearchRepository;
    @Autowired
    private FileUploader fileUploader;
    @Autowired
    private CategoryDAO categoryDAO;
    @Autowired
    private SubjectDAO subjectDAO;
    private ObjectMapper mapper = new HibernateAwareObjectMapper();
    @Autowired
    private ResourceDAO dao;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.setDisallowedFields("resource", "image");

        binder.registerCustomEditor(List.class, "categories", new CustomCollectionEditor(List.class) {
            protected Object convertElement(Object element) {
                if (element instanceof Category) {
                    return element;
                }
                if (element instanceof String) {
                    Category category = categoryDAO.get(Long.valueOf((String) element));
                    return category;
                }
                return null;
            }
        });

        binder.registerCustomEditor(List.class, "subjects", new CustomCollectionEditor(List.class) {
            protected Object convertElement(Object element) {
                if (element instanceof Category) {
                    return element;
                }
                if (element instanceof String) {
                    Subject subject = subjectDAO.get(Long.valueOf((String) element));
                    return subject;
                }
                return null;
            }
        });
    }

    @RequestMapping
    @ResponseBody
    public List<Resource> listAll() {
        return this.dao.list();
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> create(HttpServletRequest request, @ModelAttribute Resource res, @RequestParam(required = false) MultipartFile resource, @RequestParam(required = false) MultipartFile image) throws IOException {

        if (resource != null) {
            String fileUrl = fileUploader.uploadFile(resource);
            res.setResource(fileUrl);
        }
        if (image != null) {
            String imageUrl = fileUploader.uploadFile(image);
            res.setImage(imageUrl);
        }

        Resource created = dao.create(res);

        resourceSearchRepository.index(created);

        Map<String, Object> m = new HashMap<String, Object>();
        m.put("success", true);
        m.put("created", created);
        return m;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Resource get(@PathVariable Long id) {
        return this.dao.get(id);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    @ResponseBody
    public Map<String, Object> update(@PathVariable Long id, @RequestParam(value = "resource") String resourceString, @RequestParam MultipartFile file, @RequestParam(required = false) MultipartFile image) throws IOException {

        Resource resource = new HibernateAwareObjectMapper().readValue(resourceString, Resource.class);
        Resource entity = this.dao.get(id);
        try {
            BeanUtils.copyProperties(entity, resource);
        } catch (Exception e) {
            throw Throwables.propagate(e);
        }

        if (file != null) {
            String fileUrl = fileUploader.uploadFile(file);
            resource.setResource(fileUrl);
        }
        if (image != null) {
            String imageUrl = fileUploader.uploadFile(image);
            resource.setImage(imageUrl);
        }

        Resource updated = this.dao.update(resource);

        Map<String, Object> m = new HashMap<String, Object>();
        m.put("success", true);
        m.put("id", id);
        m.put("updated", updated);
        return m;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public Map<String, Object> delete(@PathVariable Long id) {
        this.dao.remove(id);
        Map<String, Object> m = new HashMap<String, Object>();
        m.put("success", true);
        return m;
    }

    @RequestMapping(params = "categoryId")
    @ResponseBody
    public List<Resource> listByCategoryId(@RequestParam("categoryId") Long categoryId) {
        return this.dao.listByCategoryId(categoryId);
    }

    @RequestMapping(params = "teacherId")
    @ResponseBody
    public List<Resource> listByTeacherIdByLinks(@RequestParam("teacherId") Long teacherId) {
        return this.dao.listResourcesByTeacherIdByLinks(teacherId);
    }
}
