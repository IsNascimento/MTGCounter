package br.com.aiefoda.mtgcounter;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;


public class Configuracao extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener{

    SharedPreferences preferencias;

    private int vida;
    private int jogadores;
    private int facesDado;

    static Object tranca = new Object();

    private static BancoJogadores banco;

    static BancoJogadores bancoJogadores(Context context) {
        synchronized (tranca) {
            if (banco == null)
                banco = Room.databaseBuilder(context,
                        BancoJogadores.class, "base-1").build();
            return banco;
        }
    }
    private List<Map<String, String>> listaAtual;
    private Handler handlerThreadPrincipal;
    private Executor executorThreadDoBanco;
    private BancoJogadores base;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configuracao);

        handlerThreadPrincipal = new Handler(Looper.getMainLooper());
        executorThreadDoBanco = Executors.newSingleThreadExecutor();

        base = bancoJogadores(getApplicationContext());

        preferencias = getSharedPreferences("pref",0);
        vida = preferencias.getInt("vida",20);
        jogadores = preferencias.getInt("jogadores",2);
        facesDado = preferencias.getInt("dado",6);

        final TextView vidaT = findViewById(R.id.vidaTodos);
        vidaT.setText(String.valueOf(getVida()));
        final TextView jogadores = findViewById(R.id.jogadores);
        jogadores.setText(String.valueOf(getJogadores()));
        final TextView dado = findViewById(R.id.dado);
        dado.setText(String.valueOf(getFacesDado()));

        final ImageButton vidaMa = findViewById(R.id.vidaMais);
        vidaMa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getVida() < 30){
                    int x = getVida();
                    ++x;
                    setVida(x);
                    preferencias
                            .edit()
                            .putInt("vida",x)
                            .apply();
                }
                vidaT.setText(String.valueOf(getVida()));
            }
        });

        ImageButton vidaMe = findViewById(R.id.vidaMenos);
        vidaMe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getVida() > 20){
                    int x = getVida();
                    --x;
                    setVida(x);
                    preferencias
                            .edit()
                            .putInt("vida",x)
                            .apply();
                }
                vidaT.setText(String.valueOf(getVida()));
            }
        });

        ImageView maisJoga = findViewById(R.id.maisJogadores);
        maisJoga.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(getJogadores() < 4){
                    int x = getJogadores();
                    ++x;
                    setJogadores(x);
                    preferencias
                            .edit()
                            .putInt("jogadores",x)
                            .apply();
                }
                jogadores.setText(String.valueOf(getJogadores()));
            }
        });

        ImageView menosJoga = findViewById(R.id.menosJogadores);
        menosJoga.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getJogadores() > 2){
                    int x = getJogadores();
                    --x;
                    setJogadores(x);
                    preferencias
                            .edit()
                            .putInt("jogadores",x)
                            .apply();
                }
                jogadores.setText(String.valueOf(getJogadores()));
            }
        });

        ImageView menosDad = findViewById(R.id.menosDado);
        menosDad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getFacesDado() > 6){
                    int x = getFacesDado();
                    --x;
                    setFacesDado(x);
                    preferencias
                            .edit()
                            .putInt("dado",x)
                            .apply();
                }
                dado.setText(String.valueOf(getFacesDado()));
            }
        });

        ImageView maisDad = findViewById(R.id.maisDado);
        maisDad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getFacesDado() < 20){
                    int x = getFacesDado();
                    ++x;
                    setFacesDado(x);
                    preferencias
                            .edit()
                            .putInt("dado",x)
                            .apply();
                }
                dado.setText(String.valueOf(getFacesDado()));
            }
        });

    }

    private void preencherConteúdo() {
        ListView visaoLista = findViewById(R.id.visaoLista);

        if (listaAtual == null) {
            visaoLista.setAdapter(null);
            return;
        }

        SimpleAdapter adapter = new SimpleAdapter(this, listaAtual, android.R.layout.simple_list_item_1, new String[]{"nome"}, new int[]{android.R.id.text1});
        visaoLista.setAdapter(adapter);
    }

    public void rodarNaThreadPrincipal(Runnable acao) {
        handlerThreadPrincipal.post(acao);
    }

    public void rodarNaThreadDoBanco(Runnable acao) {
        /*if (executorThreadDoBanco == null){
            executorThreadDoBanco = Executors.newSingleThreadExecutor();
        }*/
        executorThreadDoBanco.execute(acao);
    }

    public void setVida(int vida) {
            this.vida = vida;
    }

    public int getVida() {
        return vida;
    }

    public int getJogadores() {
        return jogadores;
    }

    public void setJogadores(int jogadores) {
        this.jogadores = jogadores;
    }

    public int getFacesDado() {
        return facesDado;
    }

    public void setFacesDado(int facesDado) {
        this.facesDado = facesDado;
    }

    public void inserirJogador(Jogador jogador){
        JogadoresDao dao = base.jogadoresDao();
        dao.inserir(jogador);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        rodarNaThreadDoBanco(
                new Runnable() {
                    @Override
                    public void run() {
                        JogadoresDao dao = base.jogadoresDao();
                        List<Jogador> jogadores = dao.listar();
                        listaAtual = new ArrayList<>();
                        for (Jogador j : jogadores) {
                            Map<String, String> mapaJogadores = new HashMap<>();
                            mapaJogadores.put("nome", j.getNome());
                            mapaJogadores.put("vida", "" + j.getVida());
                            listaAtual.add(mapaJogadores);
                        }
                        rodarNaThreadPrincipal(new Runnable() {
                            @Override
                            public void run() {
                                preencherConteúdo();
                            }
                        });
                    }
                });

        return false;
    }
}
