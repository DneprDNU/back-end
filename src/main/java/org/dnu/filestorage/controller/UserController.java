package org.dnu.filestorage.controller;

import org.dnu.filestorage.controller.generic.GenericController;
import org.dnu.filestorage.model.User;
import org.dnu.filestorage.service.dao.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/rest/user")
public class UserController extends GenericController<UserDAO, User> {

    @Autowired
    public UserController(UserDAO dao) {
        super(dao);
    }
}
