package com.example.aliab.moviesdb;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.aliab.moviesdb.Json.Movie;
import com.example.aliab.moviesdb.databinding.ActivityMainBinding;
import com.mikepenz.materialdrawer.AccountHeader;
import com.mikepenz.materialdrawer.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.ProfileDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IProfile;

import java.util.List;


public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<Movie>> {
    ListView mainListView;
    MovieArrayAdapter adapter;
    ProgressBar progressBar;
    TextView textView;
    TextView internet;
    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= DataBindingUtil.setContentView(this,R.layout.activity_main);
//        setSupportActionBar(binding.toolbar);
        IProfile ali = new ProfileDrawerItem().withEmail("aliabdolkarim98@gmail.com").withName("ali").withIcon(R.drawable.ic_launcher_background);
        IProfile avli = new ProfileDrawerItem().withEmail("aliabdolkarim98@gmadmjdslkkfil.com").withName("avli").withIcon(R.drawable.material_drawer_circle_mask);

        AccountHeader header = new AccountHeaderBuilder().withActivity(this).withHeaderBackground(R.drawable.nature)
                .addProfiles(ali, avli).build();

        new DrawerBuilder().withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
            @Override
            public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {

                return false;
            }
        }).addDrawerItems(
                new PrimaryDrawerItem().withName("hello Avraz").withIcon(R.drawable.ic_launcher_background).withSelectable(false).withIdentifier(1),
                new PrimaryDrawerItem().withName("hello Avraz").withIcon(R.drawable.ic_launcher_background).withSelectable(false).withIdentifier(2
                )
        ).withActivity(this).withAccountHeader(header).build();


        mainListView = findViewById(R.id.main_list_view);
        mainListView.setDivider(null);
        progressBar = findViewById(R.id.progressbar);
        textView = findViewById(R.id.text_tv);
        internet = findViewById(R.id.internet_iv);
        if (isConnected()) {
            getSupportLoaderManager().initLoader(0, null, MainActivity.this).forceLoad();
        } else {
            internet.setVisibility(View.VISIBLE);
            progressBar.setVisibility(View.GONE);
        }
    }


    @NonNull
    @Override
    public Loader<List<Movie>> onCreateLoader(int i, @Nullable Bundle bundle) {
        Log.e("Main", "onCreateLoader: ");
        return new MovieAsyncTask(this);
    }

    @Override
    public void onLoadFinished(@NonNull Loader<List<Movie>> loader, List<Movie> movies) {
        progressBar.setVisibility(View.GONE);
        Log.e("main", "onLoadFinished: ");
        adapter = new MovieArrayAdapter(MainActivity.this, R.layout.list_item, movies);
        mainListView.setAdapter(adapter);
        mainListView.setEmptyView(textView);
    }

    @Override
    public void onLoaderReset(@NonNull Loader<List<Movie>> loader) {
        Log.e("main", "onLoaderReset: ");
        loader.forceLoad();
    }

    private boolean isConnected() {
        ConnectivityManager manager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = null;
        if (manager != null) {
            info = manager.getActiveNetworkInfo();
        }
        return info != null && info.isConnectedOrConnecting();
    }
}
