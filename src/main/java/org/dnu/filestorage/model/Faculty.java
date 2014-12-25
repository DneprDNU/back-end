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
    private String image;
    @OneToMany(fetch = FetchType.EAGER)
    private List<Department> departments;

    public Faculty() {
    }

    public Faculty(String name, String shortName, String description) {
        super(name);
        this.shortName = shortName;
        this.description = description;
    }

    public Faculty(String name, String shortName, String description, String image) {
        super(name);
        this.shortName = shortName;
        this.description = description;
        this.image = image;
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
