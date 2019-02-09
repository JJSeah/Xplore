package sg.edu.np.s10177744connect.madassignment;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

import sg.edu.np.s10177744connect.madassignment.models.ToDo;

public class CountryList extends AppCompatActivity {

    ArrayList<Country> data = new ArrayList<>();
    RecyclerView recyclerView;
    //RecyclerView adapter;
    static String cont;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_country_list);

        // inserting json (String json ="[insert string]";)
        String json = "[{\"Photo\":\"sg\",\"Place\":\"Singapore\",\"Num\":\"0\"},{\"Photo\":\"aus\",\"Place\":\"Perth\",\"Num\":\"1\"},{\"Photo\":\"jap\",\"Place\":\"Tokyo Area\",\"Num\":\"2\"}]";

        //import gson
        Gson gs = new Gson();
        //creating list
        Type listType = new TypeToken<ArrayList<Country>>(){}.getType();

        data = gs.fromJson(json, listType);
        //Connect the data to the adapter

        recyclerView = findViewById(R.id.lvRecyclerView);
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(this, data);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        Menu menu = navigation.getMenu();
        MenuItem menuItem = menu.getItem(0);
        menuItem.setChecked(true);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    Intent intent = getIntent();
                    finish();
                    startActivity(intent);
                    overridePendingTransition(0,0);
                    return true;
                case R.id.navigation_list:
                    finish();
                    startActivity(new Intent(CountryList.this, Favourites.class));
                    overridePendingTransition(0,0);
                    return true;
                case R.id.navigation_journal:
                    finish();
                    startActivity(new Intent(CountryList.this, JournalList.class));
                    overridePendingTransition(0,0);
                    return true;
                case R.id.navigation_reminder:
                    finish();
                    startActivity(new Intent(CountryList.this, ToDoActivity.class));
                    overridePendingTransition(0,0);
                    return true;
                case R.id.navigation_expenses:
                    finish();
                    startActivity(new Intent(CountryList.this, Finance.class));
                    overridePendingTransition(0,0);
                    return true;
            }
            return false;
        }
    };

}
