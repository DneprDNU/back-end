package org.dnu.filestorage.controller;

import org.dnu.filestorage.controller.generic.GenericController;
import org.dnu.filestorage.model.Category;
import org.dnu.filestorage.service.dao.CategoryDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author demyura
 * @since 15.10.14
 */
@Controller
@RequestMapping("/rest/category")
public class CategoryController extends GenericController<Category> {

    @Autowired
    public CategoryController(CategoryDAO dao) {
        super(dao);
    }
}
