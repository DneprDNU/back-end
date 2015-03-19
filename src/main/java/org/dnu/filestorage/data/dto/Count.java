package org.dnu.filestorage.data.dto;

/**
 * @author demyura
 * @since 19.03.15
 */
public class Count {
    private Long count;

    public Count() {
    }

    public Count(Long count) {
        this.count = count;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }
}
