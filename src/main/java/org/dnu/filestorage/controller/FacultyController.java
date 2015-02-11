package org.dnu.filestorage.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wordnik.swagger.annotations.Api;
import org.dnu.filestorage.controller.generic.GenericController;
import org.dnu.filestorage.data.model.Department;
import org.dnu.filestorage.data.model.Faculty;
import org.dnu.filestorage.data.model.Resource;
import org.dnu.filestorage.data.service.FacultyService;
import org.dnu.filestorage.utils.FileUploader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartRequest;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author demyura
 * @since 15.10.14
 */
@Controller
@Api(value = "faculties", description = "Endpoint for faculty management")
@RequestMapping("/rest/faculty")
public class FacultyController extends GenericController<FacultyService, Faculty> {

    @Autowired
    public FacultyController(FacultyService service) {
        super(service);
    }

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private FileUploader fileUploader;

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    @ResponseBody
    public Map<String, Object> update(@PathVariable Long id, @RequestParam(value = "resource") String facultyString,  @RequestParam(required = false) MultipartFile image) throws IOException {

        Faculty faculty = objectMapper.readValue(facultyString, Faculty.class);

        if (image != null) {
            String imageUrl = fileUploader.uploadFile(image);
            faculty.setImage(imageUrl);
        }

        Faculty updated = this.getService().update(faculty);

        Map<String, Object> m = new HashMap<String, Object>();
        m.put("success", true);
        m.put("id", id);
        m.put("updated", updated);
        return m;
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> create(@RequestParam(value = "resource") String facultyString, @RequestParam(required = false) MultipartFile image) throws IOException {

        Faculty faculty = objectMapper.readValue(facultyString, Faculty.class);

        if (image != null) {
            String imageUrl = fileUploader.uploadFile(image);
            faculty.setImage(imageUrl);
        }

        Faculty created = this.getService().create(faculty);

        Map<String, Object> m = new HashMap<String, Object>();
        m.put("success", true);
        m.put("created", created);
        return m;
    }

    @Override
    public Faculty get(@PathVariable Long id) {
        Faculty result = getService().get(id);
        if (result.getDepartments() != null) {
            for (Department department : result.getDepartments()) {
                department.setFaculty(null);  //workaround for recursion
            }
        }
        return result;
    }
}
