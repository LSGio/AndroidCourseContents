package com.example.basiccontentprovider.model;

import android.content.ContentValues;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "dream_table")
public class Dream {

    public static final String DREAM_ID = "id";
    public static final String DREAM_NAME = "name";
    public static final String DREAM_DESCRIPTION = "description";

    @PrimaryKey(autoGenerate = true)
    private Integer id;

    @ColumnInfo(name = DREAM_NAME)
    private String name;

    @ColumnInfo(name = DREAM_DESCRIPTION)
    private String description;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public static Dream fromContentValues(ContentValues mContentValues) {

        Dream mDream = new Dream();

        if(mContentValues.containsKey(DREAM_ID)) {
            mDream.setId(mContentValues.getAsInteger(DREAM_ID));
        }
        if(mContentValues.containsKey(DREAM_NAME)) {
            mDream.setName(mContentValues.getAsString(DREAM_NAME));
        }
        if(mContentValues.containsKey(DREAM_DESCRIPTION)) {
            mDream.setDescription(mContentValues.getAsString(DREAM_DESCRIPTION));
        }

        return mDream;
    }
}
