package com.example.fanzhiyong20200506.presenter;

import com.example.fanzhiyong20200506.core.BasePresenter;
import com.example.fanzhiyong20200506.core.IContract;

import io.reactivex.Observable;

public class LoginPresenter extends BasePresenter {

    public LoginPresenter(IContract.IView iView) {
        super(iView);
    }

    @Override
    protected Observable getObservable(Object... args) {
        return getiRequest().login((String)args[0],(String)args[1]);
    }
}
