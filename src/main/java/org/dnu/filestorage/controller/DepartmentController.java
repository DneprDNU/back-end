package org.dnu.filestorage.controller;

import org.dnu.filestorage.controller.generic.GenericController;
import org.dnu.filestorage.model.Department;
import org.dnu.filestorage.service.dao.GenericDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author demyura
 * @since 15.10.14
 */
@Controller
@RequestMapping("/rest/department")
public class DepartmentController extends GenericController<Department> {

    @Autowired
    public DepartmentController(GenericDAO<Department> dao) {
        super(dao);
    }
}
