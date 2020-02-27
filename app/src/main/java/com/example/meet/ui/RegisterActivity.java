package com.example.meet.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.meet.R;
import com.liuguilin.framework.base.BaseBackActivity;

public class RegisterActivity extends BaseBackActivity implements View.OnClickListener {

    private EditText re_phone;
    private EditText re_pwd_1;
    private EditText re_pwd_2;
    private ImageButton bt_register;
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
    }
}
