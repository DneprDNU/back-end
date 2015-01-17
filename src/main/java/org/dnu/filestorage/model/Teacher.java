package org.dnu.filestorage.model;

import javax.persistence.*;
import java.util.LinkedList;
import java.util.List;

/**
 * @author demyura
 * @since 07.10.14
 */
@Entity
public class Teacher extends NamedEntity {
    @OneToMany(mappedBy = "teacher", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
    private List<LinkingEntity> links = new LinkedList<LinkingEntity>();

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "employees")
    private List<Department> departments = new LinkedList<Department>();

    @ManyToMany(fetch = FetchType.LAZY)
    private List<Speciality> specialities = new LinkedList<Speciality>();


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

    public List<Department> getDepartments() {
        return departments;
    }

    public void setDepartments(List<Department> departments) {
        this.departments = departments;
    }

    public List<Speciality> getSpecialities() {
        return specialities;
    }

    public void setSpecialities(List<Speciality> specialities) {
        this.specialities = specialities;
    }
}
