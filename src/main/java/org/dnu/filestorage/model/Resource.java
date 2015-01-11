package org.dnu.filestorage.model;

import javax.persistence.*;
import java.util.LinkedList;
import java.util.List;

@Entity
@NamedQueries({@NamedQuery(name = "getResourcesByCategoryId", query = "select r from Resource r " +
        "left join fetch r.categories c where c.id=:categoryId"),
        @NamedQuery(name = "getResourcesByTeacherIdByLinks", query = "select distinct r from Resource r " +
                "left join r.subjects s left join s.links l where l.teacher.id=:teacherId")})
public class Resource extends NamedEntity {
    @ManyToMany
    private List<Category> categories = new LinkedList<Category>();
    @ManyToMany(fetch = FetchType.LAZY)
    private List<Subject> subjects = new LinkedList<Subject>();

    @ManyToOne()
    private Speciality speciality;

    private String year;

    private String author;

    private String description;

    private String resourceURL;

    private String imageURL;

    public Resource() {

    }

    public Resource(String name, String year, String author, String description, String resourceURL, String imageURL) {
        super(name);
        this.year = year;
        this.author = author;
        this.description = description;
        this.resourceURL = resourceURL;
        this.imageURL = imageURL;
    }

    public Resource addCategory(Category category) {
        this.categories.add(category);
        category.getResources().add(this);
        return this;
    }

    public Resource addSubject(Subject subject) {
        this.subjects.add(subject);
        subject.getResources().add(this);
        return this;
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

    public Speciality getSpeciality() {
        return speciality;
    }

    public void setSpeciality(Speciality speciality) {
        this.speciality = speciality;
    }
}
