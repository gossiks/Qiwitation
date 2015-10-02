package org.kazin.qiwitation.main.fragment;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import org.kazin.qiwitation.R;
import org.kazin.qiwitation.main.fragment.misc.DividerItemDecoration;
import org.kazin.qiwitation.main.mvp.Viewer;

/**
 * A placeholder fragment containing a simple view.
 */
public class UsersFragment extends Fragment {

    public RecyclerView mRecyclerView;
    public ProgressBar mProgressBar;
    public Button mRefreshButton;
    public TextView mError;
    public Button mRepeatButton;

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
        mRefreshButton = (Button) convertView.findViewById(R.id.users_refresh_fragment_button);
        mError = (TextView) convertView.findViewById(R.id.error_users_fragment);
        mRepeatButton = (Button) convertView.findViewById(R.id.repeat_users_fragment_button);

        mLayoutManager = new LinearLayoutManager(getActivity());
        mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        mRecyclerView
                .addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL_LIST));

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRefreshButton.setOnClickListener(mViewer.getUsersOnRefreshClickListener());

        return convertView;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onResume() {
        super.onResume();
        mViewer.onResumeUserFragment();
    }

    public void setViewer(Viewer viewer){
        mViewer = viewer;
    }



    //public methods

}
