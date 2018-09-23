package br.com.aiefoda.mtgcounter;

import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.Random;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;


public class Contador extends AppCompatActivity {
    SharedPreferences prefencias;

    private Handler handlerThreadPrincipal;
    private Executor executorThreadDoBanco;

    MediaPlayer choriugann = null;
    MediaPlayer hadouken = null;
    MediaPlayer lose = null;
    MediaPlayer moedamario = null;
    MediaPlayer round = null;
    MediaPlayer soco = null;
    MediaPlayer tatagtaruguen = null;
    MediaPlayer mortal = null;
    MediaPlayer top = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        prefencias = getSharedPreferences("pref", 0);
        switch (prefencias.getInt("jogadores", 2)) {
            case 2:
                setContentView(R.layout.activity_contador);
                break;
            case 3:
                setContentView(R.layout.activity_contador3);
                break;
            case 4:
                setContentView(R.layout.activity_contador4);
                break;
            default:
                setContentView(R.layout.activity_contador);
        }

        handlerThreadPrincipal = new Handler(Looper.getMainLooper());
        executorThreadDoBanco = Executors.newSingleThreadExecutor();

        final Jogador jogador1 = new Jogador();
        final Jogador jogador2 = new Jogador();
        final Jogador jogador3 = new Jogador();
        final Jogador jogador4 = new Jogador();

        final TextView marca = findViewById(R.id.vidaJogador1);
        final TextView marca2 = findViewById(R.id.vidaJogador2);
        final TextView marca3 = findViewById(R.id.vidaJogador3);
        final TextView marca4 = findViewById(R.id.vidaJogador4);
        final EditText nome1 = findViewById(R.id.nome1);
        final EditText nome2 = findViewById(R.id.nome2);
        final EditText nome3 = findViewById(R.id.nome3);
        final EditText nome4 = findViewById(R.id.nome4);
        nome2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nome2.setText("");
            }
        });

        nome1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nome1.setText("");
            }
        });

        jogador1.setVida(prefencias.getInt("vida", 20));
        marca.setText(Integer.toString(jogador1.getVida()));
        nome1.setText(prefencias.getString("nome1", "Jogador1"));
        jogador2.setVida(prefencias.getInt("vida", 20));
        nome2.setText(prefencias.getString("nome2", "Jogador2"));
        marca2.setText(Integer.toString(jogador2.getVida()));
        if (prefencias.getInt("jogadores", 2) > 2) {
            nome3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    nome3.setText("");
                }
            });
            jogador3.setVida(prefencias.getInt("vida", 20));
            marca3.setText(Integer.toString(jogador3.getVida()));
            nome3.setText(prefencias.getString("nome3", "Jogador3"));
        }
        if (prefencias.getInt("jogadores", 2) > 3) {
            nome4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    nome4.setText("");
                }
            });
            jogador4.setVida(prefencias.getInt("vida", 20));
            marca4.setText(Integer.toString(jogador4.getVida()));
            nome4.setText(prefencias.getString("nome4", "Jogador4"));
        }

        ImageButton botaoMais1 = findViewById(R.id.maisVidaJogador1);
        botaoMais1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tocarMaisVida();
                marca.setText(Integer.toString(jogador1.maisVida()));
            }
        });

        ImageButton botaoMenos1 = findViewById(R.id.menosVidaJogador1);
        botaoMenos1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                marca.setText(Integer.toString(jogador1.menosVida()));
                if (jogador1.getVida() == 0){
                    tocarYouLose();
                } else {
                    tocarMenosVida();
                }
            }
        });

        ImageButton botaoMais2 = findViewById(R.id.maisVidaJogador2);
        botaoMais2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tocarMaisVida();
                marca2.setText(Integer.toString(jogador2.maisVida()));
            }
        });

        ImageButton botaoMenos2 = findViewById(R.id.menosVidaJogador2);
        botaoMenos2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                marca2.setText(Integer.toString(jogador2.menosVida()));
                if (jogador2.getVida() == 0){
                    tocarYouLose();
                } else {
                    tocarMenosVida();
                }
            }
        });

        Button sortear = findViewById(R.id.sort);
        sortear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView sortValor = findViewById(R.id.sortValor);
                Random sorteio = new Random();
                sortValor.setText(Integer.toString(sorteio.nextInt((prefencias.getInt("dado", 6))) + 1));
            }
        });

        ImageButton reset = findViewById(R.id.botaoResetar);
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                jogador1.setNome(nome1.getText().toString());
                prefencias
                        .edit()
                        .putString("nome1",jogador1.getNome())
                        .apply();

                jogador2.setNome(nome2.getText().toString());
                prefencias
                        .edit()
                        .putString("nome2",jogador2.getNome())
                        .apply();

                if (prefencias.getInt("jogadores", 2) > 2 && jogador3 != null && nome3 != null) {
                    jogador3.setNome(nome3.getText().toString());
                    prefencias
                            .edit()
                            .putString("nome3", jogador3.getNome())
                            .apply();
                }

                if (prefencias.getInt("jogadores", 2) > 3 && jogador4 != null && nome4 != null) {
                    jogador4.setNome(nome4.getText().toString());
                    prefencias
                            .edit()
                            .putString("nome4", jogador4.getNome())
                            .apply();
                }

                //Log.i("***+", "reset: mortal is play: " + mortal.isPlaying());
                //Log.i("***+", "reset: top is play: " + top.isPlaying());
                if (top.isPlaying()) {
                    top.stop();
                    //Log.i("***+", "reset: top parou: " + ! top.isPlaying());
                }
                if (mortal.isPlaying()){
                    mortal.stop();
                    //Log.i("***+", "reset: mortal parou: " + ! mortal.isPlaying());
                }


                final Partida partida = new Partida(jogador1, jogador2, jogador3, jogador4);

                if(jogador1.getVida() == 0 | jogador2.getVida() == 0) {
                    salvaPartidaNoBanco(partida);
                } else {
                    if(jogador3.getNome() != null && jogador3.getVida() == 0) {
                        salvaPartidaNoBanco(partida);
                    } else {
                        if(jogador4.getNome() != null && jogador4.getVida() == 0) {
                            salvaPartidaNoBanco(partida);
                        } else {
                            tocarRoud();
                            finish();
                            startActivity(getIntent());
                        }
                    }
                }

            }
        });

        ImageButton config = findViewById(R.id.configuracao);
        config.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (top != null ){
                    Log.i("***+", "config top is play: " + top.isPlaying());
                }
                if (mortal != null){
                    Log.i("***+", "config mortal is play: " + mortal.isPlaying());
                }
                startActivity(new Intent(Contador.this, Configuracao.class));
            }
        });

        if (prefencias.getInt("jogadores", 2) > 2) {

            ImageButton botaoMais3 = findViewById(R.id.maisVidaJogador3);
            botaoMais3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    tocarMaisVida();
                    marca3.setText(Integer.toString(jogador3.maisVida()));
                }
            });

            ImageButton botaoMenos3 = findViewById(R.id.menosVidaJogador3);
            botaoMenos3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    marca3.setText(Integer.toString(jogador3.menosVida()));
                    if (jogador3.getVida() == 0){
                        tocarYouLose();
                    } else {
                        tocarMenosVida();
                    }
                }
            });

        }
        if (prefencias.getInt("jogadores", 2) > 3) {
            ImageButton botaoMais4 = findViewById(R.id.maisVidaJogador4);
            botaoMais4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    tocarMaisVida();
                    marca4.setText(Integer.toString(jogador4.maisVida()));
                }
            });

            ImageButton botaoMenos4 = findViewById(R.id.menosVidaJogador4);
            botaoMenos4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    marca4.setText(Integer.toString(jogador4.menosVida()));
                    if (jogador4.getVida() == 0){
                        tocarYouLose();
                    } else {
                        tocarMenosVida();
                    }
                }
            });

        }
        tocarFundo();
    }




    private void salvaPartidaNoBanco(final Partida partida) {
        rodarNaThreadDoBanco(new Runnable() {
            @Override
            public void run() {
                BancoDeDados banco = BancoDeDados.getInstancia(Contador.this);
                PartidaDao partidaDao = banco.getPartidaDao();
                partidaDao.inserir(partida);
                rodarNaThreadPrincipal(new Runnable() {
                    @Override
                    public void run() {
                        tocarRoud();
                        finish();
                        startActivity(getIntent());
                    }
                });
            }
        });
    }

    public void tocarMenosVida(){
        //Log.i("**+", "Chamou - vida");
        Random escolher = new Random();
        int i = escolher.nextInt(4);
        View asd = findViewById(R.id.fundo2);
        switch(i) {
            case 0:
                asd.setBackground(getDrawable(R.drawable.soco));
                tocarSoco();
                break;
            case 1:
                asd.setBackground(getDrawable(R.drawable.haduken2));
                tocarHadouken();
                break;
            case 2:
                asd.setBackground(getDrawable(R.drawable.shoriuguen));
                tocarChoriugann();
                break;
            case 3:
                asd.setBackground(getDrawable(R.drawable.tekoteko));
                tocarTatagtaruguen();
                break;
            default:
                asd.setBackground(getDrawable(R.drawable.soco));
                tocarSoco();
                break;
        }

    }

    private void criaTracks() {
        this.choriugann = MediaPlayer.create(this, R.raw.choriugann);
        this.hadouken = MediaPlayer.create(this, R.raw.hadouken);
        this.lose = MediaPlayer.create(this, R.raw.lose);
        this.moedamario = MediaPlayer.create(this, R.raw.moedamario);
        this.round = MediaPlayer.create(this, R.raw.round);
        this.soco = MediaPlayer.create(this, R.raw.soco);
        this.tatagtaruguen = MediaPlayer.create(this, R.raw.tatagtaruguen);
        this.mortal = MediaPlayer.create(this, R.raw.mortal);
        this.top = MediaPlayer.create(this, R.raw.top);
    }

    public void tocarSoco(){
        soco.start();
    }

    public void tocarRoud(){
        round.start();
    }

    public void tocarFundo() {
        if (mortal == null){
            criaTracks();
        }
        int i = prefencias.getInt("fundo", 0);
        switch (i) {
            case 0:
                if (top.isPlaying()) {
                    Log.i("***+", "tocarFundo: caso 0 top is play: " + top.isPlaying());
                }
                if (mortal.isPlaying()){
                    Log.i("***+", "tocarFundo: caso 0 mortal is play: " + mortal.isPlaying());
                }
                break;
            case 1:
                if (this.mortal != null){
                    this.mortal.start();
                    //Log.i("***+", "tocarFundo: caso 1 mortal is play: " + mortal.isPlaying());
                    mortal.setLooping(true);
                }
                break;
            case 2:
                if (this.top != null){
                    this.top.start();
                    //Log.i("***+", "tocarFundo: caso 2 top is play: " + top.isPlaying());
                    top.setLooping(true);
                }
                break;
        }
    }

    public void tocarHadouken(){
        hadouken.start();
    }

    public void tocarYouLose(){
        View asd = findViewById(R.id.fundo2);
        asd.setBackground(getDrawable(R.drawable.youlose));
        lose.start();
    }

    public void tocarChoriugann(){
        choriugann.start();
    }

    public void tocarTatagtaruguen(){
        tatagtaruguen.start();
    }

    public void tocarMaisVida(){
        moedamario.start();
        //Log.i("**+", "chamou + vida");
    }
    void rodarNaThreadPrincipal(Runnable acao) {
        handlerThreadPrincipal.post(acao);
    }

    void rodarNaThreadDoBanco(Runnable acao) {
        executorThreadDoBanco.execute(acao);
    }
}
