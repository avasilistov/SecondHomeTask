package com.demo.secondhometask;

import android.content.Intent;
import android.os.Bundle;
import com.demo.secondhometask.fragment.SettingsFragment;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.view.MenuItem;


public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();

        // Добавляем кнопку назад
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);

        // Добавляем SettingsFragment
        getSupportFragmentManager().beginTransaction().setReorderingAllowed(true).addToBackStack(null)
                .add(R.id.frame_layout_settings, SettingsFragment.class, null).commit();
    }

    // Задаем действия для кнопки Назад
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        String result = getResources().getString(R.string.result);
        String message = getResources().getString(R.string.message);
        switch (item.getItemId()) {
            case android.R.id.home:
                Intent intent = new Intent();
                intent.putExtra(result, message);
                setResult(RESULT_OK, intent);
                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }
}