package com.oscarfernandez.deliverypro.api;

import android.os.AsyncTask;

import com.oscarfernandez.deliverypro.controller.LoginController;
import com.oscarfernandez.deliverypro.domain.Login;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.ref.WeakReference;
import java.net.HttpURLConnection;
import java.net.URL;

public class LoginApi {
    private final String URL_LOGIN = "http://190.196.114.17/dev/userapi.php";
    private WeakReference<LoginController> loginControllerWeakReference;

    public void processLogin(Login login, LoginController loginController) {
        this.loginControllerWeakReference = new WeakReference<>(loginController);
        LoginAsyncTask task = new LoginAsyncTask(login);
        task.execute();
    }

    private String createQueryLogin(Login login) {
        String newUrl = URL_LOGIN + "?";
        newUrl = newUrl + "user=" + login.getRut() + "&pass=" + login.getPass();
        return newUrl;
    }

    private class LoginAsyncTask extends AsyncTask<String, Void, String> {

        private Login login;

        public LoginAsyncTask(Login login) {
            this.login = login;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... strings) {
            try {
                URL url = new URL(createQueryLogin(login));
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("GET");
                urlConnection.setRequestProperty("Content-length", "0");
                urlConnection.setUseCaches(false);
                urlConnection.setAllowUserInteraction(false);
                urlConnection.setConnectTimeout(100000);
                urlConnection.setReadTimeout(100000);

                urlConnection.connect();

                int responseCode = urlConnection.getResponseCode();

                if (responseCode == HttpURLConnection.HTTP_OK) {
                    BufferedReader br = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                    StringBuilder sb = new StringBuilder();
                    String line;
                    while ((line = br.readLine()) != null) {
                        sb.append(line + "\n");
                    }
                    br.close();
                    return sb.toString();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            loginControllerWeakReference.get().sendResponse(s);
        }
    }
}
