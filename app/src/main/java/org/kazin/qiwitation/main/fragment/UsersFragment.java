package org.kazin.qiwitation.main.fragment;

import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;

import org.kazin.qiwitation.R;
import org.kazin.qiwitation.main.fragment.misc.DividerItemDecoration;
import org.kazin.qiwitation.main.mvp.Viewer;
import org.kazin.qiwitation.object.User;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * A placeholder fragment containing a simple view.
 */
public class UsersFragment extends Fragment {

    public RecyclerView mRecyclerView;
    public ProgressBar mProgressBar;
    public Button mRefeshButton;

    private Viewer mViewer;
    private LinearLayoutManager mLayoutManager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View convertView = inflater.inflate(R.layout.fragment_users, container, false);
        mRecyclerView = (RecyclerView) convertView.findViewById(R.id.users_fragment_recycler);
        mProgressBar = (ProgressBar) convertView.findViewById(R.id.users_progressbar_fragment);
        mRefeshButton = (Button) convertView.findViewById(R.id.users_refresh_fragment_button);

        mLayoutManager = new LinearLayoutManager(getActivity());
        mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        mRecyclerView
                .addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL_LIST));
        //mRecyclerView.setAdapter(new UsersFragmentAdapter(new ArrayList<User>(), mViewer.getUserOnClickListener()));

        mRecyclerView.setLayoutManager(mLayoutManager);
        mProgressBar.setVisibility(View.INVISIBLE);
        mRefeshButton.setOnClickListener(mViewer.getUsersOnRefreshClickListener());

        return convertView;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        Parcelable listtate = mLayoutManager.onSaveInstanceState();
        super.onSaveInstanceState(outState);

    }

    @Override
    public void onResume() {
        super.onResume();
        mViewer.onResume();
    }

    public void setViewer(Viewer viewer){
        mViewer = viewer;
    }



    //public methods

}
