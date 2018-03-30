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
        switch (conf.getJogadores()){
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


            final TextView marca = findViewById(R.id.vidaJogador1);
            final Jogador jogador1 = new Jogador();
            jogador1.setVida(conf.getVida());
            marca.setText(Integer.toString(jogador1.getVida()));

            final TextView marca2 = findViewById(R.id.vidaJogador2);
            final Jogador jogador2 = new Jogador();
            jogador2.setVida(conf.getVida());
            marca2.setText(Integer.toString(jogador2.getVida()));

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
            }
        });
    }
}

