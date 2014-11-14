package org.dnu.filestorage.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import java.util.List;

/**
 * @author demyura
 * @since 28.09.14
 */
@Entity
public class Department extends NamedEntity {
    private String shortName;
    @ManyToMany(fetch = FetchType.LAZY)
    private List<Speciality> specialities;
    @ManyToMany(fetch = FetchType.LAZY)
    private List<Teacher> employees;

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
