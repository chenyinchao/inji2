/*
 * Copyright (c) 2015 Zhang Hai <Dreaming.in.Code.ZH@Gmail.com>
 * All Rights Reserved.
 */

package com.top.android.inji.notification.content;

import android.accounts.Account;
import android.content.Context;
import android.os.Handler;

import com.google.gson.reflect.TypeToken;
import com.top.android.inji.network.api.info.frodo.Notification;
import com.top.android.inji.util.Callback;
import com.top.android.inji.util.DiskCacheHelper;

import java.util.List;

import com.top.android.inji.network.api.info.frodo.Notification;
import com.top.android.inji.util.Callback;
import com.top.android.inji.util.DiskCacheHelper;

public class NotificationListCache {

    private static final int MAX_LIST_SIZE = 20;

    private static final String KEY_PREFIX = NotificationListCache.class.getName();

    public static void get(Account account, Handler handler, Callback<List<Notification>> callback,
                           Context context) {
        DiskCacheHelper.getGson(getKeyForAccount(account), new TypeToken<List<Notification>>() {},
                handler, callback, context);
    }

    public static void put(Account account, List<Notification> notificationList, Context context) {
        if (notificationList.size() > MAX_LIST_SIZE) {
            notificationList = notificationList.subList(0, MAX_LIST_SIZE);
        }
        DiskCacheHelper.putGson(getKeyForAccount(account), notificationList,
                new TypeToken<List<Notification>>() {}, context);
    }

    private static String getKeyForAccount(Account account) {
        return KEY_PREFIX + '@' + account.name;
    }
}
