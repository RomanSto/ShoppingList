package com.stolbunov.roman.data.mapper;

import com.stolbunov.roman.data.entities.CredentialsEntity;
import com.stolbunov.roman.domain.entities.Credentials;

public class UserAuthorizationMapper {

    public static CredentialsEntity transformCredentialsToCredentialsEntity(Credentials credentials) {
        return new CredentialsEntity(credentials.getEmail(), credentials.getPassword());
    }
}
