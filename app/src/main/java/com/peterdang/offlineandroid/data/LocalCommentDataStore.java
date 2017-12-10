package com.peterdang.offlineandroid.data;

import com.peterdang.offlineandroid.domain.LocalCommentRepository;
import com.peterdang.offlineandroid.models.Comment;
import com.peterdang.offlineandroid.models.CommentUtils;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Single;
import timber.log.Timber;

/**
 * Created by phuoc on 2017-12-10.
 */

public class LocalCommentDataStore implements LocalCommentRepository {
    private CommentDAO commentDAO;

    public LocalCommentDataStore(CommentDAO commentDAO) {
        this.commentDAO = commentDAO;
    }

    @Override
    public Single<Comment> add(long photoId, String commentText) {
        Timber.d("creating comment for photo id %s, comment text %s", photoId, commentText);

        Comment comment = new Comment(photoId, commentText);
        commentDAO.add(comment);
        return Single.fromCallable(() -> {
            long rowId = commentDAO.add(comment);
            Timber.d("comment stored " + rowId);
            return CommentUtils.clone(comment, rowId);
        });
    }

    @Override
    public Completable update(Comment comment) {
        Timber.d("updating comment sync status for comment id %s", comment.getId());

        return Completable.fromAction(() -> {commentDAO.update(comment);});
    }

    @Override
    public Completable delete(Comment comment) {
        Timber.d("deleting comment with id %s", comment.getId());

        return Completable.fromAction(() -> {commentDAO.delete(comment);});
    }

    @Override
    public Flowable<List<Comment>> getComments(long photoId) {
        Timber.d("getting comments for photo id %s", photoId);

        return commentDAO.getComments(photoId);
    }
}
