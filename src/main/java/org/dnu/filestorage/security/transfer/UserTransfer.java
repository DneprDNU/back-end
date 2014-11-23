package org.dnu.filestorage.security.transfer;


import java.util.List;

public class UserTransfer {

    private  String name;

    private  List<String> roles;

    public UserTransfer() {}

    public UserTransfer(String userName, List<String> roles) {
        this.name = userName;
        this.roles = roles;
    }


    public String getName() {
        return this.name;
    }


    public List<String> getRoles() {
        return this.roles;
    }

}
