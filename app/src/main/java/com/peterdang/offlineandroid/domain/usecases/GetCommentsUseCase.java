package com.peterdang.offlineandroid.domain.usecases;

import com.peterdang.offlineandroid.constants.Constants;
import com.peterdang.offlineandroid.domain.LocalCommentRepository;
import com.peterdang.offlineandroid.models.Comment;

import java.util.List;

import io.reactivex.Flowable;

/**
 * Created by phuoc on 2017-12-10.
 */

public class GetCommentsUseCase {
    private final LocalCommentRepository localCommentRepository;

    public GetCommentsUseCase(LocalCommentRepository localCommentRepository) {
        this.localCommentRepository = localCommentRepository;
    }

    public Flowable<List<Comment>> getComments(){
        return localCommentRepository.getComments(Constants.DUMMY_PHOTO_ID);
    }
}
