package com.example.physiohut;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    List<R4Fragment> r4FragmentList;
    CustomAdapter customAdapter;
    private View decorView;

    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Here we make a custom toolbar with the function onCreateOptionsMenu
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.baseline_person_24);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("");

        //Here we hide the smartphone's navbar
        decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;
        decorView.setSystemUiVisibility(uiOptions);

        // ArrayList<String> arrayList=new ArrayList<>();
        // arrayList.add("12/03/22 Παροχή #2");
        // arrayList.add("16/03/22 Παροχή #3");
        // arrayList.add("09/04/22 Παροχή #4");
        // arrayList.add("11/06/22 Παροχή #5");
        // arrayList.add("01/09/22 Παροχή #6");
        // arrayList.add("22/11/22 Παροχή #7");
        // arrayList.add("30/11/22 Παροχή #8");
        // arrayList.add("19/12/22 Παροχή #9");
        // arrayList.add("14/01/23 Παροχή #10");
        // arrayList.add("01/03/23 Παροχή #11");
        // arrayList.add("25/04/23 Παροχή #12");

        displayItems();

    }

    private void displayItems() {

    }


    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.top_nav_bar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.back:
                Toast.makeText(this, "Back button", Toast.LENGTH_LONG).show();
                return true;
            case R.id.home:
                Toast.makeText(this, "Home button", Toast.LENGTH_LONG).show();
                return true;
            case R.id.logo:
                Toast.makeText(this, "logo button", Toast.LENGTH_LONG).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}