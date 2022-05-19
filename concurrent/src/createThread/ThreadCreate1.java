package createThread;

public class ThreadCreate1 extends Thread {

    @Override
    public void run() {
        System.out.println(getName() + " is running");
    }
}
