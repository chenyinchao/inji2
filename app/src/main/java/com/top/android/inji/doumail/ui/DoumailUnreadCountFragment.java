/*
 * Copyright (c) 2018 Zhang Hai <Dreaming.in.Code.ZH@Gmail.com>
 * All Rights Reserved.
 */

package com.top.android.inji.doumail.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import com.top.android.inji.doumail.content.NotificationCountResource;
import com.top.android.inji.network.api.ApiError;
import com.top.android.inji.network.api.info.frodo.NotificationCount;

import com.top.android.inji.doumail.content.NotificationCountResource;
import com.top.android.inji.main.ui.MainActivity;
import com.top.android.inji.network.api.ApiError;
import com.top.android.inji.network.api.info.frodo.NotificationCount;
import com.top.android.inji.util.LogUtils;

public class DoumailUnreadCountFragment extends Fragment
        implements NotificationCountResource.Listener {

    private NotificationCountResource mNotificationCountResource;

    public static DoumailUnreadCountFragment newInstance() {
        //noinspection deprecation
        return new DoumailUnreadCountFragment();
    }

    /**
     * @deprecated Use {@link #newInstance()} instead.
     */
    public DoumailUnreadCountFragment() {}

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mNotificationCountResource = NotificationCountResource.attachTo(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        mNotificationCountResource.detach();
    }

    @Override
    public void onLoadNotificationCountStarted(int requestCode) {}

    @Override
    public void onLoadNotificationCountFinished(int requestCode) {}

    @Override
    public void onLoadNotificationCountError(int requestCode, ApiError error) {
        LogUtils.e(error.toString());
    }

    @Override
    public void onNotificationCountChanged(int requestCode,
                                           NotificationCount newNotificationCount) {
        MainActivity activity = (MainActivity) getActivity();
        if (activity != null) {
            activity.onDoumailUnreadCountUpdate(getUnreadCount());
        }
    }

    public int getUnreadCount() {
        if (!mNotificationCountResource.has()) {
            return 0;
        }
        return mNotificationCountResource.get().doumail.count;
    }

    public void refresh() {
        mNotificationCountResource.load();
    }
}
