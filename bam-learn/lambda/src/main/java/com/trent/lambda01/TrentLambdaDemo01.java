package com.trent.lambda01;

public class TrentLambdaDemo01 {
    public static void main(String[] args) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("thread runnable");
            }
        }).start();

        //lambda写法
        new Thread(() -> {System.out.println("thread runnable");}).start();
    }
    public void lambdaDome01(){

    }
}
