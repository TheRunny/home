package com.example.dimas.mysooperapp.ui.main;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.example.dimas.mysooperapp.R;
import com.example.dimas.mysooperapp.ui.base.BaseActivity;
import com.example.dimas.mysooperapp.ui.inflater.Layout;

@Layout(id = R.layout.activity_base)
public class MainActivity extends BaseActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initUi();
    }

    private void initUi() {
        addFragment(MainFragment.getInstance());
    }
}
