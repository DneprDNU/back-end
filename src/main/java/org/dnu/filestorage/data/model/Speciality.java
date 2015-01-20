package org.dnu.filestorage.data.model;

import javax.persistence.*;
import java.util.LinkedList;
import java.util.List;

/**
 * @author demyura
 * @since 07.10.14
 */
@Entity
@NamedQueries({@NamedQuery(name = "getSpecialitiesByFacultyId", query = "select a from Speciality as a " +
        "left join a.departments d left join d.faculty f " +
        " where f.id=:facultyId")})
public class Speciality extends NamedEntity {
    private String code;
    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
    private List<Teacher> supervisors = new LinkedList<Teacher>();
    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "specialities")
    private List<Department> departments = new LinkedList<Department>();

    @OneToMany(mappedBy = "speciality", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
    private List<LinkingEntity> links = new LinkedList<LinkingEntity>();

    public Speciality() {
    }

    public Speciality(String name, String code) {
        super(name);
        this.code = code;
    }

    public Speciality addSupervisor(Teacher teacher) {
        if (!supervisors.contains(teacher)) {
            this.supervisors.add(teacher);
        }
        if (!teacher.getSpecialities().contains(this)) {
            teacher.getSpecialities().add(this);
        }
        return this;
    }

    public Speciality addDepartment(Department department) {
        if (!departments.contains(department)) {
            this.departments.add(department);
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

    public List<Teacher> getSupervisors() {
        return supervisors;
    }

    public void setSupervisors(List<Teacher> supervisors) {
        this.supervisors = supervisors;
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

    @Override
    public String toString() {
        return this.getName();
    }
}