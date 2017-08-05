package com.example.dimas.mysooperapp.ui.base;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.dimas.mysooperapp.R;
import com.example.dimas.mysooperapp.ui.utils.AppUtils;

import butterknife.BindView;

public abstract class BaseFragment extends Fragment {

    @Nullable
    @BindView(R.id.main_progress_bar)
    View progressBar;

    private Toast testToast;

    @Override
    public void onPause() {
        hideKeyboard();
        super.onPause();
    }

    @Nullable
    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container, final Bundle savedInstanceState) {
        Log.d("DEBUG", "DEBUG 15356454");
        return inflater.inflate(AppUtils.getLayoutId(this), container, false);
    }

    protected Integer getHeaderTitleId() {
        return null;
    }

    protected void showKeyboard() {
        final BaseActivity activity = getBaseActivity();
        if (activity != null) {
            activity.showKeyboard();
        }
    }

    protected void hideKeyboard() {
        final BaseActivity activity = getBaseActivity();
        if (activity != null) {
            activity.hideKeyboard();
        }
    }

    protected void showProgressBar() {
        showProgressBar(false);
    }

    protected void showProgressBar(boolean fromActivity) {
        if (fromActivity) {
            final BaseActivity activity = getBaseActivity();
            if (activity != null) {
                activity.showProgressBar();
            }
        } else if (progressBar != null) {
            progressBar.setVisibility(View.VISIBLE);
        }
    }

    protected void hideProgressBar() {
        hideProgressBar(false);
    }

    protected void hideProgressBar(boolean fromActivity) {
        if (fromActivity) {
            final BaseActivity activity = getBaseActivity();
            if (activity != null) {
                activity.hideProgressBar();
            }
        } else if (progressBar != null) {
            progressBar.setVisibility(View.GONE);
        }
    }

    private BaseActivity getBaseActivity() {
        return (BaseActivity) getActivity();
    }

    public void closeFragment() {
        hideKeyboard();
        hideProgressBar(true);
        View view = getView();

        if (view != null) {
            popBackStack();
        }
    }

    public void addFragment(final Fragment fragment) {
        final BaseActivity activity = getBaseActivity();
        if (activity != null) {
            activity.addFragment(fragment);
        }
    }

    public void replaceTopFragment(final Fragment fragment) {
        final BaseActivity activity = getBaseActivity();
        if (activity != null) {
            activity.replaceTopFragment(fragment);
        }
    }

    public void showFragment(final Fragment fragment) {
        final BaseActivity activity = getBaseActivity();
        if (activity != null) {
            activity.showFragment(fragment);
        }
    }

    public void showFragment(final Fragment fragment, @IdRes final int containerId,
                             final int enterAnimation, final int exitAnimation) {
        final FragmentManager fragmentManager = getChildFragmentManager();
        for (int i = 0; i < fragmentManager.getBackStackEntryCount(); i++) {
            fragmentManager.popBackStack();
        }
        final String tag = ((Object) fragment).getClass().getSimpleName();
        fragmentManager.beginTransaction()
                .setCustomAnimations(enterAnimation, exitAnimation)
                .replace(containerId, fragment, tag)
                .addToBackStack(tag)
                .commit();
    }

    public void showFragment(final Fragment fragment, @IdRes final int containerId) {
        final FragmentManager fragmentManager = getChildFragmentManager();
        for (int i = 0; i < fragmentManager.getBackStackEntryCount(); i++) {
            fragmentManager.popBackStack();
        }
        final String tag = ((Object) fragment).getClass().getSimpleName();
        fragmentManager.beginTransaction()
                .replace(containerId, fragment, tag)
                .addToBackStack(tag)
                .commit();
    }

    public void showTailFragment() {
        final BaseActivity activity = getBaseActivity();
        if (activity != null) {
            activity.showTailFragment();
        }
    }

    public void showOverTailFragment(final Fragment fragment) {
        final BaseActivity activity = getBaseActivity();
        if (activity != null) {
            activity.showOverTailFragment(fragment);
        }
    }

    public void addFragment(final Fragment fragment, final int enterAnimation, final int exitAnimation) {
        final BaseActivity activity = getBaseActivity();
        if (activity != null) {
            activity.addFragment(fragment, enterAnimation, exitAnimation);
        }
    }

    public boolean rollbackToFragment(final Class clazz) {
        final BaseActivity activity = getBaseActivity();
        return activity != null && activity.rollbackToFragment(clazz);
    }

    protected void showTestToast(final String text) {
        if (testToast != null) {
            testToast.cancel();
        }
        testToast = Toast.makeText(getContext(), text, Toast.LENGTH_SHORT);
        testToast.show();
    }

    private void popBackStack() {
        final BaseActivity activity = getBaseActivity();
        if (activity != null) {
            activity.closeFragment();
        }
    }
}
