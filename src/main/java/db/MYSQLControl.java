package db;
import java.sql.SQLException;
import java.util.List;
import javax.sql.DataSource;
import org.apache.commons.dbutils.QueryRunner;

import model.MoneyModel;
import model.ShareModel;
/*
 * Mysql操作的QueryRunner方法
 * 一个数据库操作类，别的程序直接调用即可
 */
public class MYSQLControl {

    //根据自己的数据库地址修改
    static DataSource ds = MyDataSource.getDataSource("jdbc:mysql://127.0.0.1:3306/moviedata?serverTimezone=UTC");
    static QueryRunner qr = new QueryRunner(ds);
    //第一类方法
    public static void executeUpdate(String sql){
        try {
            qr.update(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    //第二类数据库操作方法
    public static void executeInsert(List<ShareModel> sharesdata) throws SQLException {
        /*
         * 定义一个Object数组，行列
         * 3表示列数，根据自己的数据定义这里面的数字
         * params[i][0]等是对数组赋值，这里用到集合的get方法
         * 
         */
        Object[][] params = new Object[sharesdata.size()][2];
        for ( int i=0; i<params.length; i++ ){
            params[i][0] = sharesdata.get(i).getShareID();
            params[i][1] = sharesdata.get(i).getShareName();

        }
        qr.batch("insert into shares (id, name)"
                + "values (?,?)", params);
        System.out.println("执行数据库完毕！"+"成功插入数据："+sharesdata.size()+"条");

    }
    
    public static void executeDelete() throws SQLException {
        /*
         * 删除shares表中的数据
         */
       String str="delete from shares";
       executeUpdate(str);
        System.out.println("执行数据库完毕！shares表中的数据已删除！");
    }
    //Money 插入数据库方法
    public static void executeInsert1(List<MoneyModel> moneysdata) throws SQLException {
        /*
         * 定义一个Object数组，行列
         * 3表示列数，根据自己的数据定义这里面的数字
         * params[i][0]等是对数组赋值，这里用到集合的get方法
         * 
         */
        Object[][] params = new Object[moneysdata.size()][5];
        for ( int i=0; i<params.length; i++ ){
            params[i][0] = moneysdata.get(i).getId();
            params[i][1] = moneysdata.get(i).getYear();
            params[i][2] = moneysdata.get(i).getQian();
            params[i][3] = moneysdata.get(i).getSonggu();
            params[i][4] = moneysdata.get(i).getZhuangu();
           // if(params[i][1].equals(" ")) break;
        }

        qr.batch("insert into moneys (id, year,qian,songgu,zhuangu)"
                + "values (?,?,?,?,?)", params);
        System.out.println("执行数据库完毕！"+"成功插入数据："+moneysdata.size()+"条");
    }
    public static void executeDelete1() throws SQLException {
        /*
         * 删除moneys表中的数据
         */
       String str="delete from moneys";
       executeUpdate(str);
        System.out.println("执行数据库完毕！moneys表中的数据已删除！");
    }
    
}