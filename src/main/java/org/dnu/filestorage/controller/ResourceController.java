package org.dnu.filestorage.controller;

import org.dnu.filestorage.controller.generic.GenericController;
import org.dnu.filestorage.model.Resource;
import org.dnu.filestorage.service.dao.ResourceDAO;
import org.dnu.filestorage.utils.FileUploader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author demyura
 * @since 15.10.14
 */
@Controller
@RequestMapping("/rest/resource")
public class ResourceController extends GenericController<Resource> {

    @Autowired
    private FileUploader fileUploader;

    @Autowired
    public ResourceController(ResourceDAO dao) {
        super(dao);
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
