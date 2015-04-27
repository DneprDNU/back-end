package org.dnu.filestorage.data.model;

import javax.persistence.*;
import java.util.LinkedList;
import java.util.List;

/**
 * @author demyura
 * @since 07.10.14
 */
@Entity
@NamedQueries({@NamedQuery(name = "Speciality.getSpecialitiesByFacultyId", query = "select a from Speciality as a " +
        "left join a.departments d left join d.faculty f " +
        " where f.id=:facultyId"),
        @NamedQuery(name = "Speciality.filteredCount", query = "select count(distinct s) from Speciality s " +
                "left join s.departments d left join d.faculty f " +
                "where f.id = :facultyId")})
//@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id",
//        scope = Speciality.class)
public class Speciality extends NamedEntity {
    private String code;
    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
    private List<Teacher> supervisors = new LinkedList<Teacher>();
    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "specialities")
    private List<Department> departments = new LinkedList<Department>();

    @OneToMany(cascade = {CascadeType.ALL}, orphanRemoval = true, mappedBy = "speciality")
    private List<LinkingEntity> links = new LinkedList<LinkingEntity>();

    private String description;

    public Speciality() {
    }

    public Speciality(String name, String code) {
        super(name);
        this.code = code;
    }

    public Speciality addSupervisor(Teacher teacher) {
        if (!getSupervisors().contains(teacher)) {
            getSupervisors().add(teacher);
        }
        if (!teacher.getSpecialities().contains(this)) {
            teacher.getSpecialities().add(this);
        }
        return this;
    }

    public Speciality addDepartment(Department department) {
        if (!getDepartments().contains(department)) {
            getDepartments().add(department);
        }
        if (!department.getSpecialities().contains(this)) {
            department.getSpecialities().add(this);
        }
        return this;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public synchronized List<Teacher> getSupervisors() {
        if (supervisors == null) {
            supervisors = new LinkedList<>();
        }
        return supervisors;
    }

    public void setSupervisors(List<Teacher> supervisors) {
        this.supervisors = supervisors;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return this.getName();
    }
}
