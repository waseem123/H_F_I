package com.infostackresearch.homefit.ui;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.infostackresearch.homefit.R;
import com.infostackresearch.homefit.http.APIService;
import com.infostackresearch.homefit.http.ClientInstance;
import com.infostackresearch.homefit.http.NetworkConnectivity;
import com.infostackresearch.homefit.models.LoginData;
import com.infostackresearch.homefit.models.LoginModel;
import com.infostackresearch.homefit.sessions.UserSessionManager;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import cn.pedant.SweetAlert.SweetAlertDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    Button btn_signin;
    EditText ed_username;
    EditText ed_password;
    TextView tv_signup;

    NetworkConnectivity networkConnectivity = new NetworkConnectivity(LoginActivity.this);
    private UserSessionManager session;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        session = new UserSessionManager(getApplicationContext());

        btn_signin = findViewById(R.id.btn_signin);
        ed_username = findViewById(R.id.ed_username);
        ed_password = findViewById(R.id.ed_password);
        tv_signup = findViewById(R.id.tv_signup);

        btn_signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isValid()) {
                    if (networkConnectivity.isOnline()) {
                        progressDialog = new ProgressDialog(LoginActivity.this);
                        progressDialog.setMessage("Authenticating....");
                        progressDialog.show();
                        doLogin(ed_username.getText().toString(), ed_password.getText().toString());
                    } else {
                        SweetAlertDialog pDialog = new SweetAlertDialog(LoginActivity.this, SweetAlertDialog.ERROR_TYPE);
                        pDialog.setTitleText("Connection Error!");
                        pDialog.setContentText("You are not connected to internet. Please check your internet connection.");
                        pDialog.show();
                    }

                }
            }
        });

        tv_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this, SignupActivity.class));
            }
        });
    }

    private void doLogin(String email, String password) {
        APIService service = ClientInstance.getRetrofitInstance().create(APIService.class);


        Call<LoginModel> call = service.doLogin(new LoginData(email,password));
        call.enqueue(new Callback<LoginModel>() {
            @Override
            public void onResponse(Call<LoginModel> call, Response<LoginModel> response) {
                progressDialog.dismiss();
                if (response.body().getStatus_code().equals("200")) {
                    Toast.makeText(LoginActivity.this, "Authentication Success.", Toast.LENGTH_SHORT).show();
                    session.createUserLoginSession(response.body().getUser().getId(), response.body().getUser().getUsername(), response.body().getUser().getEmail(), response.body().getUser().getToken(), "employee");
                    ed_username.setText("");
                    ed_password.setText("");
                    Intent i = new Intent(LoginActivity.this, MainActivity.class);
                    i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(i);
                    finishAffinity();
                }
            }

            @Override
            public void onFailure(Call<LoginModel> call, Throwable t) {
                progressDialog.dismiss();
                SweetAlertDialog pDialog = new SweetAlertDialog(LoginActivity.this, SweetAlertDialog.ERROR_TYPE);
                pDialog.setTitleText("Server Error");
                pDialog.setContentText(t.getMessage());
                pDialog.show();
            }
        });
    }


    private boolean isValid() {
        boolean validFlag = false;
        if (ed_username.getText().toString() != null && !ed_username.getText().toString().isEmpty()) {
            validFlag = true;
        } else {
            ed_username.setError("Please enter Valid Email /  Mobile Number");
            validFlag = false;
            return validFlag;
        }

        if (ed_password.getText().toString() != null && !ed_password.getText().toString().isEmpty()) {
            validFlag = true;
        } else {
            ed_password.setError("Please enter password");
            validFlag = false;
            return validFlag;
        }
        return validFlag;
    }
}