package com.itheima._15单例模式;

/**
 * @author codedawn
 * @date 2021-06-24 11:07
 */
public class SingleTest {
    private SingleTest() {

    }

    private static final SingleTest SINGLE_TEST = new SingleTest();

    public static SingleTest getSingleTest() {
        return SINGLE_TEST;
    }
}


class SingleTest2 {
    private SingleTest2() {

    }

    private static SingleTest2 singleTest2;


    public static SingleTest2 getSingleTest2() {
        if (singleTest2 == null) {
            singleTest2 = new SingleTest2();
        }
        return singleTest2;
    }
}
