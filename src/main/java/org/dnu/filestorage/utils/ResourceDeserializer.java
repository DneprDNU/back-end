package org.dnu.filestorage.utils;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import org.dnu.filestorage.model.Category;
import org.dnu.filestorage.model.Resource;
import org.dnu.filestorage.model.Subject;
import org.dnu.filestorage.service.dao.CategoryDAO;
import org.dnu.filestorage.service.dao.SubjectDAO;
import org.dnu.filestorage.service.dao.impl.CategoryDAOImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

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
        Resource resource = new Resource(node.get("name").asText(), node.get("year").asText(), node.get("author").asText(),
                node.get("description").asText(), "", "");
        resource.setId(node.get("id").asLong());
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