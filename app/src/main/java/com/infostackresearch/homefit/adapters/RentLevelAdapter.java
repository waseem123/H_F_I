package com.infostackresearch.homefit.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.infostackresearch.homefit.ui.EquipmentsActivity;
import com.infostackresearch.homefit.R;
import com.infostackresearch.homefit.models.RentLevels;

import java.util.List;

public class RentLevelAdapter extends RecyclerView.Adapter<RentLevelAdapter.RentLevelViewHolder> {
    Context mContext;
    List<RentLevels> levels;

    public RentLevelAdapter(Context mContext, List<RentLevels> levels) {
        this.mContext = mContext;
        this.levels = levels;
    }

    @NonNull
    @Override
    public RentLevelViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new RentLevelViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.content_rv_level, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RentLevelViewHolder holder, final int position) {
        holder.iv_level.setImageResource(levels.get(position).getImageResource());
        holder.tv_level.setText(levels.get(position).getLevelName());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, EquipmentsActivity.class);
                intent.putExtra("level", levels.get(position).getLevelName());
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return levels.size();
    }

    public class RentLevelViewHolder extends RecyclerView.ViewHolder {
        ImageView iv_level;
        TextView tv_level;

        public RentLevelViewHolder(@NonNull View itemView) {
            super(itemView);
            iv_level = itemView.findViewById(R.id.iv_level);
            tv_level = itemView.findViewById(R.id.tv_level);
        }
    }
}
