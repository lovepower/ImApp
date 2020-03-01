package com.example.meet.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.meet.R;
import com.liuguilin.framework.base.BaseBackActivity;
import com.liuguilin.framework.entity.StatusCode;
import com.liuguilin.framework.manager.HttpManager;
import com.liuguilin.framework.utils.LogUtils;
import com.liuguilin.framework.view.LodingView;

public class RegisterActivity extends BaseBackActivity implements View.OnClickListener {

    private static final int A_CODE = 1000;
    private static final int R_CODE = 1001;
    private static final int E_CODE = 1002;

    private EditText re_phone;
    private EditText re_pwd_1;
    private EditText re_pwd_2;
    private ImageButton bt_register;
    private LodingView mLodingView;
    private Handler mHandler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(@NonNull Message msg) {
                switch (msg.what)
                {
                    case A_CODE:
                        Toast.makeText(RegisterActivity.this,"注册成功!",Toast.LENGTH_SHORT).show();
                        break;
                    case R_CODE:
                        Toast.makeText(RegisterActivity.this,"账号密码已被注册",Toast.LENGTH_SHORT).show();
                        break;
                    case E_CODE:
                        Toast.makeText(RegisterActivity.this,"服务器出现错误!",Toast.LENGTH_SHORT).show();
                        break;
            }
            return false;
        }
    });
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        re_phone = findViewById(R.id.et_phone_re);
        re_pwd_1 = findViewById(R.id.et_pwd_re);
        re_pwd_2 = findViewById(R.id.et_pwd_re_again);
        bt_register = findViewById(R.id.bt_re_register);
        bt_register.setOnClickListener(this);
        setTitle("注册");
        mLodingView = new LodingView(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.bt_re_register:
                register();
                break;
        }

    }
    private void register()
    {
        String phone = re_phone.getText().toString().trim();
        if (TextUtils.isEmpty(phone)){
            Toast.makeText(this,getString(R.string.text_login_phone_null),Toast.LENGTH_SHORT).show();
            return;
        }
        String pwd = re_pwd_1.getText().toString().trim();
        String pwd1 = re_pwd_2.getText().toString().trim();
        if (TextUtils.isEmpty(pwd)||TextUtils.isEmpty(pwd1)){
            Toast.makeText(this,getString(R.string.text_login_phone_null),Toast.LENGTH_SHORT).show();
            return;
        }
        if (!TextUtils.equals(pwd,pwd1)){
            Toast.makeText(this,getString(R.string.text_register_pwd),Toast.LENGTH_SHORT).show();
            return;
        }

        //发送注册信息
        mLodingView.show("正在注册...");
        new Thread(){
            public void run(){
                StatusCode statusCode = HttpManager.getInstance().register(phone,pwd);
                if (statusCode.getStatus().equalsIgnoreCase("200")){
                    mLodingView.hide();
                    mHandler.sendEmptyMessage(A_CODE);
                    return;
                }else if (statusCode.getStatus().equalsIgnoreCase("201")){
                    mLodingView.hide();
                   mHandler.sendEmptyMessage(R_CODE);
                    return;
                }else {
                    mLodingView.hide();
                    mHandler.sendEmptyMessage(E_CODE);
                    return;
                }
            }
        }.start();

    }
}
