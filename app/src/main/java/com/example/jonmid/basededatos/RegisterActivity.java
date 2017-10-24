package com.example.jonmid.basededatos;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.jonmid.basededatos.Connection.Sqlite;
import com.example.jonmid.basededatos.Utilities.Utilities;

public class RegisterActivity extends AppCompatActivity {

    TextInputEditText textInputEditTextName;
    TextInputEditText textInputEditTextPhone;
    TextInputEditText textInputEditTextEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        textInputEditTextName = (TextInputEditText) findViewById(R.id.id_tv_name);
        textInputEditTextPhone = (TextInputEditText) findViewById(R.id.id_tv_phone);
        textInputEditTextEmail = (TextInputEditText) findViewById(R.id.id_tv_email);


    }

    public void onClickCreateUser(View view){
//        HACE REFERENCIA AL EVENTO CREATE USER
        createUser();
    }

    public void onClickSearchUser(View view){
//        HACE REFERENCIA AL EVENTO CREATE USER
        searchUser();
    }
    public void createUser(){
//
        Sqlite sqlite = new Sqlite(this, "bd_users", null, 1);
//        SE LE DA PERMISOS DE ESCRIBIR
        SQLiteDatabase db = sqlite.getWritableDatabase();

        ContentValues values = new ContentValues();
//        SI FUERAN DOS TABLAS SE DEBE CREAR DOS INSTANCIAS DE CONTENT VALUES
//        Y DE ID RESULT
//        CONTENT: ES COMO SI SE CREARA UN ARREGLO, INSTANCIANDO LA CLASE, VALUES TIENE EL VALOR DEL ARREGLO
//        CON EL VALUES SE ADICIONAN EL NUMERO DE CAMPOS DE LA BDATOS
        values.put(Utilities.TABLA_FIELD_NAME, textInputEditTextName.getText().toString());
        values.put(Utilities.TABLA_FIELD_PHONE, textInputEditTextPhone.getText().toString());
        values.put(Utilities.TABLA_FIELD_EMAIL, textInputEditTextEmail.getText().toString());

        // (nombre de la tabla, campo que va retornar, valores a guardar)
//        CON ESTA LINEA YA SE CREA EL REGISTRO EN LA TABLA
        Long idResult = db.insert(Utilities.TABLA_NAME, Utilities.TABLA_FIELD_ID, values);
//        MUESTRA EL ULTIMO ID INGRESADO
        Toast.makeText(this, "Id registro: "+idResult, Toast.LENGTH_SHORT).show();
    }


    public void searchUser(){
//      ABRIMOS CONEXION
        Sqlite sqlite = new Sqlite(this, "bd_users", null, 1);
//        SE LE DA PERMISOS DE ESCRIBIR
        SQLiteDatabase db = sqlite.getReadableDatabase();

//        busqueme lo que yo escriba en el edit text
        String[] params={textInputEditTextName.getText().toString()};
//        FIELDS: LOS CAMPOS QUE QUIERO QUE ME RETORNE
        String[] fields={Utilities.TABLA_FIELD_NAME,Utilities.TABLA_FIELD_PHONE,Utilities.TABLA_FIELD_EMAIL};

        try {
//        =? significa compare con lo que se pone en el formulario
            Cursor cursor = db.query(Utilities.TABLA_NAME, fields, Utilities.TABLA_FIELD_NAME + "=?", params, null, null, null);
//MOVEFIRST ME MUESTRA EL PRIMER CAMPO
            cursor.moveToFirst();
            String reg="Nombre " + cursor.getString(0)+"Telefono: "+cursor.getString(1);
            cursor.close();

            Toast.makeText(this, reg, Toast.LENGTH_SHORT).show();
        }catch (Exception e){
            Toast.makeText(this, "Usuario no encontrado", Toast.LENGTH_SHORT).show();
        }
    }
}
