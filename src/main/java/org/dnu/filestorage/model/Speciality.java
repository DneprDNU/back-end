package org.dnu.filestorage.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import java.util.LinkedList;
import java.util.List;

/**
 * @author demyura
 * @since 07.10.14
 */
@Entity
//@NamedQueries({@NamedQuery(name = "getSpecialitiesByFacultyId", query = "select a from Speciality as a " +
//        "left join a.departments d left join d.faculty f " +
//        " where f.id=:facultyId")})
public class Speciality extends NamedEntity {
    private String code;
    @ManyToMany(fetch = FetchType.LAZY)
    private List<Teacher> supervisors = new LinkedList<Teacher>();
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Department> departments = new LinkedList<Department>();

    @ManyToMany(fetch = FetchType.LAZY)
    private List<LinkingEntity> links = new LinkedList<LinkingEntity>();

    public Speciality() {
    }

    public Speciality(String name, String code) {
        super(name);
        this.code = code;
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
}
