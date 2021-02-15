package ca.josue.codelab.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

import ca.josue.codelab.database.WordRoomDatabase;
import ca.josue.codelab.database.dao.WordDao;
import ca.josue.codelab.model.Word;

public class WordRepository {

    private WordDao mWordDao;
    private LiveData<List<Word>> mAllWords;

    /*
        Un référentiel gère les requêtes et vous permet d'utiliser plusieurs backends.
        Dans l'exemple le plus courant, le référentiel implémente la logique permettant de décider
        d'extraire des données d'un réseau ou d'utiliser les résultats mis en cache dans une base de données locale.
     */
    public WordRepository(Application application){
        WordRoomDatabase db = WordRoomDatabase.getDatabase(application);
        mWordDao = db.wordDao();
        mAllWords = mWordDao.getAlphabetizedWords();
    }

    /* Room execute toutes les requêtes en separant les theads
    *   LiveData observé notifiera à l'Observer quand les données auront été changées
    * */
    public LiveData<List<Word>> getAllWords() {
        return mAllWords;
    }


    /*
        Vous devez appeler cela sur un thread non-UI ou votre application lèvera une exception.
        Room garantit que vous n'effectuez aucune opération de longue durée sur le thread principal,
        bloquant l'interface utilisateur.

        Room Execute ces methodes en arrière-plan pour ne pas figer l'ecran du User.
            Nous n'avons pas besoin d'exécuter l'insertion sur le thread principal,
            nous utilisons donc le que ExecutorService dont nous avons créé dans le WordRoomDatabase
            pour effectuer l'insertion sur un thread d'arrière-plan.
    */
    public void insert(Word word){
        WordRoomDatabase.databaseWriteExecutor.execute(()-> mWordDao.insert(word));
    }

    public void delete(){
        WordRoomDatabase.databaseWriteExecutor.execute(()-> mWordDao.deleteAll());
    }

}
