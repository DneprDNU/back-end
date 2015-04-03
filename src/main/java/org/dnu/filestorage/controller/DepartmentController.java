package org.dnu.filestorage.controller;

import com.wordnik.swagger.annotations.Api;
import org.dnu.filestorage.controller.generic.GenericImageController;
import org.dnu.filestorage.data.model.Department;
import org.dnu.filestorage.data.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author demyura
 * @since 15.10.14
 */
@Controller
@Api(value = "departments", description = "Endpoint for department management")
@RequestMapping("/rest/department")
public class DepartmentController extends GenericImageController<DepartmentService, Department> {

    @Autowired
    public DepartmentController(DepartmentService service) {
        super(service, Department.class);
    }

}
