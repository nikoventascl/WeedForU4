package cl.ejercicios.weedforu4;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Main2Activity extends Activity implements View.OnClickListener {


    public EditText em,con1,con2;
    public Button btn;
    daoUsuario dao;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        em=(EditText) findViewById(R.id.editText3);
        con1=(EditText)findViewById(R.id.editText4);
        con2=(EditText)findViewById(R.id.editText5);
        btn=(Button)findViewById(R.id.button3);
        btn.setOnClickListener(this);
        dao=new daoUsuario(this);

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.button3:
                Usuario u=new Usuario();
                u.setEmail(em.getText().toString());
                u.setPassword(con1.getText().toString());
                u.setPassword(con2.getText().toString());
                if (!u.isNull()){
                    Toast.makeText(this,"ERROR: Campos vacios",Toast.LENGTH_SHORT).show();
                }else if (dao.insertUsuario(u)){
                    Toast.makeText(this, "Registrado Exitosamente", Toast.LENGTH_LONG).show();
                    Intent principal = new Intent(this,MainActivity.class);
                    startActivity(principal);
                }else{
                    Toast.makeText(this, "Email ya registrado", Toast.LENGTH_LONG).show();
                }
                break;
        }

    }
}
