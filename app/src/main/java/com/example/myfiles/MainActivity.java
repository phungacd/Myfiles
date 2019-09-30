package com.example.myfiles;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final EditText filename = findViewById(R.id.txttenfile);
        final EditText noidung = findViewById(R.id.txtnoidung);

        Button moi = findViewById(R.id.btnedit);
        moi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                filename.setText("");
                noidung.setText("");
            }
        });

        final ArrayList<String> strings = new ArrayList<>();
        strings.add("Hello");
        final Spinner spinner = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_single_choice, strings);

        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                filename.setText(strings.get(i).toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        Button luu = findViewById(R.id.btnluu);
//        luu.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                String name = filename.getText().toString();
//                strings.add(name);
//
//                try {
//                    FileOutputStream f = openFileOutput(name, Context.MODE_PRIVATE);
//                    f.write(noidung.getText().toString().getBytes());
//                    f.close();
//
//
//                } catch (Exception e) {
//                    Toast.makeText(MainActivity.this, "Lỗi lưu file", Toast.LENGTH_LONG);
//                }
//            }
//        });
        luu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    File file = getCacheDir();
                    String cachefile = filename.getText().toString();
                    String filenoidung = filename.getText()+"";
                    strings.add(filenoidung);
                    File newcacheFile = new File(file,cachefile);
                    newcacheFile.createNewFile();
                    FileOutputStream focache = new FileOutputStream(newcacheFile.getAbsolutePath());
                    focache.write(filenoidung.getBytes());
                    focache.close();
                }catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        });
        Button mo = findViewById(R.id.btnmo);
//        mo.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                String name = filename.getText().toString();
//                StringBuffer buffer = new StringBuffer();
//                String l = null;
//                try {
//                    FileInputStream in = openFileInput(name);
//                    BufferedReader r = new BufferedReader(new InputStreamReader(in));
//                    while ((l = r.readLine())!=null)
//                    {
//                        buffer.append(l).append("\n");
//                        noidung.setText(buffer.toString());
//                    }
//
//
//                }catch (Exception e){
//                    Toast.makeText(MainActivity.this,"File lỗi",Toast.LENGTH_LONG);
//                }
//            }
//        });
        mo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    File pathCacherDir = getCacheDir();
                    String strCacheFileName = filename.getText().toString();
                    File newCacheFile = new File(pathCacherDir,strCacheFileName);
                    Scanner sc = new Scanner(newCacheFile);
                    String data = "";
                    while (sc.hasNext()){
                        data+=sc.nextLine()+"\n";
                    }
                    noidung.setText(data);
                    sc.close();
                }catch (FileNotFoundException e){
                    e.printStackTrace();
                }
            }
        });

    }
}
