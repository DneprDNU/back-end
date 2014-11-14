package org.dnu.filestorage.model;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import java.util.List;

/**
 * @author demyura
 * @since 07.10.14
 */
@Entity
public class Speciality extends NamedEntity {
    private String code;
    @ManyToMany
    private List<Teacher> supervisors;

    @ManyToMany()
    private List<LinkingEntity> links;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public List<Teacher> getSupervisors() {
        return supervisors;
    }

    public void setSupervisors(List<Teacher> supervisors) {
        this.supervisors = supervisors;
    }

    public List<LinkingEntity> getLinks() {
        return links;
    }

    public void setLinks(List<LinkingEntity> links) {
        this.links = links;
    }
}
