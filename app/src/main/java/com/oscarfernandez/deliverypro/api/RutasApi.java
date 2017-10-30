package com.oscarfernandez.deliverypro.api;

import android.os.AsyncTask;
import android.util.Log;

import com.oscarfernandez.deliverypro.controller.RutasController;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.ref.WeakReference;
import java.net.HttpURLConnection;
import java.net.SocketException;
import java.net.URL;

public class RutasApi {
    private final String URL_RUTAS = "http://190.196.114.17/dev/ordenapi.php";
    private WeakReference<RutasController> rutasController;

    private String createQueryRutas(String idUser) {
        String newUrl = URL_RUTAS + "?";
        newUrl = newUrl + "user=" + idUser;
        Log.d(newUrl, newUrl);
        return newUrl;
    }

    public void getDatosRutas(String idUser, RutasController rutasController){
        this.rutasController = new WeakReference<>(rutasController);
        RutasAsyncTask task = new RutasAsyncTask(idUser);
        task.execute();
    }



    private class RutasAsyncTask extends AsyncTask<String, Void, String> {

        private String idUser;

        public RutasAsyncTask(String idUser){
            this.idUser = idUser;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... strings) {
            try {
                URL url = new URL(createQueryRutas("1"));
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("GET");
                urlConnection.setRequestProperty("Content-length", "0");
                urlConnection.setUseCaches(false);
                urlConnection.setAllowUserInteraction(false);
                urlConnection.setConnectTimeout(10000);
                urlConnection.setReadTimeout(10000);

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


            } catch (SocketException e) {
               return "";
            }
            catch (IOException i){
                i.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            rutasController.get().sendResponse(s);

        }
    }
}
