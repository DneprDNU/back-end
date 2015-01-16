package org.dnu.filestorage.utils;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import org.dnu.filestorage.model.Category;
import org.dnu.filestorage.model.Resource;
import org.dnu.filestorage.service.dao.CategoryDAO;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ResourceDeserializer extends JsonDeserializer<Resource> {

    @Autowired
    private CategoryDAO categoryDAO;

    @Override
    public Resource deserialize(JsonParser jsonParser,
                                DeserializationContext deserializationContext) throws IOException {
        ObjectCodec oc = jsonParser.getCodec();
        JsonNode node = oc.readTree(jsonParser);
        Resource resource = new Resource(node.get("name").asText(), node.get("year").asText(), node.get("author").asText(),
                node.get("description").asText(), "", "");

        List<Category> categoryList = new ArrayList<Category>();
        for (JsonNode jsonNode : node.get("categories")) {
            if (jsonNode.isLong()) {
                Category category = categoryDAO.get(jsonNode.asLong());
                categoryList.add(category);
            }
        }
        resource.setCategories(categoryList);
        return resource;
    }
}