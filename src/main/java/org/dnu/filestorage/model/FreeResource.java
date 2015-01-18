package org.dnu.filestorage.model;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class FreeResource extends NamedEntity {
    private String description;

    @Column(length = 10000)
    private String resource;

    public FreeResource() {

    }

    public FreeResource(String name, String description, String resourceURL, String imageURL) {
        super(name, imageURL);
        this.description = description;
        this.resource = resourceURL;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getResource() {
        return resource;
    }

    public void setResource(String resource) {
        this.resource = resource;
    }

}
