package com.example.fanzhiyong20200506.fragment;

import android.os.Bundle;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fanzhiyong20200506.R;
import com.example.fanzhiyong20200506.adapter.MyOrderListAdapter;
import com.example.fanzhiyong20200506.bean.Data;
import com.example.fanzhiyong20200506.bean.OrderListBean;
import com.example.fanzhiyong20200506.bean.OrderShopBean;
import com.example.fanzhiyong20200506.core.BaseFragment;

import com.example.fanzhiyong20200506.core.IContract;
import com.example.fanzhiyong20200506.core.OrderBasePresenter;
import com.example.fanzhiyong20200506.presenter.OrderPresenter;
import com.example.fanzhiyong20200506.util.RetroiftUtil;

import java.util.List;

import butterknife.BindView;


public class FragmentAll extends BaseFragment implements IContract.IView<List<OrderListBean>> {

    private String i;
    private String j;
    private int x;
    private int y;
    private OrderPresenter orderPresenter;
    @BindView(R.id.recycler_list_view)
    RecyclerView recycler_list_view;
    private MyOrderListAdapter listAdapter;

    public FragmentAll(String i, String j, int x, int y) {
        this.i = i;
        this.j = j;
        this.x = x;
        this.y = y;
    }



    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_all;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
       //初始化P层数据
        orderPresenter = new OrderPresenter(this);

        //设置布局管理器
        LinearLayoutManager recyclerListManager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
        recycler_list_view.setLayoutManager(recyclerListManager);

        //设置适配器
        listAdapter = new MyOrderListAdapter();
        recycler_list_view.setAdapter(listAdapter);

        //有网请求，无网吐司
        if (RetroiftUtil.isNetActivice(getContext())){
            orderPresenter.request(i,j,x,y);
        }else {
            Toast.makeText(getContext(), "您当前未连接网络", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void destoryPresenter() {
       if (orderPresenter!=null){
           orderPresenter.destory();
           orderPresenter=null;
       }
    }

    @Override
    public void IViewSuccess(List<OrderListBean> result, String message) {
        Toast.makeText(getContext(), "请求成功"+result, Toast.LENGTH_SHORT).show();
        listAdapter.getOrderList().addAll(result);
        listAdapter.notifyDataSetChanged();
    }

    @Override
    public void IViewFail(Data data) {
        Toast.makeText(getContext(), "请求失败", Toast.LENGTH_SHORT).show();
    }
}
