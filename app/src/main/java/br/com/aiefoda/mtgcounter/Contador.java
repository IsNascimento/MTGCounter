package br.com.aiefoda.mtgcounter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.Random;


public class Contador extends AppCompatActivity {

    Configuracao conf = new Configuracao();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        switch (conf.getJogadores()) {
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


        jogador1.setVida(conf.getVida());
        marca.setText(Integer.toString(jogador1.getVida()));
        jogador2.setVida(conf.getVida());
        marca2.setText(Integer.toString(jogador2.getVida()));
        if (conf.getJogadores() > 2) {
            jogador3.setVida(conf.getVida());
            marca3.setText(Integer.toString(jogador3.getVida()));
        }
        if(conf.getJogadores() > 3) {
            jogador4.setVida(conf.getVida());
            marca4.setText(Integer.toString(jogador4.getVida()));
        }

            ImageButton botaoMais1 = findViewById(R.id.maisVidaJogador1);
            botaoMais1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    marca.setText(Integer.toString(jogador1.maisVida()));
                }
            });

            ImageButton botaoMenos1 = findViewById(R.id.menosVidaJogador1);
            botaoMenos1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    marca.setText(Integer.toString(jogador1.menosVida()));
                }
            });

            ImageButton botaoMais2 = findViewById(R.id.maisVidaJogador2);
            botaoMais2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    marca2.setText(Integer.toString(jogador2.maisVida()));
                }
            });

            ImageButton botaoMenos2 = findViewById(R.id.menosVidaJogador2);
            botaoMenos2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    marca2.setText(Integer.toString(jogador2.menosVida()));
                }
            });

            Button sortear = findViewById(R.id.sort);
            sortear.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    TextView sortValor = findViewById(R.id.sortValor);
                    Random sorteio = new Random();
                    sortValor.setText(Integer.toString(sorteio.nextInt(conf.getFacesDado()) + 1));
                }
            });

            ImageButton reset = findViewById(R.id.botaoResetar);
            reset.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    marca2.setText(Integer.toString(conf.getVida()));
                    marca.setText(Integer.toString(conf.getVida()));
                    jogador1.setVida(conf.getVida());
                    jogador2.setVida(conf.getVida());
                    if (conf.getJogadores() > 2){
                        marca3.setText(Integer.toString(conf.getVida()));
                        jogador3.setVida(conf.getVida());
                    }
                    if (conf.getJogadores() > 3){
                        marca4.setText(Integer.toString(conf.getVida()));
                        jogador4.setVida(conf.getVida());
                    }

                }
            });

            if (conf.getJogadores() > 2){
                ImageButton botaoMais3 = findViewById(R.id.maisVidaJogador3);
                botaoMais3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        marca3.setText(Integer.toString(jogador3.maisVida()));
                    }
                });

                ImageButton botaoMenos3 = findViewById(R.id.menosVidaJogador3);
                botaoMenos3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        marca3.setText(Integer.toString(jogador3.menosVida()));
                    }
                });

            }
            if (conf.getJogadores() > 3){
                ImageButton botaoMais4 = findViewById(R.id.maisVidaJogador4);
                botaoMais4.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        marca4.setText(Integer.toString(jogador4.maisVida()));
                    }
                });

                ImageButton botaoMenos4 = findViewById(R.id.menosVidaJogador4);
                botaoMenos4.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        marca4.setText(Integer.toString(jogador4.menosVida()));
                    }
                });

            }
        }
}

