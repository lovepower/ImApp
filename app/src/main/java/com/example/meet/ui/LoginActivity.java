package com.example.meet.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.meet.R;
import com.liuguilin.framework.base.BaseUIActivity;
import com.liuguilin.framework.entity.Constants;
import com.liuguilin.framework.manager.DialogManager;
import com.liuguilin.framework.utils.SpUtils;
import com.liuguilin.framework.view.DialogView;
import com.liuguilin.framework.view.LodingView;
import com.liuguilin.framework.view.TouchPictureV;

public class LoginActivity extends BaseUIActivity implements View.OnClickListener{

    private EditText et_phone;
    private EditText et_pwd;
    private ImageButton ib_login;
    private TextView tv_register;
    private TextView tv_forget;
    private DialogView mCodeView;
    private TouchPictureV mPictureV;

    private TextView tv_test_login;

    private LodingView mLodingView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
    }
    private void initView()
    {
        initDialogView();//初始化自定义对话框
        ib_login=findViewById(R.id.bt_login);
        ib_login.setOnClickListener(this);
        et_phone = (EditText) findViewById(R.id.et_phone);
        et_pwd = (EditText) findViewById(R.id.et_pwd);
        tv_register = findViewById(R.id.bt_register);
        tv_forget = findViewById(R.id.bt_forget);
        tv_forget.setOnClickListener(this);
        tv_register.setOnClickListener(this);
        String phone = SpUtils.getInstance().getString(Constants.SP_PHONE,"");
        if (!TextUtils.isEmpty(phone))
        {
            et_phone.setText(phone);
        }

    }
    private void initDialogView()
    {
        mLodingView = new LodingView(this);
        mCodeView = DialogManager.getInstance().initView(this,R.layout.dialog_code_view);
        mPictureV = mCodeView.findViewById(R.id.mPictureV);
        mPictureV.setViewResultListener(()->{
            DialogManager.getInstance().hide(mCodeView);
        });
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.bt_login:
                login();
                break;
            case R.id.bt_register:
                register();
                break;
            case R.id.bt_forget:
                forget();
                break;
        }
    }

    /**
     * 登录
     */
    private void login(){
        final String phone = et_phone.getText().toString().trim();
        if (TextUtils.isEmpty(phone)){
            Toast.makeText(this,getString(R.string.text_login_phone_null),Toast.LENGTH_SHORT).show();
            return;
        }
        String code = et_pwd.getText().toString().trim();
        if (TextUtils.isEmpty(code)){
            Toast.makeText(this,getString(R.string.text_login_pw_null),Toast.LENGTH_SHORT).show();
            return;
        }
        mLodingView.show(getString(R.string.text_login_now_login_text));//显示加载框
        /**
         * 登录操作
         *
         */
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 注册
     */
    private void register(){
       startActivity(new Intent(this,RegisterActivity.class));
    }

    /**
     * 忘记密码
     */
    private void forget(){

    }
}
