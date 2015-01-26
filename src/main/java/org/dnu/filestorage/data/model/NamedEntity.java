package org.dnu.filestorage.data.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author demyura
 * @since 15.10.14
 */
@MappedSuperclass
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class NamedEntity implements Serializable, Identifiable {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;
    private String name;
    @Column(length = 10000)
    @JsonIgnore
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

    @JsonProperty("image")
    public String getImage() {
        return image;
    }

    @JsonIgnore
    public void setImage(String image) {
        this.image = image;
    }
}
