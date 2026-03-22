import java.util.*;  // 导入java.util包下的所有类，包括Scanner和ArrayList

/**
 * 学生管理系统主类
 * 用于管理学生的增删改查等操作
 */
public class 学生管理系统{
    
    /**
     * 程序的主方法，程序入口
     * @param args 命令行参数
     */
    public static void main(String[] args){

        // 创建Scanner对象，用于接收用户从键盘输入的数据
        Scanner sc = new Scanner(System.in);
        
        // 创建ArrayList集合，用于存储Student对象
        // ArrayList是动态数组，可以自动扩容
        ArrayList<Student> list = new ArrayList<>();

        // 使用while(true)创建无限循环，让程序持续运行直到用户选择退出
        while(true){

            // 打印系统菜单界面
            System.out.println("--------学生管理系统--------");
            System.out.println("1 添加学生");
            System.out.println("2 删除学生");
            System.out.println("3 修改学生");
            System.out.println("4 查询学生");
            System.out.println("5 显示所有学生");
            System.out.println("6 退出");

            // 接收用户输入的数字选项，并存储到choice变量中
            int choice = sc.nextInt();
            
            // 使用switch语句根据用户的选择执行不同的操作
            switch(choice){
                case 1:  // 添加学生
                    System.out.println("添加学生");
                    System.out.println("请输入学生学号：");
                    int id = sc.nextInt();

                    System.out.println("请输入学生姓名：");
                    String name = sc.next();

                    System.out.println("请输入学生年龄：");
                    int age = sc.nextInt();

                    System.out.println("请输入学生地址：");
                    String address = sc.next();

                    // 创建Student对象并设置属性
                    Student s = new Student();
                    s.setId(id);
                    s.setName(name);
                    s.setAge(age);
                    s.setAddress(address);

                    // 将学生对象添加到ArrayList中
                    list.add(s);

                    System.out.println("添加成功");
                    break;
                    
                case 2:  // 删除学生
                    System.out.println("删除学生");
                    if(list.size() == 0){
                        System.out.println("没有学生信息，无法删除");
                        break;
                    }
                    System.out.println("请输入要删除的学生学号：");
                    int delId = sc.nextInt();
                    
                    boolean found = false;  // 标记是否找到要删除的学生
                    for(int i = 0; i < list.size(); i++){
                        if(list.get(i).getId() == delId){
                            list.remove(i);  // 删除指定位置的学生
                            found = true;
                            break;
                        }
                    }
                    if(found){
                        System.out.println("删除成功");
                    }else{
                        System.out.println("未找到该学号的学生");
                    }
                    break;
                    
                case 3:  // 修改学生
                    System.out.println("修改学生");
                    if(list.size() == 0){
                        System.out.println("没有学生信息，无法修改");
                        break;
                    }
                    System.out.println("请输入要修改的学生学号：");
                    int modId = sc.nextInt();
                    
                    boolean modFound = false;
                    for(Student stu : list){
                        if(stu.getId() == modId){
                            System.out.println("请输入新的姓名：");
                            stu.setName(sc.next());
                            
                            System.out.println("请输入新的年龄：");
                            stu.setAge(sc.nextInt());
                            
                            System.out.println("请输入新的地址：");
                            stu.setAddress(sc.next());
                            
                            modFound = true;
                            break;
                        }
                    }
                    if(modFound){
                        System.out.println("修改成功");
                    }else{
                        System.out.println("未找到该学号的学生");
                    }
                    break;
                    
                case 4:  // 查询学生
                    System.out.println("查询学生");
                    if(list.size() == 0){
                        System.out.println("没有学生信息");
                        break;
                    }
                    System.out.println("请输入要查询的学生学号：");
                    int queryId = sc.nextInt();
                    
                    boolean queryFound = false;
                    for(Student stu : list){
                        if(stu.getId() == queryId){
                            System.out.println("学号：" + stu.getId());
                            System.out.println("姓名：" + stu.getName());
                            System.out.println("年龄：" + stu.getAge());
                            System.out.println("地址：" + stu.getAddress());
                            queryFound = true;
                            break;
                        }
                    }
                    if(!queryFound){
                        System.out.println("未找到该学号的学生");
                    }
                    break;
                    
                case 5:  // 显示所有学生
                    System.out.println("显示所有学生");
                    if(list.size() == 0){
                        System.out.println("没有学生信息");
                        break;
                    }
                    System.out.println("学号\t姓名\t年龄\t地址");
                    for(Student stu : list){
                        System.out.println(
                            stu.getId() + "\t" + stu.getName() + "\t" + stu.getAge() + "\t" + stu.getAddress()
                        );
                    }
                    break;
                    
                case 6:  // 退出系统
                    System.out.println("感谢使用学生管理系统，再见！");
                    System.exit(0);  // 退出程序
                    break;
                    
                default:  // 处理无效输入
                    System.out.println("输入错误，请输入1-6之间的数字");
                    break;
            }
        }
    }
}

/**
 * 学生类（Student）
 * 用于定义学生的属性信息
 */
class Student{
    // 姓名：String类型，private修饰表示私有属性，只能在类内部访问
    private String name;
    
    // 学号：int类型，整数类型
    private int id;
    
    // 年龄：int类型，整数类型
    private int age;
    
    // 地址：String类型，字符串类型
    private String address;
    
    // 获取学生姓名
    public String getName(){
        return name;
    }
    
    // 设置学生姓名
    public void setName(String name){
        this.name = name;
    }
    
    // 获取学生学号
    public int getId(){
        return id;
    }
    
    // 设置学生学号
    public void setId(int id){
        this.id = id;
    }
    
    // 获取学生年龄
    public int getAge(){
        return age;
    }
    
    // 设置学生年龄
    public void setAge(int age){
        this.age = age;
    }
    
    // 获取学生地址
    public String getAddress(){
        return address;
    }
    
    // 设置学生地址
    public void setAddress(String address){
        this.address = address;
    }
}