package com.peterdang.offlineandroid.domain.services.networking;

import com.peterdang.offlineandroid.models.Comment;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

interface RemoteCommentEndpoint {

    @POST("comments")
    Call<Comment> addComment(@Body Comment comment);
}
