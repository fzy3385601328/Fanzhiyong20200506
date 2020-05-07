package com.example.fanzhiyong20200506.core;

import com.example.fanzhiyong20200506.bean.Data;
import com.example.fanzhiyong20200506.bean.LoginBean;
import com.example.fanzhiyong20200506.bean.OrderListBean;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * 这是管理所有类的契约接口
 */
public interface IContract {
    //V层接口
    interface IView<T>{
        void IViewSuccess(T result,String message);
        void IViewFail(Data data);
    }

    //请求接口数据
    interface IRequest{
        //http://mobile.bwstudent.com/small/user/v1/login?phone=18831985600&pwd=xiaofan123
        @POST("small/user/v1/login")
        @FormUrlEncoded
        Observable<Data<LoginBean>> login(@Field("phone")String phone,@Field("pwd")String pwd);
        //http://mobile.bwstudent.com/small/order/verify/v1/findOrderListByStatus?status=0&page=1&count=5
        @GET("small/order/verify/v1/findOrderListByStatus")
        Observable<Data<List<OrderListBean>>> findOrderListByStatus(@Header("userId")String userId
        , @Header("sessionId")String sessionId, @Query("status")int status,@Query("page")int page,
                                                                    @Query("count")int count);
    }
}
