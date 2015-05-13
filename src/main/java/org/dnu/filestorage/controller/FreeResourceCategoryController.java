package org.dnu.filestorage.controller;

import com.wordnik.swagger.annotations.Api;
import org.dnu.filestorage.controller.generic.GenericImageController;
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
@Api(value = "freeResourceCategoryController", description = "Endpoint for free resource category management")
@RequestMapping("/rest/freeCategory")
public class FreeResourceCategoryController
        extends GenericImageController<FreeResourceCategoryService, FreeResourceCategory> {

    @Autowired
    public FreeResourceCategoryController(FreeResourceCategoryService service) {
        super(service, FreeResourceCategory.class);
    }
}
