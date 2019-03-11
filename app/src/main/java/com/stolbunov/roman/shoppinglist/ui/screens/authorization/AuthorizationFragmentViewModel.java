package com.stolbunov.roman.shoppinglist.ui.screens.authorization;

import android.content.Context;
import android.text.TextUtils;

import com.stolbunov.roman.domain.entities.Credentials;
import com.stolbunov.roman.domain.use_cases.IAuthUseCase;
import com.stolbunov.roman.shoppinglist.R;
import com.stolbunov.roman.shoppinglist.entities.Event;
import com.stolbunov.roman.shoppinglist.entities.Nothing;
import com.stolbunov.roman.shoppinglist.utils.Logger;
import com.stolbunov.roman.shoppinglist.utils.RxLiveData;

import javax.inject.Inject;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import io.reactivex.Observable;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class AuthorizationFragmentViewModel extends ViewModel {
    private final Context context;
    private final IAuthUseCase auth;
    private final CompositeDisposable composite = new CompositeDisposable();

    private final MutableLiveData<String> emailLD = new MutableLiveData<>();
    private final MutableLiveData<String> passwordLD = new MutableLiveData<>();

    private final MutableLiveData<Nothing> successfulAuthorizationLD = new MutableLiveData<>();
    private final MutableLiveData<Event<String>> showMessageLD = new MutableLiveData<>();

    private final MutableLiveData<Boolean> isCorrectCredentials = new MutableLiveData<>();

    @Override
    protected void onCleared() {
        super.onCleared();
        composite.dispose();
    }

    @Inject
    public AuthorizationFragmentViewModel(Context context, IAuthUseCase auth) {
        this.context = context;
        this.auth = auth;

        subscribeToWatchCorrectCredentials();
    }

    public void registration() {
        composite.add(auth.createUserWithEmailAndPassword(getCredentials())
                .subscribeOn(Schedulers.io())
                .subscribe(
                        () -> successfulAuthorizationLD.postValue(new Nothing()),
                        this::errorHandling));
    }

    public void singIn() {
        composite.add(auth.signInWithEmailAndPassword(getCredentials())
                .subscribeOn(Schedulers.io())
                .subscribe(
                        () -> successfulAuthorizationLD.postValue(new Nothing()),
                        this::errorHandling));
    }

    public boolean isCurrentUser() {
        return auth.isCurrentUser();
    }

    private void subscribeToWatchCorrectCredentials() {
        composite.add(watchCorrectCredentials().subscribe(
                isCorrectCredentials::postValue,
                this::errorHandling));
    }

    private Observable<Boolean> watchCorrectCredentials() {
        return Observable.combineLatest(
                RxLiveData.toObservable(emailLD)
                        .observeOn(Schedulers.io()),
                RxLiveData.toObservable(passwordLD)
                        .observeOn(Schedulers.io()),
                (e, p) -> (!TextUtils.isEmpty(e) && !TextUtils.isEmpty(p) && p.length() > 5));
    }

    private Credentials getCredentials() {
        return new Credentials(emailLD.getValue(), passwordLD.getValue());
    }

    private void errorHandling(Throwable t) {
        Logger.log(getClass().getSimpleName(), t);
        showMessageLD.postValue(new Event<>(context.getString(R.string.authentication_failed)));
    }

    public MutableLiveData<String> getEmailLD() {
        return emailLD;
    }

    public MutableLiveData<String> getPasswordLD() {
        return passwordLD;
    }

    public MutableLiveData<Boolean> getIsCorrectCredentials() {
        return isCorrectCredentials;
    }

    LiveData<Nothing> getSuccessfulAuthorizationLD() {
        return successfulAuthorizationLD;
    }

    LiveData<Event<String>> getShowMessageLD() {
        return showMessageLD;
    }
}
