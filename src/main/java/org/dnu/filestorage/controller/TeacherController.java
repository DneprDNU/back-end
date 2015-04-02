package org.dnu.filestorage.controller;

import com.wordnik.swagger.annotations.Api;
import org.dnu.filestorage.controller.generic.GenericController;
import org.dnu.filestorage.controller.generic.GenericImageController;
import org.dnu.filestorage.data.model.Speciality;
import org.dnu.filestorage.data.model.Teacher;
import org.dnu.filestorage.data.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author demyura
 * @since 15.10.14
 */
@Controller
@Api(value = "teachers", description = "Endpoint for teacher management")
@RequestMapping("/rest/teacher")
public class TeacherController extends GenericImageController<TeacherService, Teacher> {

    @Autowired
    public TeacherController(TeacherService service) {
        super(service, Teacher.class);
    }

//    @RequestMapping(params = "facultyId")
//    @ResponseBody
//    public List<Speciality> listAllByFacultyId(@RequestParam("facultyId") Long facultyId) {
//        return getService().getSpecialitiesByFacultyId(facultyId);
//    }
}
