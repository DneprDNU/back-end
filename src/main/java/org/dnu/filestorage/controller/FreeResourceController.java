package org.dnu.filestorage.controller;

import org.dnu.filestorage.controller.generic.GenericController;
import org.dnu.filestorage.model.FreeResource;
import org.dnu.filestorage.service.dao.FreeResourceDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author demyura
 * @since 15.10.14
 */
@Controller
@RequestMapping("/rest/free_resource")
public class FreeResourceController extends GenericController<FreeResourceDAO, FreeResource> {


    @Autowired
    public FreeResourceController(FreeResourceDAO dao) {
        super(dao);
    }
}
