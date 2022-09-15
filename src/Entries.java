public enum Entries {
    // All words
    Arrow("Arrow", new String[]{"noun", "Here is one arrow: <IMG> -=>> </IMG>"}),
    Book("Book", new String[]{"noun", "A set of pages."}),
    Book1("Book", new String[]{"noun", "A written work published in printed or electronic form."}),
    Book2("Book", new String[]{"verb", "To arrange for someone to have a seat on a plane."}),
    Book3("Book", new String[]{"verb", "To arrange something on a particular date."}),
    Distinct("Distinct", new String[]{"adjective", "Familiar. Worked in Java."}),
    Distinct1("Distinct", new String[]{"adjective", "Unique. No duplicates. Clearly different or of a different kind."}),
    Distinct2("Distinct", new String[]{"adverb", "Uniquely. Written \"distinctly\"."}),
    Distinct3("Distinct", new String[]{"noun", "A keyword in this assignment."}),
    Distinct4("Distinct", new String[]{"noun", "A keyword in this assignment."}),
    Distinct5("Distinct", new String[]{"noun", "A keyword in this assignment."}),
    Distinct6("Distinct", new String[]{"noun", "A keyword in this assignment."}),
    Distinct7("Distinct", new String[]{"noun", "An advanced search option."}),
    Distinct8("Distinct", new String[]{"noun", "Distinct is a parameter in this assignment."}),
    Placeholder("Placeholder", new String[]{"adjective", "To be updated..."}),
    Placeholder1("Placeholder", new String[]{"adjective", "To be updated..."}),
    Placeholder2("Placeholder", new String[]{"adverb", "To be updated..."}),
    Placeholder3("Placeholder",new String[]{"conjunction", "To be updated..."}),
    Placeholder4("Placeholder", new String[]{"interjection", "To be updated..."}),
    Placeholder5("Placeholder", new String[]{"noun", "To be updated..."}),
    Placeholder6("Placeholder", new String[]{"noun", "To be updated..."}),
    Placeholder7("Placeholder", new String[]{"noun", "To be updated..."}),
    Placeholder8("Placeholder", new String[]{"preposition", "To be updated..."}),
    Placeholder9("Placeholder", new String[]{"pronoun", "To be updated..."}),
    Placeholder10("Placeholder", new String[]{"verb", "To be updated..."}),
    Reverse("Reverse", new String[]{"adjective", "On back side."}),
    Reverse1("Reverse", new String[]{"adjective", "Opposite to usual or previous arrangement."}),
    Reverse2("Reverse", new String[]{"noun", "A dictionary program's parameter."}),
    Reverse3("Reverse", new String[]{"noun", "Change to opposite direction."}),
    Reverse4("Reverse", new String[]{"noun", "The opposite."}),
    Reverse5("Reverse", new String[]{"noun", "To be updated..."}),
    Reverse6("Reverse", new String[]{"noun", "To be updated..."}),
    Reverse7("Reverse", new String[]{"noun", "To be updated..."}),
    Reverse8("Reverse", new String[]{"noun", "To be updated..."}),
    Reverse9("Reverse", new String[]{"verb", "Change something to opposite."}),
    Reverse10("Reverse", new String[]{"verb", "Go back"}),
    Reverse11("Reverse", new String[]{"verb", "Revoke ruling."}),
    Reverse12("Reverse", new String[]{"verb", "To be updated..."}),
    Reverse13("Reverse", new String[]{"verb", "To be updated..."}),
    Reverse14("Reverse", new String[]{"verb", "Turn something inside out."});

    private String word;
    private String[] def;

    private Entries(String word, String[] def){
        this.word = word;
        this.def = def;
    }

    public String getWord() {
        return this.word;
    }

    public String[] getDef() {
        return this.def;
    }

    public String toString() {
        return "Name: " + this.word +
                "\nPOS: " + this.def[0] +
                "\nDef: " + this.def[1];
    }

}
