public class LinkList {
	
	private Link first;	
	private Link last;
	private int currentItems;
	
	
	public LinkList(){
		first=null;
		last=null;
	}
	
	public int getcurrentItems(){
		return currentItems;
	}
	
	public Link getFirst(){
		return first;
	}
	
	public boolean isEmpty(){
		return (first==null);
	}
	
    public void insert(int item){
    	Link l=new Link(item);
    	currentItems++;
    	if(isEmpty()){
    		l.next=first;
    		first=l;
    		last=l;
    	}else{
    		last.next=l;
    		last=l;
    		last.next=null;
    	}
    }
    
    public Link remove(){
    	Link temp=first;
    	currentItems--;
    	if(first.next==null){
    		last=null;
    	}
    	first=first.next;
    	return temp;
    }
	
	public void insertFirst(String item){
		
		Link list = new Link(item);
		list.next=first;
		first=list;	
	}
	
	public Link deleteFirst(){
		Link temp=first;
		first=first.next;
		return temp;
	}
	
	public void displayListQueue(){
		Link cur=first;
		while(cur!=null){
			cur.displayQueue();
			cur=cur.next;
		}
	}
	
	public void displayListStack(){
		Link cur=first;
		while(cur!=null){
			cur.displayStack();
			cur=cur.next;
		}
	}

}