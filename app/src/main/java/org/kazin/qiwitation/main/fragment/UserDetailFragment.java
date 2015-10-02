package org.kazin.qiwitation.main.fragment;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
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
 * Created by Alexey on 28.09.2015.
 */
public class UserDetailFragment extends Fragment {

    public RecyclerView mRecyclerView;
    public ProgressBar mProgressBar;
    public Button mRefreshButton;
    public Button mRepeatButton;
    public TextView mError;

    private Viewer mViewer;

    public UserDetailFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View convertView =  inflater.inflate(R.layout.fragment_user_detail, container, false);
        mRecyclerView = (RecyclerView) convertView.findViewById(R.id.user_detail_fragment_recycler);
        mProgressBar = (ProgressBar) convertView.findViewById(R.id.users_detail_progressbar_fragment);
        mRefreshButton = (Button) convertView.findViewById(R.id.refresh_balances_fragment_button);
        mRepeatButton = (Button) convertView.findViewById(R.id.repeat_balances_fragment_button);
        mError = (TextView) convertView.findViewById(R.id.error_balances_fragment_button);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        setRecyclerViewDecoration();

        mRecyclerView.setLayoutManager(layoutManager);
        mProgressBar.setVisibility(View.INVISIBLE);

        return convertView;
    }

    @Override
    public void onResume() {
        super.onResume();
        mViewer.onResumeUserDetailFragment();
    }

    public void setViewer(Viewer viewer){
        mViewer = viewer;
    }

    private void setRecyclerViewDecoration() {
        mRecyclerView
                .addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL_LIST));

        mRecyclerView
                .addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL_LIST));
    }
}
