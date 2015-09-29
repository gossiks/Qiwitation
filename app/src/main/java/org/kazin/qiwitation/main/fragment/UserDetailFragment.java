package org.kazin.qiwitation.main.fragment;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.kazin.qiwitation.R;
import org.kazin.qiwitation.main.fragment.misc.DividerItemDecoration;

/**
 * Created by Alexey on 28.09.2015.
 */
public class UserDetailFragment extends Fragment {

    public RecyclerView mRecyclerView;

    public UserDetailFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View convertView =  inflater.inflate(R.layout.fragment_user_detail, container, false);
        mRecyclerView = (RecyclerView) convertView.findViewById(R.id.user_detail_fragment_recycler);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        setRecyclerViewDecoration();

        mRecyclerView.setLayoutManager(layoutManager);

        return convertView;
    }

    private void setRecyclerViewDecoration() {
        mRecyclerView
                .addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL_LIST));

        mRecyclerView
                .addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL_LIST));
    }
}
