package com.oddhov.meteorfinder.ui.meteorfinder.view;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.oddhov.meteorfinder.R;
import com.oddhov.meteorfinder.data.models.Meteor;
import com.oddhov.meteorfinder.ui.meteor_detail.view.MeteorItemOnClickListener;
import com.oddhov.meteorfinder.utils.DateUtils;

import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by sammy on 15/09/17.
 */

public class MeteorViewHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.meteorName)
    TextView mMeteorName;
    @BindView(R.id.meteorYearImage)
    TextView mMeteorYear;

    private DateUtils mDateUtils;
    private View mItemView;

    public MeteorViewHolder(View itemView, DateUtils dateUtils) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        this.mItemView = itemView;
        this.mDateUtils = dateUtils;
    }

    void bind(Meteor meteor, MeteorItemOnClickListener onClickListener) {
        mMeteorName.setText(meteor.getName());
        Date date = mDateUtils.getYearFromDateString(meteor.getYear());
        String year = mDateUtils.getYearFromDate(date);
        mMeteorYear.setText(year);
        mItemView.setOnClickListener(view -> onClickListener.onItemClick(meteor.getId()));
    }
}
