package br.com.aiefoda.mtgcounter;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface PartidaDao {
    @Insert
    void inserir(Partida partida);

    @Query("SELECT * from Partida order by 1 desc")
    List<Partida> listar();
}
