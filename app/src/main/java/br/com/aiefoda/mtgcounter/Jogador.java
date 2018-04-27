package br.com.aiefoda.mtgcounter;

/**
 * Created by anderson p m on 23/03/2018.
 */

public class Jogador {

    private int vida = 0;
    private String nome;

    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getNome() {
        return nome;
    }
    public int getVida() {
        return vida;
    }
    public void setVida(int vida) {
        this.vida = vida;
    }
    public Integer maisVida(){
        return ++vida;
    }
    public Integer menosVida(){
        return --vida;
    }
}
