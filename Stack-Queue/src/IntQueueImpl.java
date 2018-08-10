import java.io.PrintStream;
import java.util.NoSuchElementException;

public class IntQueueImpl implements IntQueue {
	
	LinkList list=new LinkList();
	
    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public void put(int item) {
    	list.insert(item);   	
    }

    @Override
    public int get() throws NoSuchElementException {
        Integer temp = null;
    	if(!list.isEmpty())
    	    temp = list.remove().queue;
    	return temp;
    }

    @Override
    public int peek() throws NoSuchElementException {
        return list.getFirst().queue;
    }

    @Override
    public void printQueue(PrintStream stream) {
        Link temp=list.getFirst();
        while(true) {
            stream.println(temp.queue);
            if (temp.next == null)
                break;

            temp = temp.next;
        }
    }

    @Override
    public int size() {
        return list.getcurrentItems();
    }
}
