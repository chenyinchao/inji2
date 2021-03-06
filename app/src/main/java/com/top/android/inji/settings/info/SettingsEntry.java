/*
 * Copyright (c) 2015 Zhang Hai <Dreaming.in.Code.ZH@Gmail.com>
 * All Rights Reserved.
 */

package com.top.android.inji.settings.info;

import android.support.annotation.StringRes;

import com.top.android.inji.DouyaApplication;
import com.top.android.inji.util.SharedPrefsUtils;

import com.top.android.inji.DouyaApplication;
import com.top.android.inji.util.SharedPrefsUtils;

public abstract class SettingsEntry<T> implements SharedPrefsUtils.Entry<T> {

    private int mKeyResId;
    private int mDefaultValueResId;

    public SettingsEntry(@StringRes int keyResId, int defaultValueResId) {
        mKeyResId = keyResId;
        mDefaultValueResId = defaultValueResId;
    }

    @Override
    public String getKey() {
        return DouyaApplication.getInstance().getString(mKeyResId);
    }

    protected int getDefaultValueResId() {
        return mDefaultValueResId;
    }

    public abstract T getValue();

    public abstract void putValue(T value);

    public void remove() {
        SharedPrefsUtils.remove(this);
    }
}
