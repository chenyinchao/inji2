/*
 * Copyright (c) 2016 Zhang Hai <Dreaming.in.Code.ZH@Gmail.com>
 * All Rights Reserved.
 */

package com.top.android.inji.eventbus;

import com.top.android.inji.network.api.info.frodo.SimpleReview;

import com.top.android.inji.network.api.info.frodo.SimpleReview;

public class ReviewUpdatedEvent extends Event {

    public SimpleReview review;

    public ReviewUpdatedEvent(SimpleReview review, Object source) {
        super(source);

        this.review = review;
    }
}
