package com.example.minh.animenotification.dialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Window;

import com.example.minh.animenotification.R;
import com.example.minh.animenotification.adapter.MyIconAdapter;
import com.example.minh.animenotification.model.MyIcon;
import com.example.minh.animenotification.myService.ServiceIncomingCall;

import java.util.ArrayList;

public class ChooseIconDialog extends Dialog implements MyIconAdapter.IconListener {
    private Context mContext;
    private ArrayList<MyIcon> mIcons;
    private MyIconAdapter mAdapter;

    public ChooseIconDialog(Context context) {
        super(context);
        mContext = context;
        mIcons = new ArrayList<>();
        mAdapter = new MyIconAdapter(mContext,this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_choose_icon);
        setCanceledOnTouchOutside(false);
        setCancelable(false);
        RecyclerView recycleIcon = findViewById(R.id.recycle_icon);
        recycleIcon.setHasFixedSize(true);
        recycleIcon.setLayoutManager(new GridLayoutManager(mContext, 4));
        recycleIcon.setAdapter(mAdapter);
        getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        super.onCreate(savedInstanceState);
    }

    @Override
    public void chooseIcon(int icon) {
        ServiceIncomingCall.ICON_NAME = icon;
        cancel();
        dismiss();
    }
}
