package com.example.springwebmvc.ModelClasses;

public class LoginData {
    private String nomeUser;
    private String password;

    public LoginData() {
    }

    public String getNomeUser() {
        return nomeUser;
    }

    public void setNomeUser(String nomeUser) {
        this.nomeUser = nomeUser;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
