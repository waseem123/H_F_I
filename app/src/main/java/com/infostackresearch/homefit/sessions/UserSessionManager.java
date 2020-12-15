package com.infostackresearch.homefit.sessions;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import com.infostackresearch.homefit.DeliveryAddressActivity;
import com.infostackresearch.homefit.ui.LoginActivity;
import com.infostackresearch.homefit.ui.PlanActivity;

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

    public static final String KEY_AddressID = "addressid";
    public static final String KEY_Address = "address";
    public static final String KEY_PlanId = "planid";
    public static final String KEY_Amount = "amount";
    public static final String KEY_Discount = "discount";
    public static final String KEY_PlanTitle = "plantitle";
    private static final String IS_CART_FULL = "IsCartFull";
    private static final String IS_ADDRESS_CHOSEN = "IsAddressChosen";
    private static final String KEY_DeliverTo = "DeliverTo";
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
    public void createUserLoginSession(String user_id, String username, String email, String auth_token, String role, String addressid, String address) {//, String roleid, String rolename, String profileimage, String companyid, String companyname, String siteid, String sitename) {
        editor.putBoolean(IS_USER_LOGIN, true);
        editor.putString(KEY_UserId, user_id);
        editor.putString(KEY_UserName, username);
        editor.putString(KEY_Email, email);
        editor.putString(KEY_AuthToken, auth_token);
        editor.putString(KEY_Role, role);
        editor.putString(KEY_AddressID, addressid);
        editor.putString(KEY_Address, address);
        editor.commit();
    }

    public void createPlanData(String planid, String plantitle, String planamount, String plandiscount) {
        editor.putBoolean(IS_CART_FULL, true);
        editor.putString(KEY_PlanId, planid);
        editor.putString(KEY_PlanTitle, plantitle);
        editor.putString(KEY_Amount, planamount);
        editor.putString(KEY_Discount, plandiscount);
        editor.commit();
    }

    /*Following code is to create address in session for user delivery*/

    public void createDeliveryAddress(String addressid, String address, String deliverTo) {
        editor.putBoolean(IS_ADDRESS_CHOSEN, true);
        editor.putString(KEY_AddressID, addressid);
        editor.putString(KEY_Address, address);
        editor.putString(KEY_DeliverTo, deliverTo);
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

    public boolean checkCart() {
        if (!(this.isCartFull())) {
//            user is not logged in redirect to login activity
            Intent intent = new Intent(mContext, PlanActivity.class);

////            Close all activities from stack
//            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//
////            Add new flag to start new Activity
//            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

//            Starting login activity
            mContext.startActivity(intent);

            return true;
        }
        return false;
    }

    private boolean isCartFull() {
        return preferences.getBoolean(IS_CART_FULL, false);
    }

    public boolean checkDeliveryAddress() {
        if (!(this.isDeliveryAddress())) {
//            user is not logged in redirect to login activity
            Intent intent = new Intent(mContext, DeliveryAddressActivity.class);
//
////            Close all activities from stack
//            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//
////            Add new flag to start new Activity
//            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

//            Starting login activity
            mContext.startActivity(intent);

            return true;
        }
        return false;
    }

    private boolean isDeliveryAddress() {
        return preferences.getBoolean(IS_ADDRESS_CHOSEN, false);
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

    public HashMap<String, String> getPlanData() {
        HashMap<String, String> plan = new HashMap<>();
        plan.put(KEY_PlanId, preferences.getString(KEY_PlanId, null));
        plan.put(KEY_AddressID, preferences.getString(KEY_AddressID, null));
        plan.put(KEY_Address, preferences.getString(KEY_Address, null));
        plan.put(KEY_PlanTitle, preferences.getString(KEY_PlanTitle, null));
        plan.put(KEY_Amount, preferences.getString(KEY_Amount, null));
        plan.put(KEY_Discount, preferences.getString(KEY_Discount, null));
        return plan;
    }


    public void logoutUser(String emp_code) {

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

