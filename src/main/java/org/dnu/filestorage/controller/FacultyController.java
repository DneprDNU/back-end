package org.dnu.filestorage.controller;

import org.dnu.filestorage.controller.generic.GenericController;
import org.dnu.filestorage.model.Faculty;
import org.dnu.filestorage.service.dao.GenericDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author demyura
 * @since 15.10.14
 */
@Controller
@RequestMapping("/rest/faculty")
public class FacultyController extends GenericController<Faculty> {

    @Autowired
    public FacultyController(GenericDAO<Faculty> dao) {
        super(dao);
    }
}
