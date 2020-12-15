package com.infostackresearch.homefit;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.infostackresearch.homefit.adapters.DeliveryLocationAdapter;
import com.infostackresearch.homefit.models.DeliveryLocation;

import java.util.ArrayList;
import java.util.List;

public class DeliveryAddressActivity extends AppCompatActivity {
    RecyclerView rv_deliverylocation;
    List<DeliveryLocation> deliveryLocationList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deliveryaddress);
        rv_deliverylocation = findViewById(R.id.rv_deliverylocation);

        deliveryLocationList.add(new DeliveryLocation("1", "331, Lokmanya Nagar, Solapur", "Alexandar Pierce"));
        deliveryLocationList.add(new DeliveryLocation("2", "16, Hotgi Road, Solapur", "Waseem"));
        deliveryLocationList.add(new DeliveryLocation("3", "3B, Downhill, New York", "Vishal"));
        deliveryLocationList.add(new DeliveryLocation("4", "5A, Malibu Club, California", "John Doe"));

        rv_deliverylocation.setAdapter(new DeliveryLocationAdapter(DeliveryAddressActivity.this, deliveryLocationList));
        rv_deliverylocation.setLayoutManager(new GridLayoutManager(DeliveryAddressActivity.this, 2));
    }
}