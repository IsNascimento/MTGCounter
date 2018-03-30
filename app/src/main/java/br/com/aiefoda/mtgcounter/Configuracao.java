package br.com.aiefoda.mtgcounter;

import android.support.v7.app.AppCompatActivity;

/**
 * Created by anderson p m on 23/03/2018.
 */

public class Configuracao extends AppCompatActivity{
    private int vida = 20;
    private int jogadores = 2;
    private int facesDado = 16;

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
