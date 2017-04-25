package com.wolaris.widget.searchview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "SearchViewActivity";

    protected RecyclerView mRecyclerView;
    protected CustomAdapter mAdapter;
    protected RecyclerView.LayoutManager mLayoutManager;
    private ArrayList<Singer> singerList;
    private ArrayList<Singer> arraylist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        singerList = new ArrayList<>();
        singerList.add(new Singer("Ed Sheeran"));
        singerList.add(new Singer("Bruno Mars"));
        singerList.add(new Singer("Alan Walker"));
        singerList.add(new Singer("G-Eazy"));
        singerList.add(new Singer("The Chainsmaoker"));
        singerList.add(new Singer("Clean Bandit"));
        singerList.add(new Singer("Martin Garrix"));
 
        arraylist = new ArrayList<>();
        arraylist.addAll(singerList);

        SearchView searchView = (SearchView) findViewById(R.id.searchView);
        searchView.setIconifiedByDefault(false);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                newText = newText.toLowerCase();
                mAdapter.clear();
                if (newText.length() == 0) {
                    mAdapter.addAll(arraylist);
                } else {
                    for (Singer singer : arraylist) {
                        if (singer.getName().toLowerCase().contains(newText)) {
                            mAdapter.add(singer);

                        }
                    }
                }
                Log.e(TAG, newText);
                return false;
            }
        });

        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);

        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.scrollToPosition(0);

        mAdapter = new CustomAdapter(singerList);
        mRecyclerView.setAdapter(mAdapter);
    }
}
