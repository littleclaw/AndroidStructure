package com.ycy.ui.guide;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;

import com.ycy.ui.R;

public final class GuideFragment extends Fragment {
    private SearchView mSearchView;
    private RecyclerView mRecyclerView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_guide, container, false);
        mSearchView = v.findViewById(R.id.guide_search_view);
        mRecyclerView = v.findViewById(R.id.guide_recycler_view);
        mSearchView.setSubmitButtonEnabled(false);
        return v;
    }
}
