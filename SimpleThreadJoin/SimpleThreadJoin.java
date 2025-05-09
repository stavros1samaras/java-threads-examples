public class SimpleThreadJoin {

    public static void main(String[] args) {

        System.out.println("In main: create and start a thread ");

        MyThreadA aThreadA = new MyThreadA(0);  
        MyThreadB aThreadB = new MyThreadB(1);
        aThreadA.start();
        aThreadB.start();

        try {
            aThreadA.join();
            aThreadB.join();
        } catch (InterruptedException e) {
        }

        System.out.println("In main: thread is done");
    }
}

class MyThreadA extends Thread {

    private int myID;

    public MyThreadA(int myID) {
        this.myID = myID;
    }

    public void run() {
        System.out.println("Hello from thread " + myID + " i am from MyThreadA class!!");
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
        System.out.println("Thread " + myID + " exits");
    }
}
