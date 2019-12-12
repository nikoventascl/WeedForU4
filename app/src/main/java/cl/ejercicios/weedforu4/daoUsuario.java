package cl.ejercicios.weedforu4;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class daoUsuario {
    Context c;
    Usuario u;
    ArrayList<Usuario> lista;
    SQLiteDatabase sql;
    String bd="Usuarios";
    String tabla="create table if not exists usuario(id integer primary key autoincrement,email text, password text)";

    public daoUsuario(Context c){
        this.c=c;
        sql=c.openOrCreateDatabase(bd,c.MODE_PRIVATE,null);
        sql.execSQL(tabla);
        u=new Usuario();
    }

    public boolean insertUsuario(Usuario u){
    if (buscar(u.getEmail())==0){
        ContentValues cv=new ContentValues();
        cv.put("email",u.getEmail());
        cv.put("password",u.Password);
        return (sql.insert("usuario",null,cv)>0);
    }else{
        return false;
    }
    }

    public int buscar(String u){
        int x=0;
        lista=selectUsuario();
        for (Usuario us:lista){
            if (us.getEmail().equals(u)){
                x++;
            }
        }
        return x;

    }

    public ArrayList<Usuario> selectUsuario(){
        ArrayList<Usuario> lista=new ArrayList<Usuario>();
        lista.clear();
        Cursor cr=sql.rawQuery("select * from usuario",null);
        if (cr!=null&&cr.moveToFirst()){
            do{
                Usuario u=new Usuario();
                u.setId(cr.getInt(0));
                u.setEmail(cr.getString(1));
                u.setPassword(cr.getString(2));
                lista.add(u);
            }while (cr.moveToNext());
        }
        return lista;
    }

    public int login(String u, String p){
        int a=0;
        Cursor cr=sql.rawQuery("select * from usuario",null);
        if (cr!=null&&cr.moveToFirst()){
            do{
                if(cr.getString(1).equals(u)&&cr.getString(2).equals(p)){
                    a++;
                }
            }while (cr.moveToNext());
        }
        return a;
    }

    public Usuario getUsuario(String u, String p){
        lista=selectUsuario();
        for (Usuario us:lista){
            if (us.getEmail().equals(u)&&us.getPassword().equals(p)){
                return us;
            }
        }
        return null;
    }

    public Usuario getUsuarioByid(int id){
        lista=selectUsuario();
        for (Usuario us:lista){
            if (us.getId()==id){
                return us;
            }
        }
        return null;
    }

}
