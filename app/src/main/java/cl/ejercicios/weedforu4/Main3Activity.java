package cl.ejercicios.weedforu4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

import cl.ejercicios.weedforu4.modelo.ComprasDatabaseHelper;
import cl.ejercicios.weedforu4.modelo.ListDeCompras;
import cl.ejercicios.weedforu4.modelo.Producto;

public class Main3Activity extends AppCompatActivity {

    private ListDeCompras lista= ListDeCompras.getInstancia();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
    }

    public void verLista(View view)
    {

        ComprasDatabaseHelper helper=new ComprasDatabaseHelper(this);
        try{
            ArrayList<Producto> productos=(ArrayList<Producto>)helper.listaProductos();
            Intent intent=new Intent(this, ListaProductosActivity.class);
            startActivity(intent);
        }
        catch (Exception ex)
        {
            Toast.makeText(this,"Lista Vacía",Toast.LENGTH_SHORT).show();
        }

    }
    public void ingresarNuevo(View view)
    {
        Intent intent=new Intent(this, NuevoProductoActivity.class);
        startActivity(intent);
    }

    public void eliminarProducto(View view)
    {
        ComprasDatabaseHelper helper=new ComprasDatabaseHelper(this);
        String msg=helper.eliminarComprados();
        Toast.makeText(this,msg,Toast.LENGTH_SHORT).show();
    }

}
