package cl.ejercicios.weedforu4.modelo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class ComprasDatabaseHelper extends SQLiteOpenHelper {

    private static final String DB_NAME="productos.db";
    private static final int DB_VERSION=1;

    public ComprasDatabaseHelper(Context context) {

        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sqlTxt="create table PRODUCTOS("
                +"_id INTEGER PRIMARY KEY AUTOINCREMENT, "
                +"NOMBRE TEXT, "
                +"CANTIDAD INTEGER, "
                +"UNIDAD TEXT, "
                +"ESTADO INTEGER);";
        sqLiteDatabase.execSQL(sqlTxt);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public void ingresarProducto(Producto producto)
    {
        SQLiteDatabase db=getWritableDatabase();
        ContentValues valores=new ContentValues();
        valores.put("NOMBRE",producto.getNombre());
        valores.put("CANTIDAD",producto.getCantidad());
        valores.put("UNIDAD",producto.getUnidad());
        if(producto.isEstado())
        {
            valores.put("ESTADO",1);
        }
        else{
            valores.put("ESTADO",0);
        }
        db.insert("PRODUCTOS",null,valores);
        db.close();
    }

    public List<Producto> listaProductos()
    {
        List<Producto> productos=new ArrayList<>();
        SQLiteDatabase db=getReadableDatabase();
        Cursor cursor=db.query("PRODUCTOS",
                new String[]{"NOMBRE","CANTIDAD","UNIDAD","ESTADO"},
                null,null,null,null,
                null);
        cursor.moveToFirst();
        int estadoInt;
        boolean estado=false;
        do{
            estadoInt=cursor.getInt(3);
            if(estadoInt==1) estado=true;
            else estado=false;

            productos.add(new Producto(cursor.getString(0),
                    cursor.getInt(1),
                    cursor.getString(2),
                    estado));
        }while(cursor.moveToNext());
        cursor.close();
        db.close();

        return productos;
    }
    public Producto getProducto(String nombre)
    {
        Producto p;
        SQLiteDatabase db=getReadableDatabase();
        String sqlTxt="select NOMBRE, CANTIDAD, UNIDAD, ESTADO "
                +"FROM PRODUCTOS WHERE NOMBRE=? ";
        String[] argumentos=new String[]{nombre};
        try{
            Cursor cursor=db.rawQuery(sqlTxt,argumentos);
            cursor.moveToFirst();
            boolean estado=false;
            if(cursor.getInt(3)==1) estado=true;
            p=new Producto(cursor.getString(0),
                    cursor.getInt(1), cursor.getString(2),
                    estado);
        }catch (SQLException ex)
        {
            p=null;
        }
        return p;
    }
    public String cambiarEstado(Producto p)
    {
        int estadoInt;
        //p ya viene con el estado cambiado,
        //solo hay que cambiarlo en la BD.
        if(p.isEstado()) estadoInt=1;
        else estadoInt=0;
        String sqlTxt="UPDATE PRODUCTOS SET ESTADO=? "
                +"WHERE NOMBRE=? ";
        Object[] argumentos=new Object[]{estadoInt,p.getNombre()};
        try{
            getWritableDatabase().execSQL(sqlTxt,argumentos);
            return "Se cambió correctamente el estado";
        }catch (SQLException ex){
            return "ERROR: No se pudo cambiar el estado";
        }
    }
    public String eliminarComprados()
    {
        String sqlTxt="DELETE FROM PRODUCTOS WHERE ESTADO=0";
        try{
            getWritableDatabase().execSQL(sqlTxt);
            return "Se eliminaron todos los productos comprados";
        }
        catch (SQLException ex)
        {
            return "ERROR: No se pueden eliminar los productos";
        }
    }

}
