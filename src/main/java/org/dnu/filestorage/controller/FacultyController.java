package org.dnu.filestorage.controller;

import org.dnu.filestorage.controller.generic.GenericController;
import org.dnu.filestorage.model.Department;
import org.dnu.filestorage.model.Faculty;
import org.dnu.filestorage.service.dao.FacultyDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author demyura
 * @since 15.10.14
 */
@Controller
@RequestMapping("/rest/faculty")
public class FacultyController extends GenericController<FacultyDAO, Faculty> {

    @Autowired
    public FacultyController(FacultyDAO dao) {
        super(dao);
    }

    @Override
    public Faculty get(@PathVariable Long id) {
        Faculty result = getDao().getFacultyWithRelations(id);
        if (result.getDepartments() != null) {
            for (Department department : result.getDepartments()) {
                department.setFaculty(null);  //workaround for recursion
            }
        }
        return result;
    }
}
