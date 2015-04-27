package org.dnu.filestorage.data.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import java.util.LinkedList;
import java.util.List;

/**
 * @author demyura
 * @since 28.09.14
 */
@Entity
@NamedQueries({@NamedQuery(name = "Department.getDepartmentWithRelations", query = "select a from Department a " +
        "left join fetch a.specialities " +
        " where a.id = :departmentId"),
        @NamedQuery(name = "Department.listByFacultyId", query = "select a from Department a " +
                "where a.faculty.id=:facultyId"),
        @NamedQuery(name = "Department.filteredCount", query = "select count(distinct a) from Department a " +
                "where a.faculty.id=:facultyId")})
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id",
        scope = Department.class)
public class Department extends NamedEntity {
    private String shortName;
    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
    private List<Speciality> specialities = new LinkedList<Speciality>();
    @ManyToMany(fetch = FetchType.LAZY)
    private List<Teacher> employees = new LinkedList<Teacher>();
    @ManyToOne(fetch = FetchType.LAZY)
    private Faculty faculty;
    private String description;
    @OneToOne()
    private Teacher director;
    private String address;
    private String phone;

    public Department() {
    }

    public Department(String name, String shortName) {
        super(name);
        this.shortName = shortName;
    }

    public Department addSpeciality(Speciality speciality) {
        if (!getSpecialities().contains(speciality)) {
            getSpecialities().add(speciality);
        }
        if (!speciality.getDepartments().contains(this)) {
            speciality.getDepartments().add(this);
        }
        return this;
    }

    public Department addEmployee(Teacher teacher) {
        if (!getEmployees().contains(teacher)) {
            getEmployees().add(teacher);
        }
        if (!teacher.getDepartments().contains(this)) {
            teacher.getDepartments().add(this);
        }
        return this;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
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

    public synchronized List<Teacher> getEmployees() {
        if (employees == null) {
            employees = new LinkedList<>();
        }
        return employees;
    }

    public void setEmployees(List<Teacher> employees) {
        this.employees = employees;
    }

    public Faculty getFaculty() {
        return faculty;
    }

    public void setFaculty(Faculty faculty) {
        this.faculty = faculty;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Teacher getDirector() {
        return director;
    }

    public void setDirector(Teacher director) {
        this.director = director;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
