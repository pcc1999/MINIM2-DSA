package com.example.minim2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

public class SearchActivity extends AppCompatActivity {

    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        sharedPreferences = getSharedPreferences("mySharedPreferences", MODE_PRIVATE);
        if(sharedPreferences.getAll().size() != 0){
            TextView txt = (TextView)findViewById(R.id.txtUsername);
            txt.setText((CharSequence)sharedPreferences.getAll().get("Username"));
        }
    }

    public void onClick(View v){
        Intent openUserActivity = new Intent(getApplicationContext(), UserActivity.class);
        openUserActivity.putExtra("user", this.getText().toString());
        SharedPreferences.Editor editor = getSharedPreferences("mySharedPreferences", MODE_PRIVATE).edit();
        editor.clear();
        editor.putString("Username", getText());
        editor.apply();
        startActivity(openUserActivity);
    }

    public String getText(){
        TextView txt = (TextView)findViewById(R.id.txtUsername);
        return txt.getText().toString();
    }

}