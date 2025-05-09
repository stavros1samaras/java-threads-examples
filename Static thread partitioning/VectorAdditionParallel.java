public class VectorAdditionParallel {

    public static void main(String args[]) {
        int size = 1000000;

        double[] a = new double[size];
        double[] b = new double[size];
        double[] c = new double[size];
        for (int i = 0; i < size; i++) {
            a[i] = 0.0;
            b[i] = 1.0;
            c[i] = 0.5;
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
            if (i == (numThreads - 1))
                to = size;
            threads[i] = new SinxThread(a, b, c, from, to);
            threads[i].start();
        }
       
        for(int i = 0; i < numThreads; i++) {
			try {
				threads[i].join();
			} catch (InterruptedException e) {}
		}

        long elapsedTimeMillis = System.currentTimeMillis() - start;
        System.out.println("time in ms = " + elapsedTimeMillis);
    }
}

class SinxThread extends Thread {
    private double[] tableA;
    private double[] tableB;
    private double[] tableC;
    private int myfrom;
    private int myto;

    // constructor
    public SinxThread(double[] a, double[] b, double[] c, int from, int to) {
        tableA = a;
        tableB = b;
        tableC = c;
        myfrom = from;
        myto = to;
    }

    public void run() {
        for (int i = myfrom; i < myto; i++) {
            tableA[i] = tableB[i] + tableC[i];
        }

    }

}