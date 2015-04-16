package org.dnu.filestorage.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wordnik.swagger.annotations.Api;
import org.dnu.filestorage.data.dto.Count;
import org.dnu.filestorage.data.model.Resource;
import org.dnu.filestorage.data.service.CategoryService;
import org.dnu.filestorage.data.service.ResourceService;
import org.dnu.filestorage.data.service.SubjectService;
import org.dnu.filestorage.search.ResourceSearchRepository;
import org.dnu.filestorage.utils.FileUploader;
import org.dnu.filestorage.utils.HibernateAwareObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
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

    String defaultImage = "http://cumbrianrun.co.uk/wp-content/uploads/2014/02/default-placeholder.png";

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
            res.setFileR("http://212.3.125.102:8080/filestorage/files?fileName=" + fileUrl);
        }
        if (image != null) {
            String imageUrl = fileUploader.uploadFile(image);
            res.setImage("http://212.3.125.102:8080/filestorage/files?fileName=" +imageUrl);
        }

        Resource created = service.create(res);
        created.getSubjects().size();

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
        if (resource.getImage() == null || resource.getImage().isEmpty()) {
            resource.setImage(this.defaultImage);
        }
        return resource;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    @ResponseBody
    public Map<String, Object> update(MultipartRequest multipartRequest ,@PathVariable Long id, @RequestParam(value = "resource") String resourceString, @RequestParam(required = false) MultipartFile file, @RequestParam(required = false) MultipartFile image) throws IOException {

        Resource resource = objectMapper.readValue(resourceString, Resource.class);

        if (file != null) {
            String fileUrl = fileUploader.uploadFile(file);
            resource.setFileR("http://212.3.125.102:8080/filestorage/files?fileName=" + fileUrl);
        }
        if (image != null) {
            String imageUrl = fileUploader.uploadFile(image);
            resource.setImage("http://212.3.125.102:8080/filestorage/files?fileName=" +imageUrl);
        }

        Resource updated = this.service.update(resource);
        updated.getSubjects().size();

        resourceSearchRepository.update(updated);

        Map<String, Object> m = new HashMap<String, Object>();
        m.put("success", true);
        m.put("id", id);
        m.put("updated", updated);
        return m;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public Map<String, Object> delete(@PathVariable Long id) throws IOException {
        this.service.remove(id);
        resourceSearchRepository.delete(id);
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

    @RequestMapping(method = RequestMethod.POST, consumes = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public Map<String, Object> create(@RequestBody Resource json) throws IOException {

        Resource created = this.service.create(json);
        resourceSearchRepository.index(created);

        Map<String, Object> m = new HashMap<String, Object>();
        m.put("success", true);
        m.put("created", created);
        return m;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public Map<String, Object> update(@PathVariable Long id, @RequestBody Resource json) throws IOException {
        Resource updated = this.service.update(json);
        resourceSearchRepository.update(updated);

        Map<String, Object> m = new HashMap<String, Object>();
        m.put("success", true);
        m.put("id", id);
        m.put("updated", updated);
        return m;
    }
}
