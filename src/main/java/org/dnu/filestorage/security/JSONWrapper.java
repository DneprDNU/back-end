package org.dnu.filestorage.security;

public class JSONWrapper {
    private Object error;

    public JSONWrapper(Object error) {
        this.error = error;
    }

    public Object getError() {
        return error;
    }

    public void setError(Object error) {
        this.error = error;
    }
}