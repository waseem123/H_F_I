package com.infostackresearch.homefit.ui.profile;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.infostackresearch.homefit.R;
import com.infostackresearch.homefit.sessions.UserSessionManager;
import com.infostackresearch.homefit.ui.MySubscriptionActivity;
import com.infostackresearch.homefit.ui.PlanActivity;

import java.util.HashMap;

public class ProfileFragment extends Fragment {
    private UserSessionManager sessionManager;
    private ProfileViewModel mViewModel;
    private String user_id, user_name, user_mobile, user_email;

    private TextView tv_customer_id, tv_customer_name, tv_mobile_number, tv_email;
    private Button btn_update;
    private TextView tv_upgradePlan;
    private CardView cv_viewPlan;
    private int plan_id = 1;

    public static ProfileFragment newInstance() {
        return new ProfileFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mViewModel =
                ViewModelProviders.of(this).get(ProfileViewModel.class);
        View root = inflater.inflate(R.layout.fragment_profile, container, false);

        sessionManager = new UserSessionManager(getActivity().getApplicationContext());

        tv_customer_id = root.findViewById(R.id.tv_customer_name);
        tv_customer_name = root.findViewById(R.id.tv_customer_name);
        tv_mobile_number = root.findViewById(R.id.tv_mobile_number);
        tv_email = root.findViewById(R.id.tv_email);
        cv_viewPlan = root.findViewById(R.id.cv_myplan);
        tv_upgradePlan = root.findViewById(R.id.tv_upgradeplan);
//        btn_update = root.findViewById(R.id.btn_update);

        if (!sessionManager.checkLogin()) {
            HashMap<String, String> user = sessionManager.getUserDetails();
            user_id = user.get(UserSessionManager.KEY_UserId);
            user_name = user.get(UserSessionManager.KEY_UserName);
            user_mobile = user.get(UserSessionManager.KEY_Mobile);
            user_email = user.get(UserSessionManager.KEY_Email);
        } else {
            Toast.makeText(getActivity().getApplicationContext(), "You are not logged in.", Toast.LENGTH_SHORT).show();
            btn_update.setEnabled(false);
        }

        tv_customer_id.setText(user_id);
        tv_customer_name.setText(user_name);
        tv_mobile_number.setText(user_mobile);
        tv_email.setText(user_email);


//        btn_update.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(getActivity().getApplicationContext(), EditProfileActivity.class);
//                intent.putExtra("name", user_name);
//                intent.putExtra("mobile", user_mobile);
//                startActivity(intent);
//            }
//        });

        cv_viewPlan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity().getApplicationContext(), MySubscriptionActivity.class);
                intent.putExtra("my_plan", plan_id);
                startActivity(intent);
            }
        });

        tv_upgradePlan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity().getApplicationContext(), PlanActivity.class);
                intent.putExtra("upgrade", true);
                startActivity(intent);
            }
        });
        return root;
    }
}