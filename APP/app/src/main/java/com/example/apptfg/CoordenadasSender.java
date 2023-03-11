package com.example.apptfg;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class CoordenadasSender extends AsyncTask<String, Void, String> {
    private String serverUrl;
    private Context mContext;
    private int x;
    private int y;

    public CoordenadasSender(Context context, String serverUrl, int x, int y) {
        this.serverUrl = serverUrl;
        this.x = x;
        this.y = y;
        this.mContext = context;
    }

    @Override
    protected String doInBackground(String... strings) {
        String g = "";
        try {
            URL url = new URL(serverUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json; utf-8");
            connection.setRequestProperty("Accept", "application/json");
            connection.setDoOutput(true);

            JSONObject jsonParam = new JSONObject();
            jsonParam.put("x", x);
            jsonParam.put("y", y);

            OutputStream os = connection.getOutputStream();
            byte[] input = jsonParam.toString().getBytes("utf-8");
            os.write(input, 0, input.length);

            BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream(), "utf-8"));
            StringBuilder response = new StringBuilder();
            String responseLine = null;
            while ((responseLine = br.readLine()) != null) {
                response.append(responseLine.trim());
            }

            Log.d("CoordenadasSender", "Server response code: " + connection.getResponseCode());
            Log.d("CoordenadasSender", "Server response message: " + connection.getResponseMessage());
            Log.d("CoordenadasSender", "Server response: " + response.toString());
            g = response.toString();
            os.close();
            br.close();
            connection.disconnect();
        } catch (Exception e) {
            Log.e("CoordenadasSender", "Error: " + e.getMessage());
        }

        return g;
    }
    protected void onPostExecute(String result) {
        if (result != null) {
            Toast.makeText(mContext, result, Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(mContext, "Error en las coordenadas", Toast.LENGTH_SHORT).show();
        }
    }

}
