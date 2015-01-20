package org.dnu.filestorage.controller;

import org.dnu.filestorage.controller.generic.GenericController;
import org.dnu.filestorage.data.model.User;
import org.dnu.filestorage.data.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/rest/user")
public class UserController extends GenericController<UserService, User> {

    @Autowired
    public UserController(UserService service) {
        super(service);
    }
}
