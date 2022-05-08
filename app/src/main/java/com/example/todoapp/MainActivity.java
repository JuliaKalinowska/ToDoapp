package com.example.todoapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static final int ADD_TASK_REQUEST = 1;
    public static final int EDIT_TASK_REQUEST = 2;
    private boolean checked = false;
    private TaskViewModel taskViewModel;
    RecyclerView recyclerView;
    final TaskAdapter adapter = new TaskAdapter();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FloatingActionButton buttonAddTask = findViewById(R.id.button_add_task);
        buttonAddTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AddEditTaskActivity.class);
                startActivityForResult(intent,ADD_TASK_REQUEST);

            }
        });

        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        //final TaskAdapter adapter = new TaskAdapter();
        recyclerView.setAdapter(adapter);

        taskViewModel = new ViewModelProvider(this).get(TaskViewModel.class);

        taskViewModel.getAllTasks().observe(this, new Observer<List<Task>>() {
            @Override
            public void onChanged(List<Task> tasks) {
                //update RecyclerView
                adapter.setTasks(tasks);

            }
        });

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                taskViewModel.delete(adapter.getTaskAt(viewHolder.getAdapterPosition()));
                Toast.makeText(MainActivity.this, "Usunięto zadanie", Toast.LENGTH_SHORT).show();
            }
        }).attachToRecyclerView(recyclerView);

        adapter.setOnItemClickListener(new TaskAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Task task) {
                Intent intent = new Intent(MainActivity.this, AddEditTaskActivity.class);

                intent.putExtra(AddEditTaskActivity.EXTRA_ID, task.getId());
                intent.putExtra(AddEditTaskActivity.EXTRA_TITLE, task.getTitle());
                intent.putExtra(AddEditTaskActivity.EXTRA_DESCRIPTION, task.getDescription());
                intent.putExtra(AddEditTaskActivity.EXTRA_PRIORITY, task.getPriority());
                intent.putExtra(AddEditTaskActivity.EXTRA_DONE, task.getDone());
                intent.putExtra(AddEditTaskActivity.EXTRA_DATE, task.getDate());

                startActivityForResult(intent, EDIT_TASK_REQUEST);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == ADD_TASK_REQUEST && resultCode == RESULT_OK)
        {
            String title = data.getStringExtra(AddEditTaskActivity.EXTRA_TITLE);
            String description = data.getStringExtra(AddEditTaskActivity.EXTRA_DESCRIPTION);
            int priority = data.getIntExtra(AddEditTaskActivity.EXTRA_PRIORITY, 1);
            int done = data.getIntExtra(AddEditTaskActivity.EXTRA_DONE, 0);
            String date = data.getStringExtra(AddEditTaskActivity.EXTRA_DATE);

            Task task = new Task(title, description, priority, done, date);
            taskViewModel.insert(task);

            if (!checked) {
                taskViewModel.getAllTasksByASCName().observe(this, new Observer<List<Task>>() {
                    @Override
                    public void onChanged(List<Task> tasks) {
                        adapter.setTasks(tasks);
                    }
                });
            }
            else {
                taskViewModel.getAllNotDoneTasksByASCName().observe(this, new Observer<List<Task>>() {
                    @Override
                    public void onChanged(List<Task> tasks) {
                        adapter.setTasks(tasks);
                    }
                });
            }

            Toast.makeText(this, "Zapisano zadanie", Toast.LENGTH_SHORT).show();
        } else if (requestCode == EDIT_TASK_REQUEST && resultCode == RESULT_OK)
        {
            int id = data.getIntExtra(AddEditTaskActivity.EXTRA_ID, -1);

            if (id == -1)
            {
                Toast.makeText(this, "Zadanie nie może być edytowane", Toast.LENGTH_SHORT).show();
                return;
            }

            String title = data.getStringExtra(AddEditTaskActivity.EXTRA_TITLE);
            String description = data.getStringExtra(AddEditTaskActivity.EXTRA_DESCRIPTION);
            int priority = data.getIntExtra(AddEditTaskActivity.EXTRA_PRIORITY, 1);
            int done = data.getIntExtra(AddEditTaskActivity.EXTRA_DONE, 0);
            String date = data.getStringExtra(AddEditTaskActivity.EXTRA_DATE);

            Task task = new Task(title, description, priority, done, date);
            task.setId(id);
            taskViewModel.update(task);

            Toast.makeText(this, "Zadanie zedytowane", Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(this, "Nie zapisano zadania", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main_menu,menu);

        //MenuItem hide = menu.findItem(R.id.hide_completed_task);
        //hide.setChecked(checked);

        MenuItem search = menu.findItem(R.id.search_bar);
        SearchView searchView = (SearchView) search.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);
                return false;
            }
        });

        return true;
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId())
        {
            case R.id.delete_all_tasks:
                taskViewModel.deleteAllTasks();
                Toast.makeText(this, "Usunięto wszystkie zadania", Toast.LENGTH_SHORT).show();
                return true;

            case R.id.delete_all_done_tasks:
                taskViewModel.deleteAllDoneTasks();
                Toast.makeText(this, "Usunięto wszystkie ukończone zadania", Toast.LENGTH_SHORT).show();
                return true;

            case R.id.share:
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                //taskViewModel.getAllTasks();
                intent.putExtra(Intent.EXTRA_TEXT, "Udostępniono zadania: " + taskViewModel.getAllTasks());
                startActivity(Intent.createChooser(intent, "Udostępnij"));
                return true;

            case R.id.sort_by_name:
                if (!checked) {
                    taskViewModel.getAllTasksByASCName().observe(this, new Observer<List<Task>>() {
                        @Override
                        public void onChanged(List<Task> tasks) {
                            adapter.setTasks(tasks);
                        }
                    });
                }
                else {
                    taskViewModel.getAllNotDoneTasksByASCName().observe(this, new Observer<List<Task>>() {
                        @Override
                        public void onChanged(List<Task> tasks) {
                            adapter.setTasks(tasks);
                        }
                    });
                }
                Toast.makeText(this,"Posortowano według nazwy rosnąco", Toast.LENGTH_SHORT).show();
                return true;

            case R.id.sort_by_priority:
                if (!checked) {
                    taskViewModel.getAllTasksByASCPriority().observe(this, new Observer<List<Task>>() {
                        @Override
                        public void onChanged(List<Task> tasks) {
                            adapter.setTasks(tasks);
                        }
                    });
                }
                else {
                    taskViewModel.getAllNotDoneTasksByASCPriority().observe(this, new Observer<List<Task>>() {
                        @Override
                        public void onChanged(List<Task> tasks) {
                            adapter.setTasks(tasks);
                        }
                    });
                }
                Toast.makeText(this,"Posortowano według priorytetu rosnąco", Toast.LENGTH_SHORT).show();
                return true;

            case R.id.sort_by_date:
                if (!checked) {
                    taskViewModel.getAllTasksByASCDate().observe(this, new Observer<List<Task>>() {
                        @Override
                        public void onChanged(List<Task> tasks) {
                            adapter.setTasks(tasks);
                        }
                    });
                }
                else {
                    taskViewModel.getAllNotDoneTasksByASCDate().observe(this, new Observer<List<Task>>() {
                        @Override
                        public void onChanged(List<Task> tasks) {
                            adapter.setTasks(tasks);
                        }
                    });
                }
                Toast.makeText(this,"Posortowano według daty rosnąco", Toast.LENGTH_SHORT).show();
                return true;

            case R.id.hide_completed_task:
                checked = !checked;
                item.setChecked(checked);
                if (!checked) {
                    taskViewModel.getAllTasksByASCName().observe(this, new Observer<List<Task>>() {
                        @Override
                        public void onChanged(List<Task> tasks) {
                            adapter.setTasks(tasks);
                        }
                    });
                }
                else {
                    taskViewModel.getAllNotDoneTasksByASCName().observe(this, new Observer<List<Task>>() {
                        @Override
                        public void onChanged(List<Task> tasks) {
                            adapter.setTasks(tasks);
                        }
                    });
                }
                Toast.makeText(this,"Posortowano według nazwy rosnąco", Toast.LENGTH_SHORT).show();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }


    }
}