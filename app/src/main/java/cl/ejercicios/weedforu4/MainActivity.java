package cl.ejercicios.weedforu4;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity implements View.OnClickListener {

    private EditText etx1,etx2;
    private Button btn;
    private Button btn2;
    daoUsuario dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etx1=(EditText)findViewById(R.id.editText);
        etx2=(EditText)findViewById(R.id.editText2);
        btn=(Button)findViewById(R.id.button);
        btn2=(Button)findViewById(R.id.button2);
        btn.setOnClickListener(this);
        btn2.setOnClickListener(this);
        dao=new daoUsuario(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button:
                String u=etx1.getText().toString();
                String p=etx2.getText().toString();
                if (u.equals("")&&p.equals("")){
                    Toast.makeText(this,"ERROR: Campos vacios",Toast.LENGTH_SHORT).show();
                }else if (dao.login(u,p)==1){
                    Usuario ux=dao.getUsuario(u,p);
                    Toast.makeText(this,"Datos correctos",Toast.LENGTH_SHORT).show();
                    Intent principal=new Intent(this,Main3Activity.class);
                    principal.putExtra("id",ux.getId());
                    startActivity(principal);
                    finish();
                }else{
                    Toast.makeText(this,"Email y/o contraseña incorrectos",Toast.LENGTH_SHORT).show();
                }
                break;

            case R.id.button2:
                Intent principal2= new Intent(this,Main2Activity.class);
                startActivity(principal2);
                break;

        }


    }
}
