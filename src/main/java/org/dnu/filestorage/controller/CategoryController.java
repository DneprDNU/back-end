package org.dnu.filestorage.controller;

import org.dnu.filestorage.controller.generic.GenericController;
import org.dnu.filestorage.data.model.Category;
import org.dnu.filestorage.data.service.CategoryService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author demyura
 * @since 15.10.14
 */
@Controller
@RequestMapping("/rest/category")
public class CategoryController extends GenericController<CategoryService, Category> {

}
