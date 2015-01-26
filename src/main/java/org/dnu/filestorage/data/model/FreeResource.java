package org.dnu.filestorage.data.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import java.util.LinkedList;
import java.util.List;

@Entity
public class FreeResource extends NamedEntity {
    private String description;

    @Column(length = 10000)
    private String resource;

    @ManyToMany
    private List<FreeResourceCategory> categories = new LinkedList<FreeResourceCategory>();

    public FreeResource() {

    }

    public FreeResource(String name, String description, String resourceURL, String imageURL) {
        super(name, imageURL);
        this.description = description;
        this.resource = resourceURL;
    }

    public FreeResource addCategory(FreeResourceCategory category) {
        if (!categories.contains(category)) {
            this.categories.add(category);
        }
        if (!category.getResources().contains(this)) {
            category.getResources().add(this);
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

    public List<FreeResourceCategory> getCategories() {
        return categories;
    }

    public void setCategories(List<FreeResourceCategory> categories) {
        this.categories = categories;
    }
}
