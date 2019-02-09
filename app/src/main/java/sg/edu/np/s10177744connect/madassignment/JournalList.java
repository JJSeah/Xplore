package sg.edu.np.s10177744connect.madassignment;

import android.content.Intent;
import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;

import sg.edu.np.s10177744connect.madassignment.models.ToDo;

public class JournalList extends AppCompatActivity {
    DBHelper mDatabaseHelper;
    static String position;
    private GridView mGridView;
    ArrayList<Country> Detailslist = new ArrayList<Country>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_journal_list);

        mDatabaseHelper =  new DBHelper(this, null, null, 1);
        Cursor data = mDatabaseHelper.getJournal();

        while(data.moveToNext()){
            //get the value from the database
            //then add it to the ArrayList
            Detailslist.add(new Country(data.getString(1),data.getString(2),data.getBlob(3)));
        }
        JournalAdapter itemsAdapter = new JournalAdapter(this,R.layout.journal_layout, Detailslist);
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
        Menu menu = navigation.getMenu();
        MenuItem menuItem = menu.getItem(2);
        menuItem.setChecked(true);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        if (Detailslist.size() == 0 ){
            TextView title = findViewById(R.id.txterror);
            title.setText("You haven't journaled yet. Click + icon to add!");
        }

    }
    private void transferringvalue(String  name) {
        position = name;
        Intent intent = new Intent(this,JournalDetails.class);
        intent.putExtra("Journal", name);
        startActivity(intent);
    }
    //Visit Button
    public void visit (View view) {
        ImageButton btnVisit =(ImageButton)findViewById(R.id.btnAdd);
        startActivity(new Intent(JournalList.this, AddJournal.class));
    }
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    finish();
                    startActivity(new Intent(JournalList.this, CountryList.class));
                    overridePendingTransition(0,0);
                    return true;
                case R.id.navigation_list:
                    finish();
                    startActivity(new Intent(JournalList.this, Favourites.class));
                    overridePendingTransition(0,0);
                    return true;
                case R.id.navigation_journal:
                    finish();
                    startActivity(new Intent(JournalList.this, JournalList.class));
                    overridePendingTransition(0,0);
                    return true;
                case R.id.navigation_reminder:
                    finish();
                    startActivity(new Intent(JournalList.this, ToDoActivity.class));
                    overridePendingTransition(0,0);
                    return true;
                case R.id.navigation_expenses:
                    finish();
                    startActivity(new Intent(JournalList.this, Finance.class));
                    overridePendingTransition(0,0);
                    return true;

            }
            return false;
        }
    };
}
