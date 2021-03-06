/*
 * Copyright (c) 2017 Zhang Hai <Dreaming.in.Code.ZH@Gmail.com>
 * All Rights Reserved.
 */

package com.top.android.inji.network.api.info.frodo;

import android.os.Parcel;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class SimpleItemForumTopic extends BaseItem {

    public User author;

    @SerializedName("comments_count")
    public int commentCount;

    @SerializedName("create_time")
    public String createdAt;

    public ItemEpisode episode;

    @SerializedName("is_elite")
    public boolean isElite;

    @SerializedName("is_mute")
    public boolean isMute;

    @SerializedName("is_sticky")
    public boolean isSticky;

    @SerializedName("likers_count")
    public int likeCount;

    @SerializedName("publisher_type")
    public int publisherType;

    public ArrayList<Tag> tags = new ArrayList<>();

    @SerializedName("update_time")
    public String updatedAt;


    public static final Creator<SimpleItemForumTopic> CREATOR =
            new Creator<SimpleItemForumTopic>() {
                @Override
                public SimpleItemForumTopic createFromParcel(Parcel source) {
                    return new SimpleItemForumTopic(source);
                }
                @Override
                public SimpleItemForumTopic[] newArray(int size) {
                    return new SimpleItemForumTopic[size];
                }
            };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);

        dest.writeParcelable(author, flags);
        dest.writeInt(commentCount);
        dest.writeString(createdAt);
        dest.writeParcelable(episode, flags);
        dest.writeByte(isElite ? (byte) 1 : (byte) 0);
        dest.writeByte(isMute ? (byte) 1 : (byte) 0);
        dest.writeByte(isSticky ? (byte) 1 : (byte) 0);
        dest.writeInt(likeCount);
        dest.writeInt(publisherType);
        dest.writeTypedList(tags);
        dest.writeString(updatedAt);
    }

    public SimpleItemForumTopic() {}

    protected SimpleItemForumTopic(Parcel in) {
        super(in);

        author = in.readParcelable(User.class.getClassLoader());
        commentCount = in.readInt();
        createdAt = in.readString();
        episode = in.readParcelable(ItemEpisode.class.getClassLoader());
        isElite = in.readByte() != 0;
        isMute = in.readByte() != 0;
        isSticky = in.readByte() != 0;
        likeCount = in.readInt();
        publisherType = in.readInt();
        tags = in.createTypedArrayList(Tag.CREATOR);
        updatedAt = in.readString();
    }


    public static class Tag implements android.os.Parcelable {

        public String id;

        public String title;

        public String uri;


        public static final Creator<Tag> CREATOR = new Creator<Tag>() {
            @Override
            public Tag createFromParcel(Parcel source) {
                return new Tag(source);
            }
            @Override
            public Tag[] newArray(int size) {
                return new Tag[size];
            }
        };

        public Tag() {}

        protected Tag(Parcel in) {
            id = in.readString();
            title = in.readString();
            uri = in.readString();
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(id);
            dest.writeString(title);
            dest.writeString(uri);
        }
    }
}
