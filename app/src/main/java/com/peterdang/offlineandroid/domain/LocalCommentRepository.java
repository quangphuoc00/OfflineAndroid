package com.peterdang.offlineandroid.domain;

import com.peterdang.offlineandroid.models.Comment;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Single;

/**
 * Created by phuoc on 2017-12-10.
 */

public interface LocalCommentRepository {
    Single<Comment> add(long photoId, String commentText);
    Completable update(Comment comment);
    Completable delete(Comment comment);
    Flowable<List<Comment>> getComments(long photoId);
}
