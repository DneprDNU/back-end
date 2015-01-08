package org.dnu.filestorage.model;

import javax.persistence.*;
import java.util.LinkedList;
import java.util.List;

/**
 * @author demyura
 * @since 07.10.14
 */
@Entity
public class Subject extends NamedEntity {
    @ManyToMany(fetch = FetchType.LAZY)
    private List<Resource> resources = new LinkedList<Resource>();

    @OneToMany(mappedBy = "subject", cascade = CascadeType.ALL)
    private List<LinkingEntity> links = new LinkedList<LinkingEntity>();

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
