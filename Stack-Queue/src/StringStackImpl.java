import java.io.PrintStream;
import java.util.NoSuchElementException;


public class StringStackImpl implements StringStack {
	
	LinkList list=new LinkList();
	
    @Override
    public boolean isEmpty() {
        return size()==0;
    }

    @Override
    public void push(String item) {
    	list.insertFirst(item);
    }

    @Override
    public String pop() throws NoSuchElementException {
    	String temp = null;
    	if(!list.isEmpty())
    	    temp = list.deleteFirst().stack;

    	return temp;
    }

    @Override
    public String peek() throws NoSuchElementException {
        return list.getFirst().stack;
    }

    @Override
    public void printStack(PrintStream stream) {
        Link temp=list.getFirst();
        while(true) {
            stream.println(temp.stack);
            if (temp.next == null)
                break;

            temp = temp.next;
        }
    }

    @Override
    public int size() {
        int i = 0;
        Link cu=list.getFirst();
        while(cu!=null){
        	i++;
        	cu=cu.next;
        }
        return i;
    }
}
