package com.infostackresearch.homefit.adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.infostackresearch.homefit.R;
import com.infostackresearch.homefit.models.DeliveryLocation;
import com.infostackresearch.homefit.sessions.UserSessionManager;

import java.util.List;

public class DeliveryLocationAdapter extends RecyclerView.Adapter<DeliveryLocationAdapter.DeliveryLocationViewHolder> {
    Context mContext;
    List<DeliveryLocation> deliveryLocationList;

    public DeliveryLocationAdapter(Context mContext, List<DeliveryLocation> deliveryLocationList) {
        this.mContext = mContext;
        this.deliveryLocationList = deliveryLocationList;
    }

    @NonNull
    @Override
    public DeliveryLocationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new DeliveryLocationViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.content_address, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull DeliveryLocationViewHolder holder, int position) {
        holder.tv_deliverylocation.setText(deliveryLocationList.get(position).getAddress());
        holder.tv_deliverto.setText(deliveryLocationList.get(position).getDeliverto());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UserSessionManager userSessionManager = new UserSessionManager(mContext);
                userSessionManager.createDeliveryAddress(deliveryLocationList.get(position).getAddressid(), deliveryLocationList.get(position).getAddress(), deliveryLocationList.get(position).getDeliverto());
                ((Activity) mContext).finish();
            }
        });
    }

    @Override
    public int getItemCount() {
        return deliveryLocationList.size();
    }


    public class DeliveryLocationViewHolder extends RecyclerView.ViewHolder {
        TextView tv_deliverto, tv_deliverylocation;

        public DeliveryLocationViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_deliverto = itemView.findViewById(R.id.tv_deliverto);
            tv_deliverylocation = itemView.findViewById(R.id.tv_deliverylocation);
        }
    }
}
