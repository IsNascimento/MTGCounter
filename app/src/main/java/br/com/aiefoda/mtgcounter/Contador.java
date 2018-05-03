package br.com.aiefoda.mtgcounter;

import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.Random;


public class Contador extends AppCompatActivity {
    SharedPreferences prefencias;

    MediaPlayer choriugann = null;
    MediaPlayer hadouken = null;
    MediaPlayer lose = null;
    MediaPlayer moedamario = null;
    MediaPlayer round = null;
    MediaPlayer soco = null;
    MediaPlayer tatagtaruguen = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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

        final Jogador jogador1 = new Jogador();
        final Jogador jogador2 = new Jogador();
        final Jogador jogador3 = new Jogador();
        final Jogador jogador4 = new Jogador();

        final TextView marca = findViewById(R.id.vidaJogador1);
        final TextView marca2 = findViewById(R.id.vidaJogador2);
        final TextView marca3 = findViewById(R.id.vidaJogador3);
        final TextView marca4 = findViewById(R.id.vidaJogador4);


        jogador1.setVida(prefencias.getInt("vida", 20));
        marca.setText(Integer.toString(jogador1.getVida()));
        jogador2.setVida(prefencias.getInt("vida", 20));
        marca2.setText(Integer.toString(jogador2.getVida()));
        if (prefencias.getInt("jogadores", 2) > 2) {
            jogador3.setVida(prefencias.getInt("vida", 20));
            marca3.setText(Integer.toString(jogador3.getVida()));
        }
        if (prefencias.getInt("jogadores", 2) > 3) {
            jogador4.setVida(prefencias.getInt("vida", 20));
            marca4.setText(Integer.toString(jogador4.getVida()));
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
                if (jogador1.getVida() == 0){
                    tocarYouLose();
                } else {
                    tocarMenosVida();
                }

                marca.setText(Integer.toString(jogador1.menosVida()));

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
                if (jogador1.getVida() == 0){
                    tocarYouLose();
                } else {
                    tocarMenosVida();
                }
                marca2.setText(Integer.toString(jogador2.menosVida()));
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

        final ImageButton reset = findViewById(R.id.botaoResetar);
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tocarRoud();
                finish();
                startActivity(getIntent());
            }
        });

        ImageButton config = findViewById(R.id.configuracao);
        config.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
                    if (jogador1.getVida() == 1){
                        tocarYouLose();
                    } else {
                        tocarMenosVida();
                    }
                    marca3.setText(Integer.toString(jogador3.menosVida()));
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

                    if (jogador1.getVida() == 1){
                        tocarYouLose();
                    } else {
                        tocarMenosVida();
                    }
                    marca4.setText(Integer.toString(jogador4.menosVida()));
                }
            });

        }
    }

    public void tocarMenosVida(){
        if(soco == null) {
            this.criaTracks();
        }
        Random escolher = new Random();
        int i = escolher.nextInt(3);
        switch(i) {
            case 0:
                tocarSoco();
                break;
            case 1:
                tocarHadouken();
                break;
            case 2:
                tocarChoriugann();
                break;
            case 3:
                tocarTatagtaruguen();
                break;
            default:
                tocarSoco();
                break;
        }

    }

    private void criaTracks() {
        choriugann = MediaPlayer.create(this, R.raw.choriugann);
        hadouken = MediaPlayer.create(this, R.raw.hadouken);
        lose = MediaPlayer.create(this, R.raw.lose);
        moedamario = MediaPlayer.create(this, R.raw.moedamario);
        round = MediaPlayer.create(this, R.raw.round);
        soco = MediaPlayer.create(this, R.raw.soco);
        tatagtaruguen = MediaPlayer.create(this, R.raw.tatagtaruguen);
    }

    public void tocarSoco(){
        soco.start();
    }

    public void tocarRoud(){
        if(round == null) {
            this.criaTracks();
        }
        round.start();
    }

    public void tocarHadouken(){
        hadouken.start();
    }

    public void tocarYouLose(){
        if(lose == null) {
            this.criaTracks();
        }
        lose.start();
    }

    public void tocarChoriugann(){
        choriugann.start();
    }

    public void tocarTatagtaruguen(){
        tatagtaruguen.start();
    }

    public void tocarMaisVida(){
        if(moedamario == null) {
            this.criaTracks();
        }
        moedamario.start();
    }
}
