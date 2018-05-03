package br.com.aiefoda.mtgcounter;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

@Database(entities = {Jogador.class}, version = 1, exportSchema = false)
public abstract class BancoJogadores extends RoomDatabase {

    private static BancoJogadores instancia;

    public static BancoJogadores obterInstanciaUnica(
            Context context) {
        synchronized (BancoJogadores.class) {
            if (instancia == null)
                instancia = Room.databaseBuilder(
                        context.getApplicationContext(),
                        BancoJogadores.class,
                        "banco").build();

            return instancia;
        }
    }

    public abstract JogadoresDao jogadoresDao();

}
