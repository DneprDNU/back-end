package org.dnu.filestorage.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import java.util.List;

@Entity
public class Resource extends NamedEntity {
    @ManyToMany
    private List<Category> categories;
    @ManyToMany(fetch= FetchType.EAGER)
    private List<Subject> subjects;

    private String year;

    private String author;

    private String description;

    private String resourceURL;

    private String imageURL;

    public Resource(){

    }

    public Resource(String name, List<Category> categories, List<Subject> subjects, String year, String author, String description, String resourceURL, String imageURL) {
        super(name);
        this.categories = categories;
        this.subjects = subjects;
        this.year = year;
        this.author = author;
        this.description = description;
        this.resourceURL = resourceURL;
        this.imageURL = imageURL;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    public List<Subject> getSubjects() {
        return subjects;
    }

    public void setSubjects(List<Subject> subjects) {
        this.subjects = subjects;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getResourceURL() {
        return resourceURL;
    }

    public void setResourceURL(String resourceURL) {
        this.resourceURL = resourceURL;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }
}
