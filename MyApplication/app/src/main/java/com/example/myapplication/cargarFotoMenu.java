package com.example.myapplication;

import static com.example.myapplication.R.*;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class cargarFotoMenu extends AppCompatActivity {
    ImageView imagen;
    Button btn_inicio;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout.activity_cargar_foto);

        imagen=(ImageView) findViewById(R.id.imagenId);



        btn_inicio=(Button) findViewById(R.id.inicio);
        btn_inicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(cargarFotoMenu. this, MainActivity.class);

                startActivity(intent);
            }
        });
    }



    //cargar fotos
    public void onClick(View view){
        cargarImagen();
    }
    private void cargarImagen(){
        Intent intent= new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        intent.setType("image/");
        startActivityForResult(intent.createChooser(intent,"Seleccione la app"),10);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode,resultCode,data);
        if(resultCode== RESULT_OK){
            Uri path= data.getData();
            imagen.setImageURI(path);
        }
    }
}