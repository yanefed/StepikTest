package flashcards;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        String term, def, ans;
        Map<String, String> cards = new LinkedHashMap<>();

        Scanner sc = new Scanner(System.in);
        System.out.println("Input the number of cards:");
        int numOfCards = sc.nextInt();
        sc.nextLine();

        for (int i = 1; i <= numOfCards; i++) {
            System.out.println("The card #" + i + ":");
            term = sc.nextLine();
            while (cards.containsKey(term)) {
                System.out.println("The card \"" + term + "\" already exists. Try again:");
                term = sc.nextLine();
            }
            System.out.println("The definition of the card #" + i + ":");
            def = sc.nextLine();
            while (cards.containsValue(def)) {
                System.out.println("The definition \"" + def + "\" already exists. Try again:");
                def = sc.nextLine();
            }
            cards.put(term, def);
        }

        for (String k : cards.keySet()) {
            System.out.println("Print the definition of " + "\"" + k + "\":");
            ans = sc.nextLine();
            boolean check = false;
            if (cards.get(k).equals(ans)) System.out.println("Correct answer.");
            else {
                Set<Map.Entry<String,String>> entrySet = cards.entrySet();
                for (Map.Entry<String,String> pair : entrySet){

                    if (ans.equals(pair.getValue())) {
                        System.out.println("Wrong answer. The correct one is " + "\"" + cards.get(k) +
                                "\", you've just written the definition of \"" + pair.getKey() + "\".");
                        check = true;
                    }
                }
                if (!check) System.out.println("Wrong answer. The correct one is " + "\"" + cards.get(k) + "\".");
            }
        }
    }

}