package com.example.sdist.pf;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    private EditText nom, mes, pers;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Inicializa las variables con la pantalla
        nom= (EditText) findViewById(R.id.etNombre);
        mes= (EditText) findViewById(R.id.etMesa);
        pers= (EditText) findViewById(R.id.etPersonas);
    }

    /*
    Método que sirve para dar de alta a las personas y la mesa en la que están en la base de
    datos. Tambiém cambia a la pantalla del menú para que empiecen a ordenar
     */
    public void alta(View v){

        //Acceso a la base de datos y permiso de escritura en ella
        conexionBD admin= new conexionBD(this, "tablaMesa", null,1);
        SQLiteDatabase db= admin.getWritableDatabase();
        //Obtiene los valores que se desean meter en la base
        String nombre= nom.getText().toString();
        String mesa= mes.getText().toString();
        String personas= pers.getText().toString();
        //Se crea el content values
        ContentValues registro= new ContentValues();
        registro.put("mesa", mesa);
        registro.put("nombre", nombre);
        registro.put("personas", personas);
        //Mete a la tabla los datos que tiene
        db.insert("tablaMesa", null, registro);
        //Se cierra la base de datos
        db.close();
        //Intent para cambiar de páginas
        Intent intent= new Intent(this, Menu.class);
        startActivity(intent);
    }
}
