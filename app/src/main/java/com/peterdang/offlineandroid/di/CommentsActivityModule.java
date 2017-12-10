package com.peterdang.offlineandroid.di;

import android.app.Activity;

import com.peterdang.offlineandroid.domain.LocalCommentRepository;
import com.peterdang.offlineandroid.domain.RemoteCommentRepository;
import com.peterdang.offlineandroid.domain.services.SyncCommentLifecycleObserver;
import com.peterdang.offlineandroid.domain.usecases.AddCommentUseCase;
import com.peterdang.offlineandroid.domain.usecases.DeleteCommentUseCase;
import com.peterdang.offlineandroid.domain.usecases.GetCommentsUseCase;
import com.peterdang.offlineandroid.domain.usecases.SyncCommentUseCase;
import com.peterdang.offlineandroid.domain.usecases.UpdateCommentUseCase;
import com.peterdang.offlineandroid.ui.comments.CommentsViewModelFactory;
import com.peterdang.offlineandroid.utils.KeyboardUtils;

import dagger.Module;
import dagger.Provides;

/**
 * Define CommentsActivity-specific dependencies here.
 */
@Module
public class CommentsActivityModule {
    @Provides
    KeyboardUtils provideKeyboardUtils(){
        return new KeyboardUtils();
    }

    @Provides
    CommentsViewModelFactory provideCommentsViewModelFactory(GetCommentsUseCase getCommentsUseCase,
                                                             AddCommentUseCase addCommentUseCase) {
        return new CommentsViewModelFactory(getCommentsUseCase, addCommentUseCase);
    }

    @Provides
    SyncCommentLifecycleObserver provideSyncCommentLifecycleObserver(UpdateCommentUseCase updateCommentUseCase,
                                                                     DeleteCommentUseCase deleteCommentUseCase) {
        return new SyncCommentLifecycleObserver(updateCommentUseCase, deleteCommentUseCase);
    }

    @Provides
    AddCommentUseCase provideAddCommentUseCase(LocalCommentRepository localCommentRepository, SyncCommentUseCase syncCommentUseCase) {
        return new AddCommentUseCase(localCommentRepository, syncCommentUseCase);
    }

    @Provides
    GetCommentsUseCase provideGetCommentsUseCase(LocalCommentRepository localCommentRepository) {
        return new GetCommentsUseCase(localCommentRepository);
    }

    @Provides
    UpdateCommentUseCase provideUpdateCommentUseCase(LocalCommentRepository localCommentRepository) {
        return new UpdateCommentUseCase(localCommentRepository);
    }

    @Provides
    DeleteCommentUseCase provideDeleteCommentUseCase(LocalCommentRepository localCommentRepository) {
        return new DeleteCommentUseCase(localCommentRepository);
    }

    @Provides
    SyncCommentUseCase provideSyncCommentUseCase(RemoteCommentRepository remoteCommentRepository) {
        return new SyncCommentUseCase(remoteCommentRepository);
    }

}
