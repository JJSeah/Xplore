package sg.edu.np.s10177744connect.madassignment;

import android.content.Intent;
import android.database.Cursor;
import android.media.Image;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;

import sg.edu.np.s10177744connect.madassignment.models.ToDo;

public class CountryDetails extends AppCompatActivity {

    static String position;
    ArrayList<Country> Detailslist = new ArrayList<Country>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_country_details);

        TextView name = findViewById(R.id.text1);
        String index = getIntent().getStringExtra("Country");

        DBHelper dbHandler = new DBHelper(this, null, null, 1);
        final Country country = dbHandler.displayattraction(index);
        Cursor data = dbHandler.getData();
        while(data.moveToNext()){
            //get the value from the database
            //then add it to the ArrayList
            if (data.getString(5).equals(index)) {
                Detailslist.add(new Country(data.getString(0),data.getString(1),data.getString(2),data.getString(3),data.getString(4),data.getString(5)));
            }
        }
        //Connect the data to the adapter
        //with layout for each row
        CountryAdapter itemsAdapter = new CountryAdapter(this,R.layout.grid_layout, Detailslist);
        //hook the adapter to the GridView
        GridView gv = findViewById(R.id.gv);
        gv.setAdapter(itemsAdapter);

        gv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {
                Country selectedItem = (Country) parent.getItemAtPosition(position);
                transferringvalue(selectedItem.Attraction);


            }

        });
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        if (index.equals("Singapore")){

        }
        else{
            ImageButton btnBus =(ImageButton)findViewById(R.id.btnbus);
            btnBus.setClickable(false);
            btnBus.setVisibility(View.GONE);
        }

    }
    private void transferringvalue(String  name) {
        position = name;
        Intent intent = new Intent(this,AttractionDetails.class);
        intent.putExtra("Name", name);
        startActivity(intent);
    }
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    finish();
                    startActivity(new Intent(CountryDetails.this, CountryList.class));
                    overridePendingTransition(0,0);
                    return true;
                case R.id.navigation_list:
                    finish();
                    startActivity(new Intent(CountryDetails.this, Favourites.class));
                    overridePendingTransition(0,0);
                    return true;
                case R.id.navigation_journal:
                    finish();
                    startActivity(new Intent(CountryDetails.this, JournalList.class));
                    overridePendingTransition(0,0);
                    return true;
                case R.id.navigation_reminder:
                    finish();
                    startActivity(new Intent(CountryDetails.this, ToDoActivity.class));
                    overridePendingTransition(0,0);
                    return true;
                case R.id.navigation_expenses:
                    finish();
                    startActivity(new Intent(CountryDetails.this, Finance.class));
                    overridePendingTransition(0,0);
                    return true;
            }
            return false;
        }
    };
    public void visit (View view) {
        ImageButton btnBus =(ImageButton)findViewById(R.id.btnbus);
        startActivity(new Intent(CountryDetails.this, MainActivity.class));
        overridePendingTransition(0,0);
    }


}
