package com.example.apinstagramclone;

import androidx.appcompat.app.AppCompatActivity;

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

    private Button saveData,dataCollectbtn,allDatabtn;
    private EditText nameET,punchSpeedET,punchPowerET,kickSpeedET,kickPowerET;
    private TextView dataShowTV;
    private String data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        saveData = findViewById(R.id.saveBTN);
        nameET = findViewById(R.id.nameET);
        punchSpeedET = findViewById(R.id.punchSpeedET);
        punchPowerET = findViewById(R.id.punchPowerET);
        kickSpeedET = findViewById(R.id.kickSpeedET);
        kickPowerET = findViewById(R.id.kickPowerET);
        dataCollectbtn = findViewById(R.id.collectDatabtn);
        dataShowTV = findViewById(R.id.dataShowTV);
        allDatabtn = findViewById(R.id.allDatabtn);

        allDatabtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                data = "";
                ParseQuery<ParseObject> allQuery = ParseQuery.getQuery("KickBoxer");
                allQuery.findInBackground(new FindCallback<ParseObject>() {
                    @Override
                    public void done(List<ParseObject> objects, ParseException e) {
                        if (e == null){
                            if (objects.size() > 0){
                                for (ParseObject allkickboxer : objects){
                                    data = data+"Name: "+allkickboxer.get("Name")+"-"+allkickboxer.get("PunchPower")+"\n";
                                }

                                dataShowTV.setText(data);

                                FancyToast.makeText(MainActivity.this, data, FancyToast.LENGTH_LONG, FancyToast.SUCCESS, true).show();

                            }else {
                                FancyToast.makeText(MainActivity.this, e.getMessage(), FancyToast.LENGTH_LONG, FancyToast.ERROR, true).show();
                            }
                        }
                    }
                });

            }
        });

        saveData.setOnClickListener(MainActivity.this);
        dataCollectbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ParseQuery<ParseObject> parseQuery = ParseQuery.getQuery("KickBoxer");
                parseQuery.getInBackground("y7G2I6zmhK", new GetCallback<ParseObject>() {
                    @Override
                    public void done(ParseObject object, ParseException e) {

                        if (object != null && e == null){
                            dataShowTV.setText("Name: "+object.get("Name")+"\n"+"PunchSpeed: "
                                    +object.get("PunchSpeed")+"\n"+"PunchPower: "+object.get("PunchPower")+"\n"+
                                    "KickSpeed: "+object.get("KickSpeed")+"\n"+"KickPower: "+object.get("KickPower"));
                        }
                    }
                });
            }
        });

    }


    @Override
    public void onClick(View view) {

        try {
            final ParseObject kickBoxer = new ParseObject("KickBoxer");
            kickBoxer.put("Name", nameET.getText().toString());
            kickBoxer.put("PunchSpeed", punchSpeedET.getText().toString());
            kickBoxer.put("PunchPower", punchPowerET.getText().toString());
            kickBoxer.put("KickSpeed", kickSpeedET.getText().toString());
            kickBoxer.put("KickPower", kickPowerET.getText().toString());

            kickBoxer.saveInBackground(new SaveCallback() {
                @Override
                public void done(ParseException e) {
                    if (e == null) {
                        FancyToast.makeText(MainActivity.this, kickBoxer.get("Name") + " is Save to Server !", FancyToast.LENGTH_LONG, FancyToast.SUCCESS, true).show();
                    } else {
                        FancyToast.makeText(MainActivity.this, e.getMessage(), FancyToast.LENGTH_LONG, FancyToast.ERROR, true).show();
                    }
                }
            });

        } catch (Exception e){
            FancyToast.makeText(MainActivity.this, e.getMessage(), FancyToast.LENGTH_LONG, FancyToast.ERROR, true).show();
        }

    }
}
