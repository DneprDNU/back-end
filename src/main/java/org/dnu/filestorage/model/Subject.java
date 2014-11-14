package org.dnu.filestorage.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import java.util.List;

/**
 * @author demyura
 * @since 07.10.14
 */
@Entity
public class Subject extends NamedEntity {
    @ManyToMany(fetch = FetchType.LAZY)
    private List<Resource> resources;

    @ManyToMany()
    private List<LinkingEntity> links;

    public Subject() {
    }

    public Subject(String name) {
        super(name);
    }

    public List<Resource> getResources() {
        return resources;
    }

    public void setResources(List<Resource> resources) {
        this.resources = resources;
    }

    public List<LinkingEntity> getLinks() {
        return links;
    }

    public void setLinks(List<LinkingEntity> links) {
        this.links = links;
    }
}
