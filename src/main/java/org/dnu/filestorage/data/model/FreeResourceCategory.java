package org.dnu.filestorage.data.model;

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
//@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id",
//        scope = Category.class)
public class FreeResourceCategory extends NamedEntity {
    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "categories")
//    @JsonManagedReference
    private List<FreeResource> freeResources = new LinkedList<FreeResource>();

    public FreeResourceCategory() {
    }

    public FreeResourceCategory(String name) {
        super(name);
    }

    public FreeResourceCategory(String name, String image) {
        super(name, image);
    }

    public FreeResourceCategory addFreeResource(FreeResource resource) {
        if (!getFreeResources().contains(resource)) {
            getFreeResources().add(resource);
        }
        return this;
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

    @Override
    public String toString() {
        return this.getName();
    }
}
