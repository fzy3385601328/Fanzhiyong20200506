package com.example.fanzhiyong20200506.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fanzhiyong20200506.R;
import com.example.fanzhiyong20200506.bean.OrderListBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MyOrderListAdapter extends RecyclerView.Adapter<MyOrderListAdapter.MyViewHolder> {
    List<OrderListBean> orderList = new ArrayList<>();
    private MyOrderShopAdapter shopAdapter;

    public List<OrderListBean> getOrderList() {
        return orderList;
    }

    @NonNull
    @Override
    public MyOrderListAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.order_list_view, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyOrderListAdapter.MyViewHolder holder, int position) {
        OrderListBean orderListBean = orderList.get(position);
        holder.order_list_name.setText(orderListBean.getExpressCompName());
        holder.order_code.setText(orderListBean.getOrderId());

        LinearLayoutManager recyclerShopManager = new LinearLayoutManager(holder.recycler_shop_view.getContext(), RecyclerView.VERTICAL, false);
        holder.recycler_shop_view.setLayoutManager(recyclerShopManager);

        shopAdapter = new MyOrderShopAdapter();
        holder.recycler_shop_view.setAdapter(shopAdapter);

        shopAdapter.getOrderShop().addAll(orderListBean.getDetailList());
        shopAdapter.notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return orderList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.order_list_name)
        TextView order_list_name;
        @BindView(R.id.order_code)
        TextView order_code;
        @BindView(R.id.recycler_shop_view)
        RecyclerView recycler_shop_view;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
