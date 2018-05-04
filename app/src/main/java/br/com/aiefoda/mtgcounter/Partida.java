package br.com.aiefoda.mtgcounter;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

@Entity
public class Partida {

    @PrimaryKey (autoGenerate = true)
    public int id_partida;
    public String data;
    public String jogador1;
    public String jogador2;
    public String jogador3;
    public String jogador4;

    Partida() {

    }

    Partida(Jogador jogador1, Jogador jogador2, Jogador jogador3, Jogador jogador4) {
        //data = DateFormat.getDateInstance().format(new Date());
        SimpleDateFormat formataData = new SimpleDateFormat("dd/mm/yyyy");
        data = formataData.format(new Date(System.currentTimeMillis()));
        this.jogador1 = jogador1.toString();
        this.jogador2 = jogador2.toString();
        if(jogador3.getNome() != null) {
            this.jogador3 = jogador3.toString();
            if(jogador4.getNome() != null) {
                this.jogador4 = jogador4.toString();
            }
        }
    }

    public String toString() {

        String resutlado = data + "\n" + jogador1 + "\n" + jogador2;

        //String resutlado = jogador1 + "\n" + jogador2;
        if(this.jogador3 != null) {
            resutlado = resutlado + "\n" + jogador3;
            if (this.jogador4 != null) {
                resutlado = resutlado + "\n" + jogador4;

            }
        }
        return resutlado;
    }

}
