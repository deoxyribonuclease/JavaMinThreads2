

public class Main {
    public static void main(String[] args) {
        int arrSize = 200000;
        int threadNum = 10;
        ArrayClass arrClass = new ArrayClass(arrSize, threadNum);
        long startTime = System.nanoTime();
        String result = arrClass.threadMin();
        long endTime = System.nanoTime();

        System.out.println("Execution time: " + (endTime - startTime) / 1e6 + " milliseconds");
        System.out.println(result);
    }
}