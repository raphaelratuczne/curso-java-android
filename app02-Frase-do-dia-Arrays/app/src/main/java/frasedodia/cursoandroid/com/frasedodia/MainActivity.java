package frasedodia.cursoandroid.com.frasedodia;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private TextView textoNovaFrase;
    private Button botaoNovaFrase;

    private String[] frases = {
            "Na minha lápide podem escrever o seguinte: A partir de agora, não contem mais comigo",
            "Não é a vida que é dura, é você que é mole",
            "Se não puder ajudar, atrapalhe, afinal o importante e participar.",
            "O importante não é ganhar, o que importa é competir sem perder, nem empatar.",
            "A culpa é minha e eu coloco ela em quem eu quiser!",
            "Quando estiver triste e desanimado, e ainda assim conseguir ver uma luz lá no fim do túnel... Corre... Pode ser o Trem!",
            "O pior de não ter onde cair morto, é não ter onde ficar em pé vivo!!!",
            "Não deixe que nada te desanime, pois até um pé na bunda te empura pra frente.",
            "Se procuras uma mão disposta a te ajudar, tu a encontrarás no final do teu braço.",
            "Quando tiver um problema na sua vida que não tem solução, levante a cabeça, estufe o peito, encha a boca e fale bem alto: AGORA FUDEU",
            "Quem ri por último é retardado.",
            "Há males que vem pra pior.",
            "Depois da tempestade, vem a gripe.",
            "Fim de ano na escola é igual um filme famoso ai. À espera de um milagre.",
            "A preguiça é a mãe de todos os vícios, e como mãe, deve ser respeitada!",
            "A fé move montanhas, mas eu prefiro dinamite."
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textoNovaFrase = (TextView) findViewById(R.id.textoNovaFraseId);
        botaoNovaFrase = (Button) findViewById(R.id.botaoNovaFraseId);

        botaoNovaFrase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Random randomico = new Random();
                int numeroRandomico = randomico.nextInt( frases.length );

                textoNovaFrase.setText( frases[numeroRandomico] );
            }
        });

    }
}
