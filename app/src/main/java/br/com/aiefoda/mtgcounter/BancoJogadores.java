package br.com.aiefoda.mtgcounter;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

@Database(entities = {Jogador.class}, version = 1, exportSchema = false)
public abstract class BancoJogadores extends RoomDatabase {
    public abstract JogadoresDao jogadoresDao();
}
