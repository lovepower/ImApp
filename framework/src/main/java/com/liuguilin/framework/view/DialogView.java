package com.liuguilin.framework.view;

import android.app.Dialog;
import android.content.Context;
import android.view.Window;
import android.view.WindowManager;


public class DialogView extends Dialog {
    public DialogView(Context mContext, int layout, int style, int gravity) {
        super(mContext, style);//指定样式
        setContentView(layout);//指定布局文件
        Window window = getWindow();//获取窗口对象
        WindowManager.LayoutParams layoutParams = window.getAttributes();//获取属性
        layoutParams.width = WindowManager.LayoutParams.MATCH_PARENT;
        layoutParams.height = WindowManager.LayoutParams.WRAP_CONTENT;
        layoutParams.gravity = gravity;
        window.setAttributes(layoutParams);//设置属性通过布局参数
    }
}
