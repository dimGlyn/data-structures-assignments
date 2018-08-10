public class Link {
	String stack;
	int queue;
	Link next;
	
	public Link(String stack){
		this.stack=stack;
	}
	
	public Link(int queue){
		this.queue=queue;
	}
	
	public void displayStack(){
		System.out.println("Item :"+this.stack);
	}
	
	public void displayQueue(){
		System.out.println("Item :"+this.queue);
	}
	
}


