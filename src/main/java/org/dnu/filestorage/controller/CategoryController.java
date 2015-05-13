package org.dnu.filestorage.controller;

import com.wordnik.swagger.annotations.Api;
import org.dnu.filestorage.controller.generic.GenericImageController;
import org.dnu.filestorage.data.model.Category;
import org.dnu.filestorage.data.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author demyura
 * @since 15.10.14
 */
@Controller
@Api(value = "categories", description = "Endpoint for category management")
@RequestMapping("/rest/category")
public class CategoryController extends GenericImageController<CategoryService, Category> {

    @Autowired
    public CategoryController(CategoryService service) {
        super(service, Category.class);
    }
}
