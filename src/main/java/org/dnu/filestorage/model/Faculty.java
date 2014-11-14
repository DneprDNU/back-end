package org.dnu.filestorage.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.List;

/**
 * @author demyura
 * @since 28.09.14
 */
@Entity
public class Faculty extends NamedEntity {
    private String shortName;
    private String description;
    @OneToMany(fetch = FetchType.LAZY)
    private List<Department> departments;

    public Faculty() {
    }

    public Faculty(String name, String shortName, String description) {
        super(name);
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
