package com.a1264d.a1264scout;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

public class CreateFile extends AppCompatActivity {

    CheckBox starCheck;
    CheckBox cubeCheck;
    CheckBox AutonomousHang;

    EditText starCapa;
    EditText starsTossed;
    EditText cubeCapa;
    EditText cubesTossed;
    EditText teamNumber;
    EditText driveType;
    EditText HangTime;
    EditText AutonomousScore;
    EditText AutonomousStars;
    EditText AutonomousCubes;
    EditText Comments;

    String teamFile;
    String cubeChecked;
    String starChecked;
    String AutonHang;

    Double OPR;
    Double AuPR;
    Double AxPR;
    Double Overall;

    Spinner starSpinner;
    Spinner cubeSpinner;
    Spinner driveSpinner;
    Spinner hangingSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_file);

        starCheck = (CheckBox) findViewById(R.id.starCheck);
        cubeCheck = (CheckBox) findViewById(R.id.cubeCheck);
        starCapa = (EditText) findViewById(R.id.starCapa);
        starsTossed = (EditText) findViewById(R.id.starsTossed);
        cubeCapa = (EditText) findViewById(R.id.cubeCapa);
        cubesTossed = (EditText) findViewById(R.id.cubesTossed);
        teamNumber = (EditText) findViewById(R.id.TeamNumber);
        driveType = (EditText) findViewById(R.id.DriveType);
        AutonomousHang = (CheckBox) findViewById(R.id.AutonomousHang);
        AutonomousScore = (EditText) findViewById(R.id.AutonomousScore);
        AutonomousStars = (EditText) findViewById(R.id.AutonomousStars);
        AutonomousCubes = (EditText) findViewById(R.id.AutonomousCubes);
        HangTime = (EditText) findViewById(R.id.HangingTime);
        Comments = (EditText) findViewById(R.id.Comments);

        final String[] starDistance = new String[]{"Near", "Far"};
        starSpinner = (Spinner) findViewById(R.id.starDistance);
        ArrayAdapter<String> starAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item,starDistance);
        starAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        starSpinner.setAdapter(starAdapter);

        final String[] cubeDistance = new String[]{"Near", "Far"};
        cubeSpinner = (Spinner) findViewById(R.id.cubeDistance);
        ArrayAdapter<String> cubeAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item,cubeDistance);
        cubeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        cubeSpinner.setAdapter(cubeAdapter);

        final String[] driveSpeeds = new String[]{"Slow", "Medium", "Fast"};
        driveSpinner = (Spinner) findViewById(R.id.driveSpeed);
        ArrayAdapter<String> driveAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, driveSpeeds);
        driveAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        driveSpinner.setAdapter(driveAdapter);

        final String[] hangingOptions = new String[]{"No", "Low", "High"};
        hangingSpinner = (Spinner) findViewById(R.id.Hanging);
        ArrayAdapter<String> hangingAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, hangingOptions);
        hangingAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        hangingSpinner.setAdapter(hangingAdapter);

        starCheck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView,
                                         boolean isChecked) {
                if (isChecked) {
                    starCapa.setEnabled(true);
                    starsTossed.setEnabled(true);
                } else {
                    starCapa.setEnabled(false);
                    starsTossed.setEnabled(false);
                }
            }
        });

        cubeCheck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView,
                                         boolean isChecked) {
                if (isChecked) {
                    cubeCapa.setEnabled(true);
                    cubesTossed.setEnabled(true);
                } else {
                    cubeCapa.setEnabled(false);
                    cubesTossed.setEnabled(false);
                }
            }
        });
    }
    private void moveFile(String inputPath, String outputPath, String inputFile) {

        InputStream in;
        OutputStream out;
        try {

            File dir = new File (outputPath);
            if (!dir.exists())
            {
                dir.mkdirs();
            }

            in = new FileInputStream(inputPath + inputFile);
            out = new FileOutputStream(outputPath + inputFile);

            byte[] buffer = new byte[1024];
            int read;
            while ((read = in.read(buffer)) != -1) {
                out.write(buffer, 0, read);
            }
            in.close();
            in = null;

            // write the output file
            out.flush();
            out.close();
            out = null;

            // delete the original file
            new File(inputPath + inputFile).delete();


        }

        catch (FileNotFoundException fnfe1) {
        }
        catch (Exception e) {
        }

    }
    public void saveClick(View view) throws IOException {
        teamFile = teamNumber.getText().toString() + ".csv";
        String to = getFilesDir().getAbsolutePath() + "/Team/";
        String from = getFilesDir().getAbsolutePath() + "/";
        if(starCheck.isChecked()){
            starChecked = "Checked";
        }
        else{
            starChecked = "Unchecked";
        }
        if(cubeCheck.isChecked()) {
            cubeChecked = "Checked";
        }
        else {
            cubeChecked = "Unchecked";
        }
        if(AutonomousHang.isChecked()){
            AutonHang = "Yes";
        }
        else{
            AutonHang = "No";
        }
        OPR = 1.00;
        AuPR = 2.11;
        AxPR = 3.56;
        Overall = OPR + AuPR + AxPR;
        try {
            OutputStreamWriter out=
                    new OutputStreamWriter(openFileOutput(teamFile, 0));
            out.write(teamNumber.getText().toString());
            out.write("," + driveType.getText().toString()) ;
            out.write("," + driveSpinner.getSelectedItem().toString());
            out.write("," + starChecked);
            out.write("," + starCapa.getText().toString());
            out.write("," + starsTossed.getText().toString());
            out.write("," + starSpinner.getSelectedItem().toString());
            out.write("," + cubeChecked);
            out.write("," + cubeCapa.getText().toString());
            out.write("," + cubesTossed.getText().toString());
            out.write("," + cubeSpinner.getSelectedItem().toString());
            out.write("," + AutonomousScore.getText().toString());
            out.write("," + AutonomousStars.getText().toString());
            out.write("," + AutonomousCubes.getText().toString());
            out.write("," + AutonHang);
            out.write("," + hangingSpinner.getSelectedItem().toString());
            out.write("," + HangTime.getText().toString());
            out.write("," + Comments.getText().toString());
            out.write("," + OPR);
            out.write("," + AuPR);
            out.write("," + AxPR);
            out.write("," + Overall);
            out.close();
            moveFile(from, to, teamFile);
            Toast
                    .makeText(this, "Aqil is an absolute fag!", Toast.LENGTH_LONG)
                    .show();
            Intent saved = new Intent(view.getContext(), BaseRankings.class);
            startActivity(saved);
        }
        catch (Throwable t) {
            Toast
                    .makeText(this, "Exception: "+t.toString(), Toast.LENGTH_LONG)
                    .show();
        }
    }
}
