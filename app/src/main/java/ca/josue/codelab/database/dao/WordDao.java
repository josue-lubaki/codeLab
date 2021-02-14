package ca.josue.codelab.database.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

import ca.josue.codelab.model.Word;

@Dao
public interface WordDao {
    /*
        Qu'est-ce que le DAO?
            Un DAO (objet d'accès aux données) valide votre SQL au moment de la compilation et l'associe à une méthode.
            Dans votre salle DAO, vous utilisez des annotations pratiques, comme @Insert,
            pour représenter les opérations de base de données les plus courantes!
            Room utilise le DAO pour créer une API propre pour votre code.
            Le DAO doit être une interface ou une classe abstraite.
            Par défaut, toutes les requêtes doivent être exécutées sur un thread distinct.
    */

    // Obtenir tous les mots classés par ordre alphabétique
    @Query("SELECT * FROM word_table ORDER BY word ASC")
    LiveData<List<Word>> getAlphabetizedWords();

    @Query("DELETE FROM word_table")
    void deleteAll();

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Word word);




}
