package org.dnu.filestorage.model;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import java.util.List;

/**
 * @author demyura
 * @since 07.10.14
 */
@Entity
public class Teacher extends NamedEntity {
    @ManyToMany()
    private List<LinkingEntity> links;

    public Teacher() {
    }

    public Teacher(String name) {
        super(name);
    }

    public List<LinkingEntity> getLinks() {
        return links;
    }

    public void setLinks(List<LinkingEntity> links) {
        this.links = links;
    }
}
