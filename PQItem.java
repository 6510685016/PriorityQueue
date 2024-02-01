package PriorityQueues;

public class PQItem implements PQItemADT {
    String Priority;
    int info;

    public PQItem(String Priority, int info) {
        this.Priority = Priority;
        this.info = info;
    }

    // print information of the PQ item
    public String toString() {
        return info + "|Priority : " + Priority + "\n";
    }

    // return the priority of the PQ item
    public Object getPriority() {
        return Priority;
    }

    // compare the priority of this item with that of other item
    // return value >, ==, < 0 if the priority of this item is >, equals, < that of
    // other item
    public int compareTo(PQItem otherItem) {
        return Priority.compareTo(otherItem.Priority);
    }
}