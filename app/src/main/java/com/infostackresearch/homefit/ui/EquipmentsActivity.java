package com.infostackresearch.homefit.ui;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.infostackresearch.homefit.R;
import com.infostackresearch.homefit.adapters.EquipmentsAdapter;
import com.infostackresearch.homefit.models.Equipments;

import java.util.ArrayList;
import java.util.List;

public class EquipmentsActivity extends AppCompatActivity {
    private RecyclerView rv_equipments;
    private List<Equipments> equipments = new ArrayList<>();
    private Bundle bundle;
    private String title = "Equipments";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_equipments);

        bundle = new Bundle();
        bundle = getIntent().getExtras();

        if (null != bundle) {
            title = bundle.getString("level");
        }

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle(title);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        equipments.add(new Equipments(1, "Dumbbells", R.drawable.fitness, "100"));
        equipments.add(new Equipments(2, "Trademill", R.drawable.workoutman, "100"));
        equipments.add(new Equipments(3, "Push Up Bar", R.drawable.sport, "100"));
        equipments.add(new Equipments(4, "Kettle Bell", R.drawable.kettlebell, "100"));
        equipments.add(new Equipments(5, "Jumping Rope", R.drawable.aerobics, "100"));

        rv_equipments = findViewById(R.id.rv_equipments);
        rv_equipments.setAdapter(new EquipmentsAdapter(EquipmentsActivity.this, equipments));
        rv_equipments.setLayoutManager(new GridLayoutManager(EquipmentsActivity.this, 2));

    }
}