package com.example.dimas.mysooperapp.ui.widgets;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.dimas.mysooperapp.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Tapper extends RelativeLayout {

    @BindView(R.id.icon) ImageView icon;
    @BindView(R.id.counter) TextView counter;

    public Tapper(Context context) {
        super(context);
        init(context, null);
    }

    public Tapper(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public Tapper(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    public void setIcon(final Drawable drawable) {
        icon.setImageDrawable(drawable);
    }

    public void setCount(final String count) {
        counter.setText(count);
    }

    public void setCount(final int count) {
        setCount(String.valueOf(count));
    }

    private void init(Context context, AttributeSet attrs) {
        inflate(context, R.layout.view_tapper, this);
        ButterKnife.bind(this);
    }
}
