package org.dnu.filestorage.controller;

import org.dnu.filestorage.controller.generic.GenericController;
import org.dnu.filestorage.data.model.Subject;
import org.dnu.filestorage.data.service.SubjectService;
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
@RequestMapping("/rest/subject")
public class SubjectController extends GenericController<SubjectService, Subject> {

    @RequestMapping(params = "departmentId")
    @ResponseBody
    public List<Subject> getSubjectsByDepartmentId(@RequestParam("departmentId") Long departmentId) {
        return getService().getByDepartmentId(departmentId);
    }
}
