package ca.josue.codelab.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "word_table")
public class word {
    @PrimaryKey(autoGenerate = true)
    private int id;

    @NonNull
    @ColumnInfo(name = "word")
    private final String mWord;

    public word(@NonNull String word) {
        this.mWord = word;
    }

    public String getWord() {
        return mWord;
    }
}
