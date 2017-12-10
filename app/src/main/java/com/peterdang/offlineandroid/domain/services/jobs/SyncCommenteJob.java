package com.peterdang.offlineandroid.domain.services.jobs;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.birbit.android.jobqueue.Job;
import com.birbit.android.jobqueue.Params;
import com.birbit.android.jobqueue.RetryConstraint;
import com.peterdang.offlineandroid.domain.services.SyncCommentRxBus;
import com.peterdang.offlineandroid.domain.services.SyncResponseEventType;
import com.peterdang.offlineandroid.domain.services.networking.RemoteCommentService;
import com.peterdang.offlineandroid.models.Comment;
import com.peterdang.offlineandroid.models.CommentUtils;

import timber.log.Timber;

/**
 * Created by phuoc on 2017-12-10.
 */

public class SyncCommenteJob extends Job {
    private static final String TAG = SyncCommenteJob.class.getCanonicalName();
    private final Comment comment;

    public SyncCommenteJob(Comment comment) {
        super(new Params(JobPriority.MID)
        .requireNetwork()
        .groupBy(TAG)
        .persist());
        this.comment = comment;
    }

    @Override
    public void onAdded() {
        Timber.d("Executing onAdded() for comment " + comment);
    }

    @Override
    public void onRun() throws Throwable {
        Timber.d("Executing onRun() for comment " + comment);

        // if any exception is thrown, it will be handled by shouldReRunOnThrowable()
        RemoteCommentService.getInstance().addComment(comment);

        // remote call was successful--the Comment will be updated locally to reflect that sync is no longer pending
        Comment updatedComment = CommentUtils.clone(comment, false);
        SyncCommentRxBus.getInstance().post(SyncResponseEventType.SUCCESS, updatedComment);
    }

    @Override
    protected void onCancel(int cancelReason, @Nullable Throwable throwable) {
        Timber.d("canceling job. reason: %d, throwable: %s", cancelReason, throwable);
        // sync to remote failed
        SyncCommentRxBus.getInstance().post(SyncResponseEventType.FAILED, comment);
    }

    @Override
    protected RetryConstraint shouldReRunOnThrowable(@NonNull Throwable throwable, int runCount, int maxRunCount) {
        return null;
    }
}
