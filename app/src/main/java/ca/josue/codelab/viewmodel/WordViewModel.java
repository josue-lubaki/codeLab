package ca.josue.codelab.viewmodel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import ca.josue.codelab.model.Word;
import ca.josue.codelab.repository.WordRepository;

public class WordViewModel extends AndroidViewModel {

    // Reference sur le repository
    private WordRepository mRepository;

    // Reference sur le LiveData
    public LiveData<List<Word>> mAllWords;

    public WordViewModel (Application application) {
        super(application);
        mRepository = new WordRepository(application);
        mAllWords = mRepository.getAllWords();
    }

    public LiveData<List<Word>> getmAllWords() {
        return mAllWords;
    }

    // S'il faut inserer (Demande venant de l'interface User)
    public void insert(Word word){
        mRepository.insert(word);
    }

    // S'il faut supprimer tous les mots (Demande venant de l'interface User)
    public void deleteAllWords(Word word){
        mRepository.delete();
    }

}
