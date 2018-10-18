package com.murat.moviedbapp.Fragment;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.murat.moviedbapp.Adapter.DiscoverAdapter;
import com.murat.moviedbapp.Adapter.GridViewAdapter;
import com.murat.moviedbapp.Interface.ApiListener;
import com.murat.moviedbapp.Interface.RetroInterface;
import com.murat.moviedbapp.MainActivity;
import com.murat.moviedbapp.Models.DiscoverModel;
import com.murat.moviedbapp.R;
import com.murat.moviedbapp.Utils.ApiName;
import com.murat.moviedbapp.Utils.ApiResponse;
import com.murat.moviedbapp.Utils.RetroClient;

import java.io.Serializable;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Optional;
import retrofit2.Call;

import static android.content.Context.MODE_PRIVATE;


public class DiscoverFragment extends Fragment implements ApiListener {

    private RetroInterface retroInterface;
    private DiscoverModel discoverModel;
    private ArrayList<DiscoverModel.Result> modelList;
    private DiscoverAdapter discoverAdapter;
    private int page = 1;

    //---------For GridView Layout------------
    private ViewStub stubGrid;
    private ViewStub stubList;
    private GridView gridView;
    private ListView listView;
    private GridViewAdapter gridViewAdapter;
    static final int VIEW_MODE_LISTVIEW = 0;
    static final int VIEW_MODE_GRIDVIEW = 1;
    private int currentViewMode = 0;
    //---------For GridView Layout------------


    public DiscoverFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_discover, container, false);
        ButterKnife.bind(this, view);

        ((MainActivity) getActivity()).setActionBarTitle(getString(R.string.actionbar_title));


        return view;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        retroInterface = RetroClient.getClient().create(RetroInterface.class);
        modelList = new ArrayList<>();
        Call<DiscoverModel> call = retroInterface.getDiscover(getString(R.string.apiKey), getString(R.string.sortBy), page);
        ApiResponse.callRetrofit(call, ApiName.getDiscoverCall, getContext(), this);

    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.option_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        stubList = (ViewStub) getActivity().findViewById(R.id.stub_list);
        stubGrid = (ViewStub) getActivity().findViewById(R.id.stub_grid);
        stubList.inflate();
        stubGrid.inflate();
        listView = (ListView) getActivity().findViewById(R.id.mylistview);
        gridView = (GridView) getActivity().findViewById(R.id.mygridView);

    }

    @Override
    public void onResume() {
        super.onResume();
        switchView();

        listView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                if (scrollState == SCROLL_STATE_IDLE && listView.getLastVisiblePosition() == modelList.size() - 1) {
                    loadMore();
                }
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

            }
        });
        gridView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                if (scrollState == SCROLL_STATE_IDLE && gridView.getLastVisiblePosition() == modelList.size() - 1) {

                    loadMore();


                }
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

            }
        });

    }

    private void loadMore() {
        page++;
        if (page <= 5) {
            Call<DiscoverModel> call = retroInterface.getDiscover(getString(R.string.apiKey), getString(R.string.sortBy), page);
            ApiResponse.callRetrofit(call, ApiName.getDiscoverCall, getContext(), DiscoverFragment.this);
        }
    }

    @Override
    public void success(final ApiName strApiName, Object response) {
        if (ApiName.getDiscoverCall == strApiName) {
            discoverModel = (DiscoverModel) response;

            modelList.addAll(discoverModel.getResults());
            setAdapters();
            Log.e("RESPONSE----", " " + modelList.size());
        }

    }

    @Override
    public void error(ApiName strApiName, String error) {

    }

    @Override
    public void failure(ApiName strApiName, String message) {

    }


    //---------For GridView Layout------------
    private void switchView() {


        if (VIEW_MODE_LISTVIEW == currentViewMode) {
            //Display listview
            stubList.setVisibility(View.VISIBLE);
            //Hide gridview
            stubGrid.setVisibility(View.GONE);

        } else if (VIEW_MODE_GRIDVIEW == currentViewMode) {
            //Hide listview
            stubList.setVisibility(View.GONE);
            //Display gridview
            stubGrid.setVisibility(View.VISIBLE);

        }
        setAdapters();

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.one_row:
                if (VIEW_MODE_LISTVIEW == currentViewMode) {
                    currentViewMode = VIEW_MODE_GRIDVIEW;
                    item.setIcon(ContextCompat.getDrawable(getContext(), R.mipmap.ic_one_row));
                } else {
                    currentViewMode = VIEW_MODE_LISTVIEW;
                    item.setIcon(ContextCompat.getDrawable(getContext(), R.mipmap.grid));

                }

                switchView();

                break;
        }
        return true;
    }


    private void setAdapters() {
        if (VIEW_MODE_LISTVIEW == currentViewMode) {

            if (listView.getAdapter() != null) {
                discoverAdapter = (DiscoverAdapter) listView.getAdapter();

            } else {
                discoverAdapter = new DiscoverAdapter(getActivity(), modelList);
                listView.setAdapter(discoverAdapter);
            }

            discoverAdapter.notifyDataSetChanged();

        } else if (VIEW_MODE_GRIDVIEW == currentViewMode) {

            if (gridView.getAdapter() != null) {

                gridViewAdapter = (GridViewAdapter) gridView.getAdapter();

            } else {
                gridViewAdapter = new GridViewAdapter(getContext(), modelList);
                gridView.setAdapter(gridViewAdapter);

            }
            gridViewAdapter.notifyDataSetChanged();

        }
//---------For GridView Layout------------
    }
}
