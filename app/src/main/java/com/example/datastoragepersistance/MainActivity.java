package com.example.datastoragepersistance;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {
EditText editText;
Button read;
Button write;
TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText=findViewById(R.id.editTextTextPersonName);
        read= findViewById(R.id.read);
        write= findViewById(R.id.write);
        textView=findViewById(R.id.textView);
write.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        String text=editText.getText().toString();
        File datadir= ContextCompat.getDataDir(MainActivity.this);
        File myFile=new File(datadir,"file.text");

        try {
            //if i don't pass append this will read only one recent line only
            FileOutputStream fos= new FileOutputStream(myFile,true);
            //write function takes array of bytes so we convert text into Bytes
            fos.write(text.getBytes());
        }
        catch(FileNotFoundException fnfe){
            Toast.makeText(MainActivity.this,"File not Found",Toast.LENGTH_SHORT).show();
        }
        catch(IOException ioe){
            Toast.makeText(MainActivity.this,"Error while Writing File",Toast.LENGTH_SHORT).show();
        }
    }
});
read.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        File datadir= ContextCompat.getDataDir(MainActivity.this);
        File myFile=new File(datadir,"file.text");
        try{
            FileInputStream fis= new FileInputStream(myFile);
            InputStreamReader isr=new InputStreamReader(fis);
            //buffer reader take input stream reader
            BufferedReader br= new BufferedReader(isr);
            StringBuilder sb= new StringBuilder();
            //will read line by line from input streamer and save it into buffer
            String buffer= br.readLine();
            while (buffer!=null){
                sb.append(buffer);
                //buffer will stop after reading one line thats why read it again
                buffer=br.readLine();
            }

            String text= sb.toString();
            textView.setText(text);
        }
        catch(FileNotFoundException fnfe){
            Toast.makeText(MainActivity.this,"File not Found",Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            Toast.makeText(MainActivity.this,"Error while Writing File",Toast.LENGTH_SHORT).show();
        }
    }
});
}
}