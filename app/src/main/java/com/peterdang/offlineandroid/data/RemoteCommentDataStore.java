package com.peterdang.offlineandroid.data;

import com.birbit.android.jobqueue.JobManager;
import com.peterdang.offlineandroid.domain.LocalCommentRepository;
import com.peterdang.offlineandroid.domain.RemoteCommentRepository;
import com.peterdang.offlineandroid.domain.services.jobs.JobManagerFactory;
import com.peterdang.offlineandroid.domain.services.jobs.SyncCommenteJob;
import com.peterdang.offlineandroid.models.Comment;

import io.reactivex.Completable;

/**
 * Created by phuoc on 2017-12-10.
 */

public class RemoteCommentDataStore implements RemoteCommentRepository {

    @Override
    public Completable sync(Comment comment) {
        return Completable.fromAction(() -> {
            JobManagerFactory.getJobManager().addJobInBackground(new SyncCommenteJob(comment));
        });
    }
}
