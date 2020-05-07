package com.example.fanzhiyong20200506.activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.example.fanzhiyong20200506.R;
import com.example.fanzhiyong20200506.bean.LoginBean;
import com.example.fanzhiyong20200506.core.BaseActivity;
import com.example.fanzhiyong20200506.dao.DaoMaster;
import com.example.fanzhiyong20200506.dao.DaoSession;
import com.example.fanzhiyong20200506.dao.LoginBeanDao;
import com.example.fanzhiyong20200506.fragment.FragmentAll;
import com.example.fanzhiyong20200506.fragment.FragmentFa;
import com.example.fanzhiyong20200506.fragment.FragmentPay;
import com.example.fanzhiyong20200506.fragment.FragmentPing;
import com.example.fanzhiyong20200506.fragment.FragmentShow;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class MainActivity extends BaseActivity {
    @BindView(R.id.user_image)
    ImageView user_image;
    @BindView(R.id.user_phone)
    TextView user_phone;
    @BindView(R.id.tab_layout)
    TabLayout tab_layout;
    @BindView(R.id.view_pager)
    ViewPager view_pager;

    private LoginBeanDao loginBeanDao;
    private LoginBean loginBean;

    private List<Fragment> fragmentList = new ArrayList<>();
    private List<String> list = new ArrayList<>();

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected String getPageTitle() {
        return "订单页面";
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        Intent intent = getIntent();

        String headPic = intent.getStringExtra("headPic");
        String phone = intent.getStringExtra("phone");

        RequestOptions options = new RequestOptions().apply(RequestOptions.bitmapTransform(new CircleCrop()))
                .fallback(R.mipmap.ic_launcher)
                .error(R.mipmap.ic_launcher)
                .placeholder(R.mipmap.ic_launcher);

        Glide.with(user_image.getContext())
                .applyDefaultRequestOptions(options)
                .load(headPic)
                .into(user_image);
        user_phone.setText(phone);


        list.add("全部订单");
        list.add("待支付");
        list.add("待发货");
        list.add("待收货");
        list.add("待评价");

        tab_layout.addTab(tab_layout.newTab().setText(list.get(0)));
        tab_layout.addTab(tab_layout.newTab().setText(list.get(1)));
        tab_layout.addTab(tab_layout.newTab().setText(list.get(2)));
        tab_layout.addTab(tab_layout.newTab().setText(list.get(3)));
        tab_layout.addTab(tab_layout.newTab().setText(list.get(4)));

        //判断登录用户
        //GreenDao数据库代码
        DaoSession daoSession = DaoMaster.newDevSession(this, LoginBeanDao.TABLENAME);
        loginBeanDao = daoSession.getLoginBeanDao();

        //条件查询登录的用户
        loginBean = loginBeanDao.queryBuilder().where(LoginBeanDao.Properties.Status.eq(1)).unique();
        fragmentList.add(new FragmentAll(String.valueOf(loginBean.getUserId()),loginBean.getSessionId()
           ,0,1));
        fragmentList.add(new FragmentPay());
        fragmentList.add(new FragmentShow());
        fragmentList.add(new FragmentFa());
        fragmentList.add(new FragmentPing());


        MyFragmentPagerAdapter pagerAdapter = new MyFragmentPagerAdapter(getSupportFragmentManager());
        view_pager.setAdapter(pagerAdapter);
        tab_layout.setupWithViewPager(view_pager);
    }

    @Override
    protected void destoryPresenter() {

    }

    class MyFragmentPagerAdapter extends FragmentPagerAdapter{

        public MyFragmentPagerAdapter(@NonNull FragmentManager fm) {
            super(fm);
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            return fragmentList.get(position);
        }

        @Override
        public int getCount() {
            return fragmentList.size();
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return list.get(position);
        }
    }
}
