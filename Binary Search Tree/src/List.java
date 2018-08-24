public class List {

    private Node head;

    private class Node {

        private Node next;
        private Suspect item;

        public Node(Object item) {
            next = null;
            this.item = (Suspect) item;
        }

        public Node getNext() {
            return next;
        }

        public void setNext(Node next) {
            this.next = next;
        }

        public Suspect getItem() {
            return item;
        }
    }
    
    private static int size = 0;
    public String toString() {
        String output = "";
        if (head != null) {
            Node current = head.getNext();
            while (current != null) {
                output += "|" + current.getItem().toString() + "|\n";
                current = current.getNext();
            }
        }
        this.size = 0;
        return output;
    }

    public void add(Object o) {
        if (head == null) {
            head = new Node(o);
        }

        Node temp = new Node(o);
        Node current = head;

        if (current != null) {
            while (current.getNext() != null) {
                current = current.getNext();
            }
            current.setNext(temp);
        }
        size++;
    }

    public int size() {
        return this.size;
    }

    public boolean isEmpty() {
        return size() == 0 ? true : false;
    }
}

