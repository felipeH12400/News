package cl.ucn.disc.dsm.fherrera.news;

import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

import cl.ucn.disc.dsm.fherrera.news.model.News;
import cl.ucn.disc.dsm.fherrera.news.services.Contracts;
import cl.ucn.disc.dsm.fherrera.news.services.ContractsImplNewsApi;

public class MainActivity extends AppCompatActivity {

    /**
     * logger
     */
    private static final Logger log = LoggerFactory.getLogger(MainActivity.class);

    protected ListView listview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        log.debug(" onCreate ");
        setContentView(R.layout.activity_main);

        this.listview = findViewById(R.id.am_lv_news);

        this.listview.setOnItemClickListener((parent, view, position, id) -> {
            log.debug(" position {}, id: {}.",position,id);
        });

        //Backthread
        AsyncTask.execute(()->{
            //Get news
            Contracts contracts = new ContractsImplNewsApi("fcd01c172bf24e10a7c50dc5f65ffdcd");

            //getNews
            List<News> news = contracts.retrieveNews(20);

            //simple adapter
            ArrayAdapter<String> adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, news);

            //unithread update screen
            runOnUiThread(() ->{
            this.listview.setAdapter(adapter);
            });

        });

    }
}