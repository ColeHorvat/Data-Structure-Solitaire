import java.util.ArrayList;
import java.util.EmptyStackException;

public class StackBox<E>
{
    ArrayList<E> stack = new ArrayList<E>();

    boolean empty()
    {
        return stack.isEmpty();
    }

    //Adds an item to the stack and returns that item
    E push(E item)
    {
        stack.add(item);
        return(item);
    }

    //Takes the first item off the stack and returns that item
    E pop()
    {
        E element = stack.get(size() - 1);
        stack.remove(size() - 1);
        return(element);
    }

    //Returns the first item in the stack without affecting it
    E peek()
    {
        return(stack.get(size() - 1));
    }

    int size()
    {
        return(stack.size());
    }
}
