package com.example.todoapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.Toast;

public class AddEditTaskActivity extends AppCompatActivity {

    public static final String EXTRA_ID =
            "com.example.todoapp.EXTRA_ID";

    public static final String EXTRA_TITLE =
            "com.example.todoapp.EXTRA_TITLE";

    public static final String EXTRA_DESCRIPTION =
            "com.example.todoapp.EXTRA_DESCRIPTION";

    public static final String EXTRA_PRIORITY =
            "com.example.todoapp.EXTRA_PRIORITY";

    public static final String EXTRA_DONE =
            "com.example.todoapp.EXTRA_DONE";

    public static final String EXTRA_DATE =
            "com.example.todoapp.EXTRA_DATE";

    private EditText editTextTitle;
    private EditText editTextDescription;
    private NumberPicker numberPickerPriority;
    private DatePicker datePickerDate;
    private CheckBox checkboxDate;
    private CheckBox checkboxDone;
    private String year;
    private String month;
    private String day;
    private String[] result;
    private String date;
    private int done;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);

        editTextTitle = findViewById(R.id.edit_text_title);
        editTextDescription = findViewById(R.id.edit_text_description);
        numberPickerPriority = findViewById(R.id.number_picker_priority);
        datePickerDate = findViewById(R.id.date_picker_date);
        checkboxDate = findViewById(R.id.check_box_date);
        checkboxDone = findViewById(R.id.check_box_done);

        numberPickerPriority.setMinValue(1);
        numberPickerPriority.setMaxValue(3);

        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close);

        Intent intent = getIntent();

        if (intent.hasExtra(EXTRA_ID)) {
            setTitle("Edytuj zadanie");
            editTextTitle.setText(intent.getStringExtra(EXTRA_TITLE));
            editTextDescription.setText(intent.getStringExtra(EXTRA_DESCRIPTION));
            numberPickerPriority.setValue(intent.getIntExtra(EXTRA_PRIORITY, 1));
            checkboxDone.setChecked(intent.getIntExtra(EXTRA_DONE, 0) != 0);
            if (!intent.getStringExtra(EXTRA_DATE).isEmpty()) {
                result = intent.getStringExtra(EXTRA_DATE).split("-");
                datePickerDate.init(Integer.parseInt(result[0]), Integer.parseInt(result[1]), Integer.parseInt(result[2]), null);
            } else {
                checkboxDate.setChecked(false);
                datePickerDate.setEnabled(false);
            }
        } else {
            setTitle("Dodaj zadanie");
        }
    }

    private void saveTask() {
        String title = editTextTitle.getText().toString();
        String description = editTextDescription.getText().toString();
        int priority = numberPickerPriority.getValue();
        year = String.valueOf(datePickerDate.getYear());
        month = String.valueOf(datePickerDate.getMonth());
        day = String.valueOf(datePickerDate.getDayOfMonth());

        checkboxDate = findViewById(R.id.check_box_date);
        checkboxDone = findViewById(R.id.check_box_done);

        if (checkboxDate.isChecked()) {
            date = year + "-" + month + "-" + day;
        } else {
            date = "";
        }

        if (checkboxDone.isChecked()) {
            done = 1;
        } else {
            done = 0;
        }

        if (title.trim().isEmpty()) {
            Toast.makeText(this, "Proszę wprowadzić tytuł zadania", Toast.LENGTH_SHORT).show();
            return;
        }

        Intent data = new Intent();
        data.putExtra(EXTRA_TITLE, title);
        data.putExtra(EXTRA_DESCRIPTION, description);
        data.putExtra(EXTRA_PRIORITY, priority);
        data.putExtra(EXTRA_DONE, done);
        data.putExtra(EXTRA_DATE, date);

        int id = getIntent().getIntExtra(EXTRA_ID, -1);
        if (id != -1) {
            data.putExtra(EXTRA_ID, id);
        }

        setResult(RESULT_OK, data);
        finish();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.add_task_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.save_task:
                saveTask();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void onCheckboxClicked(View view) {
        // Is the view now checked?
        boolean checked = ((CheckBox) view).isChecked();

        // Check which checkbox was clicked
        switch (view.getId()) {
            case R.id.check_box_date:
                if (checked) {
                    datePickerDate = findViewById(R.id.date_picker_date);
                    datePickerDate.setEnabled(true);
                } else {
                    datePickerDate.setEnabled(false);
                }
                break;
            // TODO: Veggie sandwich
        }
    }

}