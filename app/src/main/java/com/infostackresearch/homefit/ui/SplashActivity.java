package com.infostackresearch.homefit.ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.infostackresearch.homefit.R;


public class SplashActivity extends AppCompatActivity {
    Animation animation_top, animation_bottom;

    ImageView iv_logo;
    TextView tv_logo;
    RelativeLayout relativeLayout;
//    private UserSessionManager session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash);
//        session = new UserSessionManager(getApplicationContext());
        animation_top = AnimationUtils.loadAnimation(this, R.anim.top_animation);
        animation_bottom = AnimationUtils.loadAnimation(this, R.anim.bottom_animation);

        iv_logo = findViewById(R.id.iv_logo);
        tv_logo = findViewById(R.id.tv_logo);
//        relativeLayout = findViewById(R.id.fl_footer);

        iv_logo.setAnimation(animation_top);
        tv_logo.setAnimation(animation_bottom);
//        relativeLayout.setAnimation(animation_bottom);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
//
//                if (!session.checkLogin()) {
//                    HashMap<String, String> user = session.getUserDetails();
//                    String usertype = user.get(UserSessionManager.KEY_UserType);
//                    if (usertype.equals("admin") || usertype == "admin") {
//                        Intent intent = new Intent(SplashActivity.this, AdminAttendanceActivity.class);
//                        startActivity(intent);
//                        finish();
//                    } else {
//                        Intent intent = new Intent(SplashActivity.this, DashboardActivity.class);
//                        startActivity(intent);
//                        finish();
//                    }
//                }
                Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(intent);
                finish();

            }
        }, 5000);

    }
}
