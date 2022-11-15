package mary.klinger;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class LeerEmpanada extends AppCompatActivity {

    private ListView lst1;
    private ArrayList<String> arreglo = new ArrayList<String>();
    private ArrayAdapter arrayAdapter;

    ImageView loginBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_empanadas);

        loginBack = findViewById(R.id.back);

        loginBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LeerEmpanada.super.onBackPressed();
            }
        });

        try{
            SQLiteDatabase db = openOrCreateDatabase("BD_EJEMPLO", Context.MODE_PRIVATE,null);
            lst1 = findViewById(R.id.lst1);
            final Cursor c = db.rawQuery("select * from persona",null);
            int id = c.getColumnIndex("id");
            int nombre = c.getColumnIndex("nombre");
            int apellido = c.getColumnIndex("apellido");
            int edad = c.getColumnIndex("edad");
            arreglo.clear();

            arrayAdapter = new ArrayAdapter(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,arreglo);

            lst1.setAdapter(arrayAdapter);

            final  ArrayList<Empanada> lista = new ArrayList<Empanada>();


            if(c.moveToFirst())
            {
                do{
                    Empanada empanada = new Empanada();
                    empanada.id = c.getString(id);
                    empanada.nombre = c.getString(nombre);
                    empanada.apellido = c.getString(apellido);
                    empanada.edad = c.getString(edad);
                    lista.add(empanada);

                    arreglo.add(c.getString(id) + " \t " + c.getString(nombre) + " \t "  + c.getString(apellido) + " \t "  + c.getString(edad) );

                } while(c.moveToNext());
                arrayAdapter.notifyDataSetChanged();
                lst1.invalidateViews();
            }

            lst1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, android.view.View view, int position, long l) {
                    Empanada empanada = lista.get(position);
                    Intent i = new Intent(getApplicationContext(), EditarEmpanada.class);
                    i.putExtra("id", empanada.id);
                    i.putExtra("nombre", empanada.nombre);
                    i.putExtra("apellido", empanada.apellido);
                    i.putExtra("edad", empanada.edad);
                    startActivity(i);
                }
            });
        }
        catch (Exception e){
            Toast.makeText(this, "Ha ocurrido un error, intentalo nuevamente.", Toast.LENGTH_SHORT).show();
        }

    }
}