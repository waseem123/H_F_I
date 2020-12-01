package com.infostackresearch.homefit.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.infostackresearch.homefit.R;
import com.infostackresearch.homefit.adapters.ViewpagerAdapter;
import com.infostackresearch.homefit.models.Plans;

import java.util.ArrayList;
import java.util.List;

public class PlansFragment extends Fragment {
    private ViewPager2 vp_plandata;
    private ViewpagerAdapter adapter;
    List<Plans> plans = new ArrayList<>();
    int[] images = {R.drawable.ic_launcher_background, R.drawable.ic_launcher_background, R.drawable.ic_launcher_background};
    // TODO: Rename parameter arguments, choose names that match

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_plans, container, false);
        vp_plandata = root.findViewById(R.id.vp_plandata);

        adapter = new ViewpagerAdapter(getActivity().getApplicationContext(), plans);
        vp_plandata.setAdapter(adapter);
        vp_plandata.getChildAt(0).setOverScrollMode(View.SCROLL_AXIS_HORIZONTAL);

        return root;
    }
}