package com.example.litepaltest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import org.litepal.LitePal;
import org.litepal.crud.DataSupport;
import org.litepal.crud.LitePalSupport;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private  String TAG = "ShowDatabase";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button createDatabase = findViewById(R.id.create_database);//创造数据库
        createDatabase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LitePal.getDatabase();
            }
        });
        Button addData = findViewById( R.id.add_database);//添加数据
        addData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Book book = new Book();
                book.setName("张三的一生");
                book.setAuthor("张三");
                book.setPages(454);
                book.setPrice(16.94);
                book.setPress("Unknow");
                if (book.save()){
                    Toast.makeText(MainActivity.this,"数据保存成功"+book.getName(),Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(MainActivity.this,"数据保存失败",Toast.LENGTH_SHORT).show();
                }

            }
        });
        Button updateData = findViewById( R.id.update_data);//更新数据
        updateData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                  Book book = new Book();
//                book.setName("李四的一生");
//                book.setAuthor("李四");
//                book.setPages(654);
//                book.setPrice(20.32);
//                book.setPress("李四出版社");
//                book.save();
//                book.setPages(666);
//                book.save();
                book.setPages(555);
                book.setPress("张三出版社");
                book.updateAll("name = ?","张三的一生");

            }
        });

        Button deleteData = findViewById(R.id.delete_data);
        deleteData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LitePal.deleteAll(Book.class,"name = ?", "张三的一生");
            }
        });

        Button queryButton= findViewById(R.id.query_data);//查询，显示所有数据
        queryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShowDatabase();
            }
        });


    }
    private void ShowDatabase() {//打印数据库中的数据
        List<Book> books = LitePal.findAll(Book.class);
        for (Book book : books) {
            Log.d(TAG, "ShowDatabase: " + book.getName());
            Log.d(TAG, "ShowDatabase: " + book.getAuthor());
            Log.d(TAG, "ShowDatabase: " + book.getPages());
            Log.d(TAG, "ShowDatabase: " + book.getPrice());
            Log.d(TAG, "ShowDatabase: " + book.getPress());
        }
    }
}
