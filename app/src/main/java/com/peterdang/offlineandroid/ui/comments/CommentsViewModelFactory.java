package com.peterdang.offlineandroid.ui.comments;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import com.peterdang.offlineandroid.domain.usecases.AddCommentUseCase;
import com.peterdang.offlineandroid.domain.usecases.GetCommentsUseCase;

/**
 * Created by phuoc on 2017-12-10.
 */

public class CommentsViewModelFactory implements ViewModelProvider.Factory {
    private final GetCommentsUseCase getCommentsUseCase;
    private final AddCommentUseCase addCommentUseCase;

    public CommentsViewModelFactory(GetCommentsUseCase getCommentsUseCase, AddCommentUseCase addCommentUseCase) {
        this.getCommentsUseCase = getCommentsUseCase;
        this.addCommentUseCase = addCommentUseCase;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if(modelClass.isAssignableFrom(CommentsViewModel.class)){
            return (T) new CommentsViewModel(getCommentsUseCase, addCommentUseCase);
        }
        throw new IllegalArgumentException("Unknown ViewModel");
    }
}
