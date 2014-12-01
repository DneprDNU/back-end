package org.dnu.filestorage.controller;

import org.dnu.filestorage.controller.generic.GenericController;
import org.dnu.filestorage.model.Resource;
import org.dnu.filestorage.service.dao.ResourceDAO;
import org.dnu.filestorage.utils.FileUploader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @author demyura
 * @since 15.10.14
 */
@Controller
@RequestMapping("/rest/resource")
public class ResourceController extends GenericController<ResourceDAO, Resource> {

    @Autowired
    private FileUploader fileUploader;

    @Autowired
    public ResourceController(ResourceDAO dao) {
        super(dao);
    }

    @RequestMapping(method = RequestMethod.POST, consumes = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public Map<String, Object> create(HttpServletRequest request,
                                      @RequestBody Resource resource, @RequestParam(value = "file") MultipartFile file) {

        String fileUrl = fileUploader.uploadFile(request, file);
        resource.setResourceURL(fileUrl);
        Resource created = getDao().saveOfUpdate(resource);

        Map<String, Object> m = new HashMap<String, Object>();
        m.put("success", true);
        m.put("created", created);
        return m;
    }


    /*@RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> create(HttpServletRequest request, @RequestParam(value = "file") MultipartFile file) throws IOException {
        String fileName = fileUploader.uploadFile(request, file);
        Map<String, Object> m = new HashMap<String, Object>();
        m.put("filename", fileName);
        m.put("created", true);
        return m;
    }*/
}
