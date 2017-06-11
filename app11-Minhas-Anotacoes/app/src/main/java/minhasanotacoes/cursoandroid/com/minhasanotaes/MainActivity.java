package minhasanotacoes.cursoandroid.com.minhasanotaes;

import android.app.Activity;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextPaint;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class MainActivity extends Activity {

    private EditText texto;
    private ImageView botao;
    private static final String ARQUIVO = "anotacao.txt";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        texto = (EditText) findViewById(R.id.textoId);
        botao = (ImageView) findViewById(R.id.botaoId);

        botao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String textoDigitado = texto.getText().toString();
                gravarNoArquivo( textoDigitado );
            }
        });

        // recuperar o que foi gravado
        if ( lerArquivo() != "" ) {
            texto.setText( lerArquivo() );
        }
    }

    private void gravarNoArquivo(String texto) {
        try {

            OutputStreamWriter outputStreamWriter = new OutputStreamWriter( openFileOutput(ARQUIVO, Context.MODE_PRIVATE) );
            outputStreamWriter.write( texto );
            outputStreamWriter.close();
            Toast.makeText(MainActivity.this, "Anotação salva com sucesso!", Toast.LENGTH_SHORT).show();

        } catch (IOException e) {
            Log.v("MainActivity", e.toString());
        }
    }

    private String lerArquivo() {

        String resultado = "";

        try {

            InputStream arquivo = openFileInput( ARQUIVO );
            if ( arquivo != null ) {

                // ler o arquivo
                InputStreamReader inputStreamReader = new InputStreamReader( arquivo );
                // gerar um buffer do arquivo lido
                BufferedReader bufferedReader = new BufferedReader( inputStreamReader );
                // recuperar textos do arquivo
                String linhaArquivo = "";
                while( (linhaArquivo = bufferedReader.readLine()) != null ) {
                    resultado += linhaArquivo + "\n";
                }

                arquivo.close();
            }

        } catch (IOException e) {
            Log.v("MainActivity", e.toString());
        }

        return resultado;
    }
}
