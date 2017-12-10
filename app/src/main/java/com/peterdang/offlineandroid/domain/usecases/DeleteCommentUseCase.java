package com.peterdang.offlineandroid.domain.usecases;

import com.peterdang.offlineandroid.domain.LocalCommentRepository;
import com.peterdang.offlineandroid.models.Comment;

import io.reactivex.Completable;

/**
 * Created by phuoc on 2017-12-10.
 */

public class DeleteCommentUseCase {
    private final LocalCommentRepository localCommentRepository;

    public DeleteCommentUseCase(LocalCommentRepository localCommentRepository) {
        this.localCommentRepository = localCommentRepository;
    }

    public Completable deleteComment(Comment comment) {
        return localCommentRepository.delete(comment);
    }
}
