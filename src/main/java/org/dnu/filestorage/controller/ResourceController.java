package org.dnu.filestorage.controller;

import com.google.common.base.Throwables;
import org.apache.commons.beanutils.BeanUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.dnu.filestorage.model.Resource;
import org.dnu.filestorage.service.dao.ResourceDAO;
import org.dnu.filestorage.utils.FileUploader;
import org.dnu.filestorage.utils.HibernateAwareObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
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
    private FileUploader fileUploader;

    private ObjectMapper mapper = new HibernateAwareObjectMapper();

    @Autowired
    private ResourceDAO dao;

    @RequestMapping
    @ResponseBody
    public List<Resource> listAll() {
        return this.dao.list();
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> create(HttpServletRequest request,
                                      @RequestParam(value = "json") String sresource, @RequestParam(value = "file") MultipartFile file) throws IOException {

        Resource resource = mapper.readValue(sresource, Resource.class);

        String fileUrl = fileUploader.uploadFile(request, file);
        resource.setResourceURL(fileUrl);
        Resource created = dao.saveOfUpdate(resource);

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

    @RequestMapping(value = "/{id}", method = RequestMethod.POST, consumes = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public Map<String, Object> update(@PathVariable Long id, @RequestBody Resource json) {

        Resource entity = this.dao.get(id);
        try {
            BeanUtils.copyProperties(entity, json);
        } catch (Exception e) {
            throw Throwables.propagate(e);
        }

        Resource updated = this.dao.saveOfUpdate(entity);

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
}
