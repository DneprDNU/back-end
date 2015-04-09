package org.dnu.filestorage.data.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.dnu.filestorage.utils.ResourceDeserializer;

import javax.persistence.*;
import java.util.LinkedList;
import java.util.List;

@JsonDeserialize(using = ResourceDeserializer.class)
@Entity
@Table(name = "resources")
@NamedQueries({@NamedQuery(name = "getResourcesByCategoryId", query = "select r from Resource r " +
        "left join fetch r.categories c where c.id=:categoryId"),
        @NamedQuery(name = "getResourcesByTeacherIdByLinks", query = "select distinct r from Resource r " +
                "left join r.subjects s left join s.links l where l.teacher.id=:teacherId"),
        @NamedQuery(name = "listResourcesByFaculty", query = "select distinct r from Resource r " +
                "left join r.subjects s left join s.links l left join l.speciality sp " +
                "left join sp.departments d left join d.faculty f where f.id=:facultyId")})
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

    private String resource;

    public Resource() {

    }

    public Resource(String name, String year, String author, String description, String resourceURL, String imageURL) {
        super(name, imageURL);
        this.year = year;
        this.author = author;
        this.description = description;
        this.resource = resourceURL;
    }

    public Resource addCategory(Category category) {
        if (!getCategories().contains(category)) {
            getCategories().add(category);
        }
        if (!category.getResources().contains(this)) {
            category.getResources().add(this);
        }
        return this;
    }

    public Resource addSubject(Subject subject) {
        if (!getSubjects().contains(subject)) {
            getSubjects().add(subject);
        }
        if (!subject.getResources().contains(this)) {
            subject.getResources().add(this);
        }
        return this;
    }

    public synchronized List<Category> getCategories() {
        if (categories == null) {
            categories = new LinkedList<>();
        }
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    public synchronized List<Subject> getSubjects() {
        if (subjects == null) {
            subjects = new LinkedList<>();
        }
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

    public String getResource() {
        return resource;
    }

    public void setResource(String resource) {
        this.resource = resource;
    }

    public Speciality getSpeciality() {
        return speciality;
    }

    public void setSpeciality(Speciality speciality) {
        this.speciality = speciality;
    }
}
