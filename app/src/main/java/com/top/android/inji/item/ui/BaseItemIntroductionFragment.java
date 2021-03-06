/*
 * Copyright (c) 2018 Zhang Hai <Dreaming.in.Code.ZH@Gmail.com>
 * All Rights Reserved.
 */

package com.top.android.inji.item.ui;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.util.Pair;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.top.android.inji.network.api.info.frodo.Celebrity;
import com.top.android.inji.network.api.info.frodo.CollectableItem;
import com.top.android.inji.util.CollectionUtils;
import com.top.android.inji.util.FragmentUtils;
import com.top.android.inji.util.StringCompat;
import com.top.android.inji.util.TintHelper;
import com.top.android.inji.util.ViewUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import com.top.android.inji.R;
import com.top.android.inji.network.api.info.frodo.Celebrity;
import com.top.android.inji.network.api.info.frodo.CollectableItem;
import com.top.android.inji.ui.AdapterGridLinearLayout;
import com.top.android.inji.util.CollectionUtils;
import com.top.android.inji.util.FragmentUtils;
import com.top.android.inji.util.StringCompat;
import com.top.android.inji.util.TintHelper;
import com.top.android.inji.util.ViewUtils;

public abstract class BaseItemIntroductionFragment<T extends CollectableItem> extends Fragment {

    private static final String KEY_PREFIX = BaseItemIntroductionFragment.class.getName() + '.';

    private static final String EXTRA_ITEM = KEY_PREFIX + "item";

    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.introduction)
    TextView mIntroductionText;
    @BindView(R.id.information)
    AdapterGridLinearLayout mInformationLayout;

    protected T mItem;

    protected void setArguments(T item) {
        Bundle arguments = FragmentUtils.ensureArguments(this);
        arguments.putParcelable(EXTRA_ITEM, item);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle arguments = getArguments();
        mItem = arguments.getParcelable(EXTRA_ITEM);

        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.item_introduction_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ButterKnife.bind(this, view);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        AppCompatActivity activity = (AppCompatActivity) getActivity();
        activity.setSupportActionBar(mToolbar);
        TintHelper.onSetSupportActionBar(mToolbar);
        activity.setTitle(mItem.title);

        mIntroductionText.setText(mItem.getPrettyIntroduction());

        List<Pair<String, String>> informationData = makeInformationData();
        boolean hasInformationData = !informationData.isEmpty();
        ViewUtils.setVisibleOrGone(mInformationLayout, hasInformationData);
        if (hasInformationData) {
            ItemIntroductionPairListAdapter informationAdapter =
                    new ItemIntroductionPairListAdapter();
            informationAdapter.replace(informationData);
            mInformationLayout.setColumnCount(2);
            // HACK: Disabled for looking weird; anyway we always have the space from word break.
            //mInformationLayout.setHorizontalDivider(R.drawable.transparent_divider_vertical_16dp);
            mInformationLayout.setAdapter(informationAdapter);
        }
    }

    protected abstract List<Pair<String, String>> makeInformationData();

    protected void addTextToData(int titleRes, String text, List<Pair<String, String>> data) {
        if (!TextUtils.isEmpty(text)) {
            String title = getString(titleRes);
            data.add(new Pair<>(title, text));
        }
    }

    private void addTextListToData(int titleRes, List<String> textList, String delimiter,
                                   List<Pair<String, String>> data) {
        if (!CollectionUtils.isEmpty(textList)) {
            String title = getString(titleRes);
            String text = StringCompat.join(delimiter, textList);
            data.add(new Pair<>(title, text));
        }
    }

    protected void addTextListToData(int titleRes, List<String> textList,
                                     List<Pair<String, String>> data) {
        addTextListToData(titleRes, textList, getString(R.string.item_information_delimiter), data);
    }

    protected void addCelebrityListToData(int titleRes, List<Celebrity> celebrityList,
                                          List<Pair<String, String>> data) {
        if (!CollectionUtils.isEmpty(celebrityList)) {
            List<String> celebrityNameList = new ArrayList<>();
            for (Celebrity director : celebrityList) {
                celebrityNameList.add(director.name);
            }
            addTextListToData(titleRes, celebrityNameList, getString(
                    R.string.item_introduction_celebrity_delimiter), data);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                getActivity().finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
