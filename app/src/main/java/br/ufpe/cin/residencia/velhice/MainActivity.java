package br.ufpe.cin.residencia.velhice;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView resultText;
    private EditText inputNascimento;
    private Button calcular;
    private View viewCardResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();

        this.startComponents();
        this.calcular.setOnClickListener(this);

    }

    private void startComponents(){

        this.calcular = findViewById(R.id.btn_calcular);
        this.resultText = findViewById(R.id.text_Result);
        this.inputNascimento = findViewById(R.id.inputIdade);
        this.viewCardResult = findViewById(R.id.viewCardResult);

    }

    private void calcularIdade(String data, View v){
        if (!data.isEmpty()){

            SimpleDateFormat dataCascimento = new SimpleDateFormat("dd/MM/yyyy");
            try{
                Date d = dataCascimento.parse(data);
                long dataEmMinutos = d.getTime()/60000;
                Date agora = dataCascimento.parse(dataCascimento.format(System.currentTimeMillis()));
                long agoraEmMinutos = agora.getTime()/60000;
                long diferenca = agoraEmMinutos - dataEmMinutos;

                this.resultText.setText(String.valueOf(diferenca)+" Minutos");
                this.viewCardResult.setVisibility(View.VISIBLE);



            }catch (Exception e){
                //throw new RuntimeException();
                Snackbar snackbar1 = Snackbar.make(v, "Informe uma data válida!", Snackbar.LENGTH_SHORT);
                snackbar1.setBackgroundTint(Color.WHITE);
                snackbar1.setTextColor(Color.BLACK);
                snackbar1.show();

            }

        }

    }

    @Override
    public void onClick(View view) {

        String data = this.inputNascimento.getText().toString();
        if(view.getId() == R.id.btn_calcular){
            this.calcularIdade(data, view);
        }

    }
}
