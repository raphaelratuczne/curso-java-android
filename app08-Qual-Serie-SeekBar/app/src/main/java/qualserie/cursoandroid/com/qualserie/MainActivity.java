package qualserie.cursoandroid.com.qualserie;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.SeekBar;

public class MainActivity extends Activity {

    private SeekBar seekBar;
    private ImageView imagem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        seekBar = (SeekBar) findViewById(R.id.seekBarId);
        seekBar.setMax(4);
        imagem = (ImageView) findViewById(R.id.imgId);
        imagem.setImageResource(0);

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                switch (progress) {
                    case 1: imagem.setImageResource( R.drawable.pouco ); break;
                    case 2: imagem.setImageResource( R.drawable.medio ); break;
                    case 3: imagem.setImageResource( R.drawable.muito ); break;
                    case 4: imagem.setImageResource( R.drawable.susto ); break;
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }
}
