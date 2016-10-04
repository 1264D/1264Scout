package com.a1264d.a1264scout;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ListView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;


public class BaseRankings extends AppCompatActivity {

    ListView mainListView;

    String path;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base_rankings);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mainListView = (ListView) findViewById(R.id.rankingList);
        path = getFilesDir().getAbsolutePath() + "/Team";
        File teams = new File(path);
        File file[] = teams.listFiles();
        String team[] = new String[file.length];
        String OPR[]= new String[file.length];
        String AuPR[] = new String[file.length];
        String AxPR[] = new String[file.length];
        String Overall[] = new String[file.length];
        for(int l = 0; l < file.length; l++){
            StringBuilder text = new StringBuilder();
            try {
                String line;
                BufferedReader br = new BufferedReader(new FileReader(file[l]));
                while ((line = br.readLine()) != null) {
                    text.append(line);
                    text.append('\n');
                }
                br.close();
            }
            catch (IOException e) {
                e.printStackTrace();
            }
            final String[] texts = text.toString().split(",");
            team[l] = texts[0];
            OPR[l] = texts[18];
            AuPR[l] = texts[19];
            AxPR[l] = texts[20];
            Overall[l] = texts[21];
        }

        Adapter adapter = new Adapter(this, team, Overall, OPR, AuPR, AxPR);
        mainListView.setAdapter(adapter);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent create = new Intent(view.getContext(), CreateFile.class);
                startActivity(create);
            }
        });
    }

        public void onItemClick(AdapterView<?> parent, View v, int pos, long id){

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_base_rankings, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.TeamName) {
            return true;
        }
        if (id == R.id.OffensivePowerRating) {
            return true;
        }
        if (id == R.id.AutonomousPowerRating) {
            return true;
        }
        if (id == R.id.AuxiliaryPowerRating) {
            return true;
        }
        if (id == R.id.OverallRating) {
            return true;
        }
        if (id == R.id.Refresh) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
