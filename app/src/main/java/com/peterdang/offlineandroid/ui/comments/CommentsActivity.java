package com.peterdang.offlineandroid.ui.comments;

import android.arch.lifecycle.LifecycleRegistry;
import android.arch.lifecycle.LifecycleRegistryOwner;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.EditText;

import com.peterdang.offlineandroid.BR;
import com.peterdang.offlineandroid.R;
import com.peterdang.offlineandroid.constants.Constants;
import com.peterdang.offlineandroid.domain.services.SyncCommentLifecycleObserver;
import com.peterdang.offlineandroid.utils.KeyboardUtils;

import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import dagger.android.AndroidInjection;

/**
 * Created by phuoc on 2017-12-10.
 */

public class CommentsActivity extends AppCompatActivity implements LifecycleRegistryOwner {

    @Inject
    CommentsViewModelFactory viewModelFactory;

    @Inject
    KeyboardUtils keyboardUtils;

    @Inject
    SyncCommentLifecycleObserver syncCommentLifecycleObserver;

    @BindView(R.id.comments_recycler_view)
    RecyclerView recyclerView;

    private CommentListAdapter recyclerViewAdapter;

    private CommentsViewModel viewModel;

    private LifecycleRegistry registry = new LifecycleRegistry(this);
    private ViewDataBinding mViewDataBinding;

    @Override
    public LifecycleRegistry getLifecycle() {
        return registry;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
        mViewDataBinding = DataBindingUtil.setContentView(this, R.layout.comments_activity);

        ButterKnife.bind(this);

        initRecyclerView();

        getLifecycle().addObserver(syncCommentLifecycleObserver);

        viewModel = ViewModelProviders.of(this, viewModelFactory).get(CommentsViewModel.class);

        viewModel.comments().observe(this, recyclerViewAdapter::updateCommentList);
        viewModel.isKeyboardLiveData().observe(this,
                isKeyboardHideLivaData -> {
                    if (isKeyboardHideLivaData) {
                        keyboardUtils.hideKeyboard(this);
                    }
                });

        mViewDataBinding.setVariable(BR.viewmodel, viewModel);

    }

    private void initRecyclerView() {
        recyclerView.setHasFixedSize(true);

        RecyclerView.LayoutManager recyclerViewLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(recyclerViewLayoutManager);

        recyclerViewAdapter = new CommentListAdapter(new ArrayList<>());
        recyclerView.setAdapter(recyclerViewAdapter);
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
