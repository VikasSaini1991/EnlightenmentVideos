package com.crinoidtechnologies.enlightenmentvideos.activity;

import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.crinoidtechnologies.enlightenmentvideos.R;
import com.crinoidtechnologies.enlightenmentvideos.models.Videos;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerSupportFragment;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Main3Activity extends AppCompatActivity {
String TAG="Main3Activity";
TextView textView1, textView2;
    ArrayList<String> stringList=new ArrayList<>(  );
YouTubePlayerSupportFragment youTubePlayerSupportFragment;
YouTubePlayer activePlayer;
    private static final String API_KEY = "AIzaSyBEsu57Fs9yKutL3je68Xiec4jSt9Uo6_I";
    FirebaseDatabase firebaseDatabase=FirebaseDatabase.getInstance();
    DatabaseReference rootdabase=firebaseDatabase.getReference(  "Videos");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.listlayoutyoutubevideo );
        textView1=findViewById( R.id.tv_yvtitle );
        textView2=findViewById( R.id.yvDescription );
        rootdabase.addListenerForSingleValueEvent( new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Log.d( TAG, "onDataChange: "+dataSnapshot.getValue() );

                for (DataSnapshot userSnapshot : dataSnapshot.getChildren()) {
                    Videos videos= userSnapshot.getValue(Videos.class);
                    textView1.setText( getIntent().getExtras().getString( "title" ) );
                    textView2.setText( getIntent().getExtras().getString( "description" ) );

//                    final String temp= (String) userSnapshot.getValue();
//                    Log.d( TAG, "onDataChange: "+temp );
                    stringList.add( videos.video_id );
//                    stringList.add( videos.getTitle() );
//                    stringList.add( videos.getDescription() );
                }
                for(int i=0; i<stringList.size(); i++){
                    Log.d( TAG, "onDataChange:"+stringList.get( i ) );
                }




                youTubePlayerSupportFragment=YouTubePlayerSupportFragment.newInstance();
                youTubePlayerSupportFragment= (YouTubePlayerSupportFragment) getSupportFragmentManager().findFragmentById( R.id.youtubeplayerfragment );
                youTubePlayerSupportFragment.initialize( API_KEY, new YouTubePlayer.OnInitializedListener() {
                    @Override
                    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {

                        activePlayer = youTubePlayer;
                        activePlayer.setPlayerStyle(YouTubePlayer.PlayerStyle.DEFAULT);
                        if(!b){
                            activePlayer.loadVideo( getIntent().getExtras().getString( "videoid" ) );

//                            youTubePlayer.cueVideo( getIntent().getExtras().getString( "videoid" ));
                        }
                    }

                    @Override
                    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {

                    }
                } );
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        } );

    }
}
