package com.example.fanzhiyong20200506.core;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseActivity extends AppCompatActivity {

    private ActionBar actionBar;
    private Unbinder bind;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //布局
        setContentView(getLayoutId());
        //ButterKnife的绑定与解绑
        bind = ButterKnife.bind(this);
        //页面标题
        actionBar = getSupportActionBar();
        actionBar.setTitle(getPageTitle());

        initView(savedInstanceState);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        destoryPresenter();
        //解绑
        bind.unbind();
    }

    protected abstract int getLayoutId();

    protected abstract String getPageTitle();

    protected abstract void initView(Bundle savedInstanceState);

    protected abstract void destoryPresenter();
}
