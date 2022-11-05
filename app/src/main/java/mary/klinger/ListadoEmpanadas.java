package mary.klinger;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

public class ListadoEmpanadas extends AppCompatActivity {

    EditText editarCodigo, editarNombre, editarPrecio;
    Button btnCrear, btnVerList;
    ImageView backBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listado_empanadas);



        editarCodigo = findViewById(R.id.et_codigo);
        editarNombre = findViewById(R.id.et_nombre);
        editarPrecio = findViewById(R.id.et_precio);

        backBtn = findViewById(R.id.back);
        btnCrear  = findViewById(R.id.crearProducto);
        btnVerList = findViewById(R.id.verProductos);


        btnVerList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent i = new Intent(getApplicationContext(), ListaDeEmpanadas.class);
                startActivity(i);
            }
        });
        btnCrear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insertar();
            }
        });
    }

    public void insertar()
    {
        try
        {
            String codigoEmpanada = editarCodigo.getText().toString();
            String codigoNombre = editarNombre.getText().toString();
            String codigoPrecio = editarPrecio.getText().toString();

            SQLiteDatabase db = openOrCreateDatabase("BD_EJEMPLO", Context.MODE_PRIVATE,null);
            db.execSQL("CREATE TABLE IF NOT EXISTS persona(id INTEGER PRIMARY KEY AUTOINCREMENT,nombre VARCHAR,apellido VARCHAR,edad VARCHAR)");

            String sql = "insert into persona(nombre,apellido,edad)values(?,?,?)";
            SQLiteStatement statement = db.compileStatement(sql);
            statement.bindString(1,codigoEmpanada);
            statement.bindString(2,codigoNombre);
            statement.bindString(3,codigoPrecio);
            statement.execute();
            Toast.makeText(this,"Datos agregados satisfactoriamente en la base de datos.",Toast.LENGTH_LONG).show();

            editarCodigo.setText("");
            editarNombre.setText("");
            editarPrecio.setText("");
            editarCodigo.requestFocus();
        }
        catch (Exception ex)
        {
            Toast.makeText(this,"Error no se pudieron guardar los datos.",Toast.LENGTH_LONG).show();
        }
    }
}