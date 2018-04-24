package com.boonsiri.projects;

import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private Button play , stop , record;
    private String outputFile;
    private MediaRecorder myAudioRecorder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        play =(Button) findViewById(R.id.play);
        stop =(Button) findViewById(R.id.stop);
        record =(Button) findViewById(R.id.record);

        stop.setEnabled(false);
        play.setEnabled(false);

        outputFile = Environment.getExternalStorageDirectory().getAbsolutePath() + "/recording.3gp";
        myAudioRecorder = new MediaRecorder();
        myAudioRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        myAudioRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        myAudioRecorder.setAudioEncoder(MediaRecorder.OutputFormat.AMR_NB);
        myAudioRecorder.setOutputFile(outputFile);

        record.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    myAudioRecorder.prepare();
                    myAudioRecorder.start();
                }catch (IllegalStateException ise){
                    //make something ...
                }catch (IOException ioe){
                    //make something ...
                }

                record.setEnabled(false);
                stop.setEnabled(false);

                Toast.makeText(getApplicationContext(),"Recording Started",Toast.LENGTH_LONG).show();
            }
        });

        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myAudioRecorder.stop();
                myAudioRecorder.release();
                myAudioRecorder = null;
                stop.setEnabled(false);
                play.setEnabled(true);
                Toast.makeText(getApplicationContext(),"Audio Recorder successfully",Toast.LENGTH_LONG).show();

            }
        });

        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MediaPlayer mediaPlayer = new MediaPlayer();

                try{
                    mediaPlayer.setDataSource(outputFile);
                    mediaPlayer.prepare();
                    mediaPlayer.start();

                    Toast.makeText(getApplicationContext(),"Playing Audio",Toast.LENGTH_LONG).show();
                } catch (Exception e){
                    //make somthing ...
                }
            }
        });
    }
}
