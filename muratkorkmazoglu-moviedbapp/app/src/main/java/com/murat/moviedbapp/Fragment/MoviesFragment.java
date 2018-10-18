package com.murat.moviedbapp.Fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.TintableImageSourceView;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.murat.moviedbapp.Adapter.GenresAdapter;
import com.murat.moviedbapp.Interface.ApiListener;
import com.murat.moviedbapp.Interface.RetroInterface;
import com.murat.moviedbapp.MainActivity;
import com.murat.moviedbapp.Models.DiscoverModel;
import com.murat.moviedbapp.Models.MovieInfoModel;
import com.murat.moviedbapp.Models.MovieModel;
import com.murat.moviedbapp.R;
import com.murat.moviedbapp.Utils.ApiName;
import com.murat.moviedbapp.Utils.ApiResponse;
import com.murat.moviedbapp.Utils.RetroClient;
import com.squareup.picasso.Picasso;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Currency;
import java.util.Locale;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;

import static java.text.NumberFormat.getCurrencyInstance;

/**
 * A simple {@link Fragment} subclass.
 */
public class MoviesFragment extends Fragment implements ApiListener {

    @BindView(R.id.poster_path)
    ImageView imageView;
    @BindView(R.id.rl)
    TextView releaseText;
    @BindView(R.id.overviewText2)
    TextView overviewText;
    @BindView(R.id.budgetMiktar)
    TextView budgetText;
    @BindView(R.id.revenueMiktar)
    TextView revenueText;
    @BindView(R.id.average)
    TextView avarageText;
    @BindView(R.id.recycler_view_genres)
    RecyclerView recyclerView;

    private RetroInterface retroInterface;
    private MovieModel movieModel;
    private ArrayList<MovieModel.Genre> modelList;
    private GenresAdapter genresAdapter;


    public MoviesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_movies, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        retroInterface = RetroClient.getClient().create(RetroInterface.class);
        modelList = new ArrayList<>();

        Bundle bundle = this.getArguments();
        if (bundle != null) {
            MovieInfoModel movieInfoModel= (MovieInfoModel) bundle.getSerializable("movieInfo");

            ((MainActivity) Objects.requireNonNull(getActivity())).setActionBarTitle(movieInfoModel.getTitle());
            releaseText.setText(movieInfoModel.getDate());
            overviewText.setText(movieInfoModel.getOverview());
            Picasso.get().load(getString(R.string.imagePath) + movieInfoModel.getImage()).into(imageView);

            Call<MovieModel> call = retroInterface.getMovie(movieInfoModel.getMovieId(), getString(R.string.apiKey));
            ApiResponse.callRetrofit(call, ApiName.getMovieCall, getContext(), this);

        }
    }

    @Override
    public void success(ApiName strApiName, Object response) {
        if (ApiName.getMovieCall.equals(strApiName)) {
            movieModel = (MovieModel) response;
            NumberFormat format = getCurrencyInstance(Locale.getDefault());

            budgetText.setText(format.format(movieModel.getBudget()));
            revenueText.setText(format.format(movieModel.getRevenue()));
            avarageText.setText("%" + movieModel.getVoteAverage().toString());

            for (int i = 0; i < movieModel.getGenres().size(); i++) {
                modelList.add(movieModel.getGenres().get(i));
            }

            genresAdapter = new GenresAdapter(getContext(), modelList);
            RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
            recyclerView.setLayoutManager(mLayoutManager);
            recyclerView.setItemAnimator(new DefaultItemAnimator());
            LinearLayoutManager horizontalLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
            recyclerView.setLayoutManager(horizontalLayoutManager);
            recyclerView.setAdapter(genresAdapter);

        }
    }

    @Override
    public void error(ApiName strApiName, String error) {

    }

    @Override
    public void failure(ApiName strApiName, String message) {

    }



}
