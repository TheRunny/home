package com.example.dimas.mysooperapp.ui.main;

import com.example.dimas.mysooperapp.R;
import com.example.dimas.mysooperapp.ui.base.BaseFragment;
import com.example.dimas.mysooperapp.ui.inflater.Layout;
import com.example.dimas.mysooperapp.ui.widgets.Tapper;

import butterknife.BindView;
import butterknife.OnClick;

@Layout(id = R.layout.fragment_main)
public class MainFragment extends BaseFragment {

    @BindView(R.id.tapper) Tapper tapper;

    private int count;

    public static MainFragment getInstance() {
        return new MainFragment();
    }

    @Override
    public void onResume() {
        super.onResume();
        tapper.setCount("0");
        tapper.setIcon(getResources().getDrawable(R.drawable.ic_beer));
    }

    @OnClick(R.id.tapper)
    public void onTapperClick() {
        count++;
        tapper.setCount(count);
    }
}
