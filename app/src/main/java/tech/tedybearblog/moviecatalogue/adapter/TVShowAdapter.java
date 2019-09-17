package tech.tedybearblog.moviecatalogue.adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import tech.tedybearblog.moviecatalogue.R;
import tech.tedybearblog.moviecatalogue.activity.TVShowDetailActivity;
import tech.tedybearblog.moviecatalogue.model.TVShow;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;

public class TVShowAdapter extends RecyclerView.Adapter<TVShowAdapter.TVShowViewHolder> {

    private ArrayList<TVShow> tvData = new ArrayList<>();

    public void setTvData(ArrayList<TVShow> items) {
        tvData.clear();
        tvData.addAll(items);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public TVShowViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int position) {
        View tvView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_cardview_tv_show, viewGroup, false);
        return new TVShowViewHolder(tvView);
    }

    @Override
    public void onBindViewHolder(@NonNull TVShowViewHolder tvShowViewHolder, int position) {
        tvShowViewHolder.bind(tvData.get(position));
    }

    @Override
    public int getItemCount() {
        return tvData.size();
    }

    class TVShowViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        ImageView imgPoster;
        TextView textViewName, textViewAirDate,
                textViewVoteAverage,textViewOverview,
                textViewVoteCount;

        TVShowViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewName = itemView.findViewById(R.id.tv_item_name);
            textViewAirDate = itemView.findViewById(R.id.tv_item_air_date);
            textViewOverview = itemView.findViewById(R.id.tv_item_overview);
            textViewVoteAverage = itemView.findViewById(R.id.tv_item_vote_average);
            textViewVoteCount = itemView.findViewById(R.id.tv_item_vote_count);
            imgPoster = itemView.findViewById(R.id.img_item_photo);

            itemView.setOnClickListener(this);
        }

        void bind(TVShow tvShow) {
            String vote_average = Double.toString(tvShow.getVote_average());
            String url_image = "https://image.tmdb.org/t/p/w185" + tvShow.getPoster_path();


            textViewName.setText(tvShow.getName());
            textViewAirDate.setText(tvShow.getFirst_air_date());
            textViewOverview.setText(tvShow.getOverview());
            textViewVoteAverage.setText(vote_average);
            textViewVoteCount.setText(tvShow.getVote_count());

            Glide.with(itemView.getContext())
                    .load(url_image)
                    .apply(new RequestOptions().placeholder(R.drawable.placeholder))
                    .into(imgPoster);
        }


        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            TVShow tvShow = tvData.get(position);
//
            tvShow.setName(tvShow.getName());
            tvShow.setOverview(tvShow.getOverview());

            Intent moveWithObjectIntent = new Intent(itemView.getContext(), TVShowDetailActivity.class);
            moveWithObjectIntent.putExtra(TVShowDetailActivity.EXTRA_TV_SHOW, tvShow);
            itemView.getContext().startActivity(moveWithObjectIntent);

        }
    }
}
