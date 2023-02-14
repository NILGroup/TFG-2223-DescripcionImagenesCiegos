package com.example.apptfg;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

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

    }
    private void ObtenerDatosVolley(){
        String url = "";
        JSONObjectRequest request= new JSONObjectRequest(Request.Method.GET,url,null,new Response.Listener<JSONObject>(){
            @Override
            public void onResponse(JSONObject response){
                try {
                    JSONArray mJson = response.getJSONArray("contacts");
                    JSONObject x= mJson.getJSONObject(0);
                    Toast.makeText(MainActivity.this,x.getString("name"),Toast.LENGTH_SHORT).show();
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }

            }
        }, new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError error){

            }
        });

    }
}