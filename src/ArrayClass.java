public class ArrayClass {
    private final int arrSize;
    private final int threadNum;
    public final int[] array;
    private long min = Long.MAX_VALUE;
    private int minIndex = -1;

    public ArrayClass(int arrSize, int threadNum) {
        this.arrSize = arrSize;
        this.threadNum = threadNum;
        array = new int[arrSize];
        generateArray();
    }

    public void generateArray() {
        for (int i = 0; i < arrSize ; i++) {
            array[i] = (int) (Math.random()*100000);
        }
        int randomIndex = (int) (Math.random() * arrSize );
        array[randomIndex] = -100;
        System.out.println("Element at index " + randomIndex + " is now negative: " + array[randomIndex]);
    }

    public long partMin(int startIndex, int finishIndex){
        long min = Long.MAX_VALUE;
        for(int i = startIndex; i < finishIndex; i++){
            if (array[i] < min) {
                min = array[i];
            }
        }
        return min;
    }
    synchronized private long getMin() {
        while (getThreadCount() < threadNum) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return min;
    }

    synchronized public void minValue(long min, int minIndex){
        if (min < this.min) {
            this.min = min;
            this.minIndex = minIndex;
        }
    }

    synchronized private int getMinIndex() {
        while (getThreadCount() < threadNum) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return minIndex;
    }
    private int threadCount = 0;

    synchronized public void incThreadCount(){
        threadCount++;
        if (threadCount == threadNum) {
            notify();
        }
    }

    private int getThreadCount() {
        return threadCount;
    }

    public String threadMin(){
        ThreadMin[] threadMins = new ThreadMin[threadNum];
        int chunkSize = arrSize / threadNum;
        for (int i = 0; i < threadNum; i++) {
            int startIndex = i * chunkSize;
            int endIndex = (i == threadNum - 1) ? arrSize : (i + 1) * chunkSize;
            threadMins[i] = new ThreadMin(startIndex, endIndex, this, i);
            threadMins[i].start();
        }
        long min = getMin();
        int minIndex = getMinIndex();
        return "Minimum element: " + min + " at index: " + minIndex;
    }
}