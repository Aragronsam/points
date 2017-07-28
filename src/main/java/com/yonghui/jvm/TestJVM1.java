package com.yonghui.jvm;

/**
 * 功能描述:
 *
 * @Author: Aragron
 * @Create: 2017-07-20-10:27
 * @Home: http://aragron.com
 */
public class TestJVM1 {
    class User {
        int id;
        String name;

        public User(int id, String name) {
            this.id = id;
            this.name = name;
        }
    }
    void alloc(int i) {
        new User(i, "name" + i);
    }

    public static void main(String[] args) {
        TestJVM1 testJVM_ = new TestJVM1();
        long s1 = System.currentTimeMillis();
        for (int i = 0; i < 1900000000; i++) {
            testJVM_.alloc(i);
        }
        long s2 = System.currentTimeMillis();
        System.out.println(s2 - s1);

    }

}
