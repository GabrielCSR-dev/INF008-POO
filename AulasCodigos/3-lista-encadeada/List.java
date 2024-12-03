public class List
{
    class Node { // Inner class
        public Object data = null;
        public Node nextNode = null;
    }
    private Node firstNode = null;

    public void insert(Object data, int position) {
        if (position < 0 || position > size()) {
            System.out.println("Invalid insert position!");
            return;
        }
        Node newNode = new Node();
        newNode.data = data;

        Node nodeBeforeInsertion = null;
        for (int i = 0; i < position; i++)
            nodeBeforeInsertion = nodeBeforeInsertion == null ? firstNode : nodeBeforeInsertion.nextNode;
        if (nodeBeforeInsertion == null) {
            newNode.nextNode = firstNode;
            firstNode = newNode;
        } else {
            newNode.nextNode = nodeBeforeInsertion.nextNode;
            nodeBeforeInsertion.nextNode = newNode;
        }
    }
    public int size() {
        int size = 0;
        Node currentNode = firstNode;
        while (currentNode != null) {
            size++;
            currentNode = currentNode.nextNode;
        }
        return size;
    }
    public void insert(Object data) {
        Node newNode = new Node();
        newNode.data = data;
        if (firstNode == null) {
            firstNode = newNode;
        } else {
            Node currentNode = firstNode;
            while(currentNode.nextNode != null)
                currentNode = currentNode.nextNode;
            currentNode.nextNode = newNode;
        }
    }
    public void display() {
        Node currentNode = firstNode;
        while(currentNode != null) {
            System.out.println(currentNode.data);
            currentNode = currentNode.nextNode;
        }
    }
    public void remove(int position) {
        if (position < 0 || position >= size()) {
            System.out.println("Invalid removal position!");
            return;
        }
        Node nodeBeforeRemoval = null;
        for (int i = 0; i < position; i++)
            nodeBeforeRemoval = nodeBeforeRemoval == null ? firstNode : nodeBeforeRemoval.nextNode;
        if (nodeBeforeRemoval == null) {
            firstNode = firstNode.nextNode;
        } else {
            nodeBeforeRemoval.nextNode = nodeBeforeRemoval.nextNode.nextNode;
        }
    }
    public int indexOf(Object data) {
        Node currentNode = firstNode;
        int position = 0;
        while (currentNode != null) {
            if (currentNode.data == data)
                return position;
            currentNode = currentNode.nextNode;
            position++;
        }

        return -1;
    }
}
