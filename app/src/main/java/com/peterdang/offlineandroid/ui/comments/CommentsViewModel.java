package com.peterdang.offlineandroid.ui.comments;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.databinding.ObservableField;
import android.view.View;

import com.peterdang.offlineandroid.domain.usecases.AddCommentUseCase;
import com.peterdang.offlineandroid.domain.usecases.GetCommentsUseCase;
import com.peterdang.offlineandroid.models.Comment;
import com.peterdang.offlineandroid.ui.base.BaseViewModel;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import timber.log.Timber;

/**
 * Created by phuoc on 2017-12-10.
 */

public class CommentsViewModel extends BaseViewModel {
    private final GetCommentsUseCase getCommentsUseCase;
    private final AddCommentUseCase addCommentUseCase;

    public ObservableField<String> commentText = new ObservableField<>();

    private MutableLiveData<List<Comment>> commentsLiveData = new MutableLiveData<>();
    private MutableLiveData<Boolean> isKeyboardHideLivaData = new MutableLiveData<>();

    public CommentsViewModel(GetCommentsUseCase getCommentsUseCase, AddCommentUseCase addCommentUseCase) {
        this.getCommentsUseCase = getCommentsUseCase;
        this.addCommentUseCase = addCommentUseCase;

        loadComments();
    }

    private void loadComments() {
        disposables.add(
                getCommentsUseCase.getComments()
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(commentsLiveData::setValue,
                                throwable -> {
                                    Timber.e(throwable, "get comments error");
                                }));
    }


    public void addComment(View view) {
        isKeyboardHideLivaData.setValue(true);
        disposables.add(
                addCommentUseCase.addComment(commentText.get())
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(() -> Timber.d("add comment success"),
                                t -> Timber.e(t, "add comment error")));
    }

    /**
     * Exposes the latest comments so the UI can observe it
     */
    public LiveData<List<Comment>> comments() {
        return commentsLiveData;
    }

    public MutableLiveData<Boolean> isKeyboardLiveData() {
        return isKeyboardHideLivaData;
    }
}

