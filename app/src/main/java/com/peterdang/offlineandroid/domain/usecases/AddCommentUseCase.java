package com.peterdang.offlineandroid.domain.usecases;

import com.peterdang.offlineandroid.constants.Constants;
import com.peterdang.offlineandroid.domain.LocalCommentRepository;
import com.peterdang.offlineandroid.domain.RemoteCommentRepository;

import io.reactivex.Completable;

/**
 * Created by phuoc on 2017-12-10.
 */

public class AddCommentUseCase {
    private final LocalCommentRepository localCommentRepository;
    private final SyncCommentUseCase remoteCommentRepository;

    public AddCommentUseCase(LocalCommentRepository localCommentRepository, SyncCommentUseCase remoteCommentRepository) {
        this.localCommentRepository = localCommentRepository;
        this.remoteCommentRepository = remoteCommentRepository;
    }

    public Completable addComment(String commentText){
        return localCommentRepository.add(Constants.DUMMY_PHOTO_ID, commentText)
                .flatMapCompletable(remoteCommentRepository::syncComment);
    }
}
