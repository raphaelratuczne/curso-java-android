package todolist.cursoandroid.com.todolist;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends Activity {

    private EditText texto;
    private Button botao;
    private ListView lista;
    private SQLiteDatabase banco;

    private ArrayAdapter<String> itensAdp;
    private ArrayList<String> itens;
    private ArrayList<Integer> ids;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {

            texto = (EditText) findViewById(R.id.textoId);
            botao = (Button) findViewById(R.id.botaoId);
            lista = (ListView) findViewById(R.id.listaId);

            // banco de dados
            banco = openOrCreateDatabase("apptarefas", MODE_PRIVATE, null);
            // criar tabelas
            banco.execSQL("CREATE TABLE IF NOT EXISTS tarefas(id INTEGER PRIMARY KEY AUTOINCREMENT, tarefa VARCHAR)");

            botao.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    String txt = texto.getText().toString();
                    salvarTarefa( txt );

                }
            });

            lista.setLongClickable(true);
            lista.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                @Override
                public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                    removerTarefa( ids.get( position ) );
                    return false;
                }
            });

            // lista as tarefas
            recuperarTarefas();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void salvarTarefa(String txt) {

        try {
            if ( txt.equals("") ) {
                Toast.makeText(MainActivity.this, "Digite uma tarefa", Toast.LENGTH_SHORT).show();
            } else {
                banco.execSQL("INSERT INTO tarefas (tarefa) VALUES ('" + txt + "')");
                Toast.makeText(MainActivity.this, "Tarefa adicionada", Toast.LENGTH_SHORT).show();
                recuperarTarefas();
                texto.setText("");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void recuperarTarefas() {
        try {
            // recupera as tarefas
            Cursor cursor = banco.rawQuery("SELECT * FROM tarefas ORDER BY id DESC", null);

            // recupera os ids das colunas
            int id = cursor.getColumnIndex("id");
            int tarefa = cursor.getColumnIndex("tarefa");

            // criar adaptador
            itens = new ArrayList<String>();
            ids = new ArrayList<Integer>();
//            itensAdp = new ArrayAdapter<String>(getApplicationContext(),
//                                                android.R.layout.simple_list_item_2,
//                                                android.R.id.text2,
//                                                itens);

            itensAdp = new ArrayAdapter<String>(this,
                    android.R.layout.simple_list_item_2,
                    android.R.id.text2,
                    itens){
                @NonNull
                @Override
                public View getView(int position, View convertView, ViewGroup parent) {
                    View view = super.getView(position, convertView, parent);

                    TextView textView = (TextView) view.findViewById(android.R.id.text2);
                    textView.setTextColor(ActivityCompat.getColor(getApplicationContext(), R.color.colorAccent));

                    return view;
                }
            };

            lista.setAdapter(itensAdp);

            // listar as tarefas
            cursor.moveToFirst();
            while ( cursor != null ) {

                itens.add( cursor.getString( tarefa ) );
                ids.add( Integer.parseInt( cursor.getString( id ) ) );

                cursor.moveToNext();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void removerTarefa(Integer id) {
        try {

            banco.execSQL("DELETE FROM tarefas WHERE id=" + id);
            recuperarTarefas();
            Toast.makeText(MainActivity.this, "Tarefa removida", Toast.LENGTH_SHORT).show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
