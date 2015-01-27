package org.dnu.filestorage.controller;

import com.wordnik.swagger.annotations.Api;
import org.dnu.filestorage.controller.generic.GenericController;
import org.dnu.filestorage.data.model.Department;
import org.dnu.filestorage.data.model.Faculty;
import org.dnu.filestorage.data.service.FacultyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

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
