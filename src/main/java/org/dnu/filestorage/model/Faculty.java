package org.dnu.filestorage.model;

import javax.persistence.*;
import java.util.List;

/**
 * @author demyura
 * @since 28.09.14
 */
@Entity
public class Faculty {
    @Id
    @GeneratedValue()
    private long id;
    private String facultyName;
    private String description;
    @OneToMany(fetch = FetchType.LAZY)
    private List<Department> departments;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFacultyName() {
        return facultyName;
    }

    public void setFacultyName(String facultyName) {
        this.facultyName = facultyName;
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
}
