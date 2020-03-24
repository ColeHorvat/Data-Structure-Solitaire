import java.util.ArrayList;

public class QueueBox<E>
{
    private E[] queue = (E[])(new Object[5]);
    private int front_idx = 0;
    private int rear_idx = 0;
    public int count = 0;

    boolean add(E e)
    {
        rear_idx++;
        count++;

        if(rear_idx == queue.length)
        {
            resize();
        }

        queue[rear_idx - 1] = e;
        return true;
        //Throws NullPointerException if e is null
    }

    E remove()
    {
        front_idx++;
        count--;
        return queue[front_idx];
        //Throws NoSuchElementException if queue is empty
    }

    E element()
    {
        return queue[front_idx];
    }

    boolean isEmpty()
    {
        return count == 0;
    }

    int size()
    {
        return count;
    }

    void resize()
    {
        E[] queue2 = (E[])(new Object[rear_idx * 2]);
        for(int i = 0; i < rear_idx; i++)
        {
            queue2[i] = queue[i];
        }
        queue = queue2;
    }
}
