/*
 * Copyright (c) 2016 Zhang Hai <Dreaming.in.Code.ZH@Gmail.com>
 * All Rights Reserved.
 */

package com.top.android.inji.content;

import android.os.Bundle;

import com.top.android.inji.network.RequestFragment;
import com.top.android.inji.network.api.ApiError;
import com.top.android.inji.network.api.ApiRequest;

import org.greenrobot.eventbus.Subscribe;

import com.top.android.inji.eventbus.EventBusUtils;
import com.top.android.inji.eventbus.PreventNoSubscriptionExceptionEvent;
import com.top.android.inji.network.RequestFragment;
import com.top.android.inji.network.api.ApiRequest;
import com.top.android.inji.network.api.ApiError;

public abstract class ResourceFragment<ResponseType, ResourceType>
        extends RequestFragment<Void, ResponseType> {

    ResourceType mResource;

    public boolean has() {
        return get() != null;
    }

    public ResourceType get() {
        return mResource;
    }

    protected void set(ResourceType resource) {
        mResource = resource;
    }

    @Override
    public final boolean isRequesting() {
        throw new UnsupportedOperationException("Use isLoading() instead");
    }

    public boolean isLoading() {
        return super.isRequesting();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        EventBusUtils.register(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        EventBusUtils.unregister(this);
    }

    @Override
    public void onStart() {
        super.onStart();

        loadOnStart();
    }

    protected void loadOnStart() {
        if (!has()) {
            onLoadOnStart();
        }
    }

    protected void onLoadOnStart() {
        load();
    }

    public void load() {
        start(null);
    }

    @Override
    protected final ApiRequest<ResponseType> onCreateRequest(Void requestState) {
        return onCreateRequest();
    }

    protected abstract ApiRequest<ResponseType> onCreateRequest();

    @Override
    protected final void onRequestStarted() {
        onLoadStarted();
    }

    protected abstract void onLoadStarted();

    @Override
    protected void onRequestFinished(boolean successful, Void requestState, ResponseType response,
                                     ApiError error) {
        onLoadFinished(successful, response, error);
    }

    protected abstract void onLoadFinished(boolean successful, ResponseType response,
                                           ApiError error);

    @Subscribe
    public final void onPreventNoSubscriptionException(PreventNoSubscriptionExceptionEvent event) {}
}
