/*
 * Copyright (c) 2016 Zhang Hai <Dreaming.in.Code.ZH@Gmail.com>
 * All Rights Reserved.
 */

package com.top.android.inji.profile.ui;

import android.content.Context;
import android.util.AttributeSet;

import com.top.android.inji.link.UriHandler;
import com.top.android.inji.network.api.info.frodo.CollectableItem;

import com.top.android.inji.link.UriHandler;
import com.top.android.inji.network.api.info.frodo.CollectableItem;

public class ProfileMusicLayout extends ProfileItemsLayout {

    public ProfileMusicLayout(Context context) {
        super(context);
    }

    public ProfileMusicLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ProfileMusicLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected CollectableItem.Type getItemType() {
        return CollectableItem.Type.MUSIC;
    }

    @Override
    protected void onViewPrimaryItems() {
        // FIXME
        UriHandler.open(String.format("https://music.douban.com/people/%s/collect",
                getUserIdOrUid()), getContext());
    }

    @Override
    protected void onViewSecondaryItems() {
        // FIXME
        UriHandler.open(String.format("https://music.douban.com/people/%s/do", getUserIdOrUid()),
                getContext());
    }

    @Override
    protected void onViewTertiaryItems() {
        // FIXME
        UriHandler.open(String.format("https://music.douban.com/people/%s/wish", getUserIdOrUid()),
                getContext());
    }
}
