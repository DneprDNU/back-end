package org.dnu.filestorage.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * @author demyura
 * @since 07.10.14
 */
@Entity
public class Teacher {
    @Id
    @GeneratedValue()
    private long id;
}
