package com.infostackresearch.homefit.adapters;


import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.infostackresearch.homefit.R;
import com.infostackresearch.homefit.models.Plans;
import com.infostackresearch.homefit.sessions.UserSessionManager;

import java.util.List;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class ViewpagerAdapter extends RecyclerView.Adapter<ViewpagerAdapter.PlanHolder> {

    private boolean upgrade;
    private List<Plans> plansList;
    private Context mContext;

    public ViewpagerAdapter(Context mContext, List<Plans> plansList) {
        this.mContext = mContext;
        this.plansList = plansList;
    }

    public ViewpagerAdapter(Context mContext, List<Plans> plansList, boolean upgrade) {
        this.mContext = mContext;
        this.plansList = plansList;
        this.upgrade = upgrade;
    }

    @NonNull
    @Override
    public PlanHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.content_plan, parent, false);
        return new PlanHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull PlanHolder holder, int position) {
        holder.tv_title.setText(plansList.get(position).getTitle());
        if (null != plansList.get(position).getSubtitle() && !plansList.get(position).getSubtitle().isEmpty())
            holder.tv_subtitle.setVisibility(View.VISIBLE);
        else
            holder.tv_subtitle.setVisibility(View.GONE);
        holder.wv_description.getSettings().setJavaScriptEnabled(true);
        String description = plansList.get(position).getDescription();
        String mime = "text/html";
        String encoding = "utf-8";
        holder.wv_description.loadDataWithBaseURL(null, description, mime, encoding, null);

        if (upgrade)
            holder.btn_subscribe.setText("Upgrade");
        holder.btn_subscribe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SweetAlertDialog pDialog = new SweetAlertDialog(mContext, SweetAlertDialog.NORMAL_TYPE);
                pDialog.setTitle("Are you sure?");
                pDialog.setTitleText("Duration (Months) - " + plansList.get(position).getDuration());
                pDialog.setContentText("Amount (INR) " + plansList.get(position).getPrice());
                pDialog.setConfirmButton("Yes, Subscribe", new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sweetAlertDialog) {
                        UserSessionManager sessionManager = new UserSessionManager(mContext);
                        if (sessionManager.checkLogin())
                            ((Activity) mContext).finish();
                        Toast.makeText(mContext, "Subscription Success", Toast.LENGTH_SHORT).show();
                        pDialog.dismissWithAnimation();
                    }
                });
                pDialog.setCancelButton("No", new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sweetAlertDialog) {
                        Toast.makeText(mContext, "Action cancelled by user.", Toast.LENGTH_SHORT).show();
                        pDialog.dismissWithAnimation();
                    }
                });
                pDialog.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return plansList.size();
    }

    public class PlanHolder extends RecyclerView.ViewHolder {

        ImageView vp_images;
        TextView tv_title, tv_subtitle;
        Button btn_subscribe;
        WebView wv_description;

        public PlanHolder(@NonNull View itemView) {
            super(itemView);
            vp_images = itemView.findViewById(R.id.iv_planimage);
            tv_subtitle = itemView.findViewById(R.id.tv_subtitle);
            tv_title = itemView.findViewById(R.id.tv_title);
            btn_subscribe = itemView.findViewById(R.id.btn_subscribe);
            wv_description = itemView.findViewById(R.id.wv_description);
        }
    }
}