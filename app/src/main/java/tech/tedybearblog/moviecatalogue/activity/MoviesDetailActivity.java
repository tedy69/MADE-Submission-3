package tech.tedybearblog.moviecatalogue.activity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import tech.tedybearblog.moviecatalogue.R;
import tech.tedybearblog.moviecatalogue.model.Movies;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

public class MoviesDetailActivity extends AppCompatActivity {
    public static final String EXTRA_MOVIE = "extra_movie";

    TextView tvTitle, tvVoteAverage, tvVoteCount, tvLanguage, tvOverview;

    private ProgressBar progressBar;
    ImageView imagePhoto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movies_detail);

        tvTitle = findViewById(R.id.tv_item_title);
        tvVoteAverage = findViewById(R.id.tv_item_voteAverege);
        tvVoteCount = findViewById(R.id.tv_item_voteCount);
        tvOverview = findViewById(R.id.tv_item_overview);
        tvLanguage = findViewById(R.id.tv_item_language);
        imagePhoto = findViewById(R.id.img_item_photo);

        progressBar = findViewById(R.id.progressDetailMovie);

        progressBar.setVisibility(View.VISIBLE);
        final Handler handler = new Handler();

        new Thread(new Runnable() {
            public void run() {
                try{
                    Thread.sleep(5000);
                }
                catch (Exception e) { }

                handler.post(new Runnable() {
                    public void run() {
                        Movies movie = getIntent().getParcelableExtra(EXTRA_MOVIE);

                        String vote_average = Double.toString(movie.getVote_average());
                        String url_image = "https://image.tmdb.org/t/p/w185" + movie.getPhoto();

                        tvTitle.setText(movie.getTitle());
                        tvVoteAverage.setText(vote_average);
                        tvVoteCount.setText(movie.getVote_count());
                        tvOverview.setText(movie.getOverview());
                        tvLanguage.setText(movie.getOriginal_language()) ;
                        Glide.with(MoviesDetailActivity.this)
                                .load(url_image)
                                .apply(new RequestOptions().placeholder(R.drawable.placeholder))
                                .into(imagePhoto);
                        progressBar.setVisibility(View.INVISIBLE);
                    }
                });
            }
        }).start();
    }

}