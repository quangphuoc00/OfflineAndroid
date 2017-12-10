package com.peterdang.offlineandroid.data;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.peterdang.offlineandroid.constants.Constants;
import com.peterdang.offlineandroid.models.Comment;

/**
 * Created by phuoc on 2017-12-10.
 */
@Database(entities = {Comment.class}, version = 1, exportSchema = true)
public abstract class CommentDatabase extends RoomDatabase{
    private static CommentDatabase instance;
    public static synchronized CommentDatabase getInstance(Context context){
        if (instance == null) {
            instance = Room
                    .databaseBuilder(context.getApplicationContext(), CommentDatabase.class, Constants.DB_NAME)
                    .allowMainThreadQueries()
                    .build();
        }
        return instance;
    }

    public abstract CommentDAO commentDAO();
}
