package com.infostackresearch.homefit.ui;

import android.app.ProgressDialog;
import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.infostackresearch.homefit.R;
import com.infostackresearch.homefit.http.NetworkConnectivity;
import com.infostackresearch.homefit.sessions.UserSessionManager;

import java.util.HashMap;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class MySubscriptionActivity extends AppCompatActivity {
    private UserSessionManager sessionManager;
    private String user_id, user_name, user_mobile, user_email;
    private Toolbar toolbar;
    private Bundle bundle;

    private String plan_id;
    private String u_id;
    private String subscription_id;
    private String price;
    private String payment_id;
    private String start_date;
    private String end_date;
    private String is_expired;
    private String created_at;
    private String updated_at;

    private NetworkConnectivity networkConnectivity;
    private ProgressDialog progressDialog;
    private TextView tv_description;
    private TextView tv_startdate;
    private TextView tv_enddate;
    private TextView tv_price;
    private TextView tv_paymentid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mysubscription);
        toolbar = findViewById(R.id.toolbar);
        sessionManager = new UserSessionManager(getApplicationContext());
        networkConnectivity = new NetworkConnectivity(MySubscriptionActivity.this);
        bundle = new Bundle();
        bundle = getIntent().getExtras();

        tv_startdate = findViewById(R.id.tv_startdate);
        tv_enddate = findViewById(R.id.tv_enddate);
        tv_price = findViewById(R.id.tv_price);
        tv_description = findViewById(R.id.tv_description);
        tv_paymentid = findViewById(R.id.tv_paymentid);

        if (bundle != null) {
            plan_id = bundle.getString("id");
            u_id = bundle.getString("user_id");
            subscription_id = bundle.getString("subscription_id");
            payment_id = bundle.getString("payment_id");

            price = bundle.getString("price");

            start_date = bundle.getString("start_date");
            end_date = bundle.getString("end_date");

            created_at = bundle.getString("created_at");
            updated_at = bundle.getString("updated_at");
        }

        tv_price.setText(price);
        tv_startdate.setText(start_date);
        tv_enddate.setText(end_date);
        tv_paymentid.setText(payment_id);
        if (!sessionManager.checkLogin()) {
            HashMap<String, String> user = sessionManager.getUserDetails();
            user_id = user.get(UserSessionManager.KEY_UserId);
            user_name = user.get(UserSessionManager.KEY_UserName);
            user_mobile = user.get(UserSessionManager.KEY_Mobile);
            user_email = user.get(UserSessionManager.KEY_Email);
        } else {
            Toast.makeText(getApplicationContext(), "You are not logged in.", Toast.LENGTH_SHORT).show();

        }

        if (networkConnectivity.isOnline()) {
            progressDialog = new ProgressDialog(MySubscriptionActivity.this);
            progressDialog.setMessage("Getting your plan....");
            progressDialog.show();
            getPlan();
            progressDialog.dismiss();
        } else {
            SweetAlertDialog pDialog = new SweetAlertDialog(MySubscriptionActivity.this, SweetAlertDialog.ERROR_TYPE);
            pDialog.setTitleText("Connection Error!");
            pDialog.setContentText("You are not connected to internet. Please check your internet connection.");
            pDialog.show();
        }

        toolbar.setTitle("abc");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            tv_description.setText(Html.fromHtml("<ul><li>abc<\\/li><li>sad<\\/li><li>sa<\\/li><li>ad<\\/li><\\/ul>", Html.FROM_HTML_MODE_COMPACT));
        } else {
            tv_description.setText(Html.fromHtml("<ul><li>abc<\\/li><li>sad<\\/li><li>sa<\\/li><li>ad<\\/li><\\/ul>"));
        }

    }

    private void getPlan() {
    }
}