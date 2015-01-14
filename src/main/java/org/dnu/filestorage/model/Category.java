package org.dnu.filestorage.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import java.util.LinkedList;
import java.util.List;

/**
 * @author demyura
 * @since 07.10.14
 */
@Entity
public class Category extends NamedEntity {

    @ManyToMany(fetch = FetchType.LAZY)
    private List<Resource> resources = new LinkedList<Resource>();

    public Category addResource(Resource resource) {
        resources.add(resource);
        resource.getCategories().add(this);
        return this;
    }

    public List<Resource> getResources() {
        return resources;
    }

    public void setResources(List<Resource> resources) {
        this.resources = resources;
    }

    @Override
    public String toString() {
        return this.getName();
    }
}
