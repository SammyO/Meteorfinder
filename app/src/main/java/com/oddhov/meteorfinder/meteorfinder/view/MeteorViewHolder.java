package com.oddhov.meteorfinder.meteorfinder.view;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.oddhov.meteorfinder.R;
import com.oddhov.meteorfinder.data.models.Meteor;
import com.oddhov.meteorfinder.meteor_detail.view.MeteorItemOnClickListener;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by sammy on 15/09/17.
 */

public class MeteorViewHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.meteorName)
    TextView mMeteorName;

    private View mItemView;

    public MeteorViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        this.mItemView = itemView;
    }

    void bind(Meteor meteor, MeteorItemOnClickListener onClickListener) {
        mMeteorName.setText(meteor.getName());
        mItemView.setOnClickListener(view -> onClickListener.onItemClick(meteor.getId()));
    }
}
