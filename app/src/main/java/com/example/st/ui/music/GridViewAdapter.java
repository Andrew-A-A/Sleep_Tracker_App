package com.example.st.ui.music;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import android.widget.ImageView;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.st.R;

import java.util.ArrayList;

public class GridViewAdapter extends ArrayAdapter<MusicViewModel>{
    View listitemView;
    public GridViewAdapter(@NonNull Context context, ArrayList<MusicViewModel> dataArrayList) {
        super(context, 0,dataArrayList);
    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        listitemView = convertView;
        MusicViewModel Model = getItem(position);

        if (listitemView == null) {
            // Layout Inflater inflates each item to be displayed in GridView.
            listitemView = LayoutInflater.from(getContext()).inflate(R.layout.card_item, parent, false);
            TextView play_pause=(TextView) listitemView.findViewById(R.id.play_pause);
            listitemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (!Model.mediaPlayer.isPlaying()) {
                        Model.mediaPlayer.start();
                        Model.mediaPlayer.setLooping(true);
                        play_pause.setText("■");
                    }
                    else{
                        Model.mediaPlayer.pause();
                        play_pause.setText("▶");
                    }
                }
            });
        }

        TextView noiseName = listitemView.findViewById(R.id.noiseName);

        ImageView noiseIc = listitemView.findViewById(R.id.ic);
        noiseName.setText(Model.noiseName);
        noiseIc.setImageResource(Model.imgid);
        return listitemView;
    }
}
