package 代码示例;

import java.util.Random;
import java.util.Scanner;

public class 面向对象demo {
    public static void main(String[] args) {
        Random rand=new Random();//得到一个随机数对象，用于得到随机数
        int data =rand.nextInt(10)+1;//生成1-10之间的随机数
        System.out.println(data);

        //创建一个Scanner对象，用于接收用户输入的数据
        Scanner sc=new Scanner(System.in);
        System.out.println("输入你的年龄");
        int age=sc.nextInt();//nextInt 是这个盒子的名字（方法名），它的功能是从控制台读取一个整数
        System.out.println(age);
    }
}
//面向对象的重点学习：学习获取已有对象并使用
//学习如何自己设计对象
//封装，this关键字。构造方法，标准javabeen，成员变量。局部变量区别

