package com.example.apptfg;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.provider.MediaStore;
import android.util.Log;
import android.widget.Toast;

import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.TimeUnit;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class ImageUploader extends AsyncTask<Uri, Void, String> {
    private Context mContext;
    private String mServerUrl;

    public ImageUploader(Context context, String serverUrl) {
        mContext = context;
        mServerUrl = serverUrl;
    }


    protected String doInBackground(Uri... uris) {
        HttpURLConnection connection = null;
        DataOutputStream outputStream = null;
        InputStream inputStream = null;
        String boundary = "*****";
        int bytesRead, bytesAvailable, bufferSize;
        byte[] buffer;
        int maxBufferSize = 1 * 1024 * 1024;

        try {
            URL url = new URL(mServerUrl);
            connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.setDoOutput(true);
            connection.setUseCaches(false);
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Connection", "Keep-Alive");
            connection.setRequestProperty("ENCTYPE", "multipart/form-data");
            connection.setRequestProperty("Content-Type", "multipart/form-data;boundary=" + boundary);
            outputStream = new DataOutputStream(connection.getOutputStream());
            inputStream = mContext.getContentResolver().openInputStream(uris[0]);
            bytesAvailable = inputStream.available();
            bufferSize = Math.min(bytesAvailable, maxBufferSize);
            buffer = new byte[bufferSize];
            bytesRead = inputStream.read(buffer, 0, bufferSize);
            while (bytesRead > 0) {
                outputStream.write(buffer, 0, bufferSize);
                bytesAvailable = inputStream.available();
                bufferSize = Math.min(bytesAvailable, maxBufferSize);
                bytesRead = inputStream.read(buffer, 0, bufferSize);
            }
            int serverResponseCode = connection.getResponseCode();
            String serverResponseMessage = connection.getResponseMessage();
            Log.d("ImageUploader", "Server response code: " + serverResponseCode);
            Log.d("ImageUploader", "Server response message: " + serverResponseMessage);
            inputStream.close();
            outputStream.flush();
            outputStream.close();
        } catch (Exception e) {
            Log.e("ImageUploader", "Exception: " + e.getMessage());
            return null;
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }

        return "Imagen subida correctamente";
    }

    @Override
    protected void onPostExecute(String result) {
        if (result != null) {
            Toast.makeText(mContext, result, Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(mContext, "Error al subir la imagen", Toast.LENGTH_SHORT).show();
        }
    }
}
