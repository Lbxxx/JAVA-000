package java0.conc0303;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class Homework03way2 {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        long start=System.currentTimeMillis();
        // 在这里创建一个线程或线程池，
        // 异步执行 下面方法

        ThreadC threadC =  new ThreadC();
        FutureTask<Integer> futureTask = new FutureTask<>(threadC);
        new Thread(futureTask).start();

        // 确保  拿到result 并输出
        System.out.println("异步计算结果为："+futureTask.get());

        System.out.println("使用时间："+ (System.currentTimeMillis()-start) + " ms");

        // 然后退出main线程
    }

    public static class ThreadC implements Callable<Integer> {

        @Override
        public Integer call() throws Exception {
            int result = sum();
            return result;
        }

    }

    private static int sum() {
        return fibo(36);
    }

    private static int fibo(int a) {
        if ( a < 2)
            return 1;
        return fibo(a-1) + fibo(a-2);
    }
}
