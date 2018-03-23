package br.com.aiefoda.mtgcounter;

/**
 * Created by anderson p m on 23/03/2018.
 */

public class Configuracao {
    private int vida = 20;
    private int jogadores = 2;

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
}
