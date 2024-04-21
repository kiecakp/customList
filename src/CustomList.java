import java.util.AbstractList;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.stream.Stream;

public class CustomList<T> extends AbstractList<T> {
    class Node{
        T value;
        Node next;
    }

    Node start;
    Node end;

    public CustomList(){
        start = null;
        end = null;
    }

    public boolean add(T t){
        addLast(t);
        return true;
    }
    @Override
    public int size() {
        if(start == null){
            return 0;
        }else{
            Node temp = start;
            int i = 1;
            while(temp != end){
                temp = temp.next;
                i++;
            }
            return i;
        }
    }

    @Override
    public T get(int index) {
        if(start == null || size() <= index){
            throw new NoSuchElementException();
        }
        Node temp = start;
        for(int i = 0; i < index; i++){
            temp = temp.next;
        }
        return temp.value;
    }

    public void addLast(T value){
        Node newNode = new Node();
        newNode.value = value;
        newNode.next = null;

        if(start == null){
            start = newNode;
            end = newNode;
        } else{
            end.next = newNode;
            end = newNode;
        }
    }
    public T getLast(){
        if(end == null){
            throw new NoSuchElementException();
        }
        return end.value;
    }
    public void addFirst(T value){
        Node newNode = new Node();
        newNode.value = value;
        newNode.next = start;

        if(start == null){
            start = newNode;
            end = newNode;
        } else{
            start = newNode;
        }
    }
    public T getFirst(){
        if(start == null){
            throw new NoSuchElementException();
        }
        return start.value;
    }
    public T removeFirst(){
        if(start == null){
            throw new NoSuchElementException();
        }
        T value = getFirst();

        if(start == end){
            start = null;
            end = null;
            return value;
        }else{
            start = start.next;
            return value;
        }
    }
    public T removeLast(){
        if(start == null){
            throw new NoSuchElementException();
        }
        T value = getLast();

        if(start == end){
            start = null;
            end = null;
            return value;
        } else{
            Node temp = start;
            while(temp.next != end){
                temp = temp.next;
            }
            temp.next = null;
            end = temp;
            return value;
        }
    }
    public Iterator<T> iterator(){
        return new Iterator<T>() {
            Node temp = start;

            @Override
            public boolean hasNext() {
                return temp != null;
            }

            @Override
            public T next() {
                T value = temp.value;
                temp = temp.next;
                return value;
            }
        };
    }
    public Stream<T> stream(){
        Stream.Builder<T> streamBuilder = Stream.builder();

        for(T item : this){
            streamBuilder.accept(item);
        }
        return streamBuilder.build();
    }
}
