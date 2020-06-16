package com.mzy.bio;

import javax.sound.midi.Soundbank;
import java.util.StringTokenizer;

/**
 * @program: nettyStudy
 * @author: mengzy 18306299232@163.com
 * @create: 2020-06-14 14:17
 **/
public class test2 {

    public static void main(String[] args) {


        Long sta = System.currentTimeMillis();
        StringTokenizer st = new StringTokenizer("www,ooobj,com", ",");
        for (int i = 0; i < 100000; i++) {
            while (st.hasMoreElements()) {
                st.nextToken();
            }

        }
        System.out.println("耗时：" + (int) (System.currentTimeMillis() - sta));

        //test
        Long sta2 = System.currentTimeMillis();
        for (int i = 0; i < 100000; i++) {
            String[] split = "www,ooobj,com".split(",");
            String s = split[0];
            String x = split[1];
            String y = split[2];
        }
        System.out.println("耗时：" + (int) (System.currentTimeMillis() - sta2));
        System.out.println(199943232<<1);


    }
}
