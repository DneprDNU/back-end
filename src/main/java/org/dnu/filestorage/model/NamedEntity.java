package org.dnu.filestorage.model;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author demyura
 * @since 15.10.14
 */
@MappedSuperclass
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class NamedEntity implements Serializable {
    @Id
    @GeneratedValue()
    private Long id;
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
