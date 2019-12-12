package cl.ejercicios.weedforu4.modelo;

import java.util.ArrayList;

public class ListDeCompras {
    private static ListDeCompras instancia=new ListDeCompras();
    private ArrayList<Producto> listaDeCompras;

    private ListDeCompras()
    {
        listaDeCompras=new ArrayList<>();
      //  agregarProducto(new Producto("Pan",1,"Kilo"));
    }
    public static ListDeCompras getInstancia()
    {
        return instancia;
    }
    public void agregarProducto(Producto producto)
    {
        listaDeCompras.add(producto);
    }
    public Producto getProducto(int id)
    {
        return listaDeCompras.get(id);
    }
    public ArrayList<Producto> getListaDeCompras()
    {
        return listaDeCompras;
    }
    public void eliminarComprados()
    {
        for(int i=0; i<listaDeCompras.size(); i++)
        {
            if(listaDeCompras.get(i).isEstado()==false)
            {
                listaDeCompras.remove(i);
                i--;
            }
        }
    }
}
