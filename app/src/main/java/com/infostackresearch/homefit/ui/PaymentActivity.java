package com.infostackresearch.homefit.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.textfield.TextInputEditText;
import com.infostackresearch.homefit.DeliveryAddressActivity;
import com.infostackresearch.homefit.R;
import com.infostackresearch.homefit.http.APIService;
import com.infostackresearch.homefit.http.ClientInstance;
import com.infostackresearch.homefit.models.OrderData;
import com.infostackresearch.homefit.models.SubscribeUser;
import com.infostackresearch.homefit.models.SubscriptionData;
import com.infostackresearch.homefit.sessions.UserSessionManager;
import com.razorpay.Checkout;
import com.razorpay.PaymentResultListener;

import org.json.JSONObject;

import java.util.HashMap;

import cn.pedant.SweetAlert.SweetAlertDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PaymentActivity extends AppCompatActivity implements PaymentResultListener {
    String TAG = "paymentActivity", txnid = "txn754587", phone = "9096288255";
    double amount = 0.0, total_amount = 0.0;
    private String planid;
    private double discount;
    private String auth_token;
    private String product_title;
    private Button btn_pay;
    Bundle bundle;
    TextInputEditText tie_title, tie_amount, tie_discount;
    private TextView tv_deliveryaddress;
    private LinearLayout linearLayout;
    private String address;
    private String addressid;
    private UserSessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);
        sessionManager = new UserSessionManager(PaymentActivity.this);
        if (sessionManager.checkLogin())
            finish();
        HashMap<String, String> user = sessionManager.getUserDetails();
        auth_token = user.get(UserSessionManager.KEY_AuthToken);

        bundle = new Bundle();
        bundle = getIntent().getExtras();

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        linearLayout = findViewById(R.id.ll_address);
        tv_deliveryaddress = findViewById(R.id.tv_deliveryaddress);

        tie_title = findViewById(R.id.tie_title);
        tie_amount = findViewById(R.id.tie_amount);
        tie_discount = findViewById(R.id.tie_discount);

        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PaymentActivity.this, DeliveryAddressActivity.class);
                startActivity(intent);
            }
        });

//        if (bundle != null) {
//            phone = bundle.getString("mobilenumber");
//            amount = bundle.getDouble("amount");
//            auth_token = bundle.getString("auth_token");
//            planid = bundle.getString("planid");
//            discount = bundle.getDouble("discount");
//            product_title = bundle.getString("product_title");
//
//            tie_amount.setText(amount + "");
//            tie_title.setText(product_title);
//            tie_discount.setText(discount + "");
//
//            tie_title.setEnabled(false);
//            tie_amount.setEnabled(false);
//            tie_discount.setEnabled(false);
//
//        } else
        if (!sessionManager.checkCart()) {
            HashMap<String, String> plan = sessionManager.getPlanData();
            phone = user.get(UserSessionManager.KEY_Mobile);
            amount = Double.parseDouble(plan.get(UserSessionManager.KEY_Amount));
            planid = plan.get(UserSessionManager.KEY_PlanId);
            address = plan.get(UserSessionManager.KEY_Address);
            addressid = plan.get(UserSessionManager.KEY_AddressID);
            product_title = plan.get(UserSessionManager.KEY_PlanTitle);
            discount = Double.parseDouble(plan.get(UserSessionManager.KEY_Discount));

            tie_amount.setText(amount + "");
            tie_title.setText(product_title);
            tie_discount.setText(discount + "");
            tv_deliveryaddress.setText(address + "");

            tie_title.setEnabled(false);
            tie_amount.setEnabled(false);
            tie_discount.setEnabled(false);
        }

        // Payment button created by you in XML layout
        btn_pay = findViewById(R.id.btn_pay);

        total_amount = amount + (amount * (discount / 100)) * 100;
//        total_amount = amount * 100;
        btn_pay.setText("Pay RS. " + total_amount);
        Checkout.preload(getApplicationContext());


        btn_pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startPayment(total_amount * 100);
            }
        });

//        startPayment();
    }

    private void startPayment(double total_amount) {

        /*
          You need to pass current activity in order to let Razorpay create CheckoutActivity
         */
        final Activity activity = this;

        final Checkout co = new Checkout();

        try {
            JSONObject options = new JSONObject();
            options.put("name", "Razorpay Corp");
            options.put("description", "Demoing Charges");
            //You can omit the image option to fetch the image from dashboard
            options.put("image", "https://s3.amazonaws.com/rzp-mobile/images/rzp.png");
            options.put("currency", "INR");
            options.put("amount", total_amount);

            JSONObject preFill = new JSONObject();
            preFill.put("email", "test@razorpay.com");
            preFill.put("contact", "9096288255");

            options.put("prefill", preFill);

            co.open(activity, options);
        } catch (Exception e) {
            Toast.makeText(activity, "Error in payment: " + e.getMessage(), Toast.LENGTH_SHORT)
                    .show();
            e.printStackTrace();
        }
    }


    @Override
    public void onPaymentSuccess(String razorpayPaymentID) {
        APIService service = ClientInstance.getRetrofitInstance().create(APIService.class);
        Call<SubscribeUser> call = service.doSubscription(new SubscriptionData(planid, razorpayPaymentID, "2020-12-10", "2021-12-09", "12345", total_amount + ""), "Bearer " + auth_token);
        call.enqueue(new Callback<SubscribeUser>() {
            @Override
            public void onResponse(Call<SubscribeUser> call, Response<SubscribeUser> response) {
                if (response.isSuccessful()) {
                    if (response.body().isSuccess()) {
                        SweetAlertDialog pDialog = new SweetAlertDialog(PaymentActivity.this, SweetAlertDialog.SUCCESS_TYPE);
                        pDialog.setTitleText("Success");
                        pDialog.setContentText("Your subscription is successful.");
                        pDialog.setConfirmButton("Ok", new SweetAlertDialog.OnSweetClickListener() {
                            @Override
                            public void onClick(SweetAlertDialog sweetAlertDialog) {
                                SubscriptionData subscriptionData = response.body().getSubscriptionData();
                                OrderData orderData = response.body().getOrderData();
                                Intent intent = new Intent(PaymentActivity.this, MySubscriptionActivity.class);
                                intent.putExtra("id", subscriptionData.getId());
                                intent.putExtra("user_id", subscriptionData.getUser_id());
                                intent.putExtra("subscription_id", subscriptionData.getSubscription_id());
                                intent.putExtra("price", subscriptionData.getPrice());
                                intent.putExtra("paymentid", subscriptionData.getPaymentid());
                                intent.putExtra("start_date", subscriptionData.getStart_date());
                                intent.putExtra("end_date", subscriptionData.getEnd_date());
                                intent.putExtra("is_expired", subscriptionData.getIs_expired());
                                intent.putExtra("created_at", subscriptionData.getCreated_at());
                                intent.putExtra("updated_at", subscriptionData.getUpdated_at());
                                intent.putExtra("user_subscription_id", orderData.getUser_subscription_id());
                                intent.putExtra("order_status", orderData.getStatus());
                                intent.putExtra("order_number", orderData.getOrder_no());
                                intent.putExtra("order_date", orderData.getOrder_date());
                                finish();
                                startActivity(intent);
                            }
                        });
                        pDialog.show();
                    } else {
                        SweetAlertDialog pDialog = new SweetAlertDialog(PaymentActivity.this, SweetAlertDialog.ERROR_TYPE);
                        pDialog.setTitleText("Server Error");
                        pDialog.setContentText("Internal Error occured");
                        pDialog.show();
                    }
                } else {
                    SweetAlertDialog pDialog = new SweetAlertDialog(PaymentActivity.this, SweetAlertDialog.ERROR_TYPE);
                    pDialog.setTitleText("Server Error");
                    pDialog.setContentText("Response is not in correct format");
                    pDialog.show();
                }
            }

            @Override
            public void onFailure(Call<SubscribeUser> call, Throwable t) {
                SweetAlertDialog pDialog = new SweetAlertDialog(PaymentActivity.this, SweetAlertDialog.ERROR_TYPE);
                pDialog.setTitleText("Server Error");
                pDialog.setContentText("Internal Error occured");
                pDialog.show();
            }
        });
//        Toast.makeText(PaymentActivity.this, "Payment successful - Payment ID - " + razorpayPaymentID, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onPaymentError(int code, String response) {
        Toast.makeText(PaymentActivity.this, "Payment failure - " + code + " - " + response, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        HashMap<String, String> userAddress = sessionManager.getPlanData();
        address = userAddress.get(UserSessionManager.KEY_Address);
        addressid = userAddress.get(UserSessionManager.KEY_AddressID);
        tv_deliveryaddress.setText(address);
    }
}