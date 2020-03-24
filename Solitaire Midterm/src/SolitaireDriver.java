//Cole Horvat
//Data Structures and Algorithms Midterm: Solitaire

import java.util.*;

public class SolitaireDriver
{
    //Scanner
    private static Scanner scan = new Scanner(System.in);

    //Fill Deck
    private static ArrayList<String> shuffled_deck = shuffledDeck();

    //Waste and Deck Stacks
    private static StackBox<String> waste = new StackBox<>();
    private static StackBox<String> deck = new StackBox<>();

    //Stack and Queue arrays
    private static StackBox<String>[] tsArray = new StackBox[7];
    private static QueueBox<String>[] tqArray = new QueueBox[7];
    private static StackBox<String>[] fsArray = new StackBox[4];


    public static void main (String[] args)
    {
        //Deals out the cards to the queues and stacks
        dealDeck();

        //Fill Foundations
        fsArray[0].push("1H");
        fsArray[1].push("1D");
        fsArray[2].push("1C");
        fsArray[3].push("1S");

        //Fill Tableau Stacks and Queues
        for(int i = 0; i < tsArray.length; i++)
        {
            int cardsNum = i + 1;
            tsArray[i] = new StackBox<>();
            tqArray[i] = new QueueBox<>();

            for(int j = cardsNum; j > 0; j--)
            {
                tqArray[i].add(deck.pop());
            }

            tsArray[i].push(tqArray[i].element());
            tqArray[i].remove();
        }

        boolean repeat = true;

        System.out.println(tqArray[1].element());

        while (repeat)
        {
            //Waste Indicator
            try
            {
                System.out.println("Top of waste is showing: " + waste.peek());
            }
            catch (EmptyStackException e)
            {
                System.out.println("Top of waste is showing: (empty)");
            }
            catch (IndexOutOfBoundsException i)
            {
                System.out.println("Top of waste is showing: (empty)");
            }

            //Tableaus Menu
            System.out.println("<TABLEAUS>");
            try
            {
                System.out.println("T0: has " + tqArray[0].size() + " face down cards and " + tsArray[0].size() + " face up cards, showing " + tsArray[0].peek());
                System.out.println("T1: has " + tqArray[1].size() + " face down cards and " + tsArray[1].size() + " face up cards, showing " + tsArray[1].peek());
                System.out.println("T2: has " + tqArray[2].size() + " face down cards and " + tsArray[2].size() + " face up cards, showing " + tsArray[2].peek());
                System.out.println("T3: has " + tqArray[3].size() + " face down cards and " + tsArray[3].size() + " face up cards, showing " + tsArray[3].peek());
                System.out.println("T4: has " + tqArray[4].size() + " face down cards and " + tsArray[4].size() + " face up cards, showing " + tsArray[4].peek());
                System.out.println("T5: has " + tqArray[5].size() + " face down cards and " + tsArray[5].size() + " face up cards, showing " + tsArray[5].peek());
                System.out.println("T6: has " + tqArray[6].size() + " face down cards and " + tsArray[6].size() + " face up cards, showing " + tsArray[6].peek());
            }
            catch(NoSuchElementException e)
            {
                System.out.println("Dealer has not dealt the cards");
            }

            //Foundations Menu
            System.out.println("\n<FOUNDATIONS>");
            try
            {
                System.out.println(fsArray[0].peek());
                System.out.println(fsArray[1].peek());
                System.out.println(fsArray[2].peek());
                System.out.println(fsArray[3].peek());
            }
            catch (IndexOutOfBoundsException e)
            {
                System.out.println("Deck is empty");
            }

            //Input Menu
            System.out.println("\nMENU:");
            System.out.println("1. Draw a card from the Deck");
            System.out.println("2. Move waste card to foundations");
            System.out.println("3. Move waste card to tableau");
            System.out.println("4. Move tableau card to another tableau");
            System.out.println("5. Move tableau card to foundations");
            System.out.println("6. Quit game\n");

            //Get user input
            String input;

            input = scan.nextLine();
            System.out.println("\n");

            switch(input)
            {
                //Player draws a card from the deck
                case "1":
                    try
                    {
                        waste.push(deck.pop());
                        System.out.println("Deck/Stock Contains: " + deck.size());
                    }
                    catch (IndexOutOfBoundsException e)
                    {
                        //Deals new cards in case deck goes to 0
                        dealDeck();
                    }

                    break;
                //Player moves waste card to tableau
                case "2":
                    //Variables
                    String wasteValue = waste.peek().replaceAll("[^0-9]", "");
                    char wasteSuit = waste.peek().charAt(waste.peek().length() - 1);
                    boolean foundRepeat = true;

                        //Index through each foundation and figure out if waste matches requirements
                        for(int i = 0; foundRepeat; i++)
                        {
                            String foundValue = fsArray[i].peek().replaceAll("[^0-9]", "");
                            System.out.println(foundValue);

                            if(Integer.parseInt(wasteValue) == Integer.parseInt(foundValue) + 1)
                            {
                                switch(wasteSuit)
                                {
                                    case 'H':
                                        System.out.println("Read H");
                                        fsArray[0].push(waste.pop());
                                        foundRepeat = false;
                                        break;
                                    case 'D':
                                        System.out.println("Read D");
                                        fsArray[1].push(waste.pop());
                                        foundRepeat = false;
                                        break;
                                    case 'C':
                                        System.out.println("Read C");
                                        fsArray[2].push(waste.pop());
                                        foundRepeat = false;
                                        break;
                                    case 'S':
                                        System.out.println("Read S");
                                        fsArray[3].push(waste.pop());
                                        foundRepeat = false;
                                        break;
                                }
                            }
                            else
                            {
                                System.out.println("**************************************************************");
                                System.out.println("Can't put " + waste.peek() + " on to the foundation yet.");
                                System.out.println("**************************************************************");
                                foundRepeat = false;
                            }

                        }
                    break;
                //Player moves waste card to tableau
                case "3":
                    //Variables
                    int tabNum = 0;
                    wasteValue = waste.peek().replaceAll("[^0-9]", "");
                    wasteSuit = waste.peek().charAt(waste.peek().length() - 1);

                    //Get user input
                    System.out.println("Move waste to which tableau? (0-6)?");
                    try
                    {
                        tabNum = scan.nextInt();
                    }
                    catch(InputMismatchException e)
                    {
                        System.out.println("Please enter valid number");
                    }

                    String tabValue = tsArray[tabNum].peek().replaceAll("[^0-9]", "");
                    char tabSuit = tsArray[tabNum].peek().charAt(tsArray[tabNum].peek().length() - 1);

                    //See requirements for moving waste to tableau
                    if(Integer.parseInt(wasteValue) == Integer.parseInt(tabValue) - 1)
                    {
                        if((wasteSuit == 'H' || wasteSuit == 'D') && (tabSuit == 'C' || tabSuit == 'S'))
                        {
                            tsArray[tabNum].push(waste.pop());
                        }
                        else if((wasteSuit == 'C' || wasteSuit == 'S') && (tabSuit == 'H' || tabSuit == 'D'))
                        {
                            tsArray[tabNum].push(waste.pop());
                        }
                        else
                        {
                            System.out.println("**************************************************************");
                            System.out.println("Can't put " + waste.peek() + " on to the tableau yet.");
                            System.out.println("**************************************************************");
                        }
                    }
                    break;
                //Player moves tableau to another tableau
                case "4":
                    //Variables
                    int oTabNum = 0;
                    int nTabNum = 0;

                    //Get user input
                    System.out.println("Move top card from which tableau?: (0-6) ");
                    try
                    {
                        oTabNum = scan.nextInt();
                        System.out.println("Move to where?: (0-6) ");
                        nTabNum = scan.nextInt();
                    }
                    catch(InputMismatchException e)
                    {
                        System.out.println("Please enter valid number");
                    }

                    String oTabValue = tsArray[oTabNum].peek().replaceAll("[^0-9]", "");
                    char oTabSuit = tsArray[oTabNum].peek().charAt(tsArray[oTabNum].peek().length() - 1);

                    String nTabValue = tsArray[nTabNum].peek().replaceAll("[^0-9]", "");
                    char nTabSuit = tsArray[nTabNum].peek().charAt(tsArray[nTabNum].peek().length() - 1);

                    //See requirements for moving tableau to tableau
                    if(Integer.parseInt(oTabValue) == Integer.parseInt(nTabValue) - 1)
                    {
                        if((oTabSuit == 'H' || oTabSuit == 'D') && (nTabSuit == 'C' || nTabSuit == 'S'))
                        {
                            tsArray[nTabNum].push(tsArray[oTabNum].pop());
                            tsArray[oTabNum].push(tqArray[oTabNum].remove());
                        }
                        else if((oTabSuit == 'C' || oTabSuit == 'S') && (nTabSuit == 'H' || nTabSuit == 'D'))
                        {
                            tsArray[nTabNum].push(tsArray[oTabNum].pop());
                            tsArray[oTabNum].push(tqArray[oTabNum].remove());
                        }
                        else
                        {
                            System.out.println("**************************************************************");
                            System.out.println("Can't put " + tsArray[oTabNum].peek() + " on to the tableau yet.");
                            System.out.println("**************************************************************");
                        }
                    }
                    break;
                 //Player moves a tableau card to the foundations
                case "5":
                    //Variables
                    foundRepeat = true;

                    //Get user input
                    System.out.println("Move what tableau to foundations?: (0-6)");
                    tabNum = scan.nextInt();

                    tabValue = tsArray[tabNum].peek().replaceAll("[^0-9]", "");
                    tabSuit = tsArray[tabNum].peek().charAt(tsArray[tabNum].peek().length() - 1);

                    //Index through each foundation and figure out if tableau card matches requirements
                    for(int i = 0; foundRepeat; i++)
                    {
                        String foundValue = fsArray[i].peek().replaceAll("[^0-9]", "");
                        System.out.println(foundValue);

                        if(Integer.parseInt(tabValue) == Integer.parseInt(foundValue) + 1)
                        {
                            switch(tabSuit)
                            {
                                case 'H':
                                    System.out.println("Read H");
                                    fsArray[0].push(tsArray[tabNum].pop());
                                    tsArray[tabNum].push(tqArray[tabNum].remove());
                                    foundRepeat = false;
                                    break;
                                case 'D':
                                    System.out.println("Read D");
                                    fsArray[1].push(tsArray[tabNum].pop());
                                    tsArray[tabNum].push(tqArray[tabNum].remove());
                                    foundRepeat = false;
                                    break;
                                case 'C':
                                    System.out.println("Read C");
                                    fsArray[2].push(tsArray[tabNum].pop());
                                    tsArray[tabNum].push(tqArray[tabNum].remove());
                                    foundRepeat = false;
                                    break;
                                case 'S':
                                    System.out.println("Read S");
                                    fsArray[3].push(tsArray[tabNum].pop());
                                    tsArray[tabNum].push(tqArray[tabNum].remove());
                                    foundRepeat = false;
                                    break;
                            }
                        }
                        else
                        {
                            System.out.println("**************************************************************");
                            System.out.println("Can't put " + tsArray[tabNum].peek() + " on to the foundation yet.");
                            System.out.println("**************************************************************");
                            foundRepeat = false;
                        }

                    }
                    break;
                //Exit Game
                case "6":
                    System.exit(0);
            }
        }
    }

    private static ArrayList<String> shuffledDeck()
    {
        ArrayList<String> oDeck = new ArrayList<>();

        String[] suit = new String[] { "H","D","C","S" };
        for(int i=1; i<=13; i++)
            for(int j=0; j<4; j++)
                oDeck.add( i + suit[j] );

        Collections.shuffle(oDeck);

        return oDeck;
    }

    private static void dealDeck()
    {
        //Fill Deck Stack and Take Out Aces
        int foundIndex = 0;
        String deckIndex;

        for(int i = 0; i < shuffled_deck.size(); i++)
        {
            deckIndex = shuffled_deck.get(i);
            if(deckIndex.charAt(0) == '1' && (deckIndex.charAt(1) == 'D' || deckIndex.charAt(1) == 'H' || deckIndex.charAt(1) == 'C' || deckIndex.charAt(1) == 'S'))
            {
                fsArray[foundIndex] = new StackBox<>();
                foundIndex++;
                shuffled_deck.remove(deckIndex);
            }
            else
            {
                deck.push(shuffled_deck.get(i));
            }
        }
    }
}
