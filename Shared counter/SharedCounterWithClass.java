
public class SharedCounterWithClass {

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

	static void check_array(SharedVariables sv) {
		int i, errors = 0;

		System.out.println("Checking...");

		for (i = 0; i < sv.end; i++) {
			if (sv.get(i) != numThreads * i) {
				errors++;
				System.out.printf("%d: %d should be %d\n", i, sv.get(i), numThreads * i);
			}
		}
		System.out.println(errors + " errors.");
	}

	static class SharedVariables {

		int end;
		int[] array;

		public SharedVariables() {
			this.end = 1000;
			this.array = new int[end];
		}

		public void inc(int i) {
			array[i]++;
		}

		public int get(int i) {
			return (array[i]);
		}

	}

	static class CounterThread extends Thread {

		SharedVariables sV;

		public CounterThread(SharedVariables sharedVariables) {
			this.sV = sharedVariables;
		}

		public void run() {

			for (int i = 0; i < sV.end; i++) {
				for (int j = 0; j < i; j++)
					sV.inc(i);
			}
		}
	}
}
