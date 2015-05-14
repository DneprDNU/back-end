package org.dnu.filestorage.data.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.LinkedList;
import java.util.List;

/**
 * @author demyura
 * @since 07.10.14
 */
@Entity
@NamedQueries({@NamedQuery(name = "Teacher.listTeachersByFacultyId", query = "select distinct t from Teacher t " +
        "left join t.departments d left join d.faculty f where f.id=:facultyId order by t.name asc"),
        @NamedQuery(name = "Teacher.listTeachersBySubjectId", query = "select distinct t from Teacher t " +
                "left join t.links l left join l.subject sp where sp.id=:subjectId order by t.name asc"),
        @NamedQuery(name = "Teacher.listTeachersBySpecialityIdByLinks", query = "select distinct t from Teacher t " +
                "left join t.links l left join l.speciality sp where sp.id=:specialityId order by t.name asc"),
        @NamedQuery(name = "Teacher.filteredCount", query = "select count(distinct t) from Teacher t " +
                "left join t.departments d left join d.faculty f where f.id=:facultyId")})
//@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id",
//        scope = Teacher.class)
public class Teacher extends NamedEntity {
    @OneToMany(fetch = FetchType.LAZY,
            cascade = {CascadeType.ALL}, orphanRemoval = true, mappedBy = "teacher")
    @JsonManagedReference
    private List<LinkingEntity> links = new LinkedList<LinkingEntity>();

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "employees")
    @JsonBackReference
    private List<Department> departments = new LinkedList<Department>();

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "supervisors")
    @JsonBackReference
    private List<Speciality> specialities = new LinkedList<Speciality>();
    private String email;
    private String phone;
    private String skype;


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
            departments = new LinkedList<Department>();
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getSkype() {
        return skype;
    }

    public void setSkype(String skype) {
        this.skype = skype;
    }

    @Override
    public String toString() {
        return this.getName();
    }

    @PreRemove
    public void preRemove() {
        for (Department department : departments) {
            department.getEmployees().remove(this);
        }
        for (Speciality speciality : specialities) {
            speciality.getSupervisors().remove(this);
        }
    }
}
