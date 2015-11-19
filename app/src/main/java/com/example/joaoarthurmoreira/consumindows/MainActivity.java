package com.example.joaoarthurmoreira.consumindows;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.joaoarthurmoreira.consumindows.webservice.Pessoa;
import com.example.joaoarthurmoreira.consumindows.webservice.Utils;

import roboguice.activity.RoboActivity;
import roboguice.inject.ContentView;
import roboguice.inject.InjectView;

@ContentView(R.layout.activity_main)
public class MainActivity extends RoboActivity {

    @InjectView(R.id.textView5)
    private TextView nome;
    @InjectView(R.id.textView11)
    private TextView sobrenome;
    @InjectView(R.id.textView8)
    private TextView email;
    @InjectView(R.id.textView7)
    private TextView endereco;
    @InjectView(R.id.textView4)
    private TextView cidade;
    @InjectView(R.id.textView3)
    private TextView estado;
    @InjectView(R.id.textView2)
    private TextView username;
    @InjectView(R.id.textView10)
    private TextView senha;
    @InjectView(R.id.textView9)
    private TextView nascimento;
    @InjectView(R.id.textView12)
    private TextView telefone;
    @InjectView(R.id.imageView)
    private ImageView foto;

    private ProgressDialog load;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);

        GetJson ws = new GetJson();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        ws.execute();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private class GetJson extends AsyncTask<Void, Void, Pessoa> {

        @Override
        protected void onPreExecute() {
            load = ProgressDialog.show(MainActivity.this, "por favor aguarde ...", "recuperando informações do servidor");
        }

        @Override
        protected Pessoa doInBackground(Void... params) {
            Utils util = new Utils();

            return util.getInformacao("https://randomuser.me/api/");
        }

        @Override
        protected void onPostExecute(Pessoa pessoa) {
            nome.setText(pessoa.getNome().substring(0, 1).toUpperCase() + pessoa.getNome().substring(1));
            sobrenome.setText(pessoa.getSobrenome().substring(0, 1).toUpperCase() + pessoa.getSobrenome().substring(1));
            email.setText(pessoa.getEmail());
            endereco.setText(pessoa.getEndereco());
            cidade.setText(pessoa.getCidade().substring(0, 1).toUpperCase() + pessoa.getCidade().substring(1));
            estado.setText(pessoa.getEstado());
            username.setText(pessoa.getUsername());
            senha.setText(pessoa.getSenha());
            nascimento.setText(pessoa.getNascimento());
            telefone.setText(pessoa.getTelefone());
            foto.setImageBitmap(pessoa.getFoto());
            load.dismiss();


        }
    }
}
