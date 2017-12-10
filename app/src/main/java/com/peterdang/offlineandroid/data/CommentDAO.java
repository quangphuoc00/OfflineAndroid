package com.peterdang.offlineandroid.data;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.peterdang.offlineandroid.models.Comment;

import java.util.List;

import io.reactivex.Flowable;

/**
 * Created by phuoc on 2017-12-10.
 */
@Dao
public interface CommentDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long add(Comment comment);

    @Update
    void update(Comment comment);

    @Delete
    void delete(Comment comment);

    @Query("Select * from comment where photo_id = :photoId Order by timestamp Desc")
    Flowable<List<Comment>> getComments(long photoId);
}
