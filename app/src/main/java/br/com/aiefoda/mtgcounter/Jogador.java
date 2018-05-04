package br.com.aiefoda.mtgcounter;

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

    public String toString() {
        return this.getNome() + ": " + String.valueOf(this.getVida());
    }
}
