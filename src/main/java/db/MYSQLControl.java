package db;
import java.sql.SQLException;
import java.util.List;
import javax.sql.DataSource;
import org.apache.commons.dbutils.QueryRunner;

import model.MoneyModel;
import model.ShareModel;
/*
 * Mysql������QueryRunner����
 * һ�����ݿ�����࣬��ĳ���ֱ�ӵ��ü���
 */
public class MYSQLControl {

    //�����Լ������ݿ��ַ�޸�
    static DataSource ds = MyDataSource.getDataSource("jdbc:mysql://127.0.0.1:3306/moviedata?serverTimezone=UTC");
    static QueryRunner qr = new QueryRunner(ds);
    //��һ�෽��
    public static void executeUpdate(String sql){
        try {
            qr.update(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    //�ڶ������ݿ��������
    public static void executeInsert(List<ShareModel> sharesdata) throws SQLException {
        /*
         * ����һ��Object���飬����
         * 3��ʾ�����������Լ������ݶ��������������
         * params[i][0]���Ƕ����鸳ֵ�������õ����ϵ�get����
         * 
         */
        Object[][] params = new Object[sharesdata.size()][2];
        for ( int i=0; i<params.length; i++ ){
            params[i][0] = sharesdata.get(i).getShareID();
            params[i][1] = sharesdata.get(i).getShareName();

        }
        qr.batch("insert into shares (id, name)"
                + "values (?,?)", params);
        System.out.println("ִ�����ݿ���ϣ�"+"�ɹ��������ݣ�"+sharesdata.size()+"��");

    }
    
    public static void executeDelete() throws SQLException {
        /*
         * ɾ��shares���е�����
         */
       String str="delete from shares";
       executeUpdate(str);
        System.out.println("ִ�����ݿ���ϣ�shares���е�������ɾ����");
    }
    //Money �������ݿⷽ��
    public static void executeInsert1(List<MoneyModel> moneysdata) throws SQLException {
        /*
         * ����һ��Object���飬����
         * 3��ʾ�����������Լ������ݶ��������������
         * params[i][0]���Ƕ����鸳ֵ�������õ����ϵ�get����
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
        System.out.println("ִ�����ݿ���ϣ�"+"�ɹ��������ݣ�"+moneysdata.size()+"��");
    }
    public static void executeDelete1() throws SQLException {
        /*
         * ɾ��moneys���е�����
         */
       String str="delete from moneys";
       executeUpdate(str);
        System.out.println("ִ�����ݿ���ϣ�moneys���е�������ɾ����");
    }
    
}