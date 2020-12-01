package com.infostackresearch.homefit.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.infostackresearch.homefit.R;
import com.infostackresearch.homefit.adapters.RentLevelAdapter;
import com.infostackresearch.homefit.adapters.VpAdapter.VpAdapter;
import com.infostackresearch.homefit.models.Ads.Ads;
import com.infostackresearch.homefit.models.RentLevels;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private ViewPager2 viewPager;
    private RecyclerView rv_rentals;

    private List<Ads> ads = new ArrayList<>();
    private List<RentLevels> levels = new ArrayList<>();

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        for (int i = 0; i < 4; i++)
            ads.add(new Ads(i + 1, "Advertise - " + (i + 1), R.drawable.adpic));

        viewPager = root.findViewById(R.id.vp_advertise);
        viewPager.setAdapter(new VpAdapter(getActivity().getApplicationContext(), ads));
        viewPager.setLayoutDirection(View.LAYOUT_DIRECTION_LTR);

        levels.add(new RentLevels(1, "Basic", R.drawable.dumbbell, "5000"));
        levels.add(new RentLevels(2, "Normal", R.drawable.workout, "15000"));
        levels.add(new RentLevels(3, "Advanced", R.drawable.aerobics, "25000"));
        levels.add(new RentLevels(4, "Silver", R.drawable.aerobics, "35000"));
        levels.add(new RentLevels(5, "Golden", R.drawable.dumbbell, "45000"));
        levels.add(new RentLevels(6, "Platinum", R.drawable.workout, "50000"));

        rv_rentals = root.findViewById(R.id.rv_rentals);
        rv_rentals.setAdapter(new RentLevelAdapter(getActivity().getApplicationContext(), levels));
        rv_rentals.setLayoutManager(new GridLayoutManager(getActivity().getApplicationContext(), 3));
        return root;
    }
}