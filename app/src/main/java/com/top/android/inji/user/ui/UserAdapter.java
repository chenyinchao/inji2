/*
 * Copyright (c) 2015 Zhang Hai <Dreaming.in.Code.ZH@Gmail.com>
 * All Rights Reserved.
 */

package com.top.android.inji.user.ui;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.top.android.inji.network.api.info.frodo.SimpleUser;
import com.top.android.inji.util.ImageUtils;
import com.top.android.inji.util.RecyclerViewUtils;
import com.top.android.inji.util.ViewUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import com.top.android.inji.R;
import com.top.android.inji.network.api.info.frodo.SimpleUser;
import com.top.android.inji.profile.ui.ProfileActivity;
import com.top.android.inji.ui.SimpleAdapter;
import com.top.android.inji.util.ImageUtils;
import com.top.android.inji.util.RecyclerViewUtils;
import com.top.android.inji.util.ViewUtils;

public class UserAdapter extends SimpleAdapter<SimpleUser, UserAdapter.ViewHolder> {

    public UserAdapter() {
        setHasStableIds(true);
    }

    @Override
    public long getItemId(int position) {
        //noinspection deprecation
        return getItem(position).id;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(ViewUtils.inflate(R.layout.user_item, parent));
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        Context context = RecyclerViewUtils.getContext(holder);
        SimpleUser user = getItem(position);
        holder.itemView.setOnClickListener(view -> context.startActivity(ProfileActivity.makeIntent(
                user, context)));
        ImageUtils.loadAvatar(holder.avatarImage, user.avatar);
        holder.nameText.setText(user.name);
        holder.descriptionText.setText(user.getIdOrUid());
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.avatar)
        public ImageView avatarImage;
        @BindView(R.id.name)
        public TextView nameText;
        @BindView(R.id.description)
        public TextView descriptionText;

        public ViewHolder(View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);
        }
    }
}
