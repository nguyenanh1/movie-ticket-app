package vn.anhnguyen.ticketmovie.presentation.ui.activities;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import vn.anhnguyen.ticketmovie.R;

public class ProfileActivity extends AppCompatActivity {

    public static Intent getIntent(Context context){
        return new Intent(context,ProfileActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
    }
}
