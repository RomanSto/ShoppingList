package com.stolbunov.roman.data.user_database;

import com.google.firebase.auth.FirebaseAuth;
import com.stolbunov.roman.data.entities.CredentialsEntity;
import com.stolbunov.roman.data.utils.RxFirebase;

import javax.inject.Inject;

import io.reactivex.Completable;

public class AuthorizationRepository {

    @Inject
    public AuthorizationRepository() {
    }

    public Completable createUserWithEmailAndPassword(CredentialsEntity credentials) {
        return RxFirebase.onCompleteAuthListener(
                FirebaseAuth.getInstance()
                        .createUserWithEmailAndPassword(credentials.getEmail(), credentials.getPassword()));
    }


    public Completable signInWithEmailAndPassword(CredentialsEntity credentials) {
        return RxFirebase.onCompleteAuthListener(
                FirebaseAuth.getInstance()
                        .signInWithEmailAndPassword(credentials.getEmail(), credentials.getPassword()));
    }

    public void signOut() {
        FirebaseAuth.getInstance().signOut();
    }

    public boolean isCurrentUser() {
        FirebaseAuth auth = FirebaseAuth.getInstance();
        return auth.getCurrentUser() != null;
    }
}