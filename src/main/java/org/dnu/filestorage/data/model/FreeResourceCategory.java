package org.dnu.filestorage.data.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import java.util.LinkedList;
import java.util.List;

/**
 * @author demyura
 * @since 26.01.15
 */
@Entity
@DiscriminatorValue("free")
public class FreeResourceCategory extends AbstractCategory {
    @ManyToMany(fetch = FetchType.LAZY)
    private List<FreeResource> resources = new LinkedList<FreeResource>();

    public FreeResourceCategory(String name) {
        super(name);
    }

    public FreeResourceCategory(String name, String image) {
        super(name, image);
    }

    public FreeResourceCategory() {
    }

    public List<FreeResource> getResources() {
        return resources;
    }

    public void setResources(List<FreeResource> resources) {
        this.resources = resources;
    }
}
