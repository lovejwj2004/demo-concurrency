package thread.lock.spinlock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * Created by lovejwj2004 on 2016/4/8.
 */
public class SpinLock implements Lock {

    private AtomicBoolean isLock =new  AtomicBoolean(false);
    private static volatile String owner = null;

    @Override
    public void lock() {
        while(true){
            if(isLock.compareAndSet(false,true)){
                owner = Thread.currentThread().getName();
                break;
            }
        }
    }

    @Override
    public void unlock(){
        if(owner.equals(Thread.currentThread().getName())) {
            owner = null;
            isLock.compareAndSet(true,false);
        }else if(!owner.equals(Thread.currentThread().getName())  && isLock.get() ){
            throw new RuntimeException("ILLEGAL UNLOCK THREAD");
        }
    }



    @Override
    public void lockInterruptibly() throws InterruptedException {

    }

    @Override
    public boolean tryLock() {
        return false;
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return false;
    }

    @Override
    public Condition newCondition() {
        return null;
    }
}
