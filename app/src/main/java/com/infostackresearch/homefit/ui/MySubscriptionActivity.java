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
    private int plan_id;
    private NetworkConnectivity networkConnectivity;
    private ProgressDialog progressDialog;
    private TextView tv_description;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mysubscription);
        toolbar = findViewById(R.id.toolbar);
        sessionManager = new UserSessionManager(getApplicationContext());
        networkConnectivity = new NetworkConnectivity(MySubscriptionActivity.this);
        bundle = new Bundle();
        bundle = getIntent().getExtras();

        tv_description = findViewById(R.id.tv_description);

        if (bundle != null)
            plan_id = bundle.getInt("my_plan");

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