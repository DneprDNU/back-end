package org.dnu.filestorage.data.model;

import javax.persistence.*;
import java.util.LinkedList;
import java.util.List;

/**
 * @author demyura
 * @since 07.10.14
 */
@Entity
@NamedQueries({@NamedQuery(name = "Subject.getSubjectsByDepartmentIdByLinks", query = "select distinct s from Subject s " +
        "left join s.links l left join l.speciality sp left join sp.departments d where d.id=:departmentId order by s.name asc"),
        @NamedQuery(name = "Subject.listSubjectsByFacultyId", query = "select distinct s from Subject s " +
                "left join s.links l left join l.speciality sp left join sp.departments d left join d.faculty f " +
                "where f.id=:facultyId order by s.name asc"),
        @NamedQuery(name = "Subject.getSubjectsBySpecialityIdByLinks", query = "select distinct s from Subject s " +
                "left join s.links l left join l.speciality sp where sp.id=:specialityId order by s.name asc"),
        @NamedQuery(name = "Subject.filteredCount", query = "select count(distinct s) from Subject s " +
                "left join s.links l left join l.speciality sp left join sp.departments d " +
                "left join d.faculty f where f.id = :facultyId")
})
//@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id",
//        scope = Subject.class)
public class Subject extends NamedEntity {
    @ManyToMany(fetch = FetchType.LAZY)
//    @JsonManagedReference
    private List<Resource> resources = new LinkedList<Resource>();

    @OneToMany(fetch = FetchType.LAZY,
            cascade = {CascadeType.ALL}, orphanRemoval = true, mappedBy = "subject")
//    @JsonManagedReference
    private List<LinkingEntity> links = new LinkedList<LinkingEntity>();

    public Subject() {
    }

    public Subject(String name) {
        super(name);
    }

    public Subject addResource(Resource resource) {
        if (!getResources().contains(resource)) {
            getResources().add(resource);
        }
        if (!resource.getSubjects().contains(this)) {
            resource.getSubjects().add(this);
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

    public synchronized List<LinkingEntity> getLinks() {
        if (links == null) {
            links = new LinkedList<>();
        }
        return links;
    }

    public void setLinks(List<LinkingEntity> links) {
        this.links = links;
    }

    @Override
    public String toString() {
        return this.getName();
    }
}
