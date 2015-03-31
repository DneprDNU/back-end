package org.dnu.filestorage.data.model;

import javax.persistence.*;
import java.util.LinkedList;
import java.util.List;

/**
 * @author demyura
 * @since 07.10.14
 */
@Entity
@NamedQueries({@NamedQuery(name = "listTeachersByFacultyId", query = "select distinct t from Teacher t " +
        "left join t.departments d left join d.faculty f where f.id=:facultyId")})
public class Teacher extends NamedEntity {
    @OneToMany(mappedBy = "teacher", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
    private List<LinkingEntity> links = new LinkedList<LinkingEntity>();

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "employees")
    private List<Department> departments = new LinkedList<Department>();

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "supervisors")
    private List<Speciality> specialities = new LinkedList<Speciality>();


    public Teacher() {
    }

    public Teacher(String name) {
        super(name);
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

    public synchronized List<Department> getDepartments() {
        if (departments == null) {
            departments = new LinkedList<>();
        }
        return departments;
    }

    public void setDepartments(List<Department> departments) {
        this.departments = departments;
    }

    public synchronized List<Speciality> getSpecialities() {
        if (specialities == null) {
            specialities = new LinkedList<>();
        }
        return specialities;
    }

    public void setSpecialities(List<Speciality> specialities) {
        this.specialities = specialities;
    }
}
