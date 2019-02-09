package sg.edu.np.s10177744connect.madassignment;

import android.content.Intent;
import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;

import java.util.ArrayList;

import sg.edu.np.s10177744connect.madassignment.models.ToDo;

public class Visit extends AppCompatActivity {

    DBHelper mDatabaseHelper;
    static String position;
    private GridView mGridView;
    ArrayList<Country> Detailslist = new ArrayList<Country>();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visit);
        mDatabaseHelper =  new DBHelper(this, null, null, 1);
        Cursor data = mDatabaseHelper.getData();
        while(data.moveToNext()){
            //get the value from the database
            //then add it to the ArrayList
            if (data.getString(6).equals("Y")) {
                Detailslist.add(new Country(data.getString(0),data.getString(1),data.getString(2),data.getString(3),data.getString(4),data.getString(5)));
            }
        }
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
        if (Detailslist.size() == 0 ){
            TextView title = findViewById(R.id.txterror);
            title.setText("You haven't visited any locations yet");
        }
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        Menu menu = navigation.getMenu();
        MenuItem menuItem = menu.getItem(1);
        menuItem.setChecked(true);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

    }
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    finish();
                    startActivity(new Intent(Visit.this, CountryList.class));
                    overridePendingTransition(0,0);
                    return true;
                case R.id.navigation_list:
                    Intent intent = getIntent();
                    finish();
                    startActivity(intent);
                    overridePendingTransition(0,0);
                    return true;
                case R.id.navigation_journal:
                    finish();
                    startActivity(new Intent(Visit.this, JournalList.class));
                    overridePendingTransition(0,0);
                    return true;
                case R.id.navigation_reminder:
                    finish();
                    startActivity(new Intent(Visit.this, ToDoActivity.class));
                    overridePendingTransition(0,0);
                    return true;
                case R.id.navigation_expenses:
                    finish();
                    startActivity(new Intent(Visit.this, Finance.class));
                    overridePendingTransition(0,0);
                    return true;
            }
            return false;
        }
    };
    private void transferringvalue(String  name) {
        position = name;
        Intent intent = new Intent(this,AttractionDetails.class);
        intent.putExtra("Name", name);
        startActivity(intent);
    }
    //Visit Button
    public void visit (View view) {
        Button btnVisit =(Button)findViewById(R.id.btnVisit);
        Intent intent = getIntent();
        finish();
        startActivity(intent);
        overridePendingTransition(0,0);
    }
    //Favourites button
    public void fav (View view) {
        Button btnVisit =(Button)findViewById(R.id.btnVisit);
        startActivity(new Intent(Visit.this, Favourites.class));
        overridePendingTransition(0,0);
    }
}
