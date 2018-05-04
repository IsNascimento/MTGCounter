package br.com.aiefoda.mtgcounter;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

@Database(entities = {Partida.class}, version = 1, exportSchema = false)
public abstract class BancoDeDados extends RoomDatabase {

    private static BancoDeDados instancia;

    public static BancoDeDados getInstancia(Context context) {

        synchronized (BancoDeDados.class) {
            if (instancia == null) {
                instancia = Room.databaseBuilder(context.getApplicationContext(), BancoDeDados.class, "banco").build();
            }
            return instancia;
        }
    }

    public abstract PartidaDao getPartidaDao();

}
