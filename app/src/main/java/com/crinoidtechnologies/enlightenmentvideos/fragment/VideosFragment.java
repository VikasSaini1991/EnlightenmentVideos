package com.crinoidtechnologies.enlightenmentvideos.fragment;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;


import com.crinoidtechnologies.enlightenmentvideos.R;

import com.crinoidtechnologies.enlightenmentvideos.adapter.AdapterVideos;
import com.crinoidtechnologies.enlightenmentvideos.models.Videos;
import com.google.android.youtube.player.YouTubePlayerSupportFragment;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Retrofit;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link VideosFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link VideosFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class VideosFragment extends Fragment {
//    ImageView imageView;
    String TAG="VideosFragment";
    Activity activity;
    List<Videos> list1=new ArrayList<>(  );
    String BASE_URL = "https://i.ytimg.com/vi/";
    Context context;
    YouTubePlayerSupportFragment youTubePlayerSupportFragment;
    RecyclerView recyclerView;
    AdapterVideos adapterVideos;
    LinearLayoutManager linearLayoutManager;
    RecyclerView.Adapter adapter;
    FirebaseDatabase firebaseDatabase=FirebaseDatabase.getInstance();
    DatabaseReference databaseReference=firebaseDatabase.getReference("Videos");





    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public VideosFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment VideosFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static VideosFragment newInstance(String param1, String param2) {
        VideosFragment fragment = new VideosFragment();
        Bundle args = new Bundle();
        args.putString( ARG_PARAM1, param1 );
        args.putString( ARG_PARAM2, param2 );
        fragment.setArguments( args );
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );

        if (getArguments() != null) {
            mParam1 = getArguments().getString( ARG_PARAM1 );
            mParam2 = getArguments().getString( ARG_PARAM2 );

        }


//        list1=new ArrayList<String>(  );
//        List<String> list=new ArrayList<String>(  );
//        list.add("_VDQjLp5X1M");
//        list.add("jFGKJBPFdUA");
//        list.add("KIcBhuem1oc");
//        list.add("_VDQjLp5X1M");
//        list.add("jFGKJBPFdUA");
//        list.add("KIcBhuem1oc");
//        list.add("_VDQjLp5X1M");
//        list.add("jFGKJBPFdUA");
//        list.add("KIcBhuem1oc");
//        list.add("_VDQjLp5X1M");
//        list.add("jFGKJBPFdUA");
//        list.add("KIcBhuem1oc");
//
//
//
//
//        for(int i=0; i<list.size(); i++) {
//            Log.d( TAG, "onCreate: " +BASE_URL+list.get( i ));
//            list1.add(BASE_URL+list.get( i )+"/mqdefault.jpg");
//
//
//        }


    }

    @Override
    public void onStart() {
        super.onStart();

    }
    public void fetchData(){
        databaseReference.addListenerForSingleValueEvent( new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Log.d( TAG, "onDataChange: "+dataSnapshot.getValue() );
                for (DataSnapshot userSnapshot : dataSnapshot.getChildren()){
                    Videos videos=userSnapshot.getValue(Videos.class);

                    Log.d( TAG, "onDataChange2: "+videos.getVideoId() );
//                      list1.add(BASE_URL+videos.getVideoId()+"/mqdefault.jpg");
                    list1.add( videos);

                }
                for(int i=0; i<list1.size(); i++){

                    Log.d( TAG, "onDataChange: "+list1.get( i ) );

                }
                adapter.notifyDataSetChanged();


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        } );

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate( R.layout.fragment_videos, container, false );
        recyclerView=view.findViewById( R.id.rv_fragmentvideo );
        linearLayoutManager= new LinearLayoutManager( getActivity() );
        //YoutubeApiInterface youtubeApiInterface= RetroFitClient.getYoutubeApiInterface();


        recyclerView.setLayoutManager( linearLayoutManager );

        adapter=new AdapterVideos(context,list1,activity,this);
        recyclerView.setAdapter( adapter );
        fetchData();
        return  view;


    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction( uri );
        }
    }

//    @Override
//    public void onAttach(Context context) {
//        super.onAttach( context );
//        if (context instanceof OnFragmentInteractionListener) {
//            mListener = (OnFragmentInteractionListener) context;
//        } else {
//            throw new RuntimeException( context.toString()
//                    + " must implement OnFragmentInteractionListener" );
//        }
//    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
