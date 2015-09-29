package org.kazin.qiwitation.main.fragment;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.kazin.qiwitation.R;
import org.kazin.qiwitation.main.fragment.misc.DividerItemDecoration;
import org.kazin.qiwitation.object.User;

import java.util.ArrayList;

/**
 * A placeholder fragment containing a simple view.
 */
public class UsersFragment extends Fragment {

    public RecyclerView mRecyclerView;

    public UsersFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View convertView = inflater.inflate(R.layout.fragment_users, container, false);
        mRecyclerView = (RecyclerView) convertView.findViewById(R.id.users_fragment_recycler);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        mRecyclerView
                .addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL_LIST));

        mRecyclerView.setLayoutManager(layoutManager);

        return convertView;
    }

    //public methods

}
