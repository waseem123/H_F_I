package com.infostackresearch.homefit.http;

import com.infostackresearch.homefit.models.LoginData;
import com.infostackresearch.homefit.models.LoginModel;
import com.infostackresearch.homefit.models.PayData;
import com.infostackresearch.homefit.models.PaymentHash;
import com.infostackresearch.homefit.models.PlanResponse;
import com.infostackresearch.homefit.models.ProfileSubscription;
import com.infostackresearch.homefit.models.SignUp;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface APIService {
    //    @FormData
    @Headers("Content-Type: application/json")
    @POST("admin-login")
    Call<LoginModel> doLogin(@Body LoginData loginData);

    //    @FormUrlEncoded
    @GET("customer/active-sp")
    Call<PlanResponse> getAllPlans();

    @POST("customer-register")
    Call<SignUp> doSignUp(@Body LoginData loginData);

    @Headers("Content-Type: application/json")
    @GET("customer/sp/user-sp")
    Call<ProfileSubscription> getProfile(@Header("Authorization") String authToken);

    @Headers("Content-Type: application/json")
    @POST("get-hash")
    Call<PaymentHash> getHash(@Body PayData payData);

//    @FormUrlEncoded
//    @POST("admin-login.php")
//    Call<AdminModel> getAdminData(@Field("emp_code") String user_id);
//
//    @FormUrlEncoded
//    @POST("new-mpin.php")
//    Call<NewMPin> setMPin(@Field("emp_code") String user_id, @Field("mpin") String mpin);
//
//    @FormUrlEncoded
//    @POST("apply-leave.php")
//    Call<LeaveModel> applyLeave(@Field("emp_code") String emp_code,
//                                @Field("account_id") String account_id,
//                                @Field("joining_date") String joining_date,
//                                @Field("from_date") String from_date,
//                                @Field("to_date") String to_date,
//                                @Field("leave_id") String leave_id,
//                                @Field("leave_reason") String leave_reason,
//                                @Field("leave_day") String leave_day);
//
//    @FormUrlEncoded
//    @POST("leave-status.php")
//    Call<LeaveModel> leaveStatus(@Field("emp_code") String emp_code);
//
//    @FormUrlEncoded
//    @POST("leave-balance.php")
//    Call<LeaveModel> leaveBalance(@Field("emp_code") String emp_code, @Field("leave_id") String leave_id);
//
//    @FormUrlEncoded
//    @POST("leave-report.php")
//    Call<LeaveModel> leaveReport(@Field("emp_code") String emp_code);
//
//    @Multipart
//    @POST("mark-attendance.php")
//    Call<Punch> mark_attendance(
//            @Part("emp_code") RequestBody emp_code,
//            @Part("latitude") RequestBody latitude,
//            @Part("longitude") RequestBody longitude,
//            @Part("address") RequestBody address,
//            @Part MultipartBody.Part file
//    );
//
////    @FormUrlEncoded
////    @POST("/attendance-report.php")
////    Call<AttendanceHistory> getAttendanceReport(@Field("emp_code") String emp_code,
////                                                @Field("account_id") String account_id,
////                                                @Field("month")  String month,
////                                                @Field("monthtext") String month_text,
////                                                @Field("year") String year
////    );
//
//    @FormUrlEncoded
//    @POST("attendance-total-report.php")
//    Call<AttendanceHistory> getAttendanceReport(@Field("emp_code") String emp_code,
//                                                @Field("account_id") String account_id,
//                                                @Field("month") String month,
//                                                @Field("monthtext") String month_text,
//                                                @Field("year") String year
//    );
//
//    @FormUrlEncoded
//    @POST("get-punch.php")
//    Call<PunchDetails> getPunchDetails(@Field("emp_code") String emp_code);
//
//    @FormUrlEncoded
//    @POST("attendance-details.php")
//    Call<AttendanceDetails> getAttedanceDetails(@Field("emp_code") String emp_code,
//                                                @Field("unique_id") String unique_id);
//
////    @FormUrlEncoded
////    @POST("salary-report.php")
////    Call<SalaryReport> getSalaryDetails(@Field("month") String month, @Field("monthtext") String monthtext, @Field("year") String year, @Field("emp_code") String emp_code,@Field("account_id") String account_id);
//
//    @FormUrlEncoded
//    @POST("salary-report-new.php")
//    Call<SalaryReport> getSalaryDetails(@Field("salary_month") String month, @Field("emp_code") String emp_code);
//
//    @FormUrlEncoded
//    @POST("leave-master.php")
//    Call<LeaveMasterFetch> getLeaveMaster(@Field("emp_code") String emp_code, @Field("account_id") String account_id);
//
//    @FormUrlEncoded
//    @POST("login-status.php")
//    Call<LoginStatus> setLoginStatus(@Field("emp_code") String emp_code, @Field("login_status") int login_status);
//
//    @FormUrlEncoded
//    @POST("admin-loginstatus.php")
//    Call<LoginStatus> setAdminLoginStatus(@Field("emp_code") String emp_code, @Field("login_status") int login_status);
//
//    @FormUrlEncoded
//    @POST("admin-fingerdata.php")
//    Call<FingerDetails> getFingerData(@Field("emp_code") String emp_code, @Field("finger_name") String finger_name);
//
//    @FormUrlEncoded
//    @POST("admin-markattendance.php")
//    Call<Punch> mark_admin_attendance(@Field("emp_code") String emp_code, @Field("latitude") String latitude, @Field("longitude") String longitude, @Field("address") String address);
//
//    @FormUrlEncoded
//    @POST("admin-employees.php")
//    Call<AdminEmployeesModel> getAdminEmployees(@Field("emp_code") String emp_code);
//
//    @FormUrlEncoded
//    @POST("sendotp.php")
//    Call<SendOTPModel> sendOTP(@Field("emp_code") String emp_code);
//
//    @FormUrlEncoded
//    @POST("verifyotp.php")
//    Call<VerifyOTPModel> verifyOTP(@Field("emp_code") String emp_code, @Field("otp") String otp);
//
//    @FormUrlEncoded
//    @POST("admin-sendotp.php")
//    Call<SendOTPModel> sendAdminOTP(@Field("emp_code") String emp_code);
//
//    @FormUrlEncoded
//    @POST("admin-verifyotp.php")
//    Call<VerifyOTPModel> verifyAdminOTP(@Field("emp_code") String emp_code, @Field("otp") String otp);
//
//    @FormUrlEncoded
//    @POST("admin-newmpin.php")
//    Call<NewMPin> setAdminMPin(@Field("emp_code") String user_id, @Field("mpin") String mpin);
}
