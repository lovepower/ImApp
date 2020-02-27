package com.example.meet.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.meet.R;
import com.liuguilin.framework.base.BaseFragment;


public class MsgFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener,View.OnClickListener{

    //二维码结果
    private static final int REQUEST_CODE = 1235;

    private TextView tv_star_title;
    private ImageView iv_camera;
    private ImageView iv_add;
    private View item_empty_view;
    private RecyclerView mChatRecordView;
    private SwipeRefreshLayout mChatRecordRefreshLayout;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_msg, null);
        initView(view);
        return view;
    }
    private void initView(final View view) {
        item_empty_view = view.findViewById(R.id.item_empty_view);
        mChatRecordRefreshLayout = view.findViewById(R.id.msg_refresh_layout);
        mChatRecordView  = view.findViewById(R.id.mChatRecordView);
        mChatRecordRefreshLayout.setOnRefreshListener(this);
        iv_add = view.findViewById(R.id.iv_add);
        iv_camera = view.findViewById(R.id.iv_camera);
        tv_star_title = view.findViewById(R.id.tv_star_title);
        iv_camera.setOnClickListener(this);
        iv_add.setOnClickListener(this);
    }
    @Override
    public void onRefresh() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_camera:
                //扫描二维码
            case R.id.iv_add:
                //添加好友
        }
    }
}

