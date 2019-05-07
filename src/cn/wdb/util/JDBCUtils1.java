package cn.wdb.util;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class JDBCUtils1 {
    private static DataSource ds ;
    static {
        Properties prop = new Properties();
        try {
            prop.load(JDBCUtils1.class.getClassLoader().getResourceAsStream("druid1.properties"));
            ds = DruidDataSourceFactory.createDataSource(prop);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static Connection getconn() throws SQLException {
            return ds.getConnection();
    }
    public static DataSource getDs(){
        return ds;
    }
}
