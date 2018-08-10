public class PQ<E extends Comparable<E>> {

    public Object heap[];
    private static int last;
    private int capacity;


    public PQ(int cap) {
        heap = new Object[cap + 1];
        last = 0;
        capacity = cap;
    }

    public int size() {
        return last;
    }


    public boolean isEmpty() {
        return size() == 0;
    }

    public void insert(E e) {
        if (size() == capacity){
            throw new IllegalArgumentException();
        }else{
            if (size()>=capacity*0.75)
                resize();
            last++;
            heap[last] = e;
            swim();
        }

    }

    private void resize() {
        Object[] newHeap;

        newHeap = new Object[capacity * 2-1];

        for (int i = 1; i < capacity * 2-1; i++){
            if(i<=last){
                newHeap[i] = heap[i];
            }else{
                newHeap[i]=new Object();
            }
        }

        heap = newHeap;
        capacity=capacity*2;
    }

    public E getMax() {
        if (isEmpty())
            throw new IllegalArgumentException();
        else {
            E max = (E)heap[1];
            heap[1] = heap[last];
            last--;
            sink();
            return max;
        }
    }




    private void swim(){
        int index = size();
        while (index > 1){
            int parent = index / 2;
            if (((Movie)heap[index]).compareTo((Movie)heap[parent])<0){
                swap(index,parent);
                index = parent;

            }else{
                break;
            }
        }
    }

    private void sink(){
        int index = 1;
        while (true){
            int child = index*2;
            if (child > size())
                break;
            if (child + 1 <= size()){
                child = findMax(child, child + 1);
            }
            if (((Movie)heap[index]).compareTo((Movie)heap[child]) <0 )break;
            swap(index,child);
            index = child;

        }
    }



    private void swap(int i, int j) {
        Object temp = heap[i];
        heap[i] = heap[j];
        heap[j] = temp;
    }

    public Movie Max(){
        return (Movie) heap[1];
    }

    private int findMax(int leftChild, int rightChild) {
        if (((Movie)heap[leftChild]).compareTo((Movie)heap[rightChild]) <= 0)
            return leftChild;
        else
            return rightChild;
    }

}