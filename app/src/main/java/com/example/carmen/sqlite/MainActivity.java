package com.example.carmen.sqlite;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
private EditText et1,et2,et3,et4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        et1 = (EditText) findViewById(R.id.editText);
        et2 = (EditText) findViewById(R.id.editText2);
        et3 = (EditText) findViewById(R.id.editText3);
        et4 = (EditText) findViewById(R.id.editText4);
    }
public void alta(View v){
        ADMINSQLITEOPENHELPER admin=new ADMINSQLITEOPENHELPER(this,"administracion",null,1);
    SQLiteDatabase bd= admin.getWritableDatabase();
        String codigo=et1.getText().toString();
        String descripcion=et2.getText().toString();
        String existencias =et3.getText().toString();
        String ubicacion=et4.getText().toString();
    ContentValues registro = new ContentValues();
        registro.put("Codigo",codigo);
        registro.put("Descripcion",descripcion);
        registro.put("Existencias",existencias);
        registro.put("Ubicacion",ubicacion);
        bd.insert("articulos",null,registro);
        bd.close();
    et1.setText("");
        et2.setText("");
        et3.setText("");
        et4.setText("");
            Toast.makeText(this,"alta registrada", Toast.LENGTH_SHORT).show();
    }
    public void consulta(View v) {
        ADMINSQLITEOPENHELPER admin = new ADMINSQLITEOPENHELPER(this,"administracion", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();
        String codigo = et1.getText().toString();

        Cursor fila = bd.rawQuery(
                "select Descripcion,Existencias,Ubicacion from articulos where Codigo=" + codigo, null);

        if (fila.moveToFirst()) {
            et2.setText(fila.getString(0));
            et3.setText(fila.getString(1));
            et4.setText(fila.getString(2));
        } else Toast.makeText(this, "\"select Descripcion,Existencias,Ubicacion from articulos where Codigo 1=\"",Toast.LENGTH_SHORT).show();

        bd.close();
    }
    public void baja(View v){
        ADMINSQLITEOPENHELPER admin=new ADMINSQLITEOPENHELPER(this,"administracion",null,1);
        SQLiteDatabase bd= admin.getWritableDatabase();
        String codigo=et1.getText().toString();
       int cant=bd.delete("Articulos","Codigo="+codigo,null);
        bd.close();
        et1.setText("");
        et2.setText("");
        et3.setText("");
        et4.setText("");
        if (cant==1)
            Toast.makeText(this,"RESISTRO BORRADO", Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(this,"REGISTRO BORRADO", Toast.LENGTH_SHORT).show();
    }
    public void cambio(View v) {
        ADMINSQLITEOPENHELPER admin = new ADMINSQLITEOPENHELPER(this,"administracion", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();
        String codigo = et1.getText().toString();
        String descripcion = et2.getText().toString();
        String existencias = et3.getText().toString();
        String ubicacion = et4.getText().toString();
        ContentValues registro = new ContentValues();
        registro.put("Descripcion", descripcion);
        registro.put("Existencias", existencias);
        registro.put("Ubicacion", ubicacion);
        int cant = bd.update("articulos", registro, "Codigo=" + codigo, null);
        bd.close();
        if (cant == 1)
            Toast.makeText(this, "Cambio Registrado", Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(this, "Codigo Inexistente",
                    Toast.LENGTH_SHORT).show();
    }

}
