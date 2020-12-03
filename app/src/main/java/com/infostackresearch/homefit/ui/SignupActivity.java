package com.infostackresearch.homefit.ui;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputLayout;
import com.infostackresearch.homefit.R;
import com.infostackresearch.homefit.http.APIService;
import com.infostackresearch.homefit.http.ClientInstance;
import com.infostackresearch.homefit.http.NetworkConnectivity;
import com.infostackresearch.homefit.models.LoginData;
import com.infostackresearch.homefit.models.SignUp;

import cn.pedant.SweetAlert.SweetAlertDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignupActivity extends AppCompatActivity {
    TextInputLayout til_username, til_mobilenumber, til_email, til_password;
    EditText ed_username, ed_mobilenumber, ed_email, ed_password, ed_confpassword, ed_countrycode;
    Button btn_signup;
    private ProgressDialog progressDialog;

    private NetworkConnectivity networkConnectivity = new NetworkConnectivity(SignupActivity.this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        til_username = findViewById(R.id.til_username);
        til_username.setStartIconTintList(null);

        til_mobilenumber = findViewById(R.id.til_mobilenumber);
        til_mobilenumber.setStartIconTintList(null);

        til_email = findViewById(R.id.til_email);
        til_email.setStartIconTintList(null);

        til_password = findViewById(R.id.til_password);
        til_password.setStartIconTintList(null);

        ed_username = findViewById(R.id.ed_username);
        ed_mobilenumber = findViewById(R.id.ed_mobilenumber);
        ed_email = findViewById(R.id.ed_email);
        ed_password = findViewById(R.id.ed_password);
        ed_confpassword = findViewById(R.id.ed_confpassword);
        ed_countrycode = findViewById(R.id.ed_countrycode);

        btn_signup = findViewById(R.id.btn_signup);

        btn_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isValid()) {
                    if (networkConnectivity.isOnline()) {
                        progressDialog = new ProgressDialog(SignupActivity.this);
                        progressDialog.setMessage("Signing you up....");
                        progressDialog.show();
                        doSignUP(ed_username.getText().toString(), ed_countrycode.getText().toString() + ed_mobilenumber.getText().toString(), ed_email.getText().toString(), ed_password.getText().toString());
                    } else {
                        SweetAlertDialog pDialog = new SweetAlertDialog(SignupActivity.this, SweetAlertDialog.ERROR_TYPE);
                        pDialog.setTitleText("Connection Error!");
                        pDialog.setContentText("You are not connected to internet. Please check your internet connection.");
                        pDialog.show();
                    }
                }
            }
        });
    }

    private void doSignUP(String username, String mobile_number, String email, String password) {
        APIService service = ClientInstance.getRetrofitInstance().create(APIService.class);
        Call<SignUp> call = service.doSignUp(new LoginData(email, password, username, mobile_number));
        call.enqueue(new Callback<SignUp>() {
            @Override
            public void onResponse(Call<SignUp> call, Response<SignUp> response) {
                progressDialog.dismiss();
                if (response.body() != null) {
                    if (null != response.body().getStatus_code()) {
                        SweetAlertDialog pDialog = new SweetAlertDialog(SignupActivity.this, SweetAlertDialog.SUCCESS_TYPE);
                        pDialog.setTitleText("Success");
                        pDialog.setContentText("Your registration is successful.");
                        pDialog.show();
                    } else {
                        if (response.body().getError().getEmailError().length > 0) {
                            ed_email.setError(response.body().getError().getEmailError()[0]);
                        }

                        if (response.body().getError().getPhoneError().length > 0) {
                            ed_mobilenumber.setError(response.body().getError().getPhoneError()[0]);
                        }

                        if (response.body().getError().getPasswordError().length > 0) {
                            ed_password.setError(response.body().getError().getPasswordError()[0]);
                        }

                        if (response.body().getError().getNameError().length > 0) {
                            ed_username.setError(response.body().getError().getNameError()[0]);
                        }
                    }

                } else {
                    SweetAlertDialog pDialog = new SweetAlertDialog(SignupActivity.this, SweetAlertDialog.ERROR_TYPE);
                    pDialog.setTitleText("Server Error");
                    pDialog.setContentText("Response is not in correct format");
                    pDialog.show();
                }
            }

            @Override
            public void onFailure(Call<SignUp> call, Throwable t) {
                progressDialog.dismiss();
                SweetAlertDialog pDialog = new SweetAlertDialog(SignupActivity.this, SweetAlertDialog.ERROR_TYPE);
                pDialog.setTitleText("Server Error");
                pDialog.setContentText(t.getMessage());
                pDialog.show();
            }
        });
    }

    private boolean isValid() {
        boolean validFlag = false;
        if (!ed_username.getText().toString().isEmpty()) {
            validFlag = true;
        } else {
            ed_username.setError("Please enter Valid Name");
            validFlag = false;
            return validFlag;
        }

        if (!ed_mobilenumber.getText().toString().isEmpty() && !ed_countrycode.getText().toString().isEmpty() && ed_mobilenumber.getText().toString().length() == 10) {
            validFlag = true;
        } else {
            ed_mobilenumber.setError("Please enter 10-digit  Mobile Number");
            validFlag = false;
            return validFlag;
        }

        if (!ed_email.getText().toString().isEmpty() && ed_email.getText().toString().contains("@")) {
            validFlag = true;
        } else {
            ed_email.setError("Please enter Valid Email");
            validFlag = false;
            return validFlag;
        }

        if (!ed_password.getText().toString().isEmpty()) {
            validFlag = true;
        } else {
            ed_password.setError("Please enter password");
            validFlag = false;
            return validFlag;
        }

        if (!ed_confpassword.getText().toString().isEmpty()) {
            validFlag = true;
        } else {
            ed_confpassword.setError("Please re-enter the password");
            validFlag = false;
            return validFlag;
        }

        if (ed_confpassword.getText().toString().equals(ed_password.getText().toString())) {
            validFlag = true;
        } else {
            ed_password.setError("Password did not match");
            ed_confpassword.setError("Password did not match");
            validFlag = false;
            return validFlag;
        }
        return validFlag;
    }
}