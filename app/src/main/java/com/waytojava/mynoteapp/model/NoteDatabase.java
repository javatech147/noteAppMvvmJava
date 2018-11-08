package com.waytojava.mynoteapp.model;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

/**
 * Created by Manish Kumar on 11/8/2018
 */

@Database(entities = Note.class, version = 1)
public abstract class NoteDatabase extends RoomDatabase {
    private static NoteDatabase instance;

    public abstract NoteDAO noteDAO();

    public static synchronized NoteDatabase getInstance(Context context) {
        if (instance == null) {
            Builder<NoteDatabase> builder = Room.databaseBuilder(context.getApplicationContext(),
                    NoteDatabase.class,
                    "note_database");
            // If you increment the version number of this database,
            // we have to tell room how to migrate to new schema.
            // If you don't do this and try to increase the version number our app will be crashed.
            // You will get IllegalStateException.
            // fallBackToDestructiveMigration() method deletes current database
            // along with tables and creates from scratch.
            builder.fallbackToDestructiveMigration();
            instance = builder.build();
        }
        return instance;
    }
}