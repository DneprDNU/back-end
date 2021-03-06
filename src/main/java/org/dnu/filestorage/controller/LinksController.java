package org.dnu.filestorage.controller;

import com.wordnik.swagger.annotations.Api;
import org.dnu.filestorage.controller.generic.GenericController;
import org.dnu.filestorage.data.model.LinkingEntity;
import org.dnu.filestorage.data.service.LinkingEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author demyura
 * @since 25.01.15
 */
@Controller
@Api(value = "links", description = "Endpoint for links management")
@RequestMapping("/rest/links")
public class LinksController extends GenericController<LinkingEntityService, LinkingEntity> {
    @Autowired
    public LinksController(LinkingEntityService service) {
        super(service);
    }
}
