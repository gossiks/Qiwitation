package org.kazin.qiwitation.main.fragment;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import org.kazin.qiwitation.R;
import org.kazin.qiwitation.main.fragment.misc.DividerItemDecoration;
import org.kazin.qiwitation.main.fragment.misc.RecyclerViewAdapterWithSelection;

/**
 * A placeholder fragment containing a simple view.
 */
public class UsersFragment extends Fragment {

    public RecyclerView mRecyclerView;
    public ProgressBar mProgressBar;

    public UsersFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View convertView = inflater.inflate(R.layout.fragment_users, container, false);
        mRecyclerView = (RecyclerView) convertView.findViewById(R.id.users_fragment_recycler);
        mProgressBar = (ProgressBar) convertView.findViewById(R.id.users_progressbar_fragment);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        mRecyclerView
                .addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL_LIST));

        mRecyclerView.setLayoutManager(layoutManager);

        mProgressBar.setVisibility(View.INVISIBLE);

        return convertView;
    }

    //public methods

}
