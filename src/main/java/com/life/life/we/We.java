package com.life.life.we;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.life.life.welcome.R;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class We extends Activity implements View.OnClickListener {
    private TextView we_date;
    private Button expenditure, income;
    private TextView date;
    private EditText money, remarks;
    private Button submit;
    private File file = null;
    private File file2, file3, file4 = null;
    private SimpleDateFormat df2;
    private SimpleDateFormat df3;
    private PrintStream out, out2 = null;
    private boolean flag = true;
    private Scanner scan, scan2, scan3 = null;
    private float jisuan = 0.00f;
    private float zhichuzonge = 0.00f;
    private float shouruzonge = 0.00f;
    private TextView gross_expenditure;
    private SimpleDateFormat df;
    private SimpleDateFormat df4;
    DecimalFormat dff1   =new DecimalFormat("#.00");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_we);
        we_date = (TextView) super.findViewById(R.id.we_date);
        expenditure = (Button) super.findViewById(R.id.expenditure);
        income = (Button) super.findViewById(R.id.income);
        date = (TextView) super.findViewById(R.id.date);
        money = (EditText) super.findViewById(R.id.money);
        remarks = (EditText) super.findViewById(R.id.remarks);
        submit = (Button) super.findViewById(R.id.submit);
        gross_expenditure = (TextView) super.findViewById(R.id.gross_expenditure);
        Date_Calculation();
        expenditure.setSelected(false);
        income.setSelected(true);
        expenditure.setOnClickListener(this);
        income.setOnClickListener(this);
        expenditure.setClickable(false);
        income.setClickable(true);
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        date.setText(df.format(new Date()));
        submit.setOnClickListener(this);
        df2 = new SimpleDateFormat("yyyyMM");
        df3 = new SimpleDateFormat("yyyyMMdd");
        df4 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        file3 = new File(Environment.getExternalStorageDirectory().toString() + File.separator + "Turnover" + File.separator + "expenditure" + File.separator + df2.format(new Date()) + File.separator + "expenditure.txt");
        file4 = new File(Environment.getExternalStorageDirectory().toString() + File.separator + "Turnover" + File.separator + "income" + File.separator + df2.format(new Date()) + File.separator + "income.txt");
        ei();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.expenditure:
                expenditure.setSelected(false);
                income.setSelected(true);
                expenditure.setClickable(false);
                income.setClickable(true);
                flag = true;
                break;
            case R.id.income:
                expenditure.setSelected(true);
                income.setSelected(false);
                expenditure.setClickable(true);
                income.setClickable(false);
                flag = false;
                break;
            case R.id.submit:
                if (money.getText().toString().trim().equals("") || remarks.getText().toString().trim().equals("")) {
                    Toast.makeText(We.this, "有东西没填！", Toast.LENGTH_SHORT).show();
                } else {
                    submitClick();
                }
                break;
        }
    }

    public void Date_Calculation() {
        df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        try {
            Date start = df.parse("2013-05-01 21:00");
            Date end = df.parse(df.format(new Date()));
            long t = (end.getTime() - start.getTime()) / (3600 * 24 * 1000);
            we_date.setText(String.valueOf(t));
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public void ei() {
        if (file3.exists()) {
            try {
                scan2 = new Scanner(new FileInputStream(file3));
                if (scan2.hasNext()) {
                    zhichuzonge = Float.parseFloat(scan2.next().toString().trim());
                }
                scan2.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            zhichuzonge = 0.00f;
        }
        if (file4.exists()) {
            try {
                scan3 = new Scanner(new FileInputStream(file4));
                if (scan3.hasNext()) {
                    shouruzonge = Float.parseFloat(scan3.next().toString().trim());
                }
                scan3.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            shouruzonge = 0.00f;
        }
        gross_expenditure.setText("支出总额：" + String.valueOf(zhichuzonge) + "元" + "\n" + "收入总额：" + String.valueOf(shouruzonge) + "元" + "\n" + "余额：" + String.valueOf(shouruzonge - zhichuzonge) + "元");

    }

    public void submitClick() {
        if (flag) {
            file = new File(Environment.getExternalStorageDirectory().toString() + File.separator + "Turnover" + File.separator + "expenditure" + File.separator + df2.format(new Date()) + File.separator + df3.format(new Date()) + ".txt");
            file2 = new File(Environment.getExternalStorageDirectory().toString() + File.separator + "Turnover" + File.separator + "expenditure" + File.separator + df2.format(new Date()) + File.separator + "expenditure.txt");
        } else {
            file = new File(Environment.getExternalStorageDirectory().toString() + File.separator + "Turnover" + File.separator + "income" + File.separator + df2.format(new Date()) + File.separator + df3.format(new Date()) + ".txt");
            file2 = new File(Environment.getExternalStorageDirectory().toString() + File.separator + "Turnover" + File.separator + "income" + File.separator + df2.format(new Date()) + File.separator + "income.txt");
        }
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (!file2.exists()) {
            try {
                file2.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            out = new PrintStream(new FileOutputStream(file, true));
            try {
                scan = new Scanner(new FileInputStream(file2));
                if (scan.hasNext()) {
                    jisuan = Float.parseFloat(scan.next().toString().trim());
                    jisuan = Float.parseFloat(money.getText().toString().trim()) + jisuan;
                    out2 = new PrintStream(new FileOutputStream(file2));
                    out2.println(dff1.format(jisuan));
                    scan.close();
                    out2.close();
                } else {
                    out2 = new PrintStream(new FileOutputStream(file2));
                    Log.e("b",dff1.format(Float.parseFloat(money.getText().toString().trim())));
                    out2.println(dff1.format(Float.parseFloat(money.getText().toString().trim())));
                    out2.close();
                }
                Toast.makeText(We.this, "保存成功！", Toast.LENGTH_SHORT).show();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            out.println(df4.format(new Date()) + "\r\n" + remarks.getText().toString().trim() + "\r\n" + dff1.format(Float.parseFloat(money.getText().toString().trim())) + "元");
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        ei();
    }

    @Override
    public boolean onKeyDown(int key, KeyEvent event) {
        if (key == event.KEYCODE_BACK) {
            We.this.finish();
        }
        return false;
    }
}
