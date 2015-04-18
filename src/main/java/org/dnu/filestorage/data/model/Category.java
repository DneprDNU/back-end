package org.dnu.filestorage.data.model;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.Transient;
import java.util.LinkedList;
import java.util.List;

/**
 * @author demyura
 * @since 07.10.14
 */
@Entity
public class Category extends NamedEntity {
    @LazyCollection(LazyCollectionOption.EXTRA)
    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "categories")
    private List<Resource> resources = new LinkedList<Resource>();

    @ManyToMany(fetch = FetchType.LAZY)
    private List<FreeResource> freeResources = new LinkedList<FreeResource>();
    @Transient
    private int resourceCount;

    public Category() {
    }

    public Category(String name) {
        super(name);
    }

    public Category(String name, String image) {
        super(name, image);
    }

    public Category addResource(Resource resource) {
        if (!getResources().contains(resource)) {
            getResources().add(resource);
        }
        if (!resource.getCategories().contains(this)) {
            resource.getCategories().add(this);
        }
        return this;
    }

    public Category addFreeResource(FreeResource resource) {
        if (!getFreeResources().contains(resource)) {
            getFreeResources().add(resource);
        }
        if (!resource.getCategories().contains(this)) {
            resource.getCategories().add(this);
        }
        return this;
    }

    public synchronized List<Resource> getResources() {
        if (resources == null) {
            resources = new LinkedList<>();
        }
        return resources;
    }

    public void setResources(List<Resource> resources) {
        this.resources = resources;
    }

    public synchronized List<FreeResource> getFreeResources() {
        if (freeResources == null) {
            freeResources = new LinkedList<>();
        }
        return freeResources;
    }

    public void setFreeResources(List<FreeResource> freeResources) {
        this.freeResources = freeResources;
    }

    public int getResourceCount() {
        return resourceCount;
    }

    public void setResourceCount(int resourceCount) {
        this.resourceCount = resourceCount;
    }

    @Override
    public String toString() {
        return this.getName();
    }
}
