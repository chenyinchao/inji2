/*
 * Copyright (c) 2016 Zhang Hai <Dreaming.in.Code.ZH@Gmail.com>
 * All Rights Reserved.
 */

package com.top.android.inji.eventbus;

public class NotificationDeletedEvent extends Event {

    public long notificationId;

    public NotificationDeletedEvent(long notificationId, Object source) {
        super(source);

        this.notificationId = notificationId;
    }
}
