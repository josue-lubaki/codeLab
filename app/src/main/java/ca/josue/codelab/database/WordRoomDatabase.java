package ca.josue.codelab.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import ca.josue.codelab.database.dao.WordDao;
import ca.josue.codelab.model.Word;

@Database(entities = {Word.class}, version = 1, exportSchema = false)
public abstract class WordRoomDatabase extends RoomDatabase {

    public abstract WordDao wordDao();

    private static final int NUMBER_OF_THREADS = 4;
    private static volatile WordRoomDatabase INSTANCE;

    /*
        Nous avons créé un ExecutorService avec un pool de threads fixe que vous utiliserez
        pour exécuter des opérations de base de données de manière asynchrone sur un thread d'arrière-plan.
    */
    static final ExecutorService databaseWriteExecutor = Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    static WordRoomDatabase getDatabase(final Context context){
        if(INSTANCE == null){
            synchronized (WordRoomDatabase.class){
                if(INSTANCE == null){
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(), WordRoomDatabase.class,
                            "word_database").build();
                }
            }
        }
        return INSTANCE;
    }

}
