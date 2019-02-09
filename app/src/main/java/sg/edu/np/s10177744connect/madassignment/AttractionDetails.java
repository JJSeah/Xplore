package sg.edu.np.s10177744connect.madassignment;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import sg.edu.np.s10177744connect.madassignment.models.ToDo;

public class AttractionDetails extends AppCompatActivity {
    static String position;
    List<Country> detailslist = new ArrayList<Country>();
    String num;
    int toggle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attraction_details);


        String num = getIntent().getStringExtra("Name");

        DBHelper dbHandler = new DBHelper(this, null, null, 1);
        Country country = dbHandler.findAttraction(num);


        TextView name = (TextView)findViewById(R.id.txtName);
        name.setText(country.Attraction);

        TextView location = (TextView)findViewById(R.id.txtAddress);
        location.setText(country.Address);

        TextView details = (TextView)findViewById(R.id.txtDetails);
        details.setText(country.Details);

        int id = getResources().
                getIdentifier(
                        country.Photo,
                        "drawable",
                        getPackageName()
                );

        ImageView imgSong = (ImageView)findViewById(R.id.imgPhoto);
        imgSong.setImageResource(id);

        final ImageButton btnStar =(ImageButton)findViewById(R.id.imgbtn);

        if (country.Status.equals("Y")) {
            btnStar.setImageResource(android.R.drawable.btn_star_big_on);
            toggle = 0;

        } else {
            btnStar.setImageResource(android.R.drawable.btn_star_big_off);
            toggle = 1;
        }
        final ImageButton btnVisit =(ImageButton)findViewById(R.id.imgbtnv);

        if (country.Visit.equals("Y")) {
            btnVisit.setImageResource(R.drawable.visit_on);
            toggle = 0;

        } else {
            btnVisit.setImageResource(R.drawable.visit_off);
            toggle = 1;
        }
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

    }
    //ADD
    public void newtrip (View view) {
        TextView name = findViewById(R.id.txtName);
        ImageButton btnStar =(ImageButton)findViewById(R.id.imgbtn);
        DBHelper dbHandler = new DBHelper(this, null, null, 1);
        if (toggle == 1){
            Country country = new Country(name.getText().toString(),"Y");
            dbHandler.updateAttraction(country);
            btnStar.setImageResource(android.R.drawable.btn_star_big_on);
            toggle = 0;
        }
        else {
            Country country = new Country(name.getText().toString(),"N");
            dbHandler.updateAttraction(country);
            btnStar.setImageResource(android.R.drawable.btn_star_big_off);
            toggle = 1;

        }

    }
    //ADD
    public void newvisit (View view) {
        TextView name = findViewById(R.id.txtName);
        ImageButton btnVisit =(ImageButton)findViewById(R.id.imgbtnv);
        DBHelper dbHandler = new DBHelper(this, null, null, 1);
        if (toggle == 1){
            Country country = new Country();
            country.setAttraction(name.getText().toString());
            country.setVisit("Y");
            dbHandler.updateVisit(country);
            btnVisit.setImageResource(R.drawable.visit_on);
            toggle = 0;
        }
        else {
            Country country = new Country();
            country.setAttraction(name.getText().toString());
            country.setVisit("N");
            dbHandler.updateVisit(country);
            btnVisit.setImageResource(R.drawable.visit_off);
            toggle = 1;

        }

    }
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    finish();
                    startActivity(new Intent(AttractionDetails.this, CountryList.class));
                    overridePendingTransition(0,0);
                    return true;
                case R.id.navigation_list:
                    finish();
                    startActivity(new Intent(AttractionDetails.this, Favourites.class));
                    overridePendingTransition(0,0);
                    return true;
                case R.id.navigation_journal:
                    finish();
                    startActivity(new Intent(AttractionDetails.this, JournalList.class));
                    overridePendingTransition(0,0);
                    return true;
                case R.id.navigation_reminder:
                    finish();
                    startActivity(new Intent(AttractionDetails.this, ToDoActivity.class));
                    overridePendingTransition(0,0);
                    return true;
                case R.id.navigation_expenses:
                    finish();
                    startActivity(new Intent(AttractionDetails.this, Finance.class));
                    overridePendingTransition(0,0);
                    return true;
            }
            return false;
        }
    };
    //ADD
    public void map (View view) {
        TextView name = findViewById(R.id.txtName);
        position = (name.getText().toString());
        Intent intent = new Intent(this,MapsActivity.class);
        intent.putExtra("Place", position);
        startActivity(intent);
    }

}
