/*
 * Copyright (c) 2017 Zhang Hai <Dreaming.in.Code.ZH@Gmail.com>
 * All Rights Reserved.
 */

package com.top.android.inji.ui;

import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.preference.DialogPreference;

import com.top.android.inji.util.FragmentUtils;

import com.top.android.inji.util.FragmentUtils;

public class RingtonePreferenceActivityFragmentCompat extends Fragment {

    // @see PreferenceDialogFragmentCompat#ARG_KEY
    private static final String ARGUMENT_KEY = "key";

    private static final int REQUEST_CODE_PICKER = 1;

    private RingtonePreference mPreference;

    private boolean mShouldStartPicker;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle arguments = getArguments();
        String preferenceKey = arguments.getString(ARGUMENT_KEY);

        Fragment fragment = getTargetFragment();
        if (!(fragment instanceof DialogPreference.TargetFragment)) {
            throw new IllegalStateException("Target fragment must implement TargetFragment" +
                    " interface");
        }
        DialogPreference.TargetFragment targetFragment = (DialogPreference.TargetFragment) fragment;
        if (savedInstanceState == null) {
            mPreference = (RingtonePreference) targetFragment.findPreference(preferenceKey);
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        if (savedInstanceState == null) {
            mShouldStartPicker = true;
        }
    }

    @Override
    public void onStart() {
        super.onStart();

        if (mShouldStartPicker) {
            startActivityForResult(mPreference.makeRingtonePickerIntent(), REQUEST_CODE_PICKER);
            mShouldStartPicker = false;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE_PICKER) {
            if (data != null) {
                Uri uri = data.getParcelableExtra(RingtoneManager.EXTRA_RINGTONE_PICKED_URI);
                if (mPreference.callChangeListener(uri)) {
                    mPreference.setRingtoneUri(uri);
                }
            }
            FragmentUtils.remove(this);
        }
    }
}
