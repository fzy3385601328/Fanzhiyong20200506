package com.example.fanzhiyong20200506.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.example.fanzhiyong20200506.R;
import com.example.fanzhiyong20200506.bean.OrderShopBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MyOrderShopAdapter extends RecyclerView.Adapter<MyOrderShopAdapter.MyViewHolder> {
    List<OrderShopBean> orderShop = new ArrayList<>();

    public List<OrderShopBean> getOrderShop() {
        return orderShop;
    }

    @NonNull
    @Override
    public MyOrderShopAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.order_shop_view, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyOrderShopAdapter.MyViewHolder holder, int position) {
        OrderShopBean shopBean = orderShop.get(position);
        String[] orderImage = shopBean.getCommodityPic().split(",");

        RequestOptions options = new RequestOptions().fallback(R.mipmap.ic_launcher)
                .apply(RequestOptions.bitmapTransform(new RoundedCorners(55)))
                .error(R.mipmap.ic_launcher)
                .placeholder(R.mipmap.ic_launcher);

        Glide.with(holder.order_image.getContext())
                .applyDefaultRequestOptions(options)
                .load(orderImage[0])
                .into(holder.order_image);

        holder.order_name.setText(shopBean.getCommodityName());
        holder.order_price.setText("$"+shopBean.getCommodityPrice());
        holder.order_count.setText("数量"+shopBean.getCommodityCount());
    }

    @Override
    public int getItemCount() {
        return orderShop.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.order_image)
        ImageView order_image;
        @BindView(R.id.order_name)
        TextView order_name;
        @BindView(R.id.order_price)
        TextView order_price;
        @BindView(R.id.order_count)
        TextView order_count;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
