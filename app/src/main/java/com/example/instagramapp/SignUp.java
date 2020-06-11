package com.example.instagramapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.SaveCallback;
import com.shashank.sony.fancytoastlib.FancyToast;

import java.util.List;

public class SignUp extends AppCompatActivity implements View.OnClickListener {

    private Button btnSave;
    private EditText edtName,edtPunchSpeed,edtPunchPower,edtKickSpeed,edtKickPower;
    private TextView txtGetDataFromServer;
    private Button getAllData;
    private String allKickBoxers;

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
        txtGetDataFromServer = findViewById(R.id.txtGetDataFromServer);
        getAllData = findViewById(R.id.btnGetAllData);

        btnSave.setOnClickListener(SignUp.this);// implementing the interface.
        txtGetDataFromServer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ParseQuery<ParseObject> parseQuery = ParseQuery.getQuery("kickBoxer");
                parseQuery.getInBackground("bBkjvgUkhX", new GetCallback<ParseObject>() {// Retrieving selected data from the server.
                    @Override
                    public void done(ParseObject object, ParseException e) {
                        if(object != null && e == null){ // if something goes wrong, we actually get an object which is null
                            // adn there is no errors.if you want get one object use the get In background. Otherwise, use the
                            // findIn Background call back method.

                            txtGetDataFromServer.setText(object.get("name")+ " - " +
                                    "Punch Power: " + object.get("punchPower") + "" );


                        }
                    }
                });

            }
        });

        getAllData.setOnClickListener(new View.OnClickListener() {// Retrieving All data from the server.
            @Override
            public void onClick(View v) {

                allKickBoxers = ""; // this is just an initial value.when the button is tapped,you are going to assign this empty value to this variable.

                ParseQuery<ParseObject> queryAll = ParseQuery.getQuery("kickBoxer");
                queryAll.findInBackground(new FindCallback<ParseObject>() {
                    @Override
                    public void done(List<ParseObject> objects, ParseException e) {// we are going to take a list of objects.

                        if(e == null){

                            if(objects.size() > 0){
                                for (ParseObject kickBoxer : objects){
                                    // we are going to iterate all the objects that we get from the parse server. in each iteration,it is going to name
                                    // each object of these objects as parse object.and we can do some operations within this for loop.
                                    allKickBoxers = allKickBoxers  + kickBoxer.get("name")  + kickBoxer.get("punchPower") + "\n" ;

                                }

                                FancyToast.makeText(SignUp.this,  allKickBoxers, FancyToast.LENGTH_LONG, FancyToast.SUCCESS, true).show();

                            }else {

                                FancyToast.makeText(SignUp.this, e.getMessage(), FancyToast.LENGTH_LONG, FancyToast.ERROR, true).show();

                            }
                        }

                    }
                });

            }
        });

    }

    //public void helloWorldTapped(View view) {

        /*ParseObject boxer = new ParseObject("Boxer");
        boxer.put("punch_speed",200);
        boxer.saveInBackground(new SaveCallback() {
            @Override
            public void done(ParseException e) {
                // this means successfully saved to the server.E means the exception.
                if(e == null){

                    // this won't work within an anonymous class.

                    Toast.makeText(SignUp.this,"Boxer object is saved successfully.!",Toast.LENGTH_LONG).show();

                }
            }
        });*/

        // In this manner, we let the user to enter the data.


   // }

    @Override
    public void onClick(View v) {// Saving the data to the Server.

        try {

            final ParseObject kickBoxer = new ParseObject("kickBoxer");
            kickBoxer.put("name", edtName.getText().toString());
            kickBoxer.put("punchSpeed", Integer.parseInt(edtPunchSpeed.getText().toString())); // IN THE SERVER THERE IS A integer value,
            // so we have to pass the int as well.Always check the Server..!!!
            kickBoxer.put("punchPower", Integer.parseInt(edtPunchPower.getText().toString()));
            kickBoxer.put("kickSpeed", Integer.parseInt(edtKickSpeed.getText().toString()));
            kickBoxer.put("kickPower", Integer.parseInt(edtKickPower.getText().toString()));
            kickBoxer.saveInBackground(new SaveCallback() {
                @Override
                public void done(ParseException e) {

                    if (e == null) {
                        FancyToast.makeText(SignUp.this, kickBoxer.get("name") + " is saved to server", FancyToast.LENGTH_LONG, FancyToast.SUCCESS, true).show();
                    } else {
                        FancyToast.makeText(SignUp.this, e.getMessage(), FancyToast.LENGTH_LONG, FancyToast.ERROR, true).show();
                    }
                }
            });
        } catch (Exception e){// Whenever something goes wrong, u can send this Toast message to the user.
            // so that the app won't crash. that is an important trick.

            FancyToast.makeText(SignUp.this, e.getMessage(), FancyToast.LENGTH_LONG, FancyToast.ERROR, true).show();



        }

    }
}
