package com.example.smartwachingmachine;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.chaquo.python.PyObject;
import com.chaquo.python.android.AndroidPlatform;
import com.chaquo.python.Python;

public class MainActivity extends AppCompatActivity {

    EditText et1,et2;
    Button btn_Confirm;
    Button btn_Cancel;
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setViews();
        initPython();

    }

    public void setViews()
    {
        et1 = (EditText)findViewById(R.id.editText1);
        et2 = (EditText)findViewById(R.id.editText2);
        btn_Confirm = (Button)findViewById(R.id.btn_confirm);
        btn_Cancel = (Button)findViewById((R.id.btn_cancel));

        tv = (TextView)findViewById(R.id.tv);
        btn_Confirm.setOnClickListener(new MyOnClickListener());
        btn_Cancel.setOnClickListener(new MyOnClickListener());
    }
    void callPythonCode()
    {
        Python py = Python.getInstance();
        PyObject name = py.getModule("test").callAttr("open");
        String s = name.toString();
        tv.setText(s);
    }

    void closeLed()
    {
        Python py = Python.getInstance();
        PyObject name = py.getModule("test").callAttr("close");
        String s = name.toString();
        tv.setText(s);
    }
    class MyOnClickListener implements View.OnClickListener{
        //按钮点击
        @Override
        public void onClick(View view) {

            if(view.getId()==R.id.btn_confirm){//被点击的是确认按钮
                callPythonCode();
            }
            else if(view.getId()==R.id.btn_cancel){
                closeLed();
            }
        }
    }

    void initPython(){
        if (! Python.isStarted()) {
            Python.start(new AndroidPlatform(this));
        }
    }
}