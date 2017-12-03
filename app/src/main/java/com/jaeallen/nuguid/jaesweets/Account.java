package com.jaeallen.nuguid.jaesweets;

/**
 * Created by Jae on 12/2/2017.
 */

public class Account {

    private int admin;
    private String username;
    private String password;


    public int isAdmin() {
        return getAdmin();
    }

    public void setAdmin(int admin) {
        this.admin = admin;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getAdmin() {
        return admin;
    }
}
