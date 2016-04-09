package thread.basic;

/**
 * Created by lovejwj2004 on 2016/4/8.
 */
public class HelloThread {
    public static void main(String[] args) throws InterruptedException{
        //Test One
//        Thread newThread = new Thread(){
//            public void run(){
//                System.out.println("Hello from the New Thread");
//            }
//        };
//        newThread.start();
//        System.out.println("Hello from the Main Thread");

        //Test Two
        class Count{
            private int count = 0;
            public synchronized void incement(){
                count++;
            }
            public int getCount(){
                return count;
            }
        }

        final Count count = new Count();

        //Create a custom Thread Class
        class CountThread extends Thread{
            public void run(){
                for (int i=0;i<10000;i++){
                    count.incement();
                }
            }
        }

        CountThread countA = new CountThread();
        CountThread countB = new CountThread();
        countA.start();
        countB.start();
        countA.join();
        countB.join();
        System.out.println(count.getCount());
    }
}
