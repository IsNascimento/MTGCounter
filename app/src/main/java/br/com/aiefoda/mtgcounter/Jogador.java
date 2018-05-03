package br.com.aiefoda.mtgcounter;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity
public class Jogador {
    @PrimaryKey(autoGenerate = true)
    public int id;
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
