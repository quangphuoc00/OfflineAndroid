package com.peterdang.offlineandroid.domain;

import com.peterdang.offlineandroid.models.Comment;

import io.reactivex.Completable;

/**
 * Created by phuoc on 2017-12-10.
 */

public interface RemoteCommentRepository {
    Completable sync(Comment comment);
}
