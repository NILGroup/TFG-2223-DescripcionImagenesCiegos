package com.example.apptfg;

import android.os.AsyncTask;
import android.util.Log;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MyTask extends AsyncTask<Void, Void, String> {

    @Override
    protected String doInBackground(Void... voids) {
        OkHttpClient client = new OkHttpClient();

        String url = "http://192.168.1.45:5000";
        Request request = new Request.Builder()
                .url(url)
                .build();
        String requestString = request.toString();
        Log.d("TAG", "Cadena de solicitud: " + requestString);
        try (Response response = client.newCall(request).execute()) {
            String responseBody = response.body().string();
            Log.d("TAG", "Respuesta del servidor: " + responseBody);
            return responseBody;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected void onPostExecute(String result) {
        // Aquí puedes procesar la respuesta
    }
}

