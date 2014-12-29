package org.dnu.filestorage.model;

import javax.persistence.*;

@Entity
@Table(name = "users")
public class User implements Identifiable {

    private Long id;
    private String username;
    private String password;
    private boolean enabled;
    private String userRole;

    public User() {
    }

    public User(String username, String password,
                boolean enabled, String userRole) {
        this.username = username;
        this.password = password;
        this.enabled = enabled;
        this.userRole = userRole;
    }

    @Column(name = "username", unique = true,
            nullable = false, length = 45)
    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Column(name = "password",
            nullable = false, length = 60)
    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Column(name = "enabled", nullable = false)
    public boolean isEnabled() {
        return this.enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    @Column(name = "user_role", nullable = false)
    public String getUserRole() {
        return this.userRole;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }

    @Override
    @Id
    @GeneratedValue()
    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}