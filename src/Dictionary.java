import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;

import java.util.Collection;

public class Dictionary {
    private final Multimap<String, String[]> dictionary;
    private int wordCount = 0;
    private int defCount = 0;

    public Dictionary() {
        this.dictionary = ArrayListMultimap.create();
    }

    // Puts all entries into multimap
    public void loadData(){
        for(Entries entry : Entries.values()) {
            if(!dictionary.containsKey(entry.getWord())) {
                wordCount++;
            }
            defCount++;

            dictionary.put(entry.getWord(), entry.getDef());
        }
    }

    /**
     * Looks up a string to see if it is in the dictionary
     * @param word string to be searched
     * @return definition of the string, if present
     */
    public Collection<String[]> lookUp(String word) {
        if(dictionary.containsKey(word)) {
            return dictionary.get(word);
        }
        return null;
    }

    // GETTERS
    public int getWordCount() { return this.wordCount; }
    public int getDefCount() { return this.defCount; }

    public boolean isWord(String key) {
        return this.dictionary.containsKey(key);
    }

    public Collection<String[]> getDefinition(String key) {
        return this.dictionary.get(key);
    }


}
