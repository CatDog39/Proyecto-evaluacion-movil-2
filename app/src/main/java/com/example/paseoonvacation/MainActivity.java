package com.example.paseoonvacation;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText jetcantidad;
    TextView jtvfinca,jtvsobrecosto, jtvtotal;
    RadioButton jrbfinca_economica,jrbfinca_normal,jrbfinca_premium;
    CheckBox jcbtransporte;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        jetcantidad=findViewById(R.id.etcantidad);
        jtvfinca=findViewById(R.id.tvfinca);
        jtvsobrecosto=findViewById(R.id.tvsobrecosto);
        jtvtotal=findViewById(R.id.tvtotal);
        jrbfinca_economica=findViewById(R.id.rbfinca_economica);
        jrbfinca_normal=findViewById(R.id.rbfinca_normal);
        jrbfinca_premium=findViewById(R.id.rbfinca_premium);
        jcbtransporte=findViewById(R.id.cbtranporte);
    }

    public void Calcular_valor(View view){
        String cantidad_personas=jetcantidad.getText().toString();
        if (cantidad_personas.isEmpty()){
            Toast.makeText(this, "La cantidad de personas es requerida", Toast.LENGTH_SHORT).show();
            jetcantidad.requestFocus();
        }
        else{
            int cantidad,valor_finca,valor_sobrecosto,valor_total,valor_opcional;
            cantidad= Integer.parseInt(cantidad_personas);

            if (cantidad < 1){
                Toast.makeText(this, "La cantidad mínima requerida es 1", Toast.LENGTH_SHORT).show();
                jetcantidad.requestFocus();
            }
            else{
                if (jrbfinca_economica.isChecked()){
                    jtvfinca.setText("680000");
                    valor_finca=680000;
                }
                else{
                    if (jrbfinca_normal.isChecked()){
                        jtvfinca.setText("985000");
                        valor_finca=985000;
                    }
                    else {
                        jtvfinca.setText("1790000");
                        valor_finca=1790000;
                    }
                }

                if (cantidad <= 30){
                    jtvsobrecosto.setText("0%");
                    valor_sobrecosto=0;

                }
                else {
                    if (cantidad < 50){
                        jtvsobrecosto.setText("10%");
                        valor_sobrecosto= ((valor_finca * cantidad))*10/100;

                    }
                    else {
                        jtvsobrecosto.setText("15%");
                        valor_sobrecosto= ((valor_finca * cantidad))*15/100;

                    }
                }

                if (jcbtransporte.isChecked()){

                    valor_opcional= cantidad * 30000;

                }
                else {

                    valor_opcional= 0;

                }



                valor_total= (valor_finca * cantidad) + valor_sobrecosto + valor_opcional;
                jtvtotal.setText(String.valueOf(valor_total));

            }
        }

    }

    public void LimpiarDatos(View view){
        //System.out.println("El codigo pasa por aqui");
        jrbfinca_economica.setChecked(true);
        jcbtransporte.setChecked(false);

        jtvsobrecosto.setText("0");
        jtvfinca.setText("680000");
        // Error jtvtransporte se elimino al final
        jtvtotal.setText("0");
        jetcantidad.setText("");
        jetcantidad.requestFocus();
    }

}