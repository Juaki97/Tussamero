package com.joaquinjimenez.tussamero;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class MainActivity extends FragmentActivity {

    private Button bViaje, bRecarga, bInfo;
    private EditText editText;
    private TextView saldo;
    public static final double PRECIO_VIAJE = 0.70;
    public static double saldoFinal = 0, saldoIntroducido, valorSaldo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bViaje = (Button) findViewById(R.id.bViaje);
        bRecarga = (Button) findViewById(R.id.bRecarga);
        bInfo = (Button) findViewById(R.id.bInfo);
        editText = (EditText) findViewById(R.id.editText);
        saldo = (TextView) findViewById(R.id.saldo);

        CargarDatos();

        bInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Info = new Intent(getApplicationContext(), Info.class);
                startActivity(Info);
            }
        });

        bViaje.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //CargarDatos();
                if(!saldo.getText().equals("")) {
                    saldoFinal = Double.parseDouble(saldo.getText().toString()) - PRECIO_VIAJE;
                    saldo.setText(String.format("%.2f", saldoFinal));
                    GuardarDatos();
                }
            }
        });

        bRecarga.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //CargarDatos();
                if (!saldo.getText().equals("")) {
                    valorSaldo = Double.parseDouble(saldo.getText().toString());
                    saldoFinal = valorSaldo;
                    saldoIntroducido = Double.parseDouble(editText.getText().toString());
                    saldoFinal = saldoFinal + saldoIntroducido;
                    saldo.setText(String.format("%.2f", saldoFinal));
                    editText.setText("");
                    GuardarDatos();
                }
            }
        });
    }

    public void CargarDatos(){
        SharedPreferences datos = getSharedPreferences("DatosGuardados", Context.MODE_PRIVATE);

        if(datos.getString("nombre", "") == null || datos.getString("nombre", "").equals("")){
            saldo.setText("0");
        }else {
            saldo.setText(datos.getString("nombre", ""));
        }
    }

    public void GuardarDatos(){
        SharedPreferences datos = getSharedPreferences("DatosGuardados", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = datos.edit();
        //Coge en la variable "nombre" el texto que haya en "editText"
        String nombre = saldo.getText().toString();
        Double.parseDouble(nombre);
        //Guarda los datos anteriores en dicha variable
        editor.putString("nombre", nombre);
        editor.commit();
    }
}
