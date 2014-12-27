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
    @Column(length = 10000)
    private String image;

    public NamedEntity() {
    }

    public NamedEntity(String name) {
        this.name = name;
    }

    public NamedEntity(String name, String image) {
        this.name = name;
        this.image = image;
    }

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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
