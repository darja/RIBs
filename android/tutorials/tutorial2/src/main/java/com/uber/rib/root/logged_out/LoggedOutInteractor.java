package com.uber.rib.root.logged_out;

import android.annotation.SuppressLint;

import androidx.annotation.Nullable;

import com.uber.rib.core.Bundle;
import com.uber.rib.core.Interactor;
import com.uber.rib.core.RibInteractor;
import com.uber.rib.root.logged_out.LoggedOutBuilder.LoggedOutScope;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * Coordinates Business Logic for {@link LoggedOutScope}.
 */
@RibInteractor
public class LoggedOutInteractor
        extends Interactor<LoggedOutInteractor.LoggedOutPresenter, LoggedOutRouter> {

    @Inject LoggedOutPresenter presenter;

    @Inject Listener listener;

    @SuppressLint("CheckResult")
    @Override
    protected void didBecomeActive(@Nullable Bundle savedInstanceState) {
        super.didBecomeActive(savedInstanceState);
        presenter
            .loginName()
            .filter(name -> name != null && !name.isEmpty())
            .subscribe(name -> listener.login(name));
    }

    /**
     * Presenter interface implemented by this RIB's view.
     */
    interface LoggedOutPresenter {
        Observable<String> loginName();
    }

    public interface Listener {
        void login(String userName);
    }
}
