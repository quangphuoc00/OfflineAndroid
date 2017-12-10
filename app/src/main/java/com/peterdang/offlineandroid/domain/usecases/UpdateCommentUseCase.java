package com.peterdang.offlineandroid.domain.usecases;

import com.peterdang.offlineandroid.domain.LocalCommentRepository;
import com.peterdang.offlineandroid.models.Comment;

import io.reactivex.Completable;

/**
 * Created by phuoc on 2017-12-10.
 */

public class UpdateCommentUseCase {
    private final LocalCommentRepository localCommentRepository;

    public UpdateCommentUseCase(LocalCommentRepository localCommentRepository) {
        this.localCommentRepository = localCommentRepository;
    }

    public Completable updateComment(Comment comment){
        return Completable.fromAction(() -> {
            localCommentRepository.update(comment);
        });
    }
}
