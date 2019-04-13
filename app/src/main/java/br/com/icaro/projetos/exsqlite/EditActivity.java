package br.com.icaro.projetos.exsqlite;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class EditActivity extends AppCompatActivity {

    BDSQLiteHelper bd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        Intent intent = getIntent();
        final int id = intent.getIntExtra("ID", 0);
        bd = new BDSQLiteHelper(this);
        final Livro livro = bd.getLivro(id);

        final EditText nome = (EditText) findViewById(R.id.edNome);
        final EditText autor = (EditText) findViewById(R.id.edAutor);
        final EditText ano = (EditText) findViewById(R.id.edAno);
        Button alterar = (Button) findViewById(R.id.btnEditLivro);
        Button remover = (Button) findViewById(R.id.btnDeleteLivro);

        nome.setText(livro.getTitulo());
        autor.setText(livro.getAutor());
        ano.setText(String.valueOf(livro.getAno()));

        alterar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                livro.setTitulo(nome.getText().toString());
                livro.setAutor(autor.getText().toString());
                livro.setAno( Integer.parseInt(ano.getText().toString()));
                bd.updateLivro(livro);
                Toast.makeText(getBaseContext(), "Livro Alterado com sucesso!", Toast.LENGTH_SHORT).show();
                finish();
            }
        });

        remover.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               bd.deleteLivro(livro);
                Toast.makeText(getBaseContext(), "Livro Deletado!!", Toast.LENGTH_SHORT).show();
                finish();
            }
        });

    }
}
