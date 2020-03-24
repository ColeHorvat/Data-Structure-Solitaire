import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author John
 */
public class Deck {

    public static void main(String[] args)
    {
        ArrayList<String> shuffled_deck = shuffledDeck();

        System.out.println( shuffled_deck );
    }

    private static ArrayList<String> shuffledDeck()
    {
        ArrayList<String> deck = new ArrayList<String>();

        String[] suit = new String[] { "H","D","C","S" };
        for(int i=1; i<=13; i++)
            for(int j=0; j<4; j++)
                deck.add( i + suit[j] );

        Collections.shuffle(deck);

        return deck;
    }
}