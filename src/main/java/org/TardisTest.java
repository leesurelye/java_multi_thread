package org;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Properties;
import java.util.Scanner;

public class TardisTest
{
    // Presto Client
    public static void main(String[] args) throws ClassNotFoundException, SQLException
    {
        String username = "presto";
        String server = "localhost";
//        String server = "10.5.111.1";
        String port = "8080";
        Connection connection = null;
        Statement stmt = null;
        ResultSet rs = null;
        Scanner input = new Scanner(System.in);
        while(true){
            try {
                Class.forName("com.facebook.presto.jdbc.PrestoDriver");
                String jdbcurl = String.format("jdbc:presto://%s:%s/?customHeaders=X-Cluster-Id:%s", server, port, "default");
                Properties props = new Properties();
                props.setProperty("user", username);
                connection = DriverManager.getConnection(jdbcurl, props);
                stmt = connection.createStatement();
                System.out.println("Persto Client: >> ");
                String sql = input.nextLine();
                System.out.println("<< Executing...");
                rs = stmt.executeQuery(sql);
                printResultSet(rs);
            } catch (ClassNotFoundException | RuntimeException e){
                e.printStackTrace();
            } finally {
                if (rs != null) {
                    rs.close();
                }
                if (stmt != null) {
                    stmt.close();
                }
                if (connection != null) {
                    connection.close();
                }
            }
        }

    }

    public static void printResultSet(ResultSet rs) throws SQLException
    {
        ResultSetMetaData resultSetMetaData = rs.getMetaData();
        // 获取列数
        int ColumnCount = resultSetMetaData.getColumnCount();
        // 保存当前列最大长度的数组
        int[] columnMaxLengths = new int[ColumnCount];
        // 缓存结果集,结果集可能有序,所以用ArrayList保存变得打乱顺序.
        ArrayList<String[]> results = new ArrayList<>();
        // 按行遍历
        while (rs.next()) {
            // 保存当前行所有列
            String[] columnStr = new String[ColumnCount];
            // 获取属性值.
            for (int i = 0; i < ColumnCount; i++) {
                // 获取一列
                columnStr[i] = rs.getString(i + 1);
                // 计算当前列的最大长度
                columnMaxLengths[i] = Math.max(columnMaxLengths[i], (columnStr[i] == null) ? 0 : columnStr[i].length());
            }
            // 缓存这一行.
            results.add(columnStr);
        }
        printSeparator(columnMaxLengths);
        printColumnName(resultSetMetaData, columnMaxLengths);
        printSeparator(columnMaxLengths);
        // 遍历集合输出结果
        Iterator<String[]> iterator = results.iterator();
        String[] columnStr;
        while (iterator.hasNext()) {
            columnStr = iterator.next();
            for (int i = 0; i < ColumnCount; i++) {
                // System.out.printf("|%" + (columnMaxLengths[i] + 1) + "s", columnStr[i]);
                System.out.printf("|%s", columnStr[i]);
            }
            System.out.println("|");
        }
        printSeparator(columnMaxLengths);
    }

    private static void printColumnName(ResultSetMetaData resultSetMetaData, int[] columnMaxLengths) throws SQLException {
        int columnCount = resultSetMetaData.getColumnCount();
        for (int i = 0; i < columnCount; i++) {
            // System.out.printf("|%" + (columnMaxLengths[i] + 1) + "s", resultSetMetaData.getColumnName(i + 1));
            System.out.printf("|%s", resultSetMetaData.getColumnName(i + 1));
        }
        System.out.println("|");
    }

    private static void printSeparator(int[] columnMaxLengths) {
        for (int i = 0, n = columnMaxLengths.length; i < n ; i++) {
            System.out.print("+");
            // for (int j = 0; j < columnMaxLengths[i] + 1; j++) {
            for (int j = 0; j < columnMaxLengths[i]; j++) {
                System.out.print("-");
            }
        }
        System.out.println("+");
    }
}
