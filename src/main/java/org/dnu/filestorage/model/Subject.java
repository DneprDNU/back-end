package org.dnu.filestorage.model;

import javax.persistence.*;
import java.util.List;

/**
 * @author demyura
 * @since 07.10.14
 */
@Entity
public class Subject {
    @Id
    @GeneratedValue()
    private long id;
    @ManyToMany(fetch = FetchType.LAZY)
    private List<Resource> resources;
}
