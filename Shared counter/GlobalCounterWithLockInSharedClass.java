import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class GlobalCounterWithLockInSharedClass {

    static int numThreads = 4;

    public static void main(String[] args) {

        SharedVariables sharedVariables = new SharedVariables();

        CounterThread threads[] = new CounterThread[numThreads];

        for (int i = 0; i < numThreads; i++) {
            threads[i] = new CounterThread(sharedVariables);
            threads[i].start();
        }

        for (int i = 0; i < numThreads; i++) {
            try {
                threads[i].join();
            } catch (InterruptedException e) {
            }
        }
        check_array(sharedVariables);

    }

    static void check_array(SharedVariables sharedVariables) {
        int i, errors = 0;

        System.out.println("Checking...");

        for (i = 0; i < sharedVariables.end; i++) {
            if (sharedVariables.getA(i) != 1) {
                errors++;
                System.out.printf("%d: %d should be 1\n", i, sharedVariables.getA(i));
            }
        }
        System.out.println(errors + " errors.");
    }

    static class SharedVariables {

        int end;
        int counter;
        int[] array;

        Lock lock = new ReentrantLock();

        public SharedVariables() {
            this.end = 10000;
            this.counter = 0;
            this.array = new int[end];
        }

        public void inc(int i) {
            lock.lock();
            try {
                array[i]++;
            } finally {
                lock.unlock();
            }
        }

        public void incC() {
            lock.lock();
            try {
                counter++;
            } finally {
                lock.unlock();
            }
        }

        public int getC() {
            lock.lock();
            try {
                return (counter);
            } finally {
                lock.unlock();
            }
        }

        public int getA(int i) {
            lock.lock();
            try {
                return (array[i]);
            } finally {
                lock.unlock();
            }
        }        

    }

    static class CounterThread extends Thread {

        SharedVariables sV;

        public CounterThread(SharedVariables sharedVariables) {
            this.sV = sharedVariables;
        }

        public void run() {

            while (true) {
                if (sV.getC() >= sV.end)
                    break;
                sV.inc(sV.getC());
                sV.incC();
            }
        }

    }
}
