package org.dnu.filestorage.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wordnik.swagger.annotations.Api;
import org.dnu.filestorage.data.dto.Count;
import org.dnu.filestorage.data.model.Category;
import org.dnu.filestorage.data.model.Resource;
import org.dnu.filestorage.data.model.Subject;
import org.dnu.filestorage.data.service.CategoryService;
import org.dnu.filestorage.data.service.ResourceService;
import org.dnu.filestorage.data.service.SubjectService;
import org.dnu.filestorage.search.ResourceSearchRepository;
import org.dnu.filestorage.utils.FileUploader;
import org.dnu.filestorage.utils.HibernateAwareObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomCollectionEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartRequest;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author demyura
 * @since 15.10.14
 */
@Controller
@Api(value = "resources", description = "Endpoint for resource management")
@RequestMapping("/rest/resource")
public class ResourceController {

    String defaultImage = "http://dnu.thebodva.com/upload/b32f3d1ef28edf602362b91cb935886f.jpg";

    @Autowired
    ResourceSearchRepository resourceSearchRepository;
    @Autowired
    private FileUploader fileUploader;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private SubjectService subjectService;
    private ObjectMapper mapper = new HibernateAwareObjectMapper();
    @Autowired
    private ResourceService service;
    @Autowired
    private ObjectMapper objectMapper;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.setDisallowedFields("resource", "image");

        binder.registerCustomEditor(List.class, "categories", new CustomCollectionEditor(List.class) {
            protected Object convertElement(Object element) {
                if (element instanceof Category) {
                    return element;
                }
                if (element instanceof String) {
                    Category category = categoryService.get(Long.valueOf((String) element));
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
                    Subject subject = subjectService.get(Long.valueOf((String) element));
                    return subject;
                }
                return null;
            }
        });
    }

    @RequestMapping
    @ResponseBody
    public List<Resource> listAll() {
        return this.service.list();
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> create(@RequestParam(value = "resource") String resourceString, @RequestParam(required = false) MultipartFile file, @RequestParam(required = false) MultipartFile image) throws IOException {

        Resource res = objectMapper.readValue(resourceString, Resource.class);

        if (file != null) {
            String fileUrl = fileUploader.uploadFile(file);
            res.setResource(fileUrl);
        }
        if (image != null) {
            String imageUrl = fileUploader.uploadFile(image);
            res.setImage(imageUrl);
        }

        Resource created = service.create(res);

        resourceSearchRepository.index(created);

        Map<String, Object> m = new HashMap<String, Object>();
        m.put("success", true);
        m.put("created", created);
        return m;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Resource get(@PathVariable Long id) throws UnknownHostException {
        Resource resource = this.service.get(id);
        if (!resource.getResource().isEmpty() && !resource.getImage().equals(defaultImage)) {
            resource.setResource("http://212.3.125.102:8080/filestorage/files?fileName=" + resource.getResource());
        }
        if (!resource.getImage().isEmpty() && !resource.getImage().equals(defaultImage)) {
            resource.setImage("http://212.3.125.102:8080/filestorage/files?fileName=" + resource.getImage());
        }
        return resource;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    @ResponseBody
    public Map<String, Object> update(MultipartRequest multipartRequest ,@PathVariable Long id, @RequestParam(value = "resource") String resourceString, @RequestParam(required = false) MultipartFile file, @RequestParam(required = false) MultipartFile image) throws IOException {

        Resource resource = objectMapper.readValue(resourceString, Resource.class);

        if (file != null) {
            String fileUrl = fileUploader.uploadFile(file);
            resource.setResource(fileUrl);
        }
        if (image != null) {
            String imageUrl = fileUploader.uploadFile(image);
            resource.setImage(imageUrl);
        }

        Resource updated = this.service.update(resource);

        Map<String, Object> m = new HashMap<String, Object>();
        m.put("success", true);
        m.put("id", id);
        m.put("updated", updated);
        return m;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public Map<String, Object> delete(@PathVariable Long id) {
        this.service.remove(id);
        Map<String, Object> m = new HashMap<String, Object>();
        m.put("success", true);
        return m;
    }

    @RequestMapping(params = "categoryId")
    @ResponseBody
    public List<Resource> listByCategoryId(@RequestParam("categoryId") Long categoryId) {
        return this.service.listByCategoryId(categoryId);
    }

    @RequestMapping(params = "teacherId")
    @ResponseBody
    public List<Resource> listByTeacherIdByLinks(@RequestParam("teacherId") Long teacherId) {
        return this.service.listResourcesByTeacherIdByLinks(teacherId);
    }

    @RequestMapping(params = {"from", "to"})
    @ResponseBody
    public List<Resource> listPaged(@RequestParam int from, @RequestParam int to) {
        return this.service.list(from, to);
    }

    @RequestMapping(value = "/count")
    @ResponseBody
    public Count getCount() {
        return new Count(this.service.getCount());
    }
}
