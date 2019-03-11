package com.stolbunov.roman.domain.use_cases;

import com.stolbunov.roman.domain.entities.Credentials;

import io.reactivex.Completable;

public interface IAuthUseCase {

    Completable createUserWithEmailAndPassword(Credentials credentials);

    Completable signInWithEmailAndPassword(Credentials credentials);

    boolean isCurrentUser();

    void signOut();
}
