package cl.ejercicios.weedforu4;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import cl.ejercicios.weedforu4.modelo.ComprasDatabaseHelper;
import cl.ejercicios.weedforu4.modelo.Producto;

public class NuevoProductoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nuevo_producto);
    }
    public void ingresarProducto(View view)
    {
        String nombre=((TextView) findViewById(R.id.ingresarNombre)).getText().toString();
        String cantidadStr=
                ((TextView)findViewById(R.id.ingresarCantidad)).getText().toString();
        String unidad=
                ((Spinner)findViewById(R.id.ingresarForma)).getSelectedItem().toString();

        int cantidad=0;
        try{
            cantidad=Integer.parseInt(cantidadStr);
        }catch (NumberFormatException ex)
        {
            Toast.makeText(this,"Debe ingresar un número en la cantidad",
                    Toast.LENGTH_SHORT).show();
        }
        if(cantidad>0)
        {
            if(unidad.equals("Otro")){

            }
            Producto producto=new Producto(nombre,cantidad,unidad);
            ComprasDatabaseHelper helper=new ComprasDatabaseHelper(this);
            helper.ingresarProducto(producto);

            /*
            ListDeCompras listaDeCompras=ListDeCompras.getInstancia();
            listaDeCompras.agregarProducto(producto);
            */

            finish();
        }
        else {
            Toast.makeText(this,"Ingrese una cantidad mayor a cero",
                    Toast.LENGTH_SHORT).show();
        }
    }


}
