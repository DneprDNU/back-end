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
@NamedQueries({@NamedQuery(name = "getFacultyWithRelations", query = "select f from Faculty f " +
        "left join fetch f.departments where f.id = :facultyId")})
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id",
        scope = Faculty.class)
public class Faculty extends NamedEntity {
    private String shortName;
    private String description;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL,
            mappedBy = "faculty")
    private List<Department> departments = new LinkedList<Department>();

    public Faculty() {
    }

    public Faculty(String name, String shortName, String description) {
        super(name);
        this.shortName = shortName;
        this.description = description;
    }

    public Faculty(String name, String shortName, String description, String image) {
        super(name, image);
        this.shortName = shortName;
        this.description = description;
    }

    public Faculty addDepartment(Department department) {
        if (!getDepartments().contains(department)) {
            getDepartments().add(department);
        }
        department.setFaculty(this);
        return this;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }
}
