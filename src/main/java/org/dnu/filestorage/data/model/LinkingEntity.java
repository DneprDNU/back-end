package org.dnu.filestorage.data.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 * @author demyura
 * @since 15.10.14
 */
@Entity
public class LinkingEntity implements Identifiable {
    @Id
    @GeneratedValue
    private Long id;
    //    @Id
    @ManyToOne()
    private Speciality speciality;
    //    @Id
    @ManyToOne()
    private Subject subject;
    //    @Id
    @ManyToOne()
    private Teacher teacher;


    public LinkingEntity() {
    }

    public LinkingEntity(Speciality speciality, Subject subject, Teacher teacher) {
        setSpeciality(speciality);
        setSubject(subject);
        setTeacher(teacher);
    }

    public Speciality getSpeciality() {
        return speciality;
    }

    public LinkingEntity setSpeciality(Speciality speciality) {
        this.speciality = speciality;
        speciality.getLinks().add(this);
        return this;
    }

    public Subject getSubject() {
        return subject;
    }

    public LinkingEntity setSubject(Subject subject) {
        this.subject = subject;
        subject.getLinks().add(this);
        return this;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public LinkingEntity setTeacher(Teacher teacher) {
        this.teacher = teacher;
        teacher.getLinks().add(this);
        return this;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
