/*
 * Copyright (c) 2017 Zhang Hai <Dreaming.in.Code.ZH@Gmail.com>
 * All Rights Reserved.
 */

package com.top.android.inji.ui;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

public class NestedRecyclerView extends RecyclerView {

    public NestedRecyclerView(Context context) {
        super(context);

        init();
    }

    public NestedRecyclerView(Context context, AttributeSet attrs) {
        super(context, attrs);

        init();
    }

    public NestedRecyclerView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);

        init();
    }

    private void init() {
        setFocusableInTouchMode(false);
    }
}
