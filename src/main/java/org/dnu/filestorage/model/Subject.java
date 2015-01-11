package org.dnu.filestorage.model;

import javax.persistence.*;
import java.util.LinkedList;
import java.util.List;

/**
 * @author demyura
 * @since 07.10.14
 */
@Entity
@NamedQueries({@NamedQuery(name = "getSubjectsByDepartmentIdByLinks", query = "select distinct s from Subject s " +
        "left join s.links l left join l.speciality sp left join sp.departments d where d.id=:departmentId")})
public class Subject extends NamedEntity {
    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH},
            mappedBy = "subjects")
    private List<Resource> resources = new LinkedList<Resource>();

    @OneToMany(mappedBy = "subject", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
    private List<LinkingEntity> links = new LinkedList<LinkingEntity>();

    public Subject() {
    }

    public Subject(String name) {
        super(name);
    }

    public Subject addResource(Resource resource) {
        this.resources.add(resource);
        resource.getSubjects().add(this);
        return this;
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
