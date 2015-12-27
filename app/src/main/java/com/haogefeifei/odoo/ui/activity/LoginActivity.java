package com.haogefeifei.odoo.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.haogefeifei.odoo.R;
import com.haogefeifei.odoo.api.inf.OdooRpcException;
import com.haogefeifei.odoo.common.utils.LogUtil;
import com.haogefeifei.odoo.service.UserServiceImpl;
import com.haogefeifei.odoo.service.inf.UserServiceInf;
import com.haogefeifei.odoo.ui.activity.base.BaseActivity;
import com.haogefeifei.odoo.ui.view.InputMethodRelativeLayout;

/**
 * 登录页面
 */
public class LoginActivity extends BaseActivity implements InputMethodRelativeLayout.OnSizeChangedListenner {

    public static final String TAG = "LoginActivity";

    private AppCompatButton btnLogin;
    private EditText edtAccount;
    private EditText edtPassWord;
    private EditText edtUrl;
    private ImageView imgLogo;
    private TextView txtUseUrl;
    private TextInputLayout layoutInputUrl;
    private InputMethodRelativeLayout layoutLoginPage;

    private UserServiceInf userService ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        transparentBaseUI(); //透明虚拟按键
        initClass();
        setupView();
    }

    private void initClass(){
        userService = new UserServiceImpl();
    }

    private void setupView() {

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        imgLogo = (ImageView) findViewById(R.id.img_logo);
        imgLogo.setFocusable(true);
        imgLogo.setFocusableInTouchMode(true);

        txtUseUrl = (TextView) findViewById(R.id.link_use_url);
        layoutInputUrl = (TextInputLayout) findViewById(R.id.layout_input_url);
        layoutLoginPage = (InputMethodRelativeLayout)findViewById(R.id.login_page);
        layoutLoginPage.setOnSizeChangedListenner(this);

        edtAccount = (EditText) findViewById(R.id.input_account);
        edtPassWord = (EditText) findViewById(R.id.input_password);
        edtUrl = (EditText) findViewById(R.id.input_url);

        btnLogin = (AppCompatButton) findViewById(R.id.btn_login);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final String account = edtAccount.getText().toString();
                final String password = edtPassWord.getText().toString();

                // Test!
                new Thread(new Runnable() {
                    @Override
                    public void run() {

                        int id = 0;
                        try {
                            id = userService.Login(account, password);
                        } catch (OdooRpcException e) {
                            e.printStackTrace();
                            LogUtil.d(TAG, "--->登录错误！" + e.getExceptionMessage());
                        }

                        Log.d(TAG, "--->登录UID:" + id);

                    }
                }).start();

                startActivity(new Intent(LoginActivity.this, MainActivity.class));
                LoginActivity.this.finish();
            }
        });

        txtUseUrl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (txtUseUrl.getText().equals(getResources().getString(R.string.txt_use_url))) {
                    txtUseUrl.setText(getResources().getString(R.string.txt_default_url));
                    layoutInputUrl.setVisibility(View.VISIBLE);
                } else {
                    txtUseUrl.setText(getResources().getString(R.string.txt_use_url));
                    layoutInputUrl.setVisibility(View.GONE);
                }

            }
        });
    }


    @Override
    public void onSizeChange(boolean flag, int w, int h) {
        if (flag) {//键盘弹出时
            layoutLoginPage.setPadding(0, -10, 0, 0);
            imgLogo.setVisibility(View.GONE);
        } else { //键盘隐藏时
            layoutLoginPage.setPadding(0, 0, 0, 0);
            imgLogo.setVisibility(View.VISIBLE);
        }
    }
}
