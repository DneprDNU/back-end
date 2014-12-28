package org.dnu.filestorage.controller;

import org.dnu.filestorage.controller.generic.GenericController;
import org.dnu.filestorage.model.Department;
import org.dnu.filestorage.service.dao.DepartmentDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author demyura
 * @since 15.10.14
 */
@Controller
@RequestMapping("/rest/department")
public class DepartmentController extends GenericController<DepartmentDAO, Department> {

    @Autowired
    public DepartmentController(DepartmentDAO dao) {
        super(dao);
    }

    @Override
    public Department get(@PathVariable Long id) {
        Department result = getDao().getDepartmentWithRelations(id);
        result.getEmployees().size(); // workaround for JPA problem with multiple join fetch in named query
        return result;
    }
}
