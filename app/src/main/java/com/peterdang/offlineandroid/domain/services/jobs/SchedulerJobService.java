package com.peterdang.offlineandroid.domain.services.jobs;

import android.support.annotation.NonNull;

import com.birbit.android.jobqueue.JobManager;
import com.birbit.android.jobqueue.scheduling.FrameworkJobSchedulerService;

/**
 * Created by phuoc on 2017-12-10.
 */

public class SchedulerJobService extends FrameworkJobSchedulerService {
    @NonNull
    @Override
    protected JobManager getJobManager() {
        return JobManagerFactory.getJobManager();
    }
}
