package 代码示例;

import java.util.Random;

public class 数组demo {
    public static void main(String[] args) {
        //格式：静态初始化
        //完整格式：数据类型[] 数组名=new 数据类型[]{元素1，元素2…};
        //简化格式：数据类型[] 数组名={元素1，元素2…}
        int[] arr1 = new int []{11,12,13,14};
        int[] arr2= {11,12,13,1 };

        String[] arr3= new String[]{"suyuhan","dashuaige","kedaibiao"};
        String[] arr4={"zhuzzhixuan","guoshiyi","wenjiarong"};

        double[] arr5=new double[]{1.66,1.77,1.88};
        double[] arr6={1.66,1.77,1.88};

        //System.out.println(arr6);//[D@6acbcfc0 地址值
        //[:表示当前是一个数组
        //D：表示当前数组里的元素都说double类型的
        //@：表示一个间隔符号
        //后面的数字字母才是真正的地址值

        //索引：也叫做角标和下标.
        //索引的特点：从零开始，逐个+1增长，连续不间断

        //利用索引对数组中的元素进行访问
        //1.获取数组里面的元素
        //格式： 数组名[索引]

        int[] arr={1,2,3,4,5};
        //获取数组中的第一元素，就是0索引上对应的元素
        int number = arr[0];
        System.out.println(number);//第一元素
        System.out.println(arr[1]);//第二元素

        //把数据存储到数组中
        //格式：数组名[索引] = 具体数组/变量；
        //一旦覆盖之后，原来的数据就不存在了
        arr[0]= 100;
        System.out.println(arr[0]);

        //数组遍历:指的是取出数组的过程，可以用来（打印，求和，判断）
        //获取数组里面所有的元素
        //格式：数组名[索引]
        System.out.println(arr[0]);
        System.out.println(arr[1]);
        System.out.println(arr[2]);
        System.out.println(arr[3]);
        System.out.println(arr[4]);

        //利用循环改进代码
        //开始条件：0
        //结束条件：数组长度-1
        for(int i = 0;i<5;i++){
            System.out.println(arr[i]);
        }

        //在java中的关于数组的长度属性，length
        //调用方式：数组名.length
        System.out.println(arr.length);

        for(int i = 0;i< arr.length;i++){
            System.out.println(arr[i]);
        }

        //数组的动态初始化
        //动态初始化：初始化时只指定数组长度，由系统为数组分配初始值
        //格式：数据类型[] 数组名 = new 数据类型[数组长度]；
        //int[] arr =new int [3];

        //定义一个数组，用来存储班级中50学生的姓名，姓名位置。等学生报道之后再进行添加
        //格式：数据类型[] 数组名 = new 数据类型[数组长度]；

        String[] arr7 = new String[50];
        arr7[0]="suyuhan";
        //……以此类推
        System.out.println(arr7[0]);
        System.out.println(arr7[1]);
        //打印出的默认初始化值null

        //数组默认初始化值规律
        //整数类型：0
        //小数类型：0.0
        //字符类型：‘/u0000’
        //布尔类型：false
        //引用数据类型：null

        //动态初始化:手动指定数组长度，只明确元素个数
        //静态初始化：手动指定元素，只明确元素本身

        Random r=new Random();//随机数
        int[] arr8={1,2,3,4,5};
        for(int j=0;j<arr8.length;j++){
            int randomIndex=r.nextInt(arr8.length);
            int temp=arr8[j];
            arr8[j]=arr8[randomIndex];
            arr8[randomIndex]=temp;
        }
        for(int j=0;j<arr8.length;j++){
            System.out.println(arr8[j]);
        }
        //打乱了一个数组
    }
}
