package com.infostackresearch.homefit.ui.workout;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.infostackresearch.homefit.R;
import com.infostackresearch.homefit.adapters.EquipmentsAdapter;
import com.infostackresearch.homefit.models.Equipments;

import java.util.ArrayList;
import java.util.List;

public class WorkoutFragment extends Fragment {

    private WorkoutViewModel dashboardViewModel;
    private RecyclerView rv_workout;
    private List<Equipments> equipments = new ArrayList<>();

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        dashboardViewModel =
                ViewModelProviders.of(this).get(WorkoutViewModel.class);
        View root = inflater.inflate(R.layout.fragment_dashboard, container, false);

        Context context = getActivity().getApplicationContext();

        equipments.add(new Equipments(1, "Chest", R.drawable.fitness, "100"));
        equipments.add(new Equipments(2, "Biceps", R.drawable.workoutman, "100"));
        equipments.add(new Equipments(3, "Back", R.drawable.sport, "100"));
        equipments.add(new Equipments(4, "Shoulders", R.drawable.dumbbell, "100"));
        equipments.add(new Equipments(5, "Core Body Workout", R.drawable.aerobics, "100"));

        rv_workout = root.findViewById(R.id.rv_workout);
        rv_workout.setAdapter(new EquipmentsAdapter(context, equipments));
        rv_workout.setLayoutManager(new GridLayoutManager(context, 2));


        return root;
    }
}