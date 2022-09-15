import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;

import java.util.Stack;

/**
 * Handles user input...
 * Tells other classes what to do
 * with input...
 */
public class Query {
    private final Dictionary dictionary;
    private String keyword;
    private String partOfSpeech;
    private boolean isValidKey;
    private boolean pos;
    private boolean distinct;
    private boolean reverse;

    private boolean printHelp;


    public Query(Dictionary dictionary) {
        this.isValidKey = false;
        this.pos = false;
        this.distinct = false;
        this.reverse = false;
        this.printHelp = false;
        this.dictionary = dictionary;
        this.keyword = "";
        this.partOfSpeech = "";
    }

    public void parse(String input) {
        if(input.length()==0) {
            Main.print("PARAMETER HOW-TO, please enter:\n" +
                    "\t 1. A search key -then 2. An optional part of speech -then\n"+
                    "\t 3. An optional 'distinct' -then 4. An optional 'reverse'");
            return;
        }

        // COMMANDS
        if(input.charAt(0)=='!') {


            switch(input) {
                case "!h":
                case "!help":
                    Main.print("PARAMETER HOW-TO, please enter:\n" +
                            "\t 1. A search key -then 2. An optional part of speech -then\n"+
                            "\t 3. An optional 'distinct' -then 4. An optional 'reverse'");

                    return;
                case "!q":
                case "!quit":
                    Main.quitProgram();
                    return;
                default:
                    return;
            }
        }
        // Split input into an array
        String[] args = input.split("\\s+");

        int i = 0;
        for(String arg : args) {
            validateArg(arg, i);
            i++;
        }

        handleWord();

    }

    // args: a string
    // index: number in parsed array
    private void validateArg(String arg, int index) {
        String num = null;

        // check first word
        if(index==0) {
            this.keyword = arg;
            this.keyword = this.keyword.substring(0,1).toUpperCase() + this.keyword.substring(1).toLowerCase();
            isValidKey = true;
            if(!dictionary.isWord(this.keyword)) {
                Main.print("<NOT FOUND> To be considered for the next release. Thank you.");
                Main.print("PARAMETER HOW-TO, please enter:\n" +
                        "\t 1. A search key -then 2. An optional part of speech -then\n"+
                        "\t 3. An optional 'distinct' -then 4. An optional 'reverse'");
                isValidKey = false;
                return;
            }
            return;
        }

        if(!isValidKey) return;

        StringBuilder invalid = new StringBuilder();

        // Need a separate switch statement to handle the
        // "parameter 'nth' is..."
        switch(index) {
            case 1:
                num = "2nd";
                break;
            case 2:
                num = "3rd";
                break;
            case 3:
                num = "4th";
                break;
        }

        switch(index) {
            case 1:
                switch(arg.toLowerCase()) {
                    case "noun":
                    case "verb":
                    case "adjective":
                    case "adverb":
                    case "conjunction":
                    case "pronoun":
                    case "interjection":
                    case "preposition":
                        pos = true;
                        this.partOfSpeech = arg.toLowerCase();
                        return;
                    default:
                        invalid.append("<The entered ").append(num).append(" parameter '").append(arg).append("' is NOT a part of speech.>\n");
                }
            case 2:
                if(arg.equals("distinct")) {
                    // filter by uniqueness
                    distinct = true;
                    return;
                } else {
                    if(index == 1 && pos)
                        return;
                    if(index!=2) invalid.append("\t ");

                    invalid.append("<The entered ").append(num).append(" parameter '").append(arg).append("' is NOT 'distinct'.>\n");
                }
            case 3:
                if(arg.equals("reverse")) {
                    // filter by uniqueness
                    reverse = true;
                    return;
                } else {
                    if(index == 2 && distinct) return;
                    if(index!=3) invalid.append("\t ");
                    invalid.append("<The entered ").append(num).append(" parameter '").append(arg).append("' is NOT 'reverse'.>\n");
                }
        }

        // invalid result
        if(invalid.length() > 0) {
            printHelp = true;
            invalid.append("\t <The entered ").append(num).append(" parameter '").append(arg).append("' was disregarded.>\n");

            switch(index) {
                case 1:
                    invalid.append("\t <The ").append(num).append(" parameter should be a part of speech or 'distinct' or 'reverse'.>");
                    break;
                case 2:
                    invalid.append("\t <The ").append(num).append(" parameter should be 'distinct' or 'reverse'.>");
                    break;
                case 3:
                    invalid.append("\t <The ").append(num).append(" parameter should be 'reverse'.>");
                    break;

            }

            Main.print(invalid.toString());

        }

    }

    private void handleWord() {
        if(!isValidKey) return;

        if(printHelp) {
            Main.print("PARAMETER HOW-TO, please enter:\n" +
                    "\t 1. A search key -then 2. An optional part of speech -then\n"+
                    "\t 3. An optional 'distinct' -then 4. An optional 'reverse'");
        }

        StringBuilder output = new StringBuilder();

        Multimap<String, String> map = ArrayListMultimap.create();
        for(String[] str : dictionary.lookUp(keyword)){
            if(distinct) {
                if(!map.containsValue(str[1]) && !pos) {
                    map.put(str[0], str[1]);
                } else if(!map.containsValue(str[1]) && str.equals(partOfSpeech)) {
                    map.put(str[0], str[1]);
                }
            }

            if(pos) {
                if(str[0].equals(partOfSpeech) && !distinct) {
                    map.put(str[0], str[1]);
                } else if (str[0].equals(partOfSpeech) && !map.containsValue(str[1])) {
                    map.put(str[0], str[1]);
                }
            }

            if(!pos & !distinct){
                map.put(str[0],str[1]);
            }
        }

        String[] normal;
        if(reverse) {
            Stack<String> stack = new Stack<>();
            map.asMap().forEach((key, collection) -> {
                collection.forEach(def -> {
                    stack.add("\t " + keyword + " [" +
                            key
                            + "] " +  def + "\n");
                });
            });
            while(!stack.isEmpty()) {
               output.append(stack.pop());
            }
        } else {
            map.asMap().forEach((key, collection) -> {
                collection.forEach(def -> {
                    output.append("\t ")
                            .append(keyword)
                            .append(" [")
                            .append(key)
                            .append("] ")
                            .append(def)
                            .append("\n");
                });
            });
        }

        if(output.toString().length()==0) {
            Main.print("<NOT FOUND> To be considered for the next release. Thank you.");
            Main.print("PARAMETER HOW-TO, please enter:\n" +
                    "\t 1. A search key -then 2. An optional part of speech -then\n"+
                    "\t 3. An optional 'distinct' -then 4. An optional 'reverse'");
            return;
        }

        Main.print(output.toString(), true);
    }

}
