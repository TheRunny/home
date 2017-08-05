package com.example.dimas.mysooperapp.ui.base;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import com.example.dimas.mysooperapp.R;
import com.example.dimas.mysooperapp.ui.utils.AppUtils;

import butterknife.BindView;
import rx.subscriptions.CompositeSubscription;

public abstract class BaseActivity extends AppCompatActivity {

    private CompositeSubscription compositeSubscription;

    @Nullable
    @BindView(R.id.main_progress_bar) View progressBar;

    @Override
    protected void onCreate(@Nullable final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(AppUtils.getLayoutId(this));
        compositeSubscription = new CompositeSubscription();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (compositeSubscription != null) {
            compositeSubscription.unsubscribe();
        }
    }

    public void showKeyboard() {
        final View view = getCurrentFocus();
        if (view != null) {
            final InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.showSoftInput(view, InputMethodManager.SHOW_IMPLICIT);
        }
    }

    public void hideKeyboard() {
        final View view = getCurrentFocus();
        if (view != null) {
            final InputMethodManager imm = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    public void addFragment(final Fragment fragment) {
        final String tag = ((Object) fragment).getClass().getSimpleName();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, fragment, tag)
                .addToBackStack(tag)
                .commit();
    }

    public void showFragment(final Fragment fragment) {
        final FragmentManager fragmentManager = getSupportFragmentManager();
        for (int i = 0; i < fragmentManager.getBackStackEntryCount(); i++) {
            fragmentManager.popBackStack();
        }
        final String tag = ((Object) fragment).getClass().getSimpleName();
        fragmentManager.beginTransaction()
                .replace(R.id.container, fragment, tag)
                .addToBackStack(tag)
                .commit();
    }

    public void showOverTailFragment(final Fragment fragment) {
        final FragmentManager fragmentManager = getSupportFragmentManager();
        for (int i = 0; i < fragmentManager.getBackStackEntryCount() - 1; i++) {
            fragmentManager.popBackStack();
        }
        final String tag = ((Object) fragment).getClass().getSimpleName();
        fragmentManager.beginTransaction()
                .replace(R.id.container, fragment, tag)
                .addToBackStack(tag)
                .commit();
    }

    public void showTailFragment() {
        final FragmentManager fragmentManager = getSupportFragmentManager();
        while (fragmentManager.getBackStackEntryCount() > 1) {
            fragmentManager.popBackStackImmediate();
        }
    }

    public void replaceTopFragment(final Fragment fragment) {
        final FragmentManager fragmentManager = getSupportFragmentManager();
        if (fragmentManager.getBackStackEntryCount() != 0) {
            fragmentManager.popBackStack();
        }

        final String tag = ((Object) fragment).getClass().getSimpleName();
        fragmentManager.beginTransaction()
                .replace(R.id.container, fragment, tag)
                .addToBackStack(tag)
                .commit();

        fragmentManager.executePendingTransactions();
    }

    public boolean rollbackToFragment(final Class clazz) {
        final FragmentManager fragmentManager = getSupportFragmentManager();
        final int count = fragmentManager.getBackStackEntryCount();
        int depth = -1;
        for (int i = count - 1; i >= 0; i--) { //from top to bottom
            final FragmentManager.BackStackEntry entry = fragmentManager.getBackStackEntryAt(i);
            if (entry.getName().equals(clazz.getSimpleName())) {
                depth = count - i - 1;
                break;
            }
        }
        if (depth < 0) {
            return false;
        }
        for (int i = 0; i < depth; i++) {
            fragmentManager.popBackStack();
        }
        return true;
    }

    public void addFragment(final Fragment fragment, final int enterAnimation, final int exitAnimation) {
        final String tag = ((Object) fragment).getClass().getSimpleName();
        getSupportFragmentManager().beginTransaction()
                .setCustomAnimations(enterAnimation, exitAnimation, enterAnimation, exitAnimation)
                .replace(R.id.container, fragment, tag)
                .addToBackStack(tag)
                .commitAllowingStateLoss();
    }

    public void closeFragment() {
        final FragmentManager fragmentManager = getSupportFragmentManager();
        if (fragmentManager.getBackStackEntryCount() == 1) {
            finish();
        } else if (fragmentManager.getBackStackEntryCount() != 0) {
            fragmentManager.popBackStack();
        }
    }

    public void showProgressBar() {
        if (progressBar != null) {
            progressBar.setVisibility(View.VISIBLE);
        }
    }

    public void hideProgressBar() {
        if (progressBar != null) {
            progressBar.setVisibility(View.GONE);
        }
    }
}
