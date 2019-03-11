package com.stolbunov.roman.data.entities;

public class CredentialsEntity {
    private final String email;
    private final String password;

    public CredentialsEntity(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
