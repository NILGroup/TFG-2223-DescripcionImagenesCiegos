package com.example.apptfg;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    Button irCargarFoto;
    private RequestQueue queue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        irCargarFoto=(Button)findViewById(R.id.button_ir_pagina_foto);
        irCargarFoto.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent i= new Intent(MainActivity.this, cargar_foto_activity.class);
                startActivity(i);
            }
          });

        queue= Volley.newRequestQueue(this);
        ObtenerDatosVolley();
        int x=0;

    }
    private void ObtenerDatosVolley(){
        String url = "https://api.androidhive.info/contacts/";
        JsonObjectRequest request= new JsonObjectRequest(Request.Method.GET,url,null,new Response.Listener<JSONObject>(){
            @Override
            public void onResponse(JSONObject response){
                try {
                    JSONArray mJson = response.getJSONArray("imagenes");
                    JSONObject x= mJson.getJSONObject(0);
                    Toast.makeText(MainActivity.this,x.getString("name"),Toast.LENGTH_SHORT).show();
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }

            }
        }, new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError error){
                int x=0;
            }
        });

        queue.add(request);
    }
}