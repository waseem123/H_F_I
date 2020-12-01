package com.infostackresearch.homefit.adapters.VpAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.infostackresearch.homefit.R;
import com.infostackresearch.homefit.models.Ads.Ads;

import java.util.List;

public class VpAdapter extends RecyclerView.Adapter<VpAdapter.VpHolder> {
    Context mContext;
    List<Ads> ads;

    public VpAdapter(Context mContext, List<Ads> ads) {
        this.mContext = mContext;
        this.ads = ads;
    }

    @NonNull
    @Override
    public VpHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.content_adviewpager, parent, false);
        return new VpHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VpHolder holder, int position) {
        holder.iv_vpad.setImageResource(ads.get(position).getImageID());
        holder.tv_vptext.setText(ads.get(position).getText());
    }

    @Override
    public int getItemCount() {
        return ads.size();
    }

    public class VpHolder extends RecyclerView.ViewHolder {
        ImageView iv_vpad;
        TextView tv_vptext;

        public VpHolder(@NonNull View itemView) {
            super(itemView);
            iv_vpad = itemView.findViewById(R.id.iv_vpad);
            tv_vptext = itemView.findViewById(R.id.tv_vptext);
        }
    }
}
