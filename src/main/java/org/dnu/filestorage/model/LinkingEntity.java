package org.dnu.filestorage.model;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author demyura
 * @since 15.10.14
 */
@Entity
//@IdClass(LinkingEntity.PrimaryKey.class)
public class LinkingEntity {
    @Id
    @GeneratedValue
    private Long id;
    //    @Id
    @ManyToOne(cascade = CascadeType.ALL)
    private Speciality speciality;
    //    @Id
    @ManyToOne(cascade = CascadeType.ALL)
    private Subject subject;
    //    @Id
    @ManyToOne(cascade = CascadeType.ALL)
    private Teacher teacher;
    //private PrimaryKey key;


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

    public void setSpeciality(Speciality speciality) {
        this.speciality = speciality;
        speciality.getLinks().add(this);
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
        subject.getLinks().add(this);
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
        teacher.getLinks().add(this);
    }

    public static class PrimaryKey implements Serializable {
        private Speciality speciality;
        private Subject subject;
        private Teacher teacher;

        public PrimaryKey(Speciality speciality, Subject subject, Teacher teacher) {
            this.speciality = speciality;
            this.subject = subject;
            this.teacher = teacher;
        }

        public PrimaryKey() {
        }

        public Speciality getSpeciality() {
            return speciality;
        }

        public void setSpeciality(Speciality speciality) {
            this.speciality = speciality;
        }

        public Subject getSubject() {
            return subject;
        }

        public void setSubject(Subject subject) {
            this.subject = subject;
        }

        public Teacher getTeacher() {
            return teacher;
        }

        public void setTeacher(Teacher teacher) {
            this.teacher = teacher;
        }
    }
}
