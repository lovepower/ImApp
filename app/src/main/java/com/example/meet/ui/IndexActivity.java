package com.example.meet.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;

import com.example.meet.R;
import com.liuguilin.framework.entity.Constants;
import com.liuguilin.framework.utils.SpUtils;

public class IndexActivity extends AppCompatActivity {

    private static  final int SKIP_MAIN = 1000;
    private Handler mHandler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(@NonNull Message msg) {
            switch (msg.what)
            {
                case SKIP_MAIN:
                    startMain();
                    break;
            }
            return false;
        }
    });
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_index);
        mHandler.sendEmptyMessageAtTime(SKIP_MAIN,2*1000);
    }

    /**
     * 进入主页
     */
    private void startMain(){
        boolean isFirstApp = SpUtils.getInstance().getBoolean(Constants.SP_IS_FIRST_APP,true);
        Intent intent = new Intent();
        if(isFirstApp){
           //跳到引导页
            startActivity(new Intent(this,GuideActivity.class));
            SpUtils.getInstance().putBoolean(Constants.SP_IS_FIRST_APP,false);
        }else {
            //不是第一次启动
            String token = SpUtils.getInstance().getString(Constants.SP_TOKEN,"");
            if (TextUtils.isEmpty(token))
            {
                //调到登录页
                startActivity(new Intent(this,LoginActivity.class));

            }else {
                //调到主页
                startActivity(new Intent(this,LoginActivity.class));
            }
        }
    }
    @Override
    public void onBackPressed() {
        //引导页无需退出
        //super.onBackPressed();
    }
}
