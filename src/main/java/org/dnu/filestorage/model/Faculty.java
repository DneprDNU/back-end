package org.dnu.filestorage.model;

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
public class Faculty extends NamedEntity {
    private String shortName;
    private String description;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "faculty")
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Department> getDepartments() {
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
