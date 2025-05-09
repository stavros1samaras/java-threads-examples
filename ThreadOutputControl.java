public class ThreadOutputControl {

    public static void main(String[] args) {

        System.out.println("In main: create and start a thread ");

        int numThreads = 10;
        Thread[] threadsA = new Thread[numThreads];
        Thread[] threadsB = new Thread[numThreads];

        for (int i = 0; i < numThreads; ++i) {
            System.out.println("In main: create and start thread " + i);
            threadsA[i] = new MyThreadA(i);
            threadsA[i].start();
        }
        for (int i = 0; i < numThreads; ++i) {
            System.out.println("In main: create and start thread " + i);
            threadsB[i] = new MyThreadB(i);
            threadsB[i].start();
        }

        /* wait for threads to finish */
        for (int i = 0; i < numThreads; ++i) {
            try {
                threadsA[i].join();
                threadsB[i].join();

            } catch (InterruptedException e) {
                System.err.println("this should not happen");
            }
        }
    }
}

class MyThreadA extends Thread {

    private int myID;

    public MyThreadA(int myID) {
        this.myID = myID;
    }

    public void run() {
        System.out.println("Hello from thread " + myID + " i am from MyThreadA class!!");
        for (int j = 1; j <= 10; j++) {
            System.out.println(j + " * " + myID + " = " + (j * myID));
        }    
        System.out.println("Thread " + myID + " exits");        

    }
}

class MyThreadB extends Thread {

    private int myID;

    public MyThreadB(int myID) {
        this.myID = myID;
    }

    public void run() {
        System.out.println("Hello from thread " + myID + " i am from MyThreadB class!!");
        for (int j = 1; j <= 10; j++) {
            System.out.println(j + " * " + myID + " = " + (j * myID));
        } 
        System.out.println("Thread " + myID + " exits");

    }
}
