package com.peterdang.offlineandroid.domain.services;

import com.peterdang.offlineandroid.models.Comment;

/**
 * Created by phuoc on 2017-12-10.
 */

public class SyncCommentResponse {
    public final SyncResponseEventType eventType;
    public final Comment comment;

    public SyncCommentResponse(SyncResponseEventType eventType, Comment comment) {
        this.eventType = eventType;
        this.comment = comment;
    }
}
