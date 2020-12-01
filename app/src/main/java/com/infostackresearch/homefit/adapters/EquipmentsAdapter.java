package com.infostackresearch.homefit.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.infostackresearch.homefit.R;
import com.infostackresearch.homefit.models.Equipments;

import java.util.List;

public class EquipmentsAdapter extends RecyclerView.Adapter<EquipmentsAdapter.EquipmentViewHolder> {
    Context mContext;
    List<Equipments> equipments;

    public EquipmentsAdapter(Context mContext, List<Equipments> equipments) {
        this.mContext = mContext;

        this.equipments = equipments;
    }

    @NonNull
    @Override
    public EquipmentsAdapter.EquipmentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new EquipmentsAdapter.EquipmentViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.content_rv_equipment, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull EquipmentsAdapter.EquipmentViewHolder holder, int position) {
        holder.iv_equipment.setImageResource(equipments.get(position).getImageResource());
        holder.tv_equipment.setText(equipments.get(position).getEquipment_name());
    }

    @Override
    public int getItemCount() {
        return equipments.size();
    }

    public class EquipmentViewHolder extends RecyclerView.ViewHolder {
        ImageView iv_equipment;
        TextView tv_equipment;

        public EquipmentViewHolder(@NonNull View itemView) {
            super(itemView);
            iv_equipment = itemView.findViewById(R.id.iv_equipment);
            tv_equipment = itemView.findViewById(R.id.tv_equipment);
        }
    }
}
