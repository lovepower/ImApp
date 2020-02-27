package com.liuguilin.framework.base;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.liuguilin.framework.utils.SystemUI;

public class BaseUIActivity extends AppCompatActivity {
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SystemUI.fixSystemUI(this);
    }
}
