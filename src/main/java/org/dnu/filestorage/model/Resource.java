package org.dnu.filestorage.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.List;

/**
 * @author demyura
 * @since 07.10.14
 */
@Entity
public class Resource {
    @Id
    @GeneratedValue()
    private long id;
    @ManyToMany
    private List<Category> categories;
    @ManyToMany
    private List<Subject> subjects;
}
