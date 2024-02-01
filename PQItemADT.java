package PriorityQueues;

public interface PQItemADT {
    // print information of the PQ item
    public String toString();

    // return the priority of the PQ item
    public Object getPriority();

    // compare the priority of this item with that of other item
    // return value >, ==, < 0 if the priority of this item is >, equals, < that of
    // other item
    public int compareTo(PQItem otherItem);
}
