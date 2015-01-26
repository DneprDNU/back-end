package org.dnu.filestorage.data.model;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

/**
 * @author demyura
 * @since 26.01.15
 */
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class AbstractCategory extends NamedEntity {
    public AbstractCategory(String name) {
        super(name);
    }

    public AbstractCategory(String name, String image) {
        super(name, image);
    }

    public AbstractCategory() {
    }
}
