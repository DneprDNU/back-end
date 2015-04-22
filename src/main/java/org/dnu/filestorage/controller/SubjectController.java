package org.dnu.filestorage.controller;

import com.wordnik.swagger.annotations.Api;
import org.dnu.filestorage.controller.generic.GenericImageController;
import org.dnu.filestorage.data.model.Subject;
import org.dnu.filestorage.data.service.SubjectService;
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
@Api(value = "subjects", description = "Endpoint for subject management")
@RequestMapping("/rest/subject")
public class SubjectController extends GenericImageController<SubjectService, Subject> {

    @Autowired
    public SubjectController(SubjectService service) {
        super(service, Subject.class);
    }

    @RequestMapping(params = "departmentId")
    @ResponseBody
    public List<Subject> getSubjectsByDepartmentId(@RequestParam("departmentId") Long departmentId) {
        return getService().getByDepartmentId(departmentId);
    }

    @RequestMapping(params = "specialityId")
    @ResponseBody
    public List<Subject> getSubjectsBySpecialityId(@RequestParam("specialityId") Long specialityId) {
        return getService().listBySpecialityId(specialityId);
    }
}
