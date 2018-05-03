package br.com.aiefoda.mtgcounter;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface JogadoresDao {
    @Insert
    void inserir(Jogador jogadores);

    @Query("SELECT * from Jogador")
    List<Jogador> listar();
}
