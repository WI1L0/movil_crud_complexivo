package com.example.crud_productos;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.crud_productos.Database.DBProducto;
import com.example.crud_productos.Model.Producto;

import java.util.ArrayList;

public class Inicio extends AppCompatActivity {

    private EditText edtcodigo, edtnombre, edtprecio, edtbuscar;

    private Button btnbuscarnombre, btnbuscarcodigo, btneliminar, btncrear, btnactualizar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicioo);

        edtcodigo = (EditText) findViewById(R.id.edtcodigo);
        edtnombre = (EditText) findViewById(R.id.edtnombre);
        edtprecio = (EditText) findViewById(R.id.edtprecio);
        edtbuscar = (EditText) findViewById(R.id.edtbuscar);


        btnbuscarnombre = (Button) findViewById(R.id.btnbuscarnombre);
        btnbuscarcodigo = (Button) findViewById(R.id.btnbuscarcodigo);
        btneliminar = (Button) findViewById(R.id.btneliminar);
        btncrear = (Button) findViewById(R.id.btncrear);
        btnactualizar = (Button) findViewById(R.id.btnactualizar);

        btnactualizar.setEnabled(false);
        btneliminar.setEnabled(false);


        btncrear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (edtcodigo.getText().toString().length() == 0 || edtnombre.getText().toString().length() == 0 || edtprecio.getText().toString().length() == 0){
                    Toast.makeText(Inicio.this, "Uno o mas campos estan vacios", Toast.LENGTH_SHORT).show();
                } else {
                    DBProducto producto = new DBProducto(Inicio.this);

                    int vrf = producto.CrearProducto(edtcodigo.getText().toString(),
                            edtnombre.getText().toString(),
                            Double.valueOf(edtprecio.getText().toString()));

                    System.out.println("++++++++++++++++ " + vrf);

                    if (vrf != 0){
                        Toast.makeText(Inicio.this, "El Producto fue creado", Toast.LENGTH_SHORT).show();
                        limpiar();
                    } else {
                        Toast.makeText(Inicio.this, "El Producto no fue creado", Toast.LENGTH_SHORT).show();

                    }
                }
            }
        });

        btnactualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (edtcodigo.getText().toString().length() == 0 &&
                        edtnombre.getText().toString().length() == 0 &&
                        edtprecio.getText().toString().length() == 0){
                    Toast.makeText(Inicio.this, "Buscar el producto a actualizar", Toast.LENGTH_SHORT).show();
                } else {
                    DBProducto producto = new DBProducto(Inicio.this);

                    int vrf = producto.ActualizarProducto(edtcodigo.getText().toString(),
                            edtnombre.getText().toString(),
                            Double.valueOf(edtprecio.getText().toString()));

                    if (vrf != 0){
                        Toast.makeText(Inicio.this, "El Producto fue actualizado", Toast.LENGTH_SHORT).show();
                        limpiar();
                    } else {
                        Toast.makeText(Inicio.this, "El Producto no fue actualizado", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        btneliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (edtcodigo.getText().toString().length() == 0 &&
                        edtnombre.getText().toString().length() == 0 &&
                        edtprecio.getText().toString().length() == 0){
                    Toast.makeText(Inicio.this, "Buscar el producto a eliminar", Toast.LENGTH_SHORT).show();
                } else {
                    DBProducto producto = new DBProducto(Inicio.this);

                    int vrf = producto.EliminarProducto(edtcodigo.getText().toString());

                    if (vrf != 0){
                        Toast.makeText(Inicio.this, "El Producto fue eliminado", Toast.LENGTH_SHORT).show();
                        limpiar();
                    } else {
                        Toast.makeText(Inicio.this, "El Producto no fue eliminado", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        btnbuscarcodigo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (edtbuscar.getText().toString().length() == 0){
                    Toast.makeText(Inicio.this, "Campo buscar vacio", Toast.LENGTH_SHORT).show();
                } else {
                    DBProducto producto = new DBProducto(Inicio.this);

                    ArrayList<Producto> productos = new ArrayList<>();

                    productos = producto.Buscarcodigo(edtbuscar.getText().toString());

                    if (productos.isEmpty()){
                        Toast.makeText(Inicio.this, "No se encontraron datos", Toast.LENGTH_SHORT).show();
                    } else {
                        edtcodigo.setText(productos.get(0).getCodigo());
                        edtnombre.setText(productos.get(0).getNombre());
                        edtprecio.setText(String.valueOf(productos.get(0).getPrecio()));

                        btnactualizar.setEnabled(true);
                        btneliminar.setEnabled(true);

                        btncrear.setEnabled(false);
                        edtbuscar.setText("");

                        Toast.makeText(Inicio.this, "Datos cargados correctamente", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        btnbuscarnombre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (edtbuscar.getText().toString().length() == 0){
                    Toast.makeText(Inicio.this, "Campo buscar vacio", Toast.LENGTH_SHORT).show();
                } else {
                    DBProducto producto = new DBProducto(Inicio.this);

                    ArrayList<Producto> productos = new ArrayList<>();

                    productos = producto.Buscarnombre(edtbuscar.getText().toString());

                    if (productos.isEmpty()){
                        Toast.makeText(Inicio.this, "No se encontraron datos", Toast.LENGTH_SHORT).show();
                    } else {
                        edtcodigo.setText(productos.get(0).getCodigo());
                        edtnombre.setText(productos.get(0).getNombre());
                        edtprecio.setText(String.valueOf(productos.get(0).getPrecio()));

                        btnactualizar.setEnabled(true);
                        btneliminar.setEnabled(true);

                        btncrear.setEnabled(false);
                        edtbuscar.setText("");

                        Toast.makeText(Inicio.this, "Datos cargados correctamente", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    public void limpiar(){
        edtbuscar.setText("");
        edtcodigo.setText("");
        edtnombre.setText("");
        edtprecio.setText("");

        btncrear.setEnabled(true);
        btnactualizar.setEnabled(false);
        btneliminar.setEnabled(false);
    }
}
