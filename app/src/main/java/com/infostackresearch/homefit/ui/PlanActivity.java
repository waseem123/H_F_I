package com.infostackresearch.homefit.ui;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager2.widget.CompositePageTransformer;
import androidx.viewpager2.widget.MarginPageTransformer;
import androidx.viewpager2.widget.ViewPager2;

import com.infostackresearch.homefit.R;
import com.infostackresearch.homefit.adapters.ViewpagerAdapter;
import com.infostackresearch.homefit.http.APIService;
import com.infostackresearch.homefit.http.ClientInstance;
import com.infostackresearch.homefit.http.NetworkConnectivity;
import com.infostackresearch.homefit.models.PlanResponse;
import com.infostackresearch.homefit.models.Plans;

import java.util.List;

import cn.pedant.SweetAlert.SweetAlertDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PlanActivity extends AppCompatActivity {
    private ViewPager2 vp_plandata;
    private ViewpagerAdapter adapter;
    private TextView tv_nodata;
    private NetworkConnectivity networkConnectivity;
    private Toolbar toolbar;
    private ProgressDialog progressDialog;
//    int[] images = {R.drawable.ic_launcher_background, R.drawable.ic_launcher_background, R.drawable.ic_launcher_background};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plan);

        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Browse Plans");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        vp_plandata = findViewById(R.id.vp_plandata);
        tv_nodata = findViewById(R.id.tv_nodata);
        networkConnectivity = new NetworkConnectivity(PlanActivity.this);
        progressDialog = new ProgressDialog(PlanActivity.this);

        if (networkConnectivity.isOnline()) {
            progressDialog.setMessage("Fetching best plans for you...");
            progressDialog.show();
            getAllPlans();
        } else {
            tv_nodata.setVisibility(View.VISIBLE);
            tv_nodata.setText("Ohh Snap! No Internet.");
            SweetAlertDialog pDialog = new SweetAlertDialog(PlanActivity.this, SweetAlertDialog.ERROR_TYPE);
            pDialog.setTitleText("Connection Error");
            pDialog.setContentText("You are not connected to Internet");
            pDialog.show();
        }
    }

    private void getAllPlans() {
        APIService service = ClientInstance.getRetrofitInstance().create(APIService.class);
        Call<PlanResponse> call = service.getAllPlans();
        call.enqueue(new Callback<PlanResponse>() {
            @Override
            public void onResponse(Call<PlanResponse> call, Response<PlanResponse> response) {
                progressDialog.dismiss();
                if (response.body().isSuccess()) {
                    tv_nodata.setVisibility(View.GONE);
                    List<Plans> plansList = response.body().getPlansList();
                    adapter = new ViewpagerAdapter(PlanActivity.this, plansList);
                    vp_plandata.setAdapter(adapter);
                    vp_plandata.setClipToPadding(false);
                    vp_plandata.setClipChildren(false);
                    vp_plandata.setOffscreenPageLimit(3);
                    vp_plandata.getChildAt(0).setOverScrollMode(View.OVER_SCROLL_NEVER);

                    CompositePageTransformer compositePageTransformer = new CompositePageTransformer();
                    compositePageTransformer.addTransformer(new MarginPageTransformer(40));
                    compositePageTransformer.addTransformer(new ViewPager2.PageTransformer() {
                        @Override
                        public void transformPage(@NonNull View page, float position) {
                            float r = 1 - Math.abs(position);
                            page.setScaleY(0.85f + r * 0.15f);
                        }
                    });
                    vp_plandata.setPageTransformer(compositePageTransformer);
                } else {
                    tv_nodata.setVisibility(View.VISIBLE);
                    SweetAlertDialog pDialog = new SweetAlertDialog(PlanActivity.this, SweetAlertDialog.ERROR_TYPE);
                    pDialog.setTitleText("No Data");
                    pDialog.setContentText("No data present");
                    pDialog.show();
                }
            }

            @Override
            public void onFailure(Call<PlanResponse> call, Throwable t) {
                progressDialog.dismiss();
                tv_nodata.setVisibility(View.VISIBLE);
                SweetAlertDialog pDialog = new SweetAlertDialog(PlanActivity.this, SweetAlertDialog.ERROR_TYPE);
                pDialog.setTitleText("Server Error");
                pDialog.setContentText(t.getMessage());
                pDialog.show();
            }
        });
    }
}