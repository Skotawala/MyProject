package myprojecttest.myproject;

import android.media.AudioFormat;
import android.media.AudioRecord;
import android.media.MediaRecorder;
import android.util.Log;

/**
 * Created by Siddharth on 3/2/2015.
 */
public class recorderThread extends Thread {
    public AudioRecord recorder;
    int bufferSize;

    public boolean recording;  //variable to start or stop recording
    public int frequency; //the public variable that contains the frequency value "heard", it is updated continually while the thread is running.
    short[] sample;
    public recorderThread() {
    }



    public void start() {
        // AudioRecord recorder;
        // int numCrossing, p;
        //  short audioData[];
        //int bufferSize;

        bufferSize = AudioRecord.getMinBufferSize(8000, AudioFormat.CHANNEL_IN_MONO,
                AudioFormat.ENCODING_PCM_16BIT) * 3; //get the buffer size to use with this audio record

        recorder = new AudioRecord(MediaRecorder.AudioSource.MIC, 8000, AudioFormat.CHANNEL_IN_MONO,
                AudioFormat.ENCODING_PCM_16BIT, bufferSize); //instantiate the AudioRecorder

        recording = true; //variable to use start or stop recording


        while (recording) {  //loop while recording is needed
            if (recorder.getState() == android.media.AudioRecord.STATE_INITIALIZED) {// check to see if the recorder has initialized yet.
                if (recorder.getRecordingState() == android.media.AudioRecord.RECORDSTATE_STOPPED)
                    recorder.startRecording();  //check to see if the Recorder has stopped or is not recording, and make it record.
                //int numCrossing, p;
                //int bufferSize;
            }
            short audioData[];
            audioData = new short[bufferSize]; //short array that pcm data is put into.
            sample = audioData;

               // else{


                recorder.read(audioData, 0, bufferSize); //read the PCM audio data into the audioData array

            }
        }//Now we need to decode the PCM data using the Zero Crossings Method

    //}
    public int frequency() {
        int numCrossing, p;

        numCrossing = 0; //initialize your number of zero crossings to 0
        for (p = 0; p < bufferSize / 4; p += 4) {
            if (sample[p] > 0 && sample[p + 1] <= 0) numCrossing++;
            if (sample[p] < 0 && sample[p + 1] >= 0) numCrossing++;
            if (sample[p + 1] > 0 && sample[p + 2] <= 0) numCrossing++;
            if (sample[p + 1] < 0 && sample[p + 2] >= 0) numCrossing++;
            if (sample[p + 2] > 0 && sample[p + 3] <= 0) numCrossing++;
            if (sample[p + 2] < 0 && sample[p + 3] >= 0) numCrossing++;
            if (sample[p + 3] > 0 && sample[p + 4] <= 0) numCrossing++;
            if (sample[p + 3] < 0 && sample[p + 4] >= 0) numCrossing++;
        }//for p

        for (p = (bufferSize / 4) * 4; p < bufferSize - 1; p++) {
            if (sample[p] > 0 && sample[p + 1] <= 0) numCrossing++;
            if (sample[p] < 0 && sample[p + 1] >= 0) numCrossing++;
        }


                 return    frequency = (8000 / bufferSize) * (numCrossing / 2);  // Set the audio Frequency to half the number of zero crossings, times the number of samples our buffersize is per second.

                }
                   // f.setText("Temp :" + String.valueOf(frequency)+(char)0x00B0);

                //else recorder started

         //while recording

public void halt(){
        if (recorder.getState() == android.media.AudioRecord.RECORDSTATE_RECORDING)
            recorder.stop(); //stop the recorder before ending the thread
        recorder.release(); //release the recorders resources
        recorder = null; //set the recorder to be garbage collected.

    }//run


}

//recorderThread


