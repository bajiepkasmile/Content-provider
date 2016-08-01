package com.nodomain.viewer.ui.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.nodomain.viewer.R;
import com.nodomain.viewer.model.Artist;
import com.nodomain.viewer.ui.interfaces.OnItemClickListener;
import com.nodomain.viewer.utils.Converter;

import java.util.List;

public class ArtistsListAdapter extends RecyclerView.Adapter<ArtistsListAdapter.ArtistViewHolder> {

    private List<Artist> artists;
    private OnItemClickListener onItemClickListener;

    public ArtistsListAdapter(List<Artist> artists, OnItemClickListener onItemClickListener) {
        this.artists = artists;
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public ArtistViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_artists_list, parent, false);
        return new ArtistViewHolder(view, onItemClickListener);
    }

    @Override
    public void onBindViewHolder(ArtistViewHolder holder, int position) {
        Artist artist = artists.get(position);

        holder.tvName.setText(artist.getName());
        if (artist.getGenres().length == 0) {
            holder.tvGenres.setVisibility(View.GONE);
        } else {
            holder.tvGenres.setText(Converter.genresToString(artist.getGenres()));
        }
        holder.tvStatistics.setText(
                Converter.albumsToString(artist.getAlbum()) +
                ", " +
                Converter.tracksToString(artist.getTracks()));
    }

    @Override
    public int getItemCount() {
        return artists.size();
    }

    public static class ArtistViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ImageView ivCover;
        TextView tvName;
        TextView tvGenres;
        TextView tvStatistics;

        private OnItemClickListener listener;

        public ArtistViewHolder(View itemView, OnItemClickListener listener) {
            super(itemView);

            ivCover = (ImageView) itemView.findViewById(R.id.iv_cover);
            tvName = (TextView) itemView.findViewById(R.id.tv_name);
            tvGenres = (TextView) itemView.findViewById(R.id.tv_genres);
            tvStatistics = (TextView) itemView.findViewById(R.id.tv_statistics);

            this.listener = listener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            listener.onItemClick(getAdapterPosition());
        }

    }

}
