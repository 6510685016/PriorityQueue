package PriorityQueue;

public interface PQADT {
    void insert(PQItem newItem) throws PQFullException;

    PQItem removeMax() throws PQEmptyException;

    PQItem max() throws PQEmptyException;
}
