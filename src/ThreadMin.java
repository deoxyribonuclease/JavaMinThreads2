class ThreadMin extends Thread {
    private final int startIndex;
    private final int endIndex;
    private final ArrayClass arrClass;
    private final int threadIndex;

    public ThreadMin(int startIndex, int endIndex, ArrayClass arrClass, int threadIndex) {
        this.startIndex = startIndex;
        this.endIndex = endIndex;
        this.arrClass = arrClass;
        this.threadIndex = threadIndex;
    }

    @Override
    public void run() {
        long min = arrClass.partMin(startIndex, endIndex);
        arrClass.minValue(min,  findMinIndex(min));
        arrClass.incThreadCount();
        System.out.println("Thead "+threadIndex+" min element: " + min + " at index: " + findMinIndex(min));
    }

    public int findMinIndex(long min) {
        for (int i = startIndex; i < endIndex; i++) {
            if (arrClass.array[i] == min) {
                return i;
            }
        }
        return -1;
    }
}