package com.task;

import java.util.List;
import java.util.Objects;

public class FrequencyElement {

    private final List<Character> charactersList;
    private final int wordLength;    

    public FrequencyElement(List<Character> charactersList, int wordLength) {
        this.charactersList = charactersList;
        this.wordLength = wordLength;
        
    }

    public List<Character> getCharactersList() {
        return charactersList;
    }

    public int getWordLength() {
        return wordLength;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FrequencyElement that = (FrequencyElement) o;

        if (wordLength != that.wordLength) return false;
        return charactersList.equals(that.charactersList) && wordLength == that.getWordLength();
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(charactersList, wordLength);
    }
}
