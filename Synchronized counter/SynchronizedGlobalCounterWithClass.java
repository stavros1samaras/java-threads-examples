public class SynchronizedGlobalCounterWithClass {
    
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
		check_array (sharedVariables);

	}

	static void check_array (SharedVariables sharedVariables)  {
		int i, errors = 0;

		System.out.println ("Checking...");

        for (i = 0; i < sharedVariables.end; i++) {
			if (sharedVariables.getA(i)!= 1) {
				errors++;
				System.out.printf("%d: %d should be 1\n", i, sharedVariables.getA(i));
			}         
		}
        System.out.println (errors+" errors.");
    }

	static class SharedVariables {

		int end;
		int counter;
		int[] array;

        //Object lockObj = new Object();

		public SharedVariables() {
			this.end = 10000;
			this.counter = 0;
			this.array = new int[end];
		}

		public void inc(int i) {
            synchronized (this)
            {
                array[i]++;
            }
		}

		public void incC() {
            synchronized (this){
			counter++;
        }
		}

		public int getC() {
            synchronized (this){
			return (counter);
            }
		}

		public int getA(int i) {
            synchronized (this){
			return (array[i]);
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
                synchronized (sV) {
				if (sV.getC() >= sV.end)
					break;
				sV.inc(sV.getC());
				sV.incC();
			}
		}}

	}
}
