package org.dnu.filestorage.data.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import java.util.LinkedList;
import java.util.List;

@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id",
        scope = FreeResource.class)
public class FreeResource extends NamedEntity {
    private String description;

       private String resource;

    @ManyToMany
    private List<Category> categories = new LinkedList<Category>();

    public FreeResource() {

    }

    public FreeResource(String name, String description, String resourceURL, String imageURL) {
        super(name, imageURL);
        this.description = description;
        this.resource = resourceURL;
    }

    public FreeResource addCategory(Category category) {
        if (!getCategories().contains(category)) {
            getCategories().add(category);
        }
        if (!category.getFreeResources().contains(this)) {
            category.getFreeResources().add(this);
        }
        return this;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getResource() {
        return resource;
    }

    public void setResource(String resource) {
        this.resource = resource;
    }

    public synchronized List<Category> getCategories() {
        if (categories == null) {
            categories = new LinkedList<>();
        }
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }
}
