package assignment.android.acadgild.calorietracker;

import android.app.Dialog;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

import assignment.android.acadgild.R;
import assignment.android.acadgild.calorietracker.database.DBAdapter;
import assignment.android.acadgild.calorietracker.listView.CalorieAdapter;
import assignment.android.acadgild.calorietracker.object.CalorieObject;

/**
 * Created by DivyaVipin on 1/19/2017.
 */

public class CalorieTracker extends AppCompatActivity{

    FloatingActionButton btnAdd;
    Dialog d;
    EditText calValueDialog;
    Spinner spactivity;
    String c_Value=null;
    int cValue=0;
    String activityname="";
    Button btnSave;
    DBAdapter db;
    String cal_val_dialog;
    DatePicker datePicker;
    CalorieAdapter adapter;
    String new_Calorie;
    int new_Calorie_value;
    ArrayList<CalorieObject> caloriedetails=new ArrayList<>();
    ListView lv;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calorie_tracker);
//Maximum calories burned updating with highest value
        final TextView maxCalorieBurned = (TextView) findViewById(R.id.txtViewMCalValue);
        maxCalorieBurned.setText(String.valueOf("0"));
       //Each time calorie adding

        // High Score



        btnAdd=(FloatingActionButton)findViewById(R.id.addDetails);
        lv=(ListView)findViewById(R.id.listViewCalorie) ;

        db=new DBAdapter(CalorieTracker.this);
        db.openDB();

        adapter=new CalorieAdapter(this,caloriedetails);

        getCalorieDetails();
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                d=new Dialog(CalorieTracker.this);
                d.setTitle("Add Calorie Details");
                d.setContentView(R.layout.cal_track_dialog);

    calValueDialog = (EditText) d.findViewById(R.id.editTextCalorie);

                //Value getting from dialog box this value needs to be replaced if it is
                datePicker=(DatePicker)d.findViewById(R.id.datePickerDate) ;

                spactivity=(Spinner)d.findViewById(R.id.spnActivity);

                String[] activities = new String[] { "Swimming","Walking","Dancing","Hockey" };
                ArrayAdapter<String> adapter=new ArrayAdapter<String>(CalorieTracker.this,android.R.layout.simple_spinner_item,activities);
                spactivity.setAdapter(adapter);

                btnSave=(Button)d.findViewById(R.id.btnCSave);
                d.show();

                btnSave.setOnClickListener(new View.OnClickListener() {
                    int date=datePicker.getDayOfMonth();
                    int month=datePicker.getMonth()+1;
                    int year=datePicker.getYear();
                    String datemonthyear="";

                        String dmy = (date + "/" + month + "/" + year).toString();

                    @Override
                    public void onClick(View view) {
                        try {
                            new_Calorie_value = Integer.parseInt(new_Calorie);
                        }
                        catch(NumberFormatException e)
                        {
                            new_Calorie_value=0;
                        }
                        maxCalorieBurned.setText(String.valueOf(new_Calorie_value));//Highest calorie to be updated on top
                        new_Calorie=calValueDialog.getText().toString();

                        calValueDialog.setText(SharedPrefManager.getIntPrefVal(CalorieTracker.this, Constants.CALORIE)+"");
                        SharedPrefManager.setIntPrefVal(CalorieTracker.this, Constants.CALORIE, new_Calorie_value);

                        // Get Stored High Score
                        if (new_Calorie_value > SharedPrefManager.getIntPrefVal(CalorieTracker.this, Constants.CALORIE)) {

                            // Get and edit high score
                            SharedPrefManager.setIntPrefVal(CalorieTracker.this, Constants.CALORIE, new_Calorie_value);
                           // highScore.setText(String.valueOf(val));

                        }

                       // db.add(spactivity.getSelectedItem().toString(),Integer.parseInt(calValueDialog.getText().toString()),dmy);
                        db.add(spactivity.getSelectedItem().toString(),new_Calorie_value,dmy);
                        d.dismiss();
                        getCalorieDetails();

                    }
                });




            }
        });

    }
    private void getCalorieDetails()
    {
        caloriedetails.clear();
        CalorieObject calorieObject=null;
        Cursor c=db.retrieve();
        while (c.moveToNext())
        {
            int id=c.getInt(0);
            String activity_name=c.getString(1);
            int cal_value=c.getInt(2);
            String date=c.getString(3);
            calorieObject=new CalorieObject();
            calorieObject.setId(id);
           calorieObject.setActivity(activity_name);
calorieObject.setCalValue(cal_value);
            calorieObject.setDate(" "+date);
            caloriedetails.add(calorieObject);
        }
        lv.setAdapter(adapter);

    }
}
