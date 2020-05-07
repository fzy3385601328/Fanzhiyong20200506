package com.example.fanzhiyong20200506.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.EditText;
import android.widget.Toast;

import com.example.fanzhiyong20200506.R;
import com.example.fanzhiyong20200506.bean.Data;
import com.example.fanzhiyong20200506.bean.LoginBean;
import com.example.fanzhiyong20200506.core.BaseActivity;
import com.example.fanzhiyong20200506.core.IContract;
import com.example.fanzhiyong20200506.dao.DaoMaster;
import com.example.fanzhiyong20200506.dao.DaoSession;
import com.example.fanzhiyong20200506.dao.LoginBeanDao;
import com.example.fanzhiyong20200506.presenter.LoginPresenter;
import com.example.fanzhiyong20200506.util.RetroiftUtil;

import butterknife.BindView;
import butterknife.OnClick;

public class LoginActivity extends BaseActivity implements IContract.IView<LoginBean> {

    private LoginPresenter loginPresenter;
    private LoginBeanDao loginBeanDao;
    private LoginBean loginBean;

    @BindView(R.id.edit_phone)
    EditText edit_phone;
    @BindView(R.id.edit_pwd)
    EditText edit_pwd;
    private String phone;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    protected String getPageTitle() {
        return "用户登录";
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        //初始化及持有P层数据
        loginPresenter = new LoginPresenter(this);
        //GreenDao数据库代码
        DaoSession daoSession = DaoMaster.newDevSession(this, LoginBeanDao.TABLENAME);
        loginBeanDao = daoSession.getLoginBeanDao();

        //条件查询登录的用户
        loginBean = loginBeanDao.queryBuilder().where(LoginBeanDao.Properties.Status.eq(1)).unique();

        if (loginBean!=null){
            startIntent();//准备跳转
        }

    }

    private void startIntent() {
        //跳转
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        intent.putExtra("headPic",loginBean.getHeadPic());
        intent.putExtra("phone",phone);
        startActivity(intent);

        finish();

    }

    @OnClick(R.id.btn_login)
    public void login(){
        phone = edit_phone.getText().toString();
        String pwd = edit_pwd.getText().toString();

        if (TextUtils.isEmpty(phone)||TextUtils.isEmpty(pwd)){
            Toast.makeText(this, "请输入正确的手机号或者密码", Toast.LENGTH_SHORT).show();
        }else {
            if (RetroiftUtil.isNetActivice(this)){
                //判断是否有网
                loginPresenter.request(phone,pwd);//发送请求
            }else {
                //无网络提示用户连接网络
                Toast.makeText(this, "抱歉，您未连接网络，请链接网络设备后重试", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    protected void destoryPresenter() {
        if (loginPresenter!=null){
            loginPresenter.destory();
            loginPresenter=null;//置空
        }
    }

    @Override
    public void IViewSuccess(LoginBean result, String message) {
        Toast.makeText(this, "登录成功"+result, Toast.LENGTH_SHORT).show();
        result.setStatus(1);

        //保存数据库
        loginBeanDao.insertOrReplaceInTx(result);

        //跳转
        startIntent();
    }



    @Override
    public void IViewFail(Data data) {
        Toast.makeText(this, "登录失败"+data.getMessage(), Toast.LENGTH_SHORT).show();
    }
}
