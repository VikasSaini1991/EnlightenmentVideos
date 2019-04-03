package com.crinoidtechnologies.enlightenmentvideos.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

import com.crinoidtechnologies.enlightenmentvideos.R;
import com.crinoidtechnologies.enlightenmentvideos.fragment.FeedbackFragment;
import com.crinoidtechnologies.enlightenmentvideos.fragment.VideosFragment;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    private TextView mTextMessage;

    FragmentManager fragmentManager;
    VideosFragment videosFragment=new VideosFragment();

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_video:
                    fragmentManager=getSupportFragmentManager();
                    fragmentManager.beginTransaction().replace( R.id.fl_Container, new VideosFragment() ).commit();
                    return true;
                case R.id.navigation_feedback:
                    fragmentManager=getSupportFragmentManager();
                    FeedbackFragment feedbackFragment=new FeedbackFragment();
                    fragmentManager.beginTransaction().replace( R.id.fl_Container,feedbackFragment ).commit();
                    return true;

            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );

        mTextMessage =  findViewById( R.id.message );
        BottomNavigationView navigation =  findViewById( R.id.navigation );
        navigation.setOnNavigationItemSelectedListener( mOnNavigationItemSelectedListener );
        fragmentManager=getSupportFragmentManager();
        fragmentManager.beginTransaction().replace( R.id.fl_Container, videosFragment ).commit();

    }

}
