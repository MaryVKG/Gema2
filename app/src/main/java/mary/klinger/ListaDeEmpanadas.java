package mary.klinger;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class ListaDeEmpanadas extends AppCompatActivity {

    private ListView lst1;
    private ArrayList<String> arreglo = new ArrayList<String>();
    private ArrayAdapter arrayAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_de_empanadas);

        try{
            SQLiteDatabase db = openOrCreateDatabase("BD_EJEMPLO", Context.MODE_PRIVATE,null);
            lst1 = findViewById(R.id.lst1);
            final Cursor c = db.rawQuery("select * from empanadas",null);
            int id = c.getColumnIndex("id");
            int codigoEmpanada = c.getColumnIndex("codigoEmpanada");
            int nombreEmpanada = c.getColumnIndex("nombreEmpanada");
            int precioEmpanada = c.getColumnIndex("precioEmpanada");
            arreglo.clear();

            arrayAdapter = new ArrayAdapter(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,arreglo);

            lst1.setAdapter(arrayAdapter);

            final  ArrayList<ProductosEmpanadas> lista = new ArrayList<ProductosEmpanadas>();


            if(c.moveToFirst())
            {
                do{
                    ProductosEmpanadas productosEmpanadas = new ProductosEmpanadas();
                    productosEmpanadas.id = c.getString(id);
                    productosEmpanadas.codigoEmpanada = c.getString(codigoEmpanada);
                    productosEmpanadas.nombreEmpanada = c.getString(nombreEmpanada);
                    productosEmpanadas.precioEmpanada = c.getString(precioEmpanada);
                    lista.add(productosEmpanadas);

                    arreglo.add(c.getString(id) + " \t " + c.getString(codigoEmpanada) + " \t "  + c.getString(nombreEmpanada) + " \t "  + c.getString(precioEmpanada) );

                } while(c.moveToNext());
                arrayAdapter.notifyDataSetChanged();
                lst1.invalidateViews();
            }

            lst1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, android.view.View view, int position, long l) {
                    ProductosEmpanadas empanadas = lista.get(position);
                    Intent i = new Intent(getApplicationContext(), EditarEmpanadas.class);
                    i.putExtra("id",empanadas.id);
                    i.putExtra("codigoEmpanada",empanadas.codigoEmpanada);
                    i.putExtra("nombreEmpanada",empanadas.nombreEmpanada);
                    i.putExtra("precioEmpanada",empanadas.precioEmpanada);
                    startActivity(i);
                }
            });
        }
        catch (Exception e){
            Toast.makeText(this, "Ha ocurrido un error, intentalo nuevamente.", Toast.LENGTH_SHORT).show();
        }


    }
}