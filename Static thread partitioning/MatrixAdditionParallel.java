public class MatrixAdditionParallel {

    public static void main(String args[]) {
        int size = 100;

        double[][] a = new double[size][size];
        double[][] b = new double[size][size];
        double[][] c = new double[size][size];
        for (int i = 0; i < size; i++)
            for (int j = 0; j < size; j++) {
                a[i][j] = 0.1;
                b[i][j] = 0.3;
                c[i][j] = 0.5;
            }
        long start = System.currentTimeMillis();

        int numThreads = Runtime.getRuntime().availableProcessors();

        SinxThread threads[] = new SinxThread[numThreads];

        int block = size / numThreads;
        int from = 0;
        int to = 0;

        for (int i = 0; i < numThreads; i++) {
            from = i * block;
            to = i * block + block;
            if (i == (numThreads - 1)) {
                to = size;
            }
            threads[i] = new SinxThread(a, b, c, from, to);
            threads[i].start();
        }

        for (int i = 0; i < numThreads; i++) {
            try {
                threads[i].join();
            } catch (InterruptedException e) {
            }
        }

        long elapsedTimeMillis = System.currentTimeMillis() - start;
        System.out.println("time in ms = " + elapsedTimeMillis);

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++)
                System.out.print(a[i][j] + " ");
            System.out.println();
        }

    }
}

class SinxThread extends Thread {
    private double[][] a, b, c;
    private int from, to;

    public SinxThread(double[][] a, double[][] b, double[][] c, int from, int to) {
        this.a = a;
        this.b = b;
        this.c = c;
        this.from = from;
        this.to = to;
    }

    public void run() {
        for (int i = from; i < to; i++) {
            for (int j = 0; j < a[0].length; j++) {
                a[i][j] = b[i][j] + c[i][j];
            }
        }
    }
}
