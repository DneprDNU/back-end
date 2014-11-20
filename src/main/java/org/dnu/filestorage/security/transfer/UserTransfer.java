package org.dnu.filestorage.security.transfer;

import java.util.Map;

/**
 * @author demyura
 * @since 19.11.14
 */
public class UserTransfer {

    private final String name;

    private final Map<String, Boolean> roles;


    public UserTransfer(String userName, Map<String, Boolean> roles) {
        this.name = userName;
        this.roles = roles;
    }


    public String getName() {
        return this.name;
    }


    public Map<String, Boolean> getRoles() {
        return this.roles;
    }

}
