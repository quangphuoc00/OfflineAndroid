package com.peterdang.offlineandroid.di;

import android.content.Context;

import com.peterdang.offlineandroid.App;
import com.peterdang.offlineandroid.data.CommentDAO;
import com.peterdang.offlineandroid.data.CommentDatabase;
import com.peterdang.offlineandroid.data.LocalCommentDataStore;
import com.peterdang.offlineandroid.data.RemoteCommentDataStore;
import com.peterdang.offlineandroid.domain.LocalCommentRepository;
import com.peterdang.offlineandroid.domain.RemoteCommentRepository;
import com.peterdang.offlineandroid.domain.services.jobs.SchedulerJobService;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by phuoc on 2017-12-10.
 */
@Module
public class AppModule {
    @Provides
    Context provideContext(App app){
        return app.getApplicationContext();
    }

    @Singleton
    @Provides
    SchedulerJobService provideSchedulerJobService() {
        return new SchedulerJobService();
    }

    @Singleton
    @Provides
    CommentDAO provideCommentDao(Context context) {
        return CommentDatabase.getInstance(context).commentDAO();
    }

    @Singleton
    @Provides
    LocalCommentRepository provideLocalCommentRepository(CommentDAO commentDao) {
        return new LocalCommentDataStore(commentDao);
    }

    @Singleton
    @Provides
    RemoteCommentRepository provideRemoteCommentRepository() {
        return new RemoteCommentDataStore();
    }
}
