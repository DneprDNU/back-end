package org.dnu.filestorage.controller.generic;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.dnu.filestorage.data.model.Faculty;
import org.dnu.filestorage.data.model.Identifiable;
import org.dnu.filestorage.data.model.NamedEntity;
import org.dnu.filestorage.data.service.GenericService;
import org.dnu.filestorage.utils.FileUploader;
import org.dnu.filestorage.utils.HibernateAwareObjectMapper;
import org.dnu.filestorage.utils.impl.HdfsFileUploader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author demyura
 * @since 14.11.14
 */
public class GenericImageController<S extends GenericService<T>, T extends Identifiable> {
    private Logger logger = LoggerFactory.getLogger(GenericImageController.class);

    private S service;

    private Class<T> type;

    String defaultImage = "http://dnu.thebodva.com/upload/b32f3d1ef28edf602362b91cb935886f.jpg";

    private ObjectMapper objectMapper = new HibernateAwareObjectMapper();

    private FileUploader fileUploader = new HdfsFileUploader();

    public GenericImageController(S service, Class<T> type) {

        this.service = service;
        this.type = type;
    }

    protected S getService() {
        return service;
    }

    @RequestMapping
    @ResponseBody
    public List<T> listAll() {
        return this.service.list();
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> create(@RequestParam(value = "resource") String string, @RequestParam(required = false) MultipartFile image) throws IOException {

        T object = objectMapper.readValue(string, type);

        if (image != null) {
            String imageUrl = fileUploader.uploadFile(image);
            ((NamedEntity)object).setImage(imageUrl);
        }

        T created = this.getService().create(object);

        Map<String, Object> m = new HashMap<String, Object>();
        m.put("success", true);
        m.put("created", created);
        return m;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public T get(@PathVariable Long id) {

        NamedEntity resource = (NamedEntity) this.service.get(id);

        if (!resource.getImage().isEmpty() && !resource.getImage().equals(defaultImage)) {
            resource.setImage("http://80.240.139.45:8080/filestorage/files?fileName=" + resource.getImage());
        }
        return (T) resource;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    @ResponseBody
    public Map<String, Object> update(@PathVariable Long id, @RequestParam(value = "resource") String string,  @RequestParam(required = false) MultipartFile image) throws IOException {

       T object = objectMapper.readValue(string, type);

        if (image != null) {
            String imageUrl = fileUploader.uploadFile(image);
            ((NamedEntity)object).setImage(imageUrl);
        }

       T updated = this.getService().update(object);

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

}
