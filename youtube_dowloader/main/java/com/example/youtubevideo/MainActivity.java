package com.example.youtubevideo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.DownloadManager;
import android.content.Context;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.SparseArray;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import at.huber.youtubeExtractor.VideoMeta;
import at.huber.youtubeExtractor.YouTubeExtractor;
import at.huber.youtubeExtractor.YouTubeUriExtractor;
import at.huber.youtubeExtractor.YtFile;

public class MainActivity extends AppCompatActivity {
EditText editText;
Button btn;
final int req=22;
String ytlink="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText=findViewById(R.id.link1);
        btn=findViewById(R.id.download);
        permi();
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ytlink = editText.getText().toString();
                if (ytlink.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Please enter youtube video link", Toast.LENGTH_SHORT).show();
                } else {
                    if (!ytlink.isEmpty()) {
                        @SuppressLint("StaticFieldLeak") YouTubeUriExtractor youTubeUriExtractor=new YouTubeUriExtractor(MainActivity.this){

                            @Override
                            public void onUrisAvailable(String videoId, String videoTitle, SparseArray<YtFile> ytFiles) {
                                if(ytFiles!=null){

                                    Toast.makeText(MainActivity.this,"video tittle"+videoTitle,Toast.LENGTH_SHORT).show();
                                    int itag=22;
                                    String nlink=ytFiles.get(itag).getUrl();
                                    DownloadManager.Request request=new DownloadManager.Request(Uri.parse(nlink));
                                    request.setTitle(videoTitle);
                                    request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS,videoTitle+".mp4");
                                    DownloadManager downloadManager= (DownloadManager) getSystemService(Context.DOWNLOAD_SERVICE);
                                    request.allowScanningByMediaScanner();
                                    request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_MOBILE);
                                    downloadManager.enqueue(request);

                                }
                            }
                        };
                        youTubeUriExtractor.execute(ytlink);
                    }

                }
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

    }

    private void permi() {
        if(ContextCompat.checkSelfPermission(this,Manifest.permission.WRITE_EXTERNAL_STORAGE)!=PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},req);
        }else{

        }

    }
}