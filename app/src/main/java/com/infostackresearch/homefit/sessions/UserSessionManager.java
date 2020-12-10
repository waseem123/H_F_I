package com.infostackresearch.homefit.sessions;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import com.infostackresearch.homefit.http.APIService;
import com.infostackresearch.homefit.http.ClientInstance;
import com.infostackresearch.homefit.ui.LoginActivity;

import java.util.HashMap;

public class UserSessionManager {

    public static final String KEY_UserId = "id";
    public static final String KEY_UserName = "username";
    public static final String KEY_Email = "email";
    public static final String KEY_Mobile = "mobile";
    public static final String KEY_Role = "role";
    public static final String IS_USER_LOGIN = "IsUserLoggedIn";
    public static final String KEY_AuthToken = "auth_token";
    public static final String KEY_ProfilePic = "profile_pic";
    public static final String PREFER_NAME = "HomeFitPref";

    //    Shared Preferences reference
    SharedPreferences preferences;
    //Editor reference for shared preferences
    Editor editor;
    //    context
    Context mContext;
    //    shared preference mode
    int PRIVATE_MODE = 0;


    //    Constructor
    public UserSessionManager(Context context) {
        this.mContext = context;
        preferences = mContext.getSharedPreferences(PREFER_NAME, PRIVATE_MODE);
        editor = preferences.edit();
    }

    //    Create Login Session
    public void createUserLoginSession(String user_id, String username, String email, String auth_token, String role) {//, String roleid, String rolename, String profileimage, String companyid, String companyname, String siteid, String sitename) {
        editor.putBoolean(IS_USER_LOGIN, true);
        editor.putString(KEY_UserId, user_id);
        editor.putString(KEY_UserName, username);
        editor.putString(KEY_Email, email);
        editor.putString(KEY_AuthToken, auth_token);
        editor.putString(KEY_Role, role);
        editor.commit();
    }

    /**
     * checkLogin method will check user login status
     * If false it will redirect the user to login page
     * Else do anything
     */
    public boolean checkLogin() {
        if (!(this.isUserLoggedIn())) {
//            user is not logged in redirect to login activity
            Intent intent = new Intent(mContext, LoginActivity.class);

//            Close all activities from stack
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

//            Add new flag to start new Activity
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

//            Starting login activity
            mContext.startActivity(intent);

            return true;
        }
        return false;
    }

    /**
     * Check For Login
     *
     * @return
     */
    public boolean isUserLoggedIn() {
        return preferences.getBoolean(IS_USER_LOGIN, false);
    }

    /**
     * Get Stored Session Data
     */
    public HashMap<String, String> getUserDetails() {
//        Use Hashmap to store user credentials
        HashMap<String, String> user = new HashMap<String, String>();

        user.put(KEY_UserId, preferences.getString(KEY_UserId, null));
        user.put(KEY_UserName, preferences.getString(KEY_UserName, null));
        user.put(KEY_Email, preferences.getString(KEY_Email, null));
        user.put(KEY_AuthToken, preferences.getString(KEY_AuthToken, null));
        user.put(KEY_Role, preferences.getString(KEY_Role, null));

        return user;
    }

    public void logoutUser(String emp_code) {
        APIService service = ClientInstance.getRetrofitInstance().create(APIService.class);

        /**
         * Clearing all data from shared preferrences
         */
        editor.clear();
        editor.commit();

        /**
         * After logout redirect user to LoginActivity
         */
        Intent intent = new Intent(mContext, LoginActivity.class);

        /**
         * Closing all activities
         */
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        /**
         * Add new flag to start new activity
         */
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        /**
         * Starting Login Activity
         */
        mContext.startActivity(intent);

    }

}

