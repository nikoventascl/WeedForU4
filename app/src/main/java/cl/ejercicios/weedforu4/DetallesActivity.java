package cl.ejercicios.weedforu4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import cl.ejercicios.weedforu4.modelo.ComprasDatabaseHelper;
import cl.ejercicios.weedforu4.modelo.Producto;

public class DetallesActivity extends AppCompatActivity {

    private Producto producto;
    private Intent intent;
    private ComprasDatabaseHelper helper=new ComprasDatabaseHelper(this);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalles);

        //Obtener el producto
        intent=getIntent();

        //Leer el nombre del producto
        String nombreProducto=(String) intent.getExtras().get("nombreProducto");

        //Traer el producto desde la base de datos
        producto=helper.getProducto(nombreProducto);

        /*
        int id=(Integer) intent.getExtras().get("idProducto");
        producto= ListDeCompras.getInstancia().getProducto(id);
        */

        //Mostrar la información del producto
        TextView txtNombre=(TextView)findViewById(R.id.txtNombre);
        txtNombre.setText(producto.getNombre());

        TextView txtCantidad=(TextView)findViewById(R.id.txtCantidad);
        txtCantidad.setText("Cantidad: "+producto.getCantidad() +" "+ producto.getUnidad());

        TextView txtEstado=(TextView)findViewById(R.id.txtEstado);
        Button cambiar=(Button) findViewById(R.id.estado);
        if(producto.isEstado())
        {
            txtEstado.setText("Pendiente");
            cambiar.setText("Marcar como comprado");
        }
        else {
            txtEstado.setText("Comprado");
            cambiar.setText("Marcar como pendiente");
        }




    }

    public void cambiarEstado(View view)
    {
        producto.setEstado(!producto.isEstado());
        //Actualizar el estado en la base de datos
        String mensaje=helper.cambiarEstado(producto);
        Toast.makeText(this,mensaje,Toast.LENGTH_SHORT).show();

        setResult(RESULT_OK,intent);
        finish();
    }
}
