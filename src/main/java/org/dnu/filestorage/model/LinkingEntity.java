package org.dnu.filestorage.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import java.io.Serializable;

/**
 * @author demyura
 * @since 15.10.14
 */
@Entity
@IdClass(LinkingEntity.PrimaryKey.class)
public class LinkingEntity {
    @Id
    private Speciality speciality;
    @Id
    private Subject subject;
    @Id
    private Teacher teacher;
    //private PrimaryKey key;

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

    class PrimaryKey implements Serializable {
        private Speciality speciality;
        private Subject subject;
        private Teacher teacher;
    }
}
