package 代码示例;
//类名首字母建议大写，并且需要见名知意，驼峰形式
//一个java文件可以定义多个class类，只能有一个类是public修饰，并且必须成为文件代码名
//成员变量定义的完整格式：
// 修饰符 数据类型 变量名称 =初始化值；
public class 类2 {
    public static void main(String[] args) {
        //创建手机对象
        类 phone =new 类();

        phone.brand="小米";//给phone赋予一个小米的brand
        phone.price=7000;//赋予价格

        System.out.println(phone.brand);
        System.out.println(phone.price);

        //调用手机中的方法即可
        phone.call();
        phone.paly();

    }
}


//javabean类中没有main方法
//编写main方法的类叫做测试类

