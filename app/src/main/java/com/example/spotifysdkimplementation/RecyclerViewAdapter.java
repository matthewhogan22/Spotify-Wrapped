package com.example.spotifysdkimplementation;

import static androidx.core.content.ContextCompat.startActivity;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.spotifysdkimplementation.TopArtistPage;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
    private Context context;

    public static ArrayList<WrappedClass> getActualList() {
        return actualList;
    }

    private static ArrayList<WrappedClass> actualList;

    public RecyclerViewAdapter(Context context, ArrayList<WrappedClass> actualList) {
        this.context = context;
        this.actualList = PreviousWrappedPage.getMasterList();
        for (int i = 0; i < TopArtistPage.getPreviousTopArtists().size(); i++) {
            WrappedClass wrapped = new WrappedClass(TopArtistPage.getPreviousTopArtists().get(i), TopTrackPage.getPreviousTopTracks().get(i));
            actualList.add(wrapped);
        }

    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView topArtist;
        private TextView topTrack;

        public ViewHolder(View view) {
            super(view);
            topArtist = view.findViewById(R.id.topArtist);
            topTrack = view.findViewById(R.id.topTrack);

        }

        public TextView getTopArtist() {
            return topArtist;
        }

        public TextView getTopTrack() {
            return topTrack;
        }

    }

    // Create new views (invoked by the layout manager)
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        // Create a new view, which defines the UI of the list item
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.recycler_view_row_layout, viewGroup, false);

        return new ViewHolder(view);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element

        String artistText = actualList.get(position).getTopArtist();
        String trackText = actualList.get(position).getTopTrack();
        viewHolder.getTopArtist().setText(artistText);
        viewHolder.getTopTrack().setText(trackText);

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return actualList.size();
    }

}
