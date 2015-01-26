package org.dnu.filestorage.controller;

import org.dnu.filestorage.controller.generic.GenericController;
import org.dnu.filestorage.data.model.FreeResourceCategory;
import org.dnu.filestorage.data.service.FreeResourceCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author demyura
 * @since 15.10.14
 */
@Controller
@RequestMapping(value = "/rest/freeCategory")
public class FreeResourceCategoryController extends GenericController<FreeResourceCategoryService, FreeResourceCategory> {

    @Autowired
    public FreeResourceCategoryController(FreeResourceCategoryService service) {
        super(service);
    }
}
