package org.dnu.filestorage.controller;

import org.dnu.filestorage.controller.generic.GenericController;
import org.dnu.filestorage.data.model.Teacher;
import org.dnu.filestorage.data.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author demyura
 * @since 15.10.14
 */
@Controller
@RequestMapping("/rest/teacher")
public class TeacherController extends GenericController<TeacherService, Teacher> {

    @Autowired
    public TeacherController(TeacherService service) {
        super(service);
    }
}
