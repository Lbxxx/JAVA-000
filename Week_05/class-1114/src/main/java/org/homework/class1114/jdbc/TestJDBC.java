package org.homework.class1114.jdbc;

import org.homework.class1114.domain.Student;

import java.util.Arrays;
import java.util.Optional;
import java.util.Random;

//https://blog.csdn.net/qq_35006660/article/details/105641560?utm_medium=distribute.pc_relevant.none-task-blog-baidulandingword-6&spm=1001.2101.3001.4242
//https://blog.csdn.net/woshisangsang/article/details/104144715
//https://blog.csdn.net/zhangjingao/article/details/80331415
//https://blog.csdn.net/u013614451/article/details/44134333
public class TestJDBC {

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        //单条插入
        int id = insert();
        select(id);
        update(id,"linboxuan");
        select(id);
        delete(id);
        long l = System.currentTimeMillis();
        //批量插入
        batchInsert(100);
        long l2 = System.currentTimeMillis();
        System.out.println(l2-l+"ms");
    }

    private static void select(final int id) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        String url = "jdbc:mysql://10.26.27.54:3306/test?characterEncoding=utf8&useSSL=false&serverTimezone=GMT";
        Connection connection = DriverManager.getConnection(url, "root", "123456");
        PreparedStatement preparedStatement = connection.prepareStatement("select * from student where id = ?");
        preparedStatement.setInt(1,id);
        ResultSet resultSet = preparedStatement.executeQuery();
        Student student = null;
        while (resultSet.next()) {
            String name = resultSet.getString("name");
            student = new Student().setId(id).setName(name);
        }
        boolean present = Optional.ofNullable(student).isPresent();
        if (present) {
            System.out.println(student);
        } else {
            System.out.println("查询内容为空");
        }
    }

    private static int insert() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        String url = "jdbc:mysql://10.26.27.54:3306/test?characterEncoding=utf8&useSSL=false&serverTimezone=GMT";
        Connection connection = DriverManager.getConnection(url, "root", "123456");
        PreparedStatement preparedStatement = connection.prepareStatement("insert into student(name) values(?)",Statement.RETURN_GENERATED_KEYS);
        preparedStatement.setString(1, "backCoder4");
        preparedStatement.executeUpdate();
        int id = 0;
        ResultSet rs = preparedStatement.getGeneratedKeys();
        if (rs.next()) {
            id = rs.getInt(1);
            System.out.println("插入id　=　" + id);
        }
        connection.close();
        return id;
    }

    private static void batchInsert(final int num) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        String url = "jdbc:mysql://10.26.27.54:3306/test?characterEncoding=utf8&useSSL=false&serverTimezone=GMT";
        Connection connection = DriverManager.getConnection(url, "root", "123456");
        connection.setAutoCommit(false);
        PreparedStatement preparedStatement = connection.prepareStatement("insert into student(name)  values(?)");
        for (int i = 0; i < num; i++) {
            preparedStatement.setString(1, getStringRandom(9));
            preparedStatement.addBatch();
        }
        int[] ints = preparedStatement.executeBatch();
        System.out.println(Arrays.toString(ints));
        connection.commit();
        String message = ints.length > 0 ? "成功插入"+ints.length+"数据" : "保存数据失败";
        System.out.println(message);
        connection.close();
    }

    private static void delete(final int id) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        String url = "jdbc:mysql://10.26.27.54:3306/test?characterEncoding=utf8&useSSL=false&serverTimezone=GMT";
        Connection connection = DriverManager.getConnection(url, "root", "123456");
        PreparedStatement preparedStatement = connection.prepareStatement("delete from student where id =?");
        preparedStatement.setInt(1, id);
        int count = preparedStatement.executeUpdate();
        String message = count > 0 ? "删除数据库的数据成功" : "删除数据库的数据失败";
        System.out.println(message);
        connection.close();
    }

    private static  void update(final int id ,final String name) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        String url = "jdbc:mysql://10.26.27.54:3306/test?characterEncoding=utf8&useSSL=false&serverTimezone=GMT";
        Connection connection = DriverManager.getConnection(url, "root", "123456");
        PreparedStatement preparedStatement = connection.prepareStatement("update student set name =? where id= ?");
        preparedStatement.setString(1,name);
        preparedStatement.setInt(2,id);
        int count = preparedStatement.executeUpdate();
        String message=count>0?"更新数据库数据成功":"更新数据库数据失败";
        System.out.println(message);
        connection.close();
    }

    //生成随机数字和字母,
    public static String getStringRandom(int length) {

        String val = "";
        Random random = new Random();

        //参数length，表示生成几位随机数
        for(int i = 0; i < length; i++) {

            String charOrNum = random.nextInt(2) % 2 == 0 ? "char" : "num";
            //输出字母还是数字
            if( "char".equalsIgnoreCase(charOrNum) ) {
                //输出是大写字母还是小写字母
                int temp = random.nextInt(2) % 2 == 0 ? 65 : 97;
                val += (char)(random.nextInt(26) + temp);
            } else if( "num".equalsIgnoreCase(charOrNum) ) {
                val += String.valueOf(random.nextInt(10));
            }
        }
        return val;
    }
}
