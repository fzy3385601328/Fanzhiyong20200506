package com.example.fanzhiyong20200506.presenter;

import com.example.fanzhiyong20200506.core.IContract;
import com.example.fanzhiyong20200506.core.OrderBasePresenter;

import io.reactivex.Observable;

public class OrderPresenter extends OrderBasePresenter {

    public OrderPresenter(IContract.IView iView) {
        super(iView);
    }

    @Override
    protected Observable getObservable(Object... args) {
        return getiRequest().findOrderListByStatus((String)args[0],(String)args[1]
           ,(int)args[2],(int)args[3],5);
    }
}
