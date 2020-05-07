package com.example.fanzhiyong20200506.core;

import com.example.fanzhiyong20200506.bean.Data;
import com.example.fanzhiyong20200506.util.RetroiftUtil;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * 这是BasePresenter类
 */
public abstract class BasePresenter {
    private IContract.IView iView;
    private IContract.IRequest iRequest;

    public BasePresenter(IContract.IView iView) {
        this.iView = iView;
        //调用Retroift工具类
        iRequest = RetroiftUtil.getInstance().create(IContract.IRequest.class);
    }

    public IContract.IRequest getiRequest() {
        return iRequest;
    }

    public void request(Object...args){//创建请求数据的方法，在括号里面设置不定参数
        getObservable(args).subscribeOn(Schedulers.io())//发射数据请求
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Data>() {
                    @Override
                    public void accept(Data data) throws Exception {
                        if (data.getStatus().equals("0000")){
                            iView.IViewSuccess(data.getResult(),data.getMessage());
                        }else {
                            iView.IViewFail(data);
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        throwable.printStackTrace();
                        iView.IViewFail(new Data(throwable.getMessage()));
                    }
                });

    }

    protected abstract Observable getObservable(Object...args);

    //解决内存泄露的方法
    public void destory(){
        if (iView!=null){
            iView=null;
        }
    }
}
