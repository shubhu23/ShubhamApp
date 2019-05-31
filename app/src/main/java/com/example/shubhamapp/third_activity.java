package com.example.shubhamapp;

import android.app.AlertDialog;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class third_activity extends AppCompatActivity implements View.OnClickListener {
    EditText NeditText1, MeditText2, ReditText3;
    Button abutton, dbutton, mbutton, vbutton, vabutton, abbutton;
    SQLiteDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third_activity);

        NeditText1 = findViewById(R.id.Name_Text); //name
        MeditText2 = findViewById(R.id.Sal_Text); //marks
        ReditText3 = findViewById(R.id.Id_Text); //rollno
        abutton = findViewById(R.id.add_btn);
        dbutton = findViewById(R.id.delete_btn);
        mbutton = findViewById(R.id.modify_btn);
        vbutton = findViewById(R.id.view_btn);
        vabutton = findViewById(R.id.viewall_btn);
        abbutton = findViewById(R.id.about_btn);

        operation();
        db=openOrCreateDatabase("StudentDB", Context.MODE_PRIVATE,null);
        db.execSQL("CREATE TABLE IF NOT EXISTS student(rollno VARCHAR,name VARCHAR,marks VARCHAR);");
    }

    private void operation() {
        abutton.setOnClickListener(this);
        dbutton.setOnClickListener(this);
        mbutton.setOnClickListener(this);
        vbutton.setOnClickListener(this);
        vabutton.setOnClickListener(this);
        abbutton.setOnClickListener(this);
    }



    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.add_btn:
                Toast.makeText(this, "Add", Toast.LENGTH_SHORT).show();
                if(ReditText3.getText().toString().trim().length()==0||
                        NeditText1.getText().toString().trim().length()==0||
                        MeditText2.getText().toString().trim().length()==0)
                {
                    Toast.makeText(this,"Error",Toast.LENGTH_SHORT).show();
                    showMessage("Error", "Please enter all values");
                    return;
                }
                db.execSQL("INSERT INTO student VALUES('"+ReditText3.getText()+"','"+NeditText1.getText()+
                        "','"+MeditText2.getText()+"');");
                showMessage("Success", "Record added");
                clearText();
                break;
            case R.id.delete_btn:
                Toast.makeText(this, "Delete", Toast.LENGTH_SHORT).show();
                if(ReditText3.getText().toString().trim().length()==0)
                {
                    showMessage("Error", "Please enter Rollno");
                    return;
                }
                Cursor c=db.rawQuery("SELECT * FROM student WHERE rollno='"+ReditText3.getText()+"'", null);
                if(c.moveToFirst())
                {
                    db.execSQL("DELETE FROM student WHERE rollno='"+ReditText3.getText()+"'");
                    showMessage("Success", "Record Deleted");
                }
                else
                {
                    showMessage("Error", "Invalid Rollno");
                }
                clearText();
                break;
            case R.id.modify_btn:
                Toast.makeText(this, "Modify", Toast.LENGTH_SHORT).show();
                if(ReditText3.getText().toString().trim().length()==0)
                {
                    showMessage("Error", "Please enter Rollno");
                    return;
                }
                Cursor c2=db.rawQuery("SELECT * FROM student WHERE rollno='"+ReditText3.getText()+"'", null);
                if(c2.moveToFirst())
                {
                    db.execSQL("UPDATE student SET name='"+NeditText1.getText()+"',marks='"+MeditText2.getText()+
                            "' WHERE rollno='"+ReditText3.getText()+"'");
                    showMessage("Success", "Record Modified");
                }
                else
                {
                    showMessage("Error", "Invalid Rollno");
                }
                clearText();
                break;
            case R.id.view_btn:
                Toast.makeText(this, "View", Toast.LENGTH_SHORT).show();
                if(ReditText3.getText().toString().trim().length()==0)
                {
                    showMessage("Error", "Please enter Rollno");
                    return;
                }
                Cursor c3=db.rawQuery("SELECT * FROM student WHERE rollno='"+ReditText3.getText()+"'", null);
                if(c3.moveToFirst())
                {
                    NeditText1.setText(c3.getString(1));
                    MeditText2.setText(c3.getString(2));
                }
                else {
                    showMessage("Error", "Invalid Rollno");
                    clearText();
                }
                break;
            case R.id.viewall_btn:
                Toast.makeText(this, "View All", Toast.LENGTH_SHORT).show();
                Cursor c4=db.rawQuery("SELECT * FROM student", null);
                if(c4.getCount()==0)
                {
                    showMessage("Error", "No records found");
                    return;
                }
                StringBuffer buffer=new StringBuffer();
                while(c4.moveToNext())
                {
                    buffer.append("Rollno: "+c4.getString(0)+"\n");
                    buffer.append("Name: "+c4.getString(1)+"\n");
                    buffer.append("Marks: "+c4.getString(2)+"\n\n");
                }
                showMessage("Student Details", buffer.toString());
                break;
            case R.id.about_btn:
                Toast.makeText(this, "About", Toast.LENGTH_SHORT).show();
                showMessage("Student Record Application","Developed by Shubham");
                break;
        }
    }

    private void clearText() {
        NeditText1.setText("");
        MeditText2.setText("");
        ReditText3.setText("");
    }

    public void showMessage(String title,String message) {
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setIcon(R.mipmap.ic_launcher_round);
        builder.setMessage(message);
        builder.show();
    }
}
