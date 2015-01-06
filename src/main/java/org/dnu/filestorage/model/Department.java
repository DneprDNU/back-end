package org.dnu.filestorage.model;

import javax.persistence.*;
import java.util.LinkedList;
import java.util.List;

/**
 * @author demyura
 * @since 28.09.14
 */
@Entity
@NamedQueries({@NamedQuery(name = "getDepartmentWithRelations", query = "select a from Department a " +
        "left join fetch a.specialities " +
        " where a.id = :departmentId")})
public class Department extends NamedEntity {
    private String shortName;
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//    @IndexColumn(name = "INDEX_COL")   //workaround for multiple fetch joins
    private List<Speciality> specialities = new LinkedList<Speciality>();
    @ManyToMany(fetch = FetchType.LAZY)
    private List<Teacher> employees = new LinkedList<Teacher>();
//    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//    @Transient
//    private Faculty faculty;

    public Department() {
    }

    public Department(String name, String shortName) {
        super(name);
        this.shortName = shortName;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public List<Speciality> getSpecialities() {
        return specialities;
    }

    public void setSpecialities(List<Speciality> specialities) {
        this.specialities = specialities;
    }

    public List<Teacher> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Teacher> employees) {
        this.employees = employees;
    }

}
