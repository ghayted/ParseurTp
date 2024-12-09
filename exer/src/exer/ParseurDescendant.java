package exer;

import java.util.*;

public class ParseurDescendant {
    private String input;   
    private int pos;        

    public ParseurDescendant(String input) {
        this.input = input;
        this.pos = 0;
    }

    public boolean parseS() {
        if (pos == input.length()) {
            return true;
        }

        // la règle S → bSb
        if (match('b')) {
            if (parseS()) {
                if (match('b')) {
                    return true;
                }
            }
        }

        // la règle S → cAc
        if (match('c')) {
            if (parseA()) {
                if (match('c')) {
                    return true;
                }
            }
        }
        return false;  
    }

    // Méthode pour analyser A
    public boolean parseA() { 
        if (pos == input.length()) {
            return true;
        }

        // la règle A → bAA
        if (match('b')) {
            if (parseA() && parseA()) {
                return true;
            }
        }

        // la règle A → cASAb
        if (match('c')) {
            if (parseA() && parseS() && parseA() && match('b')) {
                return true;
            }
        }

        //  la règle A → dcb
        if (match('d')) {
            if (match('c') && match('b')) {
                return true;
            }
        }
        return false;  
    }

    private boolean match(char c) {
        if (pos < input.length() && input.charAt(pos) == c) {
            pos++;
            return true;
        }
        return false;
    }

    public boolean parse() {
        boolean result = parseS(); 
        return result && pos == input.length(); 
    }

    public static void main(String[] args) {
        List<String> testCases = Arrays.asList(
            "cdcbc",        
            "bcdcbcb",       
            "cbdcbdcbc",     
            "ccdcbcdcbcdcbbcr", 
            "cdcbbb",        
            "cdcb",          
            ""               
        );

        for (String testCase : testCases) {
            ParseurDescendant parser = new ParseurDescendant(testCase);
            System.out.println("Chaîne : \"" + testCase + "\" -> " + (parser.parse() ? "Valide" : "Invalide"));
        }
    }
}
