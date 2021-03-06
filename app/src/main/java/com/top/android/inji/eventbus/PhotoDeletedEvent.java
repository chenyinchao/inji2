/*
 * Copyright (c) 2017 Zhang Hai <Dreaming.in.Code.ZH@Gmail.com>
 * All Rights Reserved.
 */

package com.top.android.inji.eventbus;

public class PhotoDeletedEvent extends Event {

    public long photoId;

    public PhotoDeletedEvent(long photoId, Object source) {
        super(source);

        this.photoId = photoId;
    }
}
