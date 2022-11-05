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

public class EditarEmpanadas extends AppCompatActivity {
    EditText editarCodigo, editarNombre, editarPrecio, editarId ;
    Button  btnEditar, btnEliminar;
    ImageView backBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_empanadas);


        editarCodigo = findViewById(R.id.et_codigo);
        editarNombre = findViewById(R.id.et_nombre);
        editarPrecio = findViewById(R.id.et_precio);
        editarId = findViewById(R.id.edit_id);
    //me falta el id

        backBtn = findViewById(R.id.back);
        btnEditar  = findViewById(R.id.editarEmpanada);
        btnEliminar = findViewById(R.id.borrarProductos);


        Intent i = getIntent();

        String codigoID = i.getStringExtra("id").toString();
        String codigoEmpanadita = i.getStringExtra("codigoEmpanada").toString();
        String codigoProdcutoNamw = i.getStringExtra("nombreEmpanada").toString();
        String codigoPrecio = i.getStringExtra("precioEmpanada").toString();

        editarId.setText(codigoID);
        editarCodigo.setText(codigoEmpanadita);
        editarNombre.setText(codigoProdcutoNamw);
        editarPrecio.setText(codigoPrecio);

        btnEditar.setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(android.view.View view) {
                editar();
            }
        });

        btnEliminar.setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(android.view.View view) {
                eliminar();
            }
        });

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditarEmpanadas.super.onBackPressed();
            }
        });
    }

    public void eliminar()
    {
        try
        {
            String id = editarId.getText().toString();

            SQLiteDatabase db = openOrCreateDatabase("BD_EJEMPLO", Context.MODE_PRIVATE,null);


            String sql = "delete from persona where id = ?";
            SQLiteStatement statement = db.compileStatement(sql);

            statement.bindString(1,id);
            statement.execute();
            Toast.makeText(this,"Datos eliminados de la base de datos.",Toast.LENGTH_LONG).show();

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
    public void editar()
    {
        try
        {
            String nombre = editarCodigo.getText().toString();
            String apellido = editarNombre.getText().toString();
            String edad = editarPrecio.getText().toString();
            String id = editarCodigo.getText().toString();

            SQLiteDatabase db = openOrCreateDatabase("BD_EJEMPLO",Context.MODE_PRIVATE,null);

            String sql = "update persona set nombre = ?,apellido=?,edad=? where id= ?";
            SQLiteStatement statement = db.compileStatement(sql);
            statement.bindString(1,nombre);
            statement.bindString(2,apellido);
            statement.bindString(3,edad);
            statement.bindString(4,id);
            statement.execute();
            Toast.makeText(this,"Datos actualizados satisfactoriamente en la base de datos.",Toast.LENGTH_LONG).show();

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