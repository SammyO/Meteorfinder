package com.oddhov.meteorfinder.meteorfinder.view;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.oddhov.meteorfinder.R;
import com.oddhov.meteorfinder.data.models.Meteor;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sammy on 15/09/17.
 */

public class MeteorAdapter extends RecyclerView.Adapter<MeteorViewHolder> {
    private final List<Meteor> mMeteors;

    public MeteorAdapter() {
        this.mMeteors = new ArrayList<>();
    }

    public void setupData(List<Meteor> meteors) {
        mMeteors.clear();
        mMeteors.addAll(meteors);

        notifyDataSetChanged();
    }

    @Override
    public MeteorViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_meteor, parent, false);

        return new MeteorViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MeteorViewHolder holder, int position) {
        holder.bind(mMeteors.get(position));
    }

    @Override
    public int getItemCount() {
        return mMeteors.size();
    }
}
