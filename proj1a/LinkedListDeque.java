public class LinkedListDeque<T>{

    private Node<T> tail;
    private Node<T> head;
    private int size ;

    LinkedListDeque(){
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    public class Node<T>{
        private Node<T> next;
        private Node<T> prev;
        private T data;

        private Node(T data){
            this.data = data;
        }
        public Node(){}
    }

    public boolean isEmpty(){
        return size == 0;
    }

    public void addFirst(T item){
        Node<T> sentinel = new Node<>(item);
        //dllist is empty
        if(isEmpty()){
            head = sentinel;
            tail = sentinel;
        }
        //not empty
        else {
            Node<T> temp = head;
            head = sentinel;
            temp.prev = head;
            head.next = temp;
        }
        size++;
    }

    public void addLast(T item){
        Node<T> sentinel = new Node<>(item);
        if(isEmpty()){
            head = sentinel;
            tail = sentinel;
        }
        else {
            Node<T> temp = tail;
            tail = sentinel;
            temp.next = tail;
            tail.prev = temp;
        }
        size++;
    }

    public int size(){
        return size;
    }

    public void printDeque(){
        Node<T> temp = head;
        if(isEmpty()){
            System.out.print("empty deque!");
        }
        while(temp != null){
            System.out.print(temp.data + " ");
            temp = temp.next;
        }
        System.out.println();
    }

    public T removeFirst(){
        if(isEmpty()){
            return null;
        }
        else if(size == 1){
            T temp = head.data;
            head = null;
            tail = null;
            size--;
            return temp;
        }
        else{
            T temp = head.data;
            head = head.next;
            head.prev = null;
            size--;
            return temp;
        }
    }

    public T removeLast(){
        if(isEmpty()){
            return null;
        }
        else if(size == 1){
            head = null;
            tail = null;
            size--;
            return null;
        }
        else{
            T temp = tail.data;
            tail = tail.prev;
            tail.next = null;
            size--;
            return temp;
        }
    }

    public T get(int index){
        if(isEmpty()){
            return null;
        }
        else if(index >= size() || index < 0){
            return null;
        }
        else {
            Node<T> temp = head;
            int flag = 0;
            while (flag != index){
                temp = temp.next;
                flag++;
            }
            return temp.data;
        }
    }

    public T getRecursive(int index){
        return getRecursiveHelper(head, index);
    }

    public T getRecursiveHelper(Node<T> current, int index){
        if(isEmpty()){
            return null;
        }
        else if(index < 0 || index >= size()){
            return null;
        }
        else if(index == 0){
            return current.data;
        }
        return getRecursiveHelper(current.next, index - 1);
    }

}