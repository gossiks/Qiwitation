package org.kazin.qiwitation.main.fragment;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import org.kazin.qiwitation.R;
import org.kazin.qiwitation.main.fragment.misc.RecyclerViewAdapterWithSelection;
import org.kazin.qiwitation.object.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alexey on 28.09.2015.
 */
public class UsersFragmentAdapter extends RecyclerViewAdapterWithSelection<User,UsersFragmentAdapter.ViewHolderUser> {
    private List<User> mUsers;

    public UsersFragmentAdapter(List<User> mUsers) {
        this.mUsers = mUsers;
    }

    @Override
    public UsersFragmentAdapter.ViewHolderUser onCreateViewHolder(ViewGroup parent, int viewType) {
        TextView v = (TextView) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_username_recycler, parent, false);
        return new ViewHolderUser(v);
    }

    @Override
    public void onBindViewHolder(UsersFragmentAdapter.ViewHolderUser holder, int position) {
        holder.username.setText(mUsers.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return mUsers.size();
    }

    //public methods
    public void setUsers(ArrayList<User> users){
        mUsers = users;
    }

    //misc
    class ViewHolderUser extends RecyclerView.ViewHolder{

        public TextView username;

        public ViewHolderUser(TextView itemView) {
            super(itemView);
            username = itemView;
        }


    }
}
