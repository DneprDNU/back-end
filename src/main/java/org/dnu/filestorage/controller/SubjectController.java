package org.dnu.filestorage.controller;

import org.dnu.filestorage.controller.generic.GenericController;
import org.dnu.filestorage.model.Subject;
import org.dnu.filestorage.service.dao.SubjectDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author demyura
 * @since 15.10.14
 */
@Controller
@RequestMapping("/rest/subject")
public class SubjectController extends GenericController<SubjectDAO, Subject> {

    @Autowired
    public SubjectController(SubjectDAO dao) {
        super(dao);
    }
}
