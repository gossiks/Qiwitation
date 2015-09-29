package org.kazin.qiwitation.main.fragment;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.kazin.qiwitation.R;
import org.kazin.qiwitation.object.Balance;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alexey on 29.09.2015.
 */
public class UserDetailFragmentAdapter extends RecyclerView.Adapter<UserDetailFragmentAdapter.ViewHolderBalance> {

    private List<Balance> mBalances;

    public UserDetailFragmentAdapter(List<Balance> mBalances) {
        this.mBalances = mBalances;
    }

    @Override
    public ViewHolderBalance onCreateViewHolder(ViewGroup parent, int i) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_userbalance_recycler, parent, false);
        return new ViewHolderBalance(v);
    }

    @Override
    public void onBindViewHolder(ViewHolderBalance holder, int position) {
        holder.amount.setText(
                Double.toString(mBalances.get(position).getAmount()));

        holder.currency.setText(
                mBalances.get(position).getCurrency());
    }


    @Override
    public int getItemCount() {
        return mBalances.size();
    }

    //misc
    class ViewHolderBalance extends RecyclerView.ViewHolder{

        public TextView currency;
        public TextView amount;

        public ViewHolderBalance(View itemView) {
            super(itemView);
            currency = (TextView) itemView.findViewById(R.id.currency_item_userbalance_recycler);
            amount = (TextView) itemView.findViewById(R.id.amount_item_userbalance_recycler);
        }
    }
}
