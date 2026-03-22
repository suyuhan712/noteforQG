package 代码示例;

public class 方法demo {
    public static void main(String[] args) {
        int i=10,j=15;
        int c=add(i,j);
        System.out.println(c);//方法相当于c语言的函数
    }
//public static 返回值类型 方法名（参数）{
    //方法体；
    //return 返回值；
// }
    public static int add(int a,int b){
        return a+b;
    }
}
