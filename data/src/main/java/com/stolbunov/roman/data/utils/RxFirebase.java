package com.stolbunov.roman.data.utils;

import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import androidx.annotation.NonNull;
import io.reactivex.Completable;
import io.reactivex.Single;

public final class RxFirebase {
    private RxFirebase() {
    }

    public static Completable onCompleteAuthListener(Task<AuthResult> task) {
        return Completable.create(emitter ->
                task.addOnCompleteListener(task1 -> {
                    if (task.isSuccessful()) {
                        emitter.onComplete();
                    } else {
                        emitter.onError(task.getException());
                    }
                }));
    }

    public static Single<DataSnapshot> singleValueEventObserver(DatabaseReference reference) {
        return Single.create(emitter ->
                reference.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        emitter.onSuccess(dataSnapshot);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                        emitter.onError(databaseError.toException());
                    }
                }));
    }
}
