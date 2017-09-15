package com.oddhov.meteorfinder.meteorfinder.view;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.oddhov.meteorfinder.R;
import com.oddhov.meteorfinder.data.models.Meteor;
import com.oddhov.meteorfinder.meteor_detail.view.MeteorItemOnClickListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sammy on 15/09/17.
 */

public class MeteorAdapter extends RecyclerView.Adapter<MeteorViewHolder> {
    private final List<Meteor> mMeteors;
    private MeteorItemOnClickListener mMeteorItemOnClickListener;

    public MeteorAdapter() {
        this.mMeteors = new ArrayList<>();
    }

    public void setData(List<Meteor> meteors) {
        this.mMeteors.clear();
        this.mMeteors.addAll(meteors);

        notifyDataSetChanged();
    }

    public void setMeteorItemOnClickListener(MeteorItemOnClickListener meteorItemOnClickListener) {
        this.mMeteorItemOnClickListener = meteorItemOnClickListener;
    }

    @Override
    public MeteorViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_meteor, parent, false);

        return new MeteorViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MeteorViewHolder holder, int position) {
        holder.bind(mMeteors.get(position), mMeteorItemOnClickListener);
    }

    @Override
    public int getItemCount() {
        return mMeteors.size();
    }
}
