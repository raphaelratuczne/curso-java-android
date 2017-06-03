package dasolinaoualcool.cursoandroid.com.gasolinaoualcool;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private EditText precoAlcool;
    private EditText precoGasolina;
    private Button botaoVerificar;
    private TextView textoResultado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // localizar componentes na tela
        precoAlcool = (EditText) findViewById(R.id.precoAlcoolId);
        precoGasolina = (EditText) findViewById(R.id.precoGasolinaId);
        botaoVerificar = (Button) findViewById(R.id.botaoVerificarId);
        textoResultado = (TextView) findViewById(R.id.textoResultadoId);

        botaoVerificar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // recuperar valores digitados
                String textoPrecoAlcool = precoAlcool.getText().toString();
                String textoPrecoGasolina = precoGasolina.getText().toString();

                // converter valores strings para numeros
                double valorAlcool = Double.parseDouble( textoPrecoAlcool );
                double valorGasolina = Double.parseDouble( textoPrecoGasolina );

                // alcool / gasolina
                double resultado = valorAlcool / valorGasolina;

                if ( resultado >= 0.7 ) {
                    // Gasolina
                    textoResultado.setText("É melhor utilizar gasolina");
                } else {
                    // Alcool
                    textoResultado.setText("É melhor utilizar álcool");
                }
            }
        });
    }
}
