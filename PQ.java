package PriorityQueues;

public class PQ implements PQADT {
    private PQItem[] item;
    private int nItems;
    private int maxSize;

    public PQ(int maxSize) {
        this.maxSize = maxSize;
        item = new PQItem[maxSize];
        nItems = 0;
    }

    // เพิ่ม PQItem เข้า Priority Queue
    public void insert(PQItem newItem) throws PQFullException {
        if (nItems == maxSize) {
            throw new PQFullException("Priority Queue is full.");
        }
        int p = nItems++;
        while ((p != 0) && (item[parent(p)].compareTo(newItem) < 0)) {
            item[p] = item[parent(p)];
            p = parent(p);
        }
        item[p] = newItem;
    }

    // นำออกและคืนค่า PQItem ที่มีค่ามากที่สุด
    public PQItem removeMax() throws PQEmptyException {
        if (isEmpty()) {
            throw new PQEmptyException("Priority Queue is empty.");
        }
        PQItem max = item[0];
        item[0] = item[--nItems];
        maxHeapify(0);
        return max;
    }

    // คืนค่า PQItem ที่มีค่ามากที่สุดโดยไม่นำออกจาก Priority Queue
    public PQItem max() throws PQEmptyException {
        if (isEmpty()) {
            throw new PQEmptyException("Priority Queue is empty.");
        }
        return item[0];
    }

    // ส่งคืนจำนวนสมาชิกใน Priority Queue
    public int size() {
        return nItems;
    }

    // ตรวจสอบว่า Priority Queue ว่างหรือไม่
    public boolean isEmpty() {
        return nItems == 0;
    }

    // จัดเรียงให้เป็น Max Heap
    private void maxHeapify(int i) {
        int l = leftChild(i);
        int r = rightChild(i);
        int largest;

        if ((l < nItems) && (item[l].compareTo(item[i]) > 0)) {
            largest = l;
        } else {
            largest = i;
        }

        if ((r < nItems) && (item[r].compareTo(item[largest]) > 0)) {
            largest = r;
        }

        if (largest != i) {
            PQItem temp = item[largest];
            item[largest] = item[i];
            item[i] = temp;
            maxHeapify(largest);
        }
    }

    // คำนวณตำแหน่งของ parent
    private int parent(int i) {
        return (i - 1) / 2;
    }

    // คำนวณตำแหน่งของ left child
    private int leftChild(int i) {
        return 2 * i + 1;
    }

    // คำนวณตำแหน่งของ right child
    private int rightChild(int i) {
        return 2 * i + 2;
    }

    public static void main(String[] args) {
        try {
            // สร้าง Priority Queue ที่มีขนาดสูงสุด 5
            PQ priorityQueue = new PQ(5);

            // เพิ่มข้อมูลลงใน Priority Queue
            priorityQueue.insert(new PQItem("B", 10));
            priorityQueue.insert(new PQItem("A", 10));
            priorityQueue.insert(new PQItem("A", 15));
            priorityQueue.insert(new PQItem("B", 5));

            // แสดงข้อมูลทั้งหมดใน Priority Queue
            System.out.println("Priority Queue content:");
            printPriorityQueue(priorityQueue);

            priorityQueue.insert(new PQItem("B", 10));
            priorityQueue.insert(new PQItem("A", 10));
            priorityQueue.insert(new PQItem("A", 15));
            priorityQueue.insert(new PQItem("B", 5));

            // นำข้อมูลที่มีค่ามากที่สุดออกจาก Priority Queue
            PQItem maxItem = priorityQueue.removeMax();
            System.out.println("\nRemoved item with key: " + maxItem.getPriority());

            // แสดงข้อมูลทั้งหมดใน Priority Queue หลังจากนำออก
            System.out.println("\nPriority Queue content after removal:");
            printPriorityQueue(priorityQueue);

            priorityQueue.insert(new PQItem("B", 10));
            priorityQueue.insert(new PQItem("A", 10));
            priorityQueue.insert(new PQItem("A", 15));
            priorityQueue.insert(new PQItem("B", 5));

            // แสดงข้อมูลที่มีค่ามากที่สุดใน Priority Queue
            PQItem maxItemAfterRemoval = priorityQueue.max();
            System.out.println("\nMax item in Priority Queue after removal: " + maxItemAfterRemoval.getPriority());

        } catch (PQEmptyException | PQFullException e) {
            // จัดการ Exception กรณี Priority Queue ว่างหรือเต็ม
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void printPriorityQueue(PQ priorityQueue) {
        while (!priorityQueue.isEmpty()) {
            try {
                PQItem item = priorityQueue.removeMax();
                System.out.print(item.getPriority() + " ");
            } catch (PQEmptyException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }
}