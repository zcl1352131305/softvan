package cn.com.softvan.utils.jdbc;

//import java.beans.PropertyVetoException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

//import com.mchange.v2.c3p0.ComboPooledDataSource;

/**
 * 数据连接池
 * 
 * @author Dell
 * @date 2011-06-24
 */
public final class JdbcUtils {

    private static JdbcUtils instance;
    private static	String jdbc_user = null;
    private static  String jdbc_password = null;
    private static  String jdbc_driver = null;
    private static  String jdbc_url = null;
   static{
 	   try {
           InputStream inputStream = JdbcUtils.class.getClassLoader().getResourceAsStream("generatorConfig.properties");
		   Properties properties = new Properties();
		   properties.load(inputStream);
		   jdbc_user = properties.getProperty("userId");
		   jdbc_password = properties.getProperty("password");
		   jdbc_driver = properties.getProperty("driverClass");
		   jdbc_url = properties.getProperty("connectionURL");
		} catch (IOException e) {
			e.printStackTrace();
		}
   }
    public static final JdbcUtils getInstance() {
        if (instance == null) {
            try {
                instance = new JdbcUtils();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return instance;
    }

    public synchronized final Connection getConnection() throws Exception{
        Connection conn = null;
        
// 	   String jdbc_user = null;
// 	   String jdbc_password = null;
// 	   String jdbc_driver = null;
// 	   String jdbc_url = null;
// 	   
////        try {
//        	   InputStream inputStream = ConnectionManager.class.getClassLoader().getResourceAsStream("jdbc.properties");
//        	   Properties properties = new Properties();
//        	   properties.load(inputStream);
//        	   jdbc_user = properties.getProperty("mysql.username");
//        	   jdbc_password = properties.getProperty("mysql.password");
//        	   jdbc_driver = properties.getProperty("mysql.driverClassName");
//        	   jdbc_url = properties.getProperty("mysql.url");

//        	  } catch (MissingResourceException mre) {
//        		  mre.printStackTrace();
//        	  } catch (FileNotFoundException fnf) {
//        		  fnf.printStackTrace();
//        	  } catch (IOException ioe) {
//        		  ioe.printStackTrace();
//        	  }
        
//        try {
        	
            //实例化MySql驱动
            Class.forName(jdbc_driver);
            //连接数据库并返回数据库的连接句柄
            conn = DriverManager.getConnection(jdbc_url, jdbc_user, jdbc_password);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        } catch (ClassNotFoundException e) {
//			e.printStackTrace();
//		}
        return conn;
    }
}
