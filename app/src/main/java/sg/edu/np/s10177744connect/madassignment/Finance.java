package sg.edu.np.s10177744connect.madassignment;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import sg.edu.np.s10177744connect.madassignment.models.Money;

public class Finance extends AppCompatActivity {
    private FinanceAdapter mAdapter;
    private List<Money> moneyList = new ArrayList<>();
    private CoordinatorLayout coordinatorLayout;
    private RecyclerView recyclerView;
    private TextView noMoneyView;

    private DBHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finance);


        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        Menu menu = navigation.getMenu();
        MenuItem menuItem = menu.getItem(4);
        menuItem.setChecked(true);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        recyclerView = findViewById(R.id.recycler_view);
        noMoneyView = findViewById(R.id.empty_money_view);

        db = new DBHelper(this, null, null, 1);

        moneyList.addAll(db.getAllAmount());

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showMoneyDialog(false, null, -1);
            }
        });

        mAdapter = new FinanceAdapter(this, moneyList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL, 16));
        recyclerView.setAdapter(mAdapter);

        toggleEmptyMoney();


         //On long press on RecyclerView item, open alert dialog with options to choose Edit and Delete
        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(this,
                recyclerView, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, final int position) {
            }

            @Override
            public void onLongClick(View view, int position) {
                showActionsDialog(position);
            }
        }));

    }


     //Inserting new note in db and refreshing the list
    private void createMoney(String desc, double amt) {
        // inserting money in db and getting newly inserted money id
        long id = db.insertMoney(desc, amt);

        // get the newly inserted money from db
        Money m = db.getMoney(id);

        if (m != null) {
            // adding new note to array list at 0 position
            moneyList.add(0, m);

            // refreshing the list
            mAdapter.notifyDataSetChanged();

            toggleEmptyMoney();
        }
    }

    //Updating money in db and updating item in the list by its position
    private void updateMoney(String money, int position) {
        Money n = moneyList.get(position);
        // updating money text
        n.setDescription(money);

        // updating money in db
        db.updateMoney(n);

        // refreshing the list
        moneyList.set(position, n);
        mAdapter.notifyItemChanged(position);

        toggleEmptyMoney();
    }

    //Deleting note from SQLite and removing the item from the list by its position
    private void deleteMoney(int position) {
        // deleting the note from db
        db.deleteMoney(moneyList.get(position));

        // removing the note from the list
        moneyList.remove(position);
        mAdapter.notifyItemRemoved(position);

        toggleEmptyMoney();
    }

    /*
     Opens dialog with Edit - Delete options
     Edit - 0
     Delete - 1
     */
    private void showActionsDialog(final int position) {
        CharSequence colors[] = new CharSequence[]{"Edit", "Delete"};

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Choose option");
        builder.setItems(colors, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (which == 0) {
                    showMoneyDialog(true, moneyList.get(position), position);
                } else {
                    deleteMoney(position);
                }
            }
        });
        builder.show();
    }


    /*
     Shows alert dialog with EditText options to enter / edit a money.
     when shouldUpdate=true, it automatically displays old money and changes the
     button text to UPDATE
     */
    private void showMoneyDialog(final boolean shouldUpdate, final Money money, final int position) {
        LayoutInflater layoutInflaterAndroid = LayoutInflater.from(getApplicationContext());
        View view = layoutInflaterAndroid.inflate(R.layout.finance_dialog, null);

        AlertDialog.Builder alertDialogBuilderUserInput = new AlertDialog.Builder(Finance.this);
        alertDialogBuilderUserInput.setView(view);

        final EditText inputDesc = view.findViewById(R.id.description);
        final EditText inputAmt = view.findViewById(R.id.amount);
        TextView dialogTitle = view.findViewById(R.id.dialog_title);
        dialogTitle.setText(!shouldUpdate ? getString(R.string.lbl_new_money_title) : getString(R.string.lbl_edit_money_title));

        if (shouldUpdate && money != null) {
            inputDesc.setText(money.getDescription());
            inputAmt.setText(Double.toString(money.getAmount()));
        }
        alertDialogBuilderUserInput
                .setCancelable(false)
                .setPositiveButton(shouldUpdate ? "update" : "save", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogBox, int id) {

                    }
                })
                .setNegativeButton("cancel",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialogBox, int id) {
                                dialogBox.cancel();
                            }
                        });

        final AlertDialog alertDialog = alertDialogBuilderUserInput.create();
        alertDialog.show();

        alertDialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Show toast message when no text is entered
                if (TextUtils.isEmpty(inputDesc.getText().toString())) {
                    Toast.makeText(Finance.this, "Enter description!", Toast.LENGTH_SHORT).show();
                    return;
                } else if (TextUtils.isEmpty(inputAmt.getText().toString())) {
                    Toast.makeText(Finance.this, "Enter Amount!", Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    alertDialog.dismiss();
                }

                // check if user updating money
                if (shouldUpdate && money != null) {
                    // update money by it's id
                    updateMoney(inputDesc.getText().toString(), position);
                } else {
                    // create new money
                    createMoney(inputDesc.getText().toString(), Double.parseDouble(inputAmt.getText().toString()));
                }
            }
        });
    }

    //Toggling list and empty notes view
    private void toggleEmptyMoney() {
        // you can check moneyList.size() > 0

        if (db.getMoneyCount() > 0) {
            noMoneyView.setVisibility(View.GONE);
        } else {
            noMoneyView.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    finish();
                    startActivity(new Intent(Finance.this, CountryList.class));
                    overridePendingTransition(0,0);
                    return true;
                case R.id.navigation_list:
                    finish();
                    startActivity(new Intent(Finance.this, Favourites.class));
                    overridePendingTransition(0,0);
                    return true;
                case R.id.navigation_journal:
                    finish();
                    startActivity(new Intent(Finance.this, JournalList.class));
                    overridePendingTransition(0,0);
                    return true;
                case R.id.navigation_reminder:
                    finish();
                    startActivity(new Intent(Finance.this, ToDoActivity.class));
                    overridePendingTransition(0,0);
                    return true;
                case R.id.navigation_expenses:
                    finish();
                    startActivity(new Intent(Finance.this, Finance.class));
                    overridePendingTransition(0,0);
                    return true;

            }
            return false;
        }
    };
}
