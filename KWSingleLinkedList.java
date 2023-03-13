public class KWSingleLinkedList<T> implements Iterable<T> {
    private int size = 0;
    private Node<T> head = null;
    private Node<T> tail = null;

    static class Node<T> {
        private String name;

        private String email;
        private String number;
        private Node<T> next;

        public Node(String name, String email, String number, Node<T> next) {
            this.name = name;
            this.email = email;
            this.number = number;
            this.next = next;
        }
    }

    public void clear() {
        Node<T> trav = head;
        while (trav != null) {
            Node<T> next = trav.next;
            trav.next = null;
            trav.name = null;
            trav.email = null;
            trav.number = null;
            trav = next;
        }
        head = tail = trav = null;
        size = 0;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public void add(String name, String number, String email) {
        addLast(name, number, email);
    }

    public void addLast(String name, String number, String email) {
        if (isEmpty()) {
            head = tail = new Node<T>(name, email, number, null);
        } else {
            tail.next = new Node<T>(name, email, number, null);
            tail = tail.next;
        }
        size++;
    }

    public void info(String node){
        Node<T> trav;
        for (trav = head; trav != null; trav = trav.next) {
            if (node.equals(trav.name) || node.equals(trav.number) || node.equals(trav.email) ){
                System.out.println("Name: " + trav.name);
                System.out.println("Number: " + trav.number);
                System.out.println("Email: " + trav.email);
            }
        }
    }

    public boolean contains(String name){
        Node<T> trav;
        for (trav = head; trav != null; trav = trav.next) {
            if (name.equals(trav.name) || name.equals(trav.number) || name.equals(trav.email) ){
                return true;
            }
        }
        return false;
    }

    public void addFirst(String name, String email, String number) {
        if (isEmpty()) {
            head = tail = new Node<T>(name, email, number, null);
        } else {
            Node<T> trav = head;
            head = new Node<T>(name, email,number, trav);
        }
        size++;
    }

    public void addAt(int index, String name, String number, String email) throws Exception {
        if (index < 0 || index > size) {
            throw new Exception("Illegal Index");
        }
        if (index == 0) {
            addFirst(name, number,email);
            return;
        }

        if (index == size) {
            addLast(name, number,email);
            return;
        }

        Node<T> temp = head;
        for (int i = 0; i < index - 1; i++) {
            temp = temp.next;
        }
        Node<T> newNode = new Node<>(name, email, number, temp.next);
        temp.next = newNode;

        size++;
    }

    public String peekFirst() {
        if (isEmpty()) throw new RuntimeException("Empty list");
        return head.name;
    }

    public String peekLast() {
        if (isEmpty()) throw new RuntimeException("Empty list");
        return tail.name;
    }

    public void removeFirst() {
        if (isEmpty()) throw new RuntimeException("Empty list");

        Node<T> trav = head;
        head = head.next;
        --size;

        // If the list is empty set the tail to null
        if (isEmpty()) tail = null;

        else {
            trav = null;
        }

    }

    public void removeLast() {
        Node<T> trav = head;
        Node<T> temp;
        for (int i = 0; i < size-2; i++) {
            trav = trav.next;
        }
        tail = trav;
        temp = trav.next;
        String data = temp.name;
        temp.name = null;
        temp.number = null;
        temp.email = null;
        temp.next = null;
        tail.next = null;
    }

    private void updateTail(){
        Node<T> temp = head;
        for (int i = 0; i < size - 1; i++) {
            if(temp.next == null) break;
            else temp = temp.next;
        }
        tail = temp;
    }

    private void remove(Node<T> node) {

        Node<T> temp = head;
        for (int i = 0; i < size - 1; i++) {
            if(temp.next == node) break;
            else temp = temp.next;
        }
        temp.next = node.next;


        node.name = null;
        node.email = null;
        node.number = null;
        node = node.next = null;

        --size;
        updateTail();
    }

    public boolean removeAt(String name) {
        // Make sure the index provided is valid
        if (isEmpty()) {
            throw new IllegalArgumentException();
        }

        int i;
        Node<T> trav;
        for (trav = head; trav.name != name; trav = trav.next) {
                trav = trav.next;
        }
        remove(trav);
        return true;
    }

    public boolean remove(Object obj) {
        Node<T> trav = head;

        if (obj == null) {
            for (trav = head; trav != null; trav = trav.next) {
                if (trav.name == null || trav.number == null || trav.email == null) {
                    remove(trav);
                    return true;
                }
            }
        } else {
            for (trav = head; trav != null; trav = trav.next) {
                if (obj.equals(trav.name) || obj.equals(trav.number) || obj.equals(trav.email)) {
                    remove(trav);
                    return true;
                }
            }
        }
        return false;
    }

    public int indexOf(Object obj) {
        int index = 0;
        Node<T> trav = head;

        if (obj == null) {
            for (; trav != null; trav = trav.next, index++) {
                if (trav.name == null || trav.number == null || trav.email == null) {
                    return index;
                }
            }
        } else {
            for (; trav != null; trav = trav.next, index++) {
                if (obj.equals(trav.name) || obj.equals(trav.number) || obj.equals(trav.email)) {
                    return index;
                }
            }
        }
        return -1;
    }

    public boolean contains(Object obj) {
        return indexOf(obj) != -1;
    }

    @Override
    public java.util.Iterator<T> iterator() {
        return new java.util.Iterator<T>() {
            private Node<T> trav = head;

            @Override
            public boolean hasNext() {
                return trav != null;
            }

            @Override
            public T next() {
                String data = trav.name;
                trav = trav.next;
                return (T) data;
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("*******************\n");
        Node<T> trav = head;
        int i = 1;
        while (trav != null) {
            sb.append(i + ". Name: "+trav.name+"\n");
            sb.append("Number: "+trav.number+"\n");
            sb.append("Email: "+trav.email+"\n");
            if (trav.next != null) {
                sb.append("\n");
            }
            trav = trav.next;
            i++;
        }
        sb.append("*******************\n");
        return sb.toString();
    }
}