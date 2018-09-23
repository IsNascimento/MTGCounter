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
import android.widget.ArrayAdapter;
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


public class Configuracao extends AppCompatActivity {

    SharedPreferences preferencias;

    private int vida;
    private int jogadores;
    private int facesDado;
    private int fundo;

    private List<Map<String, String>> listaAtual;
    private Handler handlerThreadPrincipal;
    private Executor executorThreadDoBanco;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configuracao);

        handlerThreadPrincipal = new Handler(Looper.getMainLooper());
        executorThreadDoBanco = Executors.newSingleThreadExecutor();

        preferencias = getSharedPreferences("pref", 0);
        vida = preferencias.getInt("vida", 20);
        jogadores = preferencias.getInt("jogadores", 2);
        facesDado = preferencias.getInt("dado", 6);
        fundo = preferencias.getInt("fundo", 0);

        final TextView vidaT = findViewById(R.id.vidaTodos);
        vidaT.setText(String.valueOf(getVida()));
        final TextView jogadores = findViewById(R.id.jogadores);
        jogadores.setText(String.valueOf(getJogadores()));
        final TextView dado = findViewById(R.id.dado);
        dado.setText(String.valueOf(getFacesDado()));
        final TextView fundo = findViewById(R.id.fundo);
        fundo.setText(String.valueOf(getFundo()));

        final ImageButton vidaMa = findViewById(R.id.vidaMais);
        vidaMa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getVida() < 30) {
                    int x = getVida();
                    ++x;
                    setVida(x);
                    preferencias
                            .edit()
                            .putInt("vida", x)
                            .apply();
                }
                vidaT.setText(String.valueOf(getVida()));
            }
        });

        ImageButton vidaMe = findViewById(R.id.vidaMenos);
        vidaMe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getVida() > 20) {
                    int x = getVida();
                    --x;
                    setVida(x);
                    preferencias
                            .edit()
                            .putInt("vida", x)
                            .apply();
                }
                vidaT.setText(String.valueOf(getVida()));
            }
        });

        ImageView maisJoga = findViewById(R.id.maisJogadores);
        maisJoga.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getJogadores() < 4) {
                    int x = getJogadores();
                    ++x;
                    setJogadores(x);
                    preferencias
                            .edit()
                            .putInt("jogadores", x)
                            .apply();
                }
                jogadores.setText(String.valueOf(getJogadores()));
            }
        });

        ImageView menosJoga = findViewById(R.id.menosJogadores);
        menosJoga.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getJogadores() > 2) {
                    int x = getJogadores();
                    --x;
                    setJogadores(x);
                    preferencias
                            .edit()
                            .putInt("jogadores", x)
                            .apply();
                }
                jogadores.setText(String.valueOf(getJogadores()));
            }
        });

        ImageView menosDad = findViewById(R.id.menosDado);
        menosDad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getFacesDado() > 6) {
                    int x = getFacesDado();
                    --x;
                    setFacesDado(x);
                    preferencias
                            .edit()
                            .putInt("dado", x)
                            .apply();
                }
                dado.setText(String.valueOf(getFacesDado()));
            }
        });

        ImageView maisDad = findViewById(R.id.maisDado);
        maisDad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getFacesDado() < 20) {
                    int x = getFacesDado();
                    ++x;
                    setFacesDado(x);
                    preferencias
                            .edit()
                            .putInt("dado", x)
                            .apply();
                }
                dado.setText(String.valueOf(getFacesDado()));
            }
        });

        rodarNaThreadDoBanco(new Runnable() {
            @Override
            public void run() {
                BancoDeDados banco = BancoDeDados.getInstancia(Configuracao.this);
                PartidaDao partidaDao = banco.getPartidaDao();
                final List<Partida> partidas = partidaDao.listar();

                rodarNaThreadPrincipal(new Runnable() {
                    @Override
                    public void run() {
                        ArrayAdapter<Partida> adaptador = new ArrayAdapter<>(Configuracao.this,
                                android.R.layout.simple_list_item_1, partidas);
                        ListView listaResultados = findViewById(R.id.listaResultados);
                        listaResultados.setAdapter(adaptador);
                    }
                });

            }
        });


        ImageView maisFundo = findViewById(R.id.maisfundo);
        maisFundo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getFundo() < 2) {
                    int x = getFundo();
                    ++x;
                    setFundo(x);
                    preferencias
                            .edit()
                            .putInt("fundo", x)
                            .apply();
                }
                fundo.setText(String.valueOf(getFundo()));
            }
        });

        ImageView menosFundo = findViewById(R.id.menosfundo);
        menosFundo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getFundo() > 0) {
                    int x = getFundo();
                    --x;
                    setFundo(x);
                    preferencias
                            .edit()
                            .putInt("fundo", x)
                            .apply();
                }
                fundo.setText(String.valueOf(getFundo()));
            }
        });

    }
        void rodarNaThreadPrincipal(Runnable acao) {
        handlerThreadPrincipal.post(acao);
    }

    void rodarNaThreadDoBanco(Runnable acao) {
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

    public int getFundo() { return fundo; }

    public void setFundo(int fundo) { this.fundo = fundo; }
}
