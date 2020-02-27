package com.example.meet;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.liuguilin.framework.base.BaseUIActivity;

public class MainActivity extends BaseUIActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }
    private void initView(){

    }

    @Override
    public void onClick(View v) {

    }
    /*
     *返回逻辑设置
     */
    //第一次按下时间 两次返回
    private long firstClick;

    @Override
    public void onBackPressed() {
        AppExit();
        //super.onBackPressed();
    }

    /**
     * 再按一次退出
     */
    public void AppExit() {
        if (System.currentTimeMillis() - this.firstClick > 2000L) {
            this.firstClick = System.currentTimeMillis();
            Toast.makeText(this, getString(R.string.text_main_exit), Toast.LENGTH_LONG).show();
            return;
        }
        finish();
    }
}
