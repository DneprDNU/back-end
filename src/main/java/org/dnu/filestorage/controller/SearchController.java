package org.dnu.filestorage.controller;

import com.wordnik.swagger.annotations.Api;
import org.dnu.filestorage.data.model.Category;
import org.dnu.filestorage.data.model.Resource;
import org.dnu.filestorage.search.ResourceSearchRepository;
import org.elasticsearch.search.SearchHit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


/**
 * Controller for searching through resources.
 */
@Controller
@Api(value = "search", description = "Endpoint for search")
public class SearchController {

    String defaultImage = "http://cumbrianrun.co.uk/wp-content/uploads/2014/02/default-placeholder.png";

    @Autowired
    ResourceSearchRepository resourceSearchRepository;

    /**
     * Search resources by user searchKey.
     *
     * @param searchKey User searchKey to search resources by.
     * @return Resources as a result of user search searchKey.
     * @throws IOException Input-Output Exception.
     */
    @RequestMapping("/search")
    @ResponseBody
    public Resource[] search(@RequestParam(required = false) String searchKey) throws IOException {

        // We need to handle Ukrainian symbols.
//        if (searchKey != null) {
//            searchKey = new String((searchKey).getBytes("ISO-8859-1"), "UTF-8");
//        }

        SearchHit[] searchHits = resourceSearchRepository.search(searchKey);
        Resource[] resources = new Resource[searchHits.length];

        for (int i = 0; i < searchHits.length; i++) {
            String resourceName = (String) searchHits[i].getSource().get("resourceName");
            String author = (String) searchHits[i].getSource().get("author");
            String description = (String) searchHits[i].getSource().get("description");
            String year = (String) searchHits[i].getSource().get("year");
            List<String> categoriesString = (List<String>) searchHits[i].getSource().get("categories");
            List<Category> categories = new ArrayList<Category>();
            for (String category : categoriesString) {
                Category cat = new Category();
                cat.setName(category);
                categories.add(cat);
            }

            Resource resource = new Resource(resourceName, year, author, description, "", "");
            resource.setId(Long.parseLong(searchHits[i].id()));
            resource.setCategories(categories);
            String image = (String)searchHits[i].getSource().get("image");
            if (!resource.getImage().equals(defaultImage) && !image.isEmpty()) {
                resource.setImage(image);
            }
            else{
                resource.setImage(this.defaultImage);
            }

            resources[i] = resource;
        }
        return resources;
    }
}