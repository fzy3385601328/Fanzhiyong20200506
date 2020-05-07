package com.example.fanzhiyong20200506.core;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseFragment extends Fragment {

    private Unbinder bind;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = View.inflate(getContext(), getLayoutResId(), null);
        bind = ButterKnife.bind(this, view);
        initView(savedInstanceState);
        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        destoryPresenter();
        bind.unbind();//解绑
    }

    protected abstract int getLayoutResId();

    protected abstract void initView(Bundle savedInstanceState);

    protected abstract void destoryPresenter();
}
