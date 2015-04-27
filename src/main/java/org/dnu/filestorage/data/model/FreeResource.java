package org.dnu.filestorage.data.model;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import java.util.LinkedList;
import java.util.List;

@Entity
//@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id",
//        scope = FreeResource.class)
public class FreeResource extends NamedEntity {
    private String description;
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

    public synchronized List<FreeResourceCategory> getCategories() {
        if (categories == null) {
            categories = new LinkedList<>();
        }
        return categories;
    }

    public void setCategories(List<FreeResourceCategory> categories) {
        this.categories = categories;
    }
}
