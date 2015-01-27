package org.dnu.filestorage.controller;

import com.wordnik.swagger.annotations.Api;
import org.dnu.filestorage.controller.generic.GenericController;
import org.dnu.filestorage.data.model.FreeResource;
import org.dnu.filestorage.data.service.FreeResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author demyura
 * @since 15.10.14
 */
@Controller
@Api(value = "freeResources", description = "Endpoint for free resource management")
@RequestMapping("/rest/free_resource")
public class FreeResourceController extends GenericController<FreeResourceService, FreeResource> {

    @Autowired
    public FreeResourceController(FreeResourceService service) {
        super(service);
    }
}