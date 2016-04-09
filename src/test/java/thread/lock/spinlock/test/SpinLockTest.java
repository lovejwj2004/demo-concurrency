package thread.lock.spinlock.test;

import org.junit.Assert;
import org.junit.Test;
import thread.lock.spinlock.SpinLock;


/**
 * Created by lovejwj2004 on 2016/4/9.
 */
public class SpinLockTest {
    @Test
    public void testLockAndUnlock(){
        int countNum = 10000000;
        SpinLock spinLock = new SpinLock();
//        Lock spinLock = new ReentrantLock();
         class TestValue{
             private int i = 0;
             public void increase(){
                 i++;
             }
             public int getValue(){
                 return i;
             }
         }

        final TestValue value = new TestValue();
        class CThread extends Thread{
            public void run(){
                for(int i=0 ; i< countNum ; i++){
                    spinLock.lock();
                    value.increase();
                    spinLock.unlock();
                }
            }
        }

        CThread threadA = new CThread();
        CThread threadB = new CThread();

        threadA.start();
        threadB.start();
        try {
            threadA.join();
            threadB.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Assert.assertEquals(countNum*2,value.getValue());
    }
}
