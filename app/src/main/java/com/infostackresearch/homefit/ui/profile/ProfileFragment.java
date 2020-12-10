package com.infostackresearch.homefit.ui.profile;

import android.app.ProgressDialog;
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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.infostackresearch.homefit.R;
import com.infostackresearch.homefit.adapters.MyPlansAdapter;
import com.infostackresearch.homefit.http.APIService;
import com.infostackresearch.homefit.http.ClientInstance;
import com.infostackresearch.homefit.http.NetworkConnectivity;
import com.infostackresearch.homefit.models.ProfileSubscription;
import com.infostackresearch.homefit.sessions.UserSessionManager;

import java.util.HashMap;

import cn.pedant.SweetAlert.SweetAlertDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileFragment extends Fragment {
    private UserSessionManager sessionManager;
    private ProfileViewModel mViewModel;
    private String user_id, user_name, user_mobile, user_email;

    private TextView tv_customer_id, tv_customer_name, tv_mobile_number, tv_email;
    private Button btn_update;
    private TextView tv_upgradePlan;
    private CardView cv_viewPlan;
    private int plan_id = 1;
    private String auth_token;
    private NetworkConnectivity networkConnectivity;
    private ProgressDialog progressDialog;
    private RecyclerView rv_myplans;

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
        networkConnectivity = new NetworkConnectivity(getActivity().getApplicationContext());
        progressDialog = new ProgressDialog(getContext());

        tv_customer_id = root.findViewById(R.id.tv_customer_id);
        tv_customer_name = root.findViewById(R.id.tv_customer_name);
        tv_mobile_number = root.findViewById(R.id.tv_mobile_number);
        tv_email = root.findViewById(R.id.tv_email);

        rv_myplans = root.findViewById(R.id.rv_myplans);
//        cv_viewPlan = root.findViewById(R.id.cv_myplan);
//        tv_upgradePlan = root.findViewById(R.id.tv_upgradeplan);
//        tv_plan_name = root.findViewById(R.id.tv_plan_name);
//        btn_update = root.findViewById(R.id.btn_update);

        if (!sessionManager.checkLogin()) {
            HashMap<String, String> user = sessionManager.getUserDetails();
            user_id = user.get(UserSessionManager.KEY_UserId);
            auth_token = user.get(UserSessionManager.KEY_AuthToken);
            if (networkConnectivity.isOnline()) {

                progressDialog.setMessage("Fetching your profile...");
                progressDialog.show();
                getUserSubscription(auth_token);
            } else {
                SweetAlertDialog pDialog = new SweetAlertDialog(getActivity().getApplicationContext(), SweetAlertDialog.ERROR_TYPE);
                pDialog.setTitleText("Connection Error!");
                pDialog.setContentText("You are not connected to internet. Please check your internet connection.");
                pDialog.show();
            }

//            user_name = user.get(UserSessionManager.KEY_UserName);
//            user_mobile = user.get(UserSessionManager.KEY_Mobile);
//            user_email = user.get(UserSessionManager.KEY_Email);
        } else {
            Toast.makeText(getActivity().getApplicationContext(), "You are not logged in.", Toast.LENGTH_SHORT).show();
            btn_update.setEnabled(false);
        }

//        tv_customer_id.setText(user_id);
//        tv_customer_name.setText(user_name);
//        tv_mobile_number.setText(user_mobile);
//        tv_email.setText(user_email);


//        btn_update.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(getActivity().getApplicationContext(), EditProfileActivity.class);
//                intent.putExtra("name", user_name);
//                intent.putExtra("mobile", user_mobile);
//                startActivity(intent);
//            }
//        });

//        cv_viewPlan.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(getActivity().getApplicationContext(), MySubscriptionActivity.class);
//                intent.putExtra("my_plan", plan_id);
//                startActivity(intent);
//            }
//        });
//
//        tv_upgradePlan.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(getActivity().getApplicationContext(), PlanActivity.class);
//                intent.putExtra("upgrade", true);
//                startActivity(intent);
//            }
//        });
        return root;
    }

    private void getUserSubscription(String token) {
        APIService service = ClientInstance.getRetrofitInstance().create(APIService.class);
        Call<ProfileSubscription> call = service.getProfile("Bearer " + token);
        call.enqueue(new Callback<ProfileSubscription>() {
            @Override
            public void onResponse(Call<ProfileSubscription> call, Response<ProfileSubscription> response) {
                progressDialog.dismiss();
                if (response.isSuccessful())
                    if (response.body().isSuccess()) {
                        tv_customer_id.setText(response.body().getUser().getId());
                        tv_customer_name.setText(response.body().getUser().getName());
                        tv_email.setText(response.body().getUser().getEmail());
                        tv_mobile_number.setText(response.body().getUser().getPhone());

                        rv_myplans.setAdapter(new MyPlansAdapter(getActivity().getApplicationContext(), response.body().getSubscriptionData()));
                        rv_myplans.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false));
                    } else {
                        SweetAlertDialog pDialog = new SweetAlertDialog(getActivity().getApplicationContext(), SweetAlertDialog.ERROR_TYPE);
                        pDialog.setTitleText("Error!");
                        pDialog.setContentText("Internal error occured.");
                        pDialog.show();
                    }
                else {
                    SweetAlertDialog pDialog = new SweetAlertDialog(getActivity().getApplicationContext(), SweetAlertDialog.ERROR_TYPE);
                    pDialog.setTitleText("Response Error!");
                    pDialog.setContentText("Response is malformed.");
                    pDialog.show();
                }
            }

            @Override
            public void onFailure(Call<ProfileSubscription> call, Throwable t) {
                progressDialog.dismiss();
                SweetAlertDialog pDialog = new SweetAlertDialog(getActivity().getApplicationContext(), SweetAlertDialog.ERROR_TYPE);
                pDialog.setTitleText("Server Error!");
                pDialog.setContentText(t.getMessage());
                pDialog.show();
            }
        });
    }
}