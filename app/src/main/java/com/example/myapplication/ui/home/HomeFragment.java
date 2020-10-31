package com.example.myapplication.ui.home;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.MainActivity;
import com.example.myapplication.R;
import com.example.myapplication.WebView2;
import com.example.myapplication.ui.RegisterActivity;

import java.util.List;

public class HomeFragment extends Fragment {

    private List<Integer> mDatas;
    private RecyclerView mRecyclerView;
    private HomeViewModel homeViewModel;
    private VideoView mVideoView;
    private Uri uri;

    private WebView web;
    private Button read;
    private Button share;

    private MediaController mediaCon;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        //homeViewModel =
        ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        View root2 = inflater.inflate(R.layout.activity_web_view2, container, false);
        String videoPath = "android.resource://" + getContext().getPackageName() + "/" + R.raw.videoplayback;
        Uri uri = Uri.parse(videoPath);
        mVideoView = root.findViewById(R.id.videoView);
        mVideoView.setVideoURI(uri);
        MediaController mediaCon = new MediaController(getContext());
        mVideoView.setMediaController(mediaCon);
        mediaCon.setAnchorView(mVideoView);
        //final TextView textView = root.findViewById(R.id.text_home);
        //homeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
        //@Override
        //public void onChanged(@Nullable String s) {
        //    textView.setText("BUt");
        //}
        //})
        web=root.findViewById(R.id.webview1);
        read=root.findViewById(R.id.readmore);
        share=root.findViewById(R.id.share);
        read.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(),WebView2.class);
                intent.putExtra("url","https://www.naturalbeachliving.com/reasons-why-reading-is-important/");
                startActivity(intent);
            }
        });
        share.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                shareTo("","https://www.naturalbeachliving.com/reasons-why-reading-is-important/","Share to");
            }
        });
        Button read1=root.findViewById(R.id.readmore1);
        read1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(),WebView2.class);
                intent.putExtra("url","https://www.brainpickings.org/2016/08/03/neil-gaiman-view-from-the-cheap-seats-reading/");
                startActivity(intent);
            }
        });
        Button share1=root.findViewById(R.id.share1);
        share1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shareTo("","https://www.brainpickings.org/2016/08/03/neil-gaiman-view-from-the-cheap-seats-reading/","Share to");
            }
        });
        Button read2=root.findViewById(R.id.readmore2);
        read2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(),WebView2.class);
                intent.putExtra("url","https://www.irishtimes.com/culture/books/why-do-we-read-1.2084524#:~:text=We%20read%20because%20we%20know,human%20desire%2C%20anguish%20and%20beauty");
                startActivity(intent);
            }
        });
        Button share2=root.findViewById(R.id.share2);
        share2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
        shareTo("","https://www.irishtimes.com/culture/books/why-do-we-read-1.2084524#:~:text=We%20read%20because%20we%20know,human%20desire%2C%20anguish%20and%20beauty","Share to");
    }
});
        return root;
        }
private void shareTo(String subject, String body, String chooserTitle) {

        Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
        sharingIntent.setType("text/plain");
        sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, subject);
        sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, body);

        startActivity(Intent.createChooser(sharingIntent, chooserTitle));
    }
}
