package org.dnu.filestorage.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wordnik.swagger.annotations.Api;
import org.dnu.filestorage.controller.generic.GenericController;
import org.dnu.filestorage.controller.generic.GenericImageController;
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
public class FacultyController extends GenericImageController<FacultyService, Faculty> {

    @Autowired
    public FacultyController(FacultyService service) {
        super(service, Faculty.class);
    }


    @Override
    public Faculty get(@PathVariable Long id) {
        Faculty result = getService().get(id);
        if (result.getDepartments() != null) {
            for (Department department : result.getDepartments()) {
                department.setFaculty(null);  //workaround for recursion
            }
        }
        return processImage(result);
    }
}
