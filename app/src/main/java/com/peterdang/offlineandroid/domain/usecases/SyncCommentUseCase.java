package com.peterdang.offlineandroid.domain.usecases;

import com.peterdang.offlineandroid.domain.RemoteCommentRepository;
import com.peterdang.offlineandroid.models.Comment;

import io.reactivex.Completable;

/**
 * Responsible for syncing a comment with remote repository.
 */
public class SyncCommentUseCase {
    private final RemoteCommentRepository remoteCommentRepository;

    public SyncCommentUseCase(RemoteCommentRepository remoteCommentRepository) {
        this.remoteCommentRepository = remoteCommentRepository;
    }

    public Completable syncComment(Comment comment) {
        return remoteCommentRepository.sync(comment);
    }
}
