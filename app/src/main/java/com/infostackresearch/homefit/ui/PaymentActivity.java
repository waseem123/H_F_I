package com.infostackresearch.homefit.ui;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.infostackresearch.homefit.R;
import com.infostackresearch.homefit.http.APIService;
import com.infostackresearch.homefit.http.ClientInstance;
import com.infostackresearch.homefit.models.PayData;
import com.infostackresearch.homefit.models.PaymentHash;
import com.payumoney.core.PayUmoneySdkInitializer;
import com.payumoney.core.entity.TransactionResponse;
import com.payumoney.sdkui.ui.utils.PayUmoneyFlowManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PaymentActivity extends AppCompatActivity {
    String TAG = "paymentActivity", txnid = "txn123456", amount = "100", phone = "9096288255", merchantId = "56789", key = "qkJmgzBW";
    private String planid;
    private String discount;
    private String auth_token;

    PayUmoneySdkInitializer.PaymentParam.Builder builder = new PayUmoneySdkInitializer.PaymentParam.Builder();
    private String product_title;
    private PayUmoneySdkInitializer.PaymentParam paymentParams;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);
        Intent intent = getIntent();
        phone = intent.getExtras().getString("mobilenumber");
        amount = intent.getExtras().getString("amount");
        auth_token = intent.getExtras().getString("auth_token");
        planid = intent.getExtras().getString("planid");
        discount = intent.getExtras().getString("discount");
        product_title = intent.getExtras().getString("product_title");

        startPayment();
    }

    private void startPayment() {
        builder.setAmount("100")
                .setPhone("9096288255")
                .setTxnId(txnid)
                .setFirstName("Waseem")
                .setProductName(product_title)
                .setKey(key)
                .setIsDebug(true)
                .setEmail("waseemattar@gmail.com")
                .setUdf1("")
                .setUdf2("")
                .setUdf3("")
                .setUdf4("")
                .setUdf5("")
                .setUdf6("")
                .setUdf7("")
                .setUdf8("")
                .setUdf9("")
                .setUdf10("")
                .setMerchantId(merchantId)
                .setsUrl("https://www.infostackresearch.com/")
                .setfUrl("https://www.google.com/");

        try {
            paymentParams = builder.build();
            getHashKey();
        } catch (Exception e) {
            Log.d(TAG, "Exception - " + e.getMessage());
        }
    }

    private void getHashKey() {
        APIService service = ClientInstance.getRetrofitInstance().create(APIService.class);
        Call<PaymentHash> call = service.getHash(new PayData("test", "123"));
        call.enqueue(new Callback<PaymentHash>() {
            @Override
            public void onResponse(Call<PaymentHash> call, Response<PaymentHash> response) {
                String merchantHash = response.body().getPayment_hash();
                if (merchantHash.isEmpty() || merchantHash.equals("")) {
                    Toast.makeText(PaymentActivity.this, "Could not generate hash", Toast.LENGTH_SHORT).show();
                } else {
                    paymentParams.setMerchantHash(merchantHash);
                    PayUmoneyFlowManager.startPayUMoneyFlow(paymentParams, PaymentActivity.this, R.style.AppTheme_default, false);
                }
            }

            @Override
            public void onFailure(Call<PaymentHash> call, Throwable t) {
                Toast.makeText(PaymentActivity.this, "Error : " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PayUmoneyFlowManager.REQUEST_CODE_PAYMENT && resultCode == RESULT_OK && data != null) {
            TransactionResponse transactionResponse = data.getParcelableExtra(PayUmoneyFlowManager.INTENT_EXTRA_TRANSACTION_RESPONSE);
            if (transactionResponse.getTransactionStatus().equals(TransactionResponse.TransactionStatus.SUCCESSFUL)) {
                Toast.makeText(PaymentActivity.this, "Transaction Success", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(PaymentActivity.this, "Transaction Failure", Toast.LENGTH_SHORT).show();
            }
            String payuResponse = transactionResponse.getPayuResponse();
            String merchantResponse = transactionResponse.getTransactionDetails();
        }
    }
}