package com.mzy.bio;

import java.io.*;

/**
 * @program: nettyStudy
 * @author: mengzy 18306299232@163.com
 * @create: 2020-06-17 10:48
 **/
public class readDemo {

    //字符流

    public static void copyByinputstreamReader(String input, String output) {

        FileInputStream fileInputStream = null;
        try {
            fileInputStream = new FileInputStream(input);
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
            FileWriter fileWriter = new FileWriter(output);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String s;
            while ((s=bufferedReader.readLine())!=null){
                bufferedWriter.write(s);

            }
            bufferedWriter.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public static void main(String[] args) {
        readDemo.copyByinputstreamReader("d://1.txt", "d://4.txt");


    }
}
