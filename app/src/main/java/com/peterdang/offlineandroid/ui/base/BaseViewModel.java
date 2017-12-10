package com.peterdang.offlineandroid.ui.base;

import android.arch.lifecycle.ViewModel;

import javax.xml.transform.Transformer;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by phuoc on 2017-12-10.
 */

public class BaseViewModel extends ViewModel{
    protected CompositeDisposable disposables  = new CompositeDisposable();

    @Override
    protected void onCleared() {
        disposables.clear();
    }
}
