package com.infostackresearch.homefit.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.infostackresearch.homefit.R;
import com.infostackresearch.homefit.models.SubscriptionData;
import com.infostackresearch.homefit.ui.MySubscriptionActivity;

import java.util.List;

import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;

public class MyPlansAdapter extends RecyclerView.Adapter<MyPlansAdapter.MyPlanViewHolder> {
    Context mContext;
    List<SubscriptionData> subscriptionData;

    public MyPlansAdapter(Context mContext, List<SubscriptionData> subscriptionData) {
        this.mContext = mContext;
        this.subscriptionData = subscriptionData;
    }

    @NonNull
    @Override
    public MyPlanViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.content_myplan, parent, false);
        return new MyPlanViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyPlanViewHolder holder, int position) {
        String id = subscriptionData.get(position).getId();
        String user_id = subscriptionData.get(position).getUser_id();
        String subscription_id = subscriptionData.get(position).getSubscription_id();
        String price = subscriptionData.get(position).getPrice();
        String paymentid = subscriptionData.get(position).getPaymentid();
        String start_date = subscriptionData.get(position).getStart_date();
        String end_date = subscriptionData.get(position).getEnd_date();
        String is_expired = subscriptionData.get(position).getIs_expired();
        String created_at = subscriptionData.get(position).getCreated_at();
        String updated_at = subscriptionData.get(position).getUpdated_at();

        holder.tv_planid.setText(subscriptionData.get(position).getId());
        holder.tv_subscriptionid.setText("Subscription ID - " + subscriptionData.get(position).getSubscription_id());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, MySubscriptionActivity.class);
                intent.putExtra("id", id);
                intent.putExtra("user_id", user_id);
                intent.putExtra("subscription_id", subscription_id);
                intent.putExtra("price", price);
                intent.putExtra("paymentid", paymentid);
                intent.putExtra("start_date", start_date);
                intent.putExtra("end_date", end_date);
                intent.putExtra("is_expired", is_expired);
                intent.putExtra("created_at", created_at);
                intent.putExtra("updated_at", updated_at);
                intent.addFlags(FLAG_ACTIVITY_NEW_TASK);
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return subscriptionData.size();
    }

    public class MyPlanViewHolder extends RecyclerView.ViewHolder {
        TextView tv_planid;
        TextView tv_subscriptionid;

        public MyPlanViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_planid = itemView.findViewById(R.id.tv_planid);
            tv_subscriptionid = itemView.findViewById(R.id.tv_subscriptionid);
        }
    }
}
