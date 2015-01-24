package org.dnu.filestorage.utils;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import org.dnu.filestorage.data.dao.CategoryDAO;
import org.dnu.filestorage.data.dao.SubjectDAO;
import org.dnu.filestorage.data.model.Category;
import org.dnu.filestorage.data.model.Resource;
import org.dnu.filestorage.data.model.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class ResourceDeserializer extends JsonDeserializer<Resource> {

    @Autowired
    private CategoryDAO categoryDAO;

    @Autowired
    private SubjectDAO subjectDAO;

    public void setCategoryDAO(CategoryDAO categoryDAO) {
        this.categoryDAO = categoryDAO;
    }

    @Override
    public Resource deserialize(JsonParser jsonParser,
                                DeserializationContext deserializationContext) throws IOException {
        ObjectCodec oc = jsonParser.getCodec();
        JsonNode node = oc.readTree(jsonParser);

        String name = "";
        String year = "";
        String author = "";
        String description = "";

        if (node.get("name") != null) {
            name = node.get("name").asText();
        }
        if (node.get("year") != null) {
            year = node.get("year").asText();
        }
        if (node.get("author") != null) {
            author = node.get("author").asText();
        }
        if (node.get("description") != null) {
            description = node.get("description").asText();
        }


        Resource resource = new Resource(name, year, author,description, "", "");
        if (node.get("id") != null) {
            resource.setId(node.get("id").asLong());
        }
        List<Category> categoryList = new ArrayList<Category>();
        for (JsonNode jsonNode : node.get("categories")) {
            if (jsonNode.isInt()) {
                Category category = categoryDAO.get(Long.valueOf(jsonNode.asInt()));
                categoryList.add(category);
            }
        }
        resource.setCategories(categoryList);

        List<Subject> subjectList = new ArrayList<Subject>();
        for (JsonNode jsonNode : node.get("subjects")) {
            if (jsonNode.isInt()) {
                Subject subject = subjectDAO.get(Long.valueOf(jsonNode.asInt()));
                subjectList.add(subject);
            }
        }
        resource.setSubjects(subjectList);
        return resource;
    }
}