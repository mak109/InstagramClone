package com.example.mayukh.instagramclone;

import android.content.Intent;
import android.print.PrinterId;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.SaveCallback;
import com.shashank.sony.fancytoastlib.FancyToast;

import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private Button btnSave,btnGetAll,btnTransition;
    private EditText edtName,edtPunchSpeed,edtPunchPower,edtKickSpeed,edtKickPower;
    private TextView txtGetData;
    String allData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnSave = findViewById(R.id.btnSave);
        edtName = findViewById(R.id.edtName);
        edtPunchSpeed = findViewById(R.id.edtPunchSpeed);
        edtPunchPower = findViewById(R.id.edtPunchPower);
        edtKickSpeed = findViewById(R.id.edtKickSpeed);
        edtKickPower = findViewById(R.id.edtKickPower);
        txtGetData = findViewById(R.id.txtGetData);
        btnGetAll = findViewById(R.id.btnGetAll);
        btnTransition = findViewById(R.id.btnNextActivity);
        btnSave.setOnClickListener(MainActivity.this);
        txtGetData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ParseQuery<ParseObject> parseQuery = ParseQuery.getQuery("KickBoxer");
                parseQuery.getInBackground("aSrEiU0vyv", new GetCallback<ParseObject>() {
                    @Override
                    public void done(ParseObject object, ParseException e) {
                        if(object!=null && e == null){
                            txtGetData.setText(object.get("name")+" Punch Speed -"+object.get("punch_speed")+"Punch Power - "+
                                    object.get("punch_power")+"");
                        }
                    }
                });
            }
        });

        btnGetAll.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

            ParseQuery<ParseObject> queryAll = ParseQuery.getQuery("KickBoxer");
            //queryAll.where..() methods  help us to choose parse objects conditionally
            queryAll.whereNotEqualTo("name",null);
            queryAll.whereGreaterThan("punch_power",200);
            //setLimit(int) sets the limit of the objects that queryAll can hold
            queryAll.setLimit(4);
            queryAll.findInBackground(new FindCallback<ParseObject>() {
                @Override
                public void done(List<ParseObject> objects, ParseException e) {

                    if(e==null) {
                        if(objects.size()>0) {
                            allData="";

                            for (ParseObject kickboxer : objects)
                            {
                                allData+=(kickboxer.get("name") +" "+ kickboxer.get("punch_speed") +" "+ kickboxer.get("punch_power") + "\n");
                            }
                            FancyToast.makeText(MainActivity.this,allData,FancyToast.LENGTH_LONG,FancyToast.SUCCESS,true).show();
                            allData="";
                        }
                    }
                    else {
                        FancyToast.makeText(MainActivity.this, e.getMessage(), FancyToast.LENGTH_LONG, FancyToast.ERROR, true).show();
                    }
                }
            });
            }
        });
        btnTransition.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,
                        SignUpLoginActivity.class);
                startActivity(intent);

            }
        });
    }

    @Override
    public void onClick(View v) {
        try {
            final ParseObject kickboxer = new ParseObject("KickBoxer");
            kickboxer.put("name", edtName.getText().toString());
            kickboxer.put("punch_speed", Integer.parseInt(edtPunchSpeed.getText().toString()));
            kickboxer.put("punch_power", Integer.parseInt(edtPunchPower.getText().toString()));
            kickboxer.put("kick_speed", Integer.parseInt(edtKickSpeed.getText().toString()));
            kickboxer.put("kick_power", Integer.parseInt(edtKickPower.getText().toString()));
            kickboxer.saveInBackground(new SaveCallback() {
                @Override
                public void done(ParseException e) {
                    if (e == null)
                        FancyToast.makeText(MainActivity.this, kickboxer.get("name") + " object saved successfully", FancyToast.LENGTH_LONG, FancyToast.DEFAULT, true).show();
                    else
                        FancyToast.makeText(MainActivity.this, e.getMessage(), FancyToast.LENGTH_LONG, FancyToast.ERROR, true).show();
                }
            });
        }
        catch (Exception e){
            FancyToast.makeText(MainActivity.this, e.getMessage(), FancyToast.LENGTH_LONG, FancyToast.ERROR, true).show();
        }
    }
}
