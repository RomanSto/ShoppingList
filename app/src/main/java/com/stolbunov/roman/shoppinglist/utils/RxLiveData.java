package com.stolbunov.roman.shoppinglist.utils;

import androidx.lifecycle.LiveData;
import io.reactivex.Observable;

public final class RxLiveData {
    private RxLiveData() {
    }

    public static <T> Observable<T> toObservable(LiveData<T> liveData) {
        return Observable.create(emitter -> liveData.observeForever(emitter::onNext));
    }
}
