package sg.edu.np.s10177744connect.madassignment;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SimpleItemAnimator;
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

import sg.edu.np.s10177744connect.madassignment.models.ToDo;

public class ToDoActivity extends AppCompatActivity {
    private ToDoAdapter tdAdapter;
    private List<ToDo> toDoList = new ArrayList<>();
    private CoordinatorLayout coordinatorLayout;
    private RecyclerView recyclerView;

    private DBHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_to_do);


        recyclerView = findViewById(R.id.todoRecycler);
        db = new DBHelper(this, null, null, 1);

        toDoList.addAll(db.getAllToDO());

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showToDoDialog(false, null, -1);
            }
        });

        tdAdapter = new ToDoAdapter(this, toDoList);
        RecyclerView.LayoutManager tdLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(tdLayoutManager);
        ((SimpleItemAnimator) recyclerView.getItemAnimator()).setSupportsChangeAnimations(false);
        recyclerView.setAdapter(tdAdapter);
        recyclerView.setHasFixedSize(true);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        Menu menu = navigation.getMenu();
        MenuItem menuItem = menu.getItem(3);
        menuItem.setChecked(true);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_to_do, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void createToDo(String desc) {
        // inserting money in db and getting
        // newly inserted money id
        long id = db.insertToDo(desc);

        // get the newly inserted money from db
        ToDo td = db.getToDo(id);

        if (td != null) {
            // adding new note to array list at 0 position
            toDoList.add(0, td);

            // refreshing the list
            tdAdapter.notifyDataSetChanged();
        }
    }

    /**
     * Updating money in db and updating
     * item in the list by its position
     */
    private void updateToDo(String todo, int position) {
        ToDo td = toDoList.get(position);
        // updating money text
        td.setDescription(todo);

        // updating money in db
        db.updateToDo(td);

        // refreshing the list
        toDoList.set(position, td);
        tdAdapter.notifyItemChanged(position);
    }

    /**
     * Deleting To Do from SQLite and removing the
     * item from the list by its position
     */
    public void deleteToDo(int position) {
        // deleting the To Do from db
        ToDo toDo = toDoList.get(position);
        db.deleteToDo(toDo);
        toDoList.remove(position);
        tdAdapter.notifyItemRemoved(position);

        // showing snack bar with Undo option
        Snackbar snackbar = Snackbar
                .make(coordinatorLayout, "A To Do hsa been removed from list!", Snackbar.LENGTH_LONG);
        snackbar.setAction("UNDO", new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // undo is selected, restore the deleted To Do
                toDoList.add(position, toDo);
                db.insertdetailToDo(toDo.getDescription(), toDo.getId(), toDo.getTimestamp());
                tdAdapter.notifyItemInserted(position);
            }
        });
        snackbar.setActionTextColor(Color.YELLOW);
        snackbar.show();

    }


    public void showToDoDialog(final boolean shouldUpdate, final ToDo toDo, final int position) {
        LayoutInflater layoutInflaterAndroid = LayoutInflater.from(getApplicationContext());
        View view = layoutInflaterAndroid.inflate(R.layout.todo_dialog, null);

        AlertDialog.Builder alertDialogBuilderUserInput = new AlertDialog.Builder(ToDoActivity.this);
        alertDialogBuilderUserInput.setView(view);

        final EditText inputDesc = view.findViewById(R.id.description);
        TextView dialogTitle = view.findViewById(R.id.dialog_title);
        dialogTitle.setText(!shouldUpdate ? getString(R.string.lbl_new_todo_title) : getString(R.string.lbl_edit_todo_title));

        if (shouldUpdate && toDo != null) {
            inputDesc.setText(toDo.getDescription());
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
                    Toast.makeText(ToDoActivity.this, "Enter description!", Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    alertDialog.dismiss();
                }

                // check if user updating to Do
                if (shouldUpdate && toDo != null) {
                    // update to Do by it's id
                    updateToDo(inputDesc.getText().toString(), position);
                } else {
                    // create new to Do
                    createToDo(inputDesc.getText().toString());
                }
            }
        });
    }
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    finish();
                    startActivity(new Intent(ToDoActivity.this, CountryList.class));
                    overridePendingTransition(0,0);
                    return true;
                case R.id.navigation_list:
                    finish();
                    startActivity(new Intent(ToDoActivity.this, Favourites.class));
                    overridePendingTransition(0,0);
                    return true;
                case R.id.navigation_journal:
                    finish();
                    startActivity(new Intent(ToDoActivity.this, JournalList.class));
                    overridePendingTransition(0,0);
                    return true;
                case R.id.navigation_reminder:
                    finish();
                    startActivity(new Intent(ToDoActivity.this, ToDoActivity.class));
                    overridePendingTransition(0,0);
                    return true;
                case R.id.navigation_expenses:
                    finish();
                    startActivity(new Intent(ToDoActivity.this, Finance.class));
                    overridePendingTransition(0,0);
                    return true;

            }
            return false;
        }
    };
}
