package org.dnu.filestorage.security.transfer;


import java.util.List;

public class UserTransfer {

    private Long id;

    private  String name;

    private  List<String> roles;

    public UserTransfer() {}

    public UserTransfer(Long id, String userName, List<String> roles) {
        this.id = id;
        this.name = userName;
        this.roles = roles;
    }


    public String getName() {
        return this.name;
    }


    public List<String> getRoles() {
        return this.roles;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
