package 代码示例;

//类（设计图）：是对象共同特征的描述；
//对象：是真实存在的东西
//需要先设计类，才能获得对象

/*public class 类名{
1.成员变量（代表属性，一般是名词）
2.成员方法（代表行为，一般是动词）
3.构造器
4.代码块
5.内部类
 */
public class 类 {
    //属性
    String brand;
    double price;

    //行为
    public void call(){
        System.out.println("手机在打电话");
    }
    public void paly(){
        System.out.println("手机在玩游戏");
    }

}

/*举例子
public class Phone{
属性()成员变量；
string brand;
double price;
//行为(方法)
public void call(){
}
public void playGame(){
}
 */