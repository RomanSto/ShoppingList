package com.stolbunov.roman.domain.repository;

import com.stolbunov.roman.domain.entities.Credentials;

import io.reactivex.Completable;

public interface IAuthorizationRepository {
    Completable createUserWithEmailAndPassword(Credentials credentials);

    Completable signInWithEmailAndPassword(Credentials credentials);

    void signOut();

    boolean isCurrentUser();
}
