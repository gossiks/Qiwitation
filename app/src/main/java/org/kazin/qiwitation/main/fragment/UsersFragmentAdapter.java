package org.kazin.qiwitation.main.fragment;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.romainpiel.shimmer.ShimmerTextView;

import org.kazin.qiwitation.R;
import org.kazin.qiwitation.object.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alexey on 28.09.2015.
 */
public class UsersFragmentAdapter extends RecyclerView.Adapter<UsersFragmentAdapter.ViewHolderUser> {
    private List<User> mUsers;
    private View.OnClickListener itemSelectedListener;

    public UsersFragmentAdapter(List<User> mUsers, View.OnClickListener itemSelectedListener) {
        this.mUsers = mUsers;
        this.itemSelectedListener = itemSelectedListener;
    }

    @Override
    public UsersFragmentAdapter.ViewHolderUser onCreateViewHolder(ViewGroup parent, int viewType) {
        ShimmerTextView v = (ShimmerTextView) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_username_recycler, parent, false);
        return new ViewHolderUser(v, itemSelectedListener);
    }

    @Override
    public void onBindViewHolder(UsersFragmentAdapter.ViewHolderUser holder, int position) {
        holder.usernameView.setText(mUsers.get(position).getName());
        holder.usernameView.setId(position);
    }

    @Override
    public int getItemCount() {
        return mUsers.size();
    }



    //public methods
    public void setUsers(ArrayList<User> users){
        mUsers = users;
    }

    public User getUserByPosition(int position){
        return mUsers.get(position);
    }

    //misc
    class ViewHolderUser extends RecyclerView.ViewHolder{

        public ShimmerTextView usernameView;

        public ViewHolderUser(ShimmerTextView itemView,  View.OnClickListener itemSelectedListener) {
            super(itemView);
            usernameView = itemView;
            usernameView.setOnClickListener(itemSelectedListener);
        }
    }

    public void clearData() {
        int size = mUsers.size();
        if (size > 0) {
            for (int i = 0; i < size; i++) {
                mUsers.remove(0);
            }

            this.notifyItemRangeRemoved(0, size);
        }
    }

}
