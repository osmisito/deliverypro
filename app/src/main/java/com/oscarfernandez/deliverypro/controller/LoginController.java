package com.oscarfernandez.deliverypro.controller;

import com.oscarfernandez.deliverypro.activity.LoginActivity;
import com.oscarfernandez.deliverypro.api.LoginApi;
import com.oscarfernandez.deliverypro.data.SharedPreferenceHelper;
import com.oscarfernandez.deliverypro.domain.Login;
import com.oscarfernandez.deliverypro.domain.User;

import org.json.JSONObject;

import java.lang.ref.WeakReference;

public class LoginController {

    private WeakReference<LoginActivity> loginActivity;

    private LoginApi api;

    public LoginController(LoginActivity activity) {
        this.loginActivity = new WeakReference<>(activity);
        api = new LoginApi();
    }

    public void processLogin(Login login) {
        api.processLogin(login, this);
    }

    public void sendResponse(String json) {
        User user = convertJson(json);
        if (user.getIsValid().equals("1")) {
            SharedPreferenceHelper.setSharedPreference(loginActivity.get(), "isLoged", true);
            SharedPreferenceHelper.setSharedPreference(loginActivity.get(), "idUser", user.getUserId());
            loginActivity.get().goToNextScreen();
        } else {
            SharedPreferenceHelper.setSharedPreference(loginActivity.get(), "isLoged", false);
            loginActivity.get().showError();
        }
    }

    private User convertJson(String json) {
        User user = new User();
        try {
            JSONObject jsonObject = new JSONObject(json);
            user.setUserId(jsonObject.getString("idUsuario"));
            user.setIsValid(jsonObject.getString("Existe"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }
}
