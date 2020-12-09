package cl.ucn.disc.dsm.fherrera.news;

import android.os.AsyncTask;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.mikepenz.fastadapter.BuildConfig;
import com.mikepenz.fastadapter.FastAdapter;
import com.mikepenz.fastadapter.adapters.ModelAdapter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

import cl.ucn.disc.dsm.fherrera.news.model.News;
import cl.ucn.disc.dsm.fherrera.news.services.Contracts;
import cl.ucn.disc.dsm.fherrera.news.services.ContractsImplNewsApi;
import cl.ucn.disc.dsm.fherrera.news.services.NewsItems;


public class MainActivity extends AppCompatActivity {

    private static final Logger log = LoggerFactory.getLogger(MainActivity.class);

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //fast adapter
        ModelAdapter<News, NewsItems> newsAdapter = new ModelAdapter<>(NewsItems::new);
        FastAdapter<NewsItems> fastAdapter = FastAdapter.with(newsAdapter);
        fastAdapter.withSelectable(false);

        //recyclerview
        RecyclerView recyclerView = findViewById(R.id.am_rv_news);
        recyclerView.setAdapter(fastAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));

        AsyncTask.execute(() -> {
            Contracts contracts = new ContractsImplNewsApi(BuildConfig.APPLICATION_ID);
            List<News> listNews = contracts.retrieveNews(30 );
            runOnUiThread(() -> {
                newsAdapter.add(listNews);
            });
        });

    }
}











    }

























}