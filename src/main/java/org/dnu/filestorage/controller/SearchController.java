package org.dnu.filestorage.controller;

import org.dnu.filestorage.model.Resource;
import org.dnu.filestorage.search.ResourceSearchRepository;
import org.elasticsearch.search.SearchHit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@Controller
public class SearchController {

    @Autowired
    ResourceSearchRepository resourceSearchRepository;

    @RequestMapping("/search")
    @ResponseBody
    public Resource[] search(@RequestParam(required = false) String query,
                             HttpServletResponse response) throws IOException {


        SearchHit[] searchHits = resourceSearchRepository.search(query);
        Resource[] resources = new Resource[searchHits.length];

        for (int i = 0; i < searchHits.length; i++) {
            String resourceName = (String) searchHits[i].getSource().get("resourceName");
            String author = (String) searchHits[i].getSource().get("author");
            String description = (String) searchHits[i].getSource().get("description");

            resources[i] = new Resource(resourceName, "2006", author, description, "", "");
        }
        return resources;
    }
}
