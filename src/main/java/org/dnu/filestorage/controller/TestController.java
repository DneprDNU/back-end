package org.dnu.filestorage.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Test controller for servlet mapping test
 *
 * @author DemYura
 * @since 21.09.2014
 */
@Controller

public class TestController {
    @RequestMapping("/")
    @ResponseBody
    public String testRest() {
        return "Hello";
    }
}
