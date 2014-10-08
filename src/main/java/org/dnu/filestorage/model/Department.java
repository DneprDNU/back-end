package org.dnu.filestorage.model;

import javax.persistence.*;
import java.util.List;

/**
 * @author demyura
 * @since 28.09.14
 */
@Entity
public class Department {
    @Id
    @GeneratedValue()
    private long id;
    private String name;
    @ManyToMany(fetch = FetchType.LAZY)
    private List<Speciality> specialities;
    @ManyToMany(fetch = FetchType.LAZY)
    private List<Teacher> employees;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
