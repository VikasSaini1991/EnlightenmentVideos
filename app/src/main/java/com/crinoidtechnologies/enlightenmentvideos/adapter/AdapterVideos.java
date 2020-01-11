package com.crinoidtechnologies.enlightenmentvideos.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.crinoidtechnologies.enlightenmentvideos.R;
import com.crinoidtechnologies.enlightenmentvideos.activity.YouTubePlayerActivity;
import com.crinoidtechnologies.enlightenmentvideos.fragment.VideosFragment;
import com.crinoidtechnologies.enlightenmentvideos.models.Videos;
import com.squareup.picasso.Picasso;

import java.util.List;

public class AdapterVideos extends RecyclerView.Adapter<AdapterVideos.VideoViewHolder> {
    String TAG="AdapterVideos";
    String BASE_URL = "https://i.ytimg.com/vi/";
    VideosFragment videosFragment;
    Context context;
    List<Videos> list1;
    Activity activity;
    Fragment fragment;



    public AdapterVideos(Context context,List<Videos> list1, Activity activity,Fragment fragment) {
        this.context=context;
        this.list1=list1;
        this.activity=activity;
        this.fragment=fragment;

    }



    @NonNull
    @Override
    public VideoViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from( viewGroup.getContext() ).inflate( R.layout.list_item_layout, viewGroup, false );

        return new VideoViewHolder( view );
    }

    @Override
    public void onBindViewHolder(@NonNull VideoViewHolder videoViewHolder, int i) {

//for(i =0; i<list1.size(); i+)
        final Videos videos=list1.get( i );

     Picasso.with(fragment.getContext()).load(BASE_URL+videos.getVideoId()+"/mqdefault.jpg").into(videoViewHolder.imageView  );

      videoViewHolder.textView.setText( videos.getTitle() );
       videoViewHolder.imageView.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(fragment.getActivity(), YouTubePlayerActivity.class );
                intent.putExtra( "title",videos.getTitle() );
                intent.putExtra( "description", videos.getDescription() );
                intent.putExtra( "videoid",videos.getVideoId() );
                fragment.getActivity().startActivity( intent );



            }
        } );

    }

    @Override
    public int getItemCount() {
        Log.d( TAG, "getItemCount: "+list1.size() );
        return list1.size();
    }

    public class VideoViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView textView;
        public VideoViewHolder(@NonNull View itemView) {
            super( itemView );
            imageView=itemView.findViewById(R.id.iv_image);
            textView=itemView.findViewById(R.id.tv_rvtext);




        }

    }
}
