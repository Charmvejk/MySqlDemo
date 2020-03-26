package com.example.mysqldemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.TextView;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new Thread(runnable).start();

    }

    Runnable runnable = new Runnable() {

        private Connection con = null;

        @Override

        public void run() {

            // TODO Auto-generated method stub

            try {

                Class.forName("com.mysql.jdbc.Driver");

                //引用代码此处需要修改，address为数据IP，Port为端口号，DBName为数据名称，UserName为数据库登录账户，Password为数据库登录密码

                con =

                        //DriverManager.getConnection("jdbc:mysql://192.168.1.202:3306/b2b", "root", "");

                        DriverManager.getConnection("jdbc:mysql://192.168.3.101:3306/mysql",

                                "root", "123456");

            } catch (SQLException e) {

                // TODO Auto-generated catch block

                System.out.println(e.getMessage());

            } catch (ClassNotFoundException e) {

                // TODO Auto-generated catch block

                System.out.println(e.getMessage());

            }
            try {

                insert("weishuyong", 12, con);


            } catch (Exception e) {

            }


        }


    };


    private static int insert(String name, int age, Connection con) {

        int i = 0;
        String sql = "insert into ceshi (name,age) values(?,?)";
        PreparedStatement pstmt;
        try {
            pstmt = (PreparedStatement) con.prepareStatement(sql);
            pstmt.setString(1, name);
            pstmt.setInt(2, age);
            i = pstmt.executeUpdate();
            pstmt.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return i;
    }
}


