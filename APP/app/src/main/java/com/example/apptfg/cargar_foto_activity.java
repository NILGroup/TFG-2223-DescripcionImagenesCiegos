package com.example.apptfg;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class cargar_foto_activity extends AppCompatActivity {

    ImageView imagen;
    Button cargarImagen;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cargar_foto);

        imagen=(ImageView) findViewById(R.id.imagenCargada);

        //lo que hace el boton---------------
        cargarImagen= (Button) findViewById(R.id.cargarFoto);
        cargarImagen.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                cargarI();

            }
        });
        //-------------------------------



    }

    private void cargarI(){
        Intent i= new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        i.setType("image/");
        startActivityForResult(i.createChooser(i,"Seleccione foto"),10);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode== RESULT_OK){
            Uri path= data.getData();
            imagen.setImageURI(path);
        }
    }
}