package cl.ucn.disc.dsm.fherrera.news.services;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mikepenz.fastadapter.items.ModelAbstractItem;

import org.threeten.bp.format.DateTimeFormatter;

import java.util.List;

import cl.ucn.disc.dsm.fherrera.news.R;
import cl.ucn.disc.dsm.fherrera.news.model.News;

public final class NewsItems extends ModelAbstractItem<News, NewsItems, NewsItems.ViewHolder> {

    private static final DateTimeFormatter FORMATTER =
            DateTimeFormatter.ofPattern("HH:mm d.LLL.yyyy");

    public NewsItems(@NonNull News news) {
        super(news);
    }
    @NonNull
    @Override
    public ViewHolder getViewHolder(@NonNull View view) {
        return new ViewHolder(view);
    }

    @Override
    public int getType() {
        return R.id.am_rv_news;
    }

    @Override
    public int getLayoutRes() {
        return R.layout.item_news;
    }

    @Override
    public void bindView(@NonNull ViewHolder holder, @NonNull List<Object> payloads) {
        super.bindView(holder, payloads);
        holder.title.setText(getModel().getTitle());
        holder.author.setText(getModel().getAuthor());
        holder.source.setText(getModel().getSource());
        holder.description.setText(getModel().getDescription());
        holder.publishedAt.setText(FORMATTER.format(getModel().getPublishedAt()));
    }

    @Override
    public void unbindView(@NonNull ViewHolder holder) {
        super.unbindView(holder);
        holder.title.setText(null);
        holder.author.setText(null);
        holder.source.setText(null);
        holder.description.setText(null);
        holder.publishedAt.setText(null);

    }

    protected static class ViewHolder extends RecyclerView.ViewHolder {
        protected TextView title;
        protected TextView author;
        protected TextView source;
        protected TextView description;
        protected TextView publishedAt;

        public ViewHolder(@NonNull View view) {
            super(view);
            this.title = view.findViewById(R.id.in_tv_title);
            this.author = view.findViewById(R.id.in_tv_autor);
            this.source = view.findViewById(R.id.in_tv_source);
            this.description = view.findViewById(R.id.in_tv_description);
            this.publishedAt = view.findViewById(R.id.in_tv_published_at);
        }
    }

}

