package org.dnu.filestorage.data.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.util.LinkedList;
import java.util.List;

@Entity
@Table(name = "resources")
@NamedQueries({@NamedQuery(name = "Resource.getResourcesByCategoryId", query = "select distinct r from Resource r " +
        "left join fetch r.categories c where c.id=:categoryId"),
        @NamedQuery(name = "Resource.getResourcesByTeacherIdByLinks", query = "select distinct r from Resource r " +
                "left join r.subjects s left join s.links l where l.teacher.id=:teacherId"),
        @NamedQuery(name = "Resource.listResourcesByFaculty", query = "select distinct r from Resource r " +
                "left join r.subjects s left join s.links l left join l.speciality sp " +
                "left join sp.departments d left join d.faculty f where f.id=:facultyId"),
        @NamedQuery(name = "Resource.loadWithRelations", query = "select r from Resource r " +
                "left join r.categories c " +
                "left join fetch r.subjects s " +
                "where r.id = :resourceId")})
public class Resource extends NamedEntity {
    @ManyToMany()
    private List<Category> categories = new LinkedList<Category>();
    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "resources")
    private List<Subject> subjects = new LinkedList<Subject>();

    @ManyToOne()
    private Speciality speciality;

    private String year;

    private String author;

    private String description;

    @JsonIgnore
    private String fileR;

    public Resource() {

    }

    public Resource(String name, String year, String author, String description, String resourceURL, String imageURL) {
        super(name, imageURL);
        this.year = year;
        this.author = author;
        this.description = description;
        this.fileR = resourceURL;
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

    @JsonProperty("fileR")
    public String getFileR() {
        return fileR;
    }

    @JsonIgnore
    public void setFileR(String fileR) {
        this.fileR = fileR;
    }

    public Speciality getSpeciality() {
        return speciality;
    }

    public void setSpeciality(Speciality speciality) {
        this.speciality = speciality;
    }

    @PreRemove
    public void removeFromCategoryAndSubject() {
        for (Category category : categories) {
            category.getResources().remove(this);
        }
        for (Subject subject : subjects) {
            subject.getResources().remove(this);
        }
    }
}
