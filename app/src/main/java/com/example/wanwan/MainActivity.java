package com.example.wanwan;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

public class MainActivity extends AppCompatActivity {

    @InterView(id = R.id.ddd)
    private Button ddd;
    
    @InterView(id=R.id.ceshi)
    private Button ceshi;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        find();
        
        
        ddd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "哒哒哒", Toast.LENGTH_SHORT).show();
            }
        });
        
        ceshi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "哈哈哈哈h", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void find() {

        Class<? extends MainActivity> aClass = getClass();

        Field[] fields = aClass.getDeclaredFields();


        for (Field ff:fields) {
            InterView annotation = ff.getAnnotation(InterView.class);
            if(annotation!=null){
                int id = annotation.id();

                View viewById = this.findViewById(id);

                ff.setAccessible(true);

                try {
                    ff.set(this,viewById);


                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}