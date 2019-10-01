package com.example.nhapxuatfile_2activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ArrayList<String> listtenfile = new ArrayList<>();
    SharedPreferences pref;
    SharedPreferences.Editor editor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final EditText ettenfile =(EditText) findViewById(R.id.ettenfile);
        final EditText etnoidungfile =(EditText) findViewById(R.id.etnoidungfile);
        Button btnnhapmoi = (Button)findViewById(R.id.btnnhapmoi);


        try{
            Intent intent = getIntent();
            ettenfile.setText(intent.getStringExtra("tenfile"));
            String tenfile = intent.getStringExtra("tenfile");
                    /*StringBuffer buffer = new StringBuffer();
                    String line = null;
                    try{
                       FileInputStream fin = openFileInput(tenfile);
                    BufferedReader br = new BufferedReader(new InputStreamReader(fin));
                    while((line = br.readLine())!=null){
                        buffer.append(line).append("\n");
                        etnhapthongtin.setText(buffer.toString());
                    }
                }catch (Exception e){

                }*/
            SharedPreferences pref = getApplicationContext().getSharedPreferences(tenfile,Context.MODE_PRIVATE);
            etnoidungfile.setText(pref.getString("content",null));
        }catch (Exception e){

        }


        btnnhapmoi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ettenfile.setText("");
                etnoidungfile.setText("");
            }
        });



        Button btnluu = (Button)findViewById(R.id.btnluufile);
        btnluu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String tenfile = ettenfile.getText().toString();
                listtenfile.add(tenfile);
                /*try{
                    FileOutputStream fout = openFileOutput(tenfile, Context.MODE_PRIVATE);
                    fout.write(etnhapthongtin.getText().toString().getBytes());
                    fout.close();
                }catch (Exception e){
                    Toast.makeText(MainActivity.this,"Lỗi lưu file",Toast.LENGTH_LONG).show();
                }*/
                pref = getApplicationContext().getSharedPreferences(tenfile, Context.MODE_PRIVATE);
                editor = pref.edit();
                editor.putString("content",etnoidungfile.getText().toString());
                editor.commit();
            }
        });


        Button btnmo = (Button)findViewById(R.id.btnchonfile);
        btnmo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,Main2Activity.class);
                intent.putStringArrayListExtra("listtenfile",listtenfile);
                startActivity(intent);
            }
        });
    }
}
