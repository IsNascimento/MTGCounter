package br.com.aiefoda.mtgcounter;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by anderson p m on 23/03/2018.
 */

public class Configuracao extends AppCompatActivity{

    SharedPreferences preferencias;

    private int vida;
    private int jogadores;
    private int facesDado;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configuracao);

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
}
