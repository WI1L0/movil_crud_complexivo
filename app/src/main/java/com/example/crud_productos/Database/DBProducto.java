package com.example.crud_productos.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteConstraintException;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.crud_productos.Model.Producto;

import java.util.ArrayList;

public class DBProducto extends DB{

    Context cont;

    public DBProducto(@Nullable Context context) {
        super(context);
        this.cont = context;
    }

    public int CrearProducto(String codigo, String nombre, Double precio){
        int cod = 0, vrf = verificar(codigo, nombre);
        try {

            if (vrf == 1){
                Toast.makeText(cont, "El codigo ya esta registrado", Toast.LENGTH_SHORT).show();
            } else if (vrf == 2){
                Toast.makeText(cont, "El nombre ya esta registrado", Toast.LENGTH_SHORT).show();
            } else {

                SQLiteDatabase db = (new DB(cont)).getWritableDatabase();
                ContentValues values = new ContentValues();
                values.put("Codigo", codigo);
                values.put("Nombre", nombre);
                values.put("Precio", precio);

                cod = (int) db.insert(TABLE_PRODUCTO, null, values);
            }
        } catch (SQLiteConstraintException e){
            Toast.makeText(cont, "Codigo ya existente", Toast.LENGTH_SHORT).show();

        } catch ( Exception e1){
            Toast.makeText(cont, "Error crear Producto", Toast.LENGTH_SHORT).show();
        }
        return cod;
    }

    public int ActualizarProducto(String codigo, String nombre, Double precio){
        int cod = 0;
        try {
            SQLiteDatabase db = (new DB(cont)).getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put("Codigo", codigo);
            values.put("Nombre", nombre);
            values.put("Precio", precio);

            cod = (int) db.update(TABLE_PRODUCTO, values, "Codigo = ?", new String[]{codigo});
        } catch (Exception e){
            Toast.makeText(cont, "Error actualizar Producto", Toast.LENGTH_SHORT).show();
        }
        return cod;
    }

    public int EliminarProducto(String codigo){
        int cod = 0;
        try {

            if (verificar(codigo, "") != 1){
                Toast.makeText(cont, "El codigo ya esta registrado", Toast.LENGTH_SHORT).show();
            } else {
                SQLiteDatabase db = (new DB(cont)).getWritableDatabase();
                cod = (int) db.delete(TABLE_PRODUCTO, "Codigo = ?", new String[]{codigo});
            }
        } catch (Exception e){
            Toast.makeText(cont, "Error eliminar Producto", Toast.LENGTH_SHORT).show();
        }
        return cod;
    }


    public ArrayList<Producto> Buscarcodigo(String codigo){

        ArrayList<Producto> list = new ArrayList();
        Producto producto;

        try {
            SQLiteDatabase db = (new DB(cont)).getReadableDatabase();
            Cursor sql = db.rawQuery("SELECT Codigo, Nombre, Precio FROM " + TABLE_PRODUCTO + " WHERE Codigo = ?", new String[]{codigo});
            while (sql.moveToNext()){
                producto = new Producto(sql.getString(0), sql.getString(1), sql.getDouble(2));
                list.add(producto);
            }
        } catch (Exception e){
            Toast.makeText(cont, "Error Busacar por codigo de producto", Toast.LENGTH_SHORT).show();
        }
        return list;
    }

    public ArrayList<Producto> Buscarnombre(String nombre){

        ArrayList<Producto> list = new ArrayList();
        Producto producto;

        try {
            SQLiteDatabase db = (new DB(cont)).getReadableDatabase();
            Cursor sql = db.rawQuery("SELECT Codigo, Nombre, Precio FROM " + TABLE_PRODUCTO + " WHERE Nombre = ?", new String[]{nombre});
            while (sql.moveToNext()){
                producto = new Producto(sql.getString(0), sql.getString(1), sql.getDouble(2));
                list.add(producto);
            }
        } catch (Exception e){
            Toast.makeText(cont, "Error Busacar por nombre de producto", Toast.LENGTH_SHORT).show();
        }
        return list;
    }

    public int verificar(String codigo, String nombre){

        boolean est1 = false;
        boolean est2 = false;

        ArrayList list;

        try {
            SQLiteDatabase db = (new DB(cont)).getReadableDatabase();

            if (codigo.length() != 0){
                list = new ArrayList();
                Cursor sql = db.rawQuery("SELECT Codigo FROM " + TABLE_PRODUCTO + " WHERE Codigo = ?", new String[]{codigo});
                while (sql.moveToNext()){
                    list.add(sql.getString(0));
                }

                if (!list.isEmpty()){
                    est1 = true;
                }
            }

            if (nombre.length() != 0){
                list = new ArrayList();
                Cursor sql = db.rawQuery("SELECT Nombre FROM " + TABLE_PRODUCTO + " WHERE Nombre = ?", new String[]{nombre});
                while (sql.moveToNext()){
                    list.add(sql.getString(0));
                }

                if (!list.isEmpty()){
                    est2 = true;
                }
            }
        } catch (Exception e){
            Toast.makeText(cont, "Error al verificar", Toast.LENGTH_SHORT).show();
        }

        if (est1 == true){
            return 1;
        } else if (est2 == true){
            return 2;
        } else {
            return 0;
        }
    }
}
