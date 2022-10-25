package com.example.myawesomedatadisplay_er;

import static com.example.myawesomedatadisplay_er.Network.downloadFileIfNotPresent;
import static com.example.myawesomedatadisplay_er.Network.get_text_from_file;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.os.HandlerCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ItemRecyclerView {
    private static RecyclerView mRecyclerView;

    static void setup_recycler_view(Activity activity, View v) {
        mRecyclerView = v.findViewById(R.id.recycler_view);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(activity));
        download_and_setup_json(activity);
//        recyclerView.addItemDecoration(new
//                DividerItemDecoration(activity, DividerItemDecoration.VERTICAL));
    }

    @NonNull
    private static void download_and_setup_json(Activity activity) {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        Handler handler = HandlerCompat.createAsync(Looper.getMainLooper());

        executorService.execute(() -> {

            downloadFileIfNotPresent(activity);
            String text = get_text_from_file(activity);
            List<Object> itemsAndHeaders = getItemsAndHeaders(text);

            handler.post(() -> {
                updateRecyclerView(activity, itemsAndHeaders);
                activity.findViewById(R.id.progressBar).setVisibility(View.INVISIBLE);
            });
        });

    }

    private static void updateRecyclerView(Activity activity, List<Object> itemsAndHeaders) {
        mRecyclerView.setAdapter(new ItemAdapter(itemsAndHeaders, activity));
        mRecyclerView.setHasFixedSize(true);
    }

    @NonNull
    private static List<Object> getItemsAndHeaders(String json) {
        List<Item> items = JsonParser.getValidatedItemsFromJson(json);
        List<Object> itemsAndHeaders = Header.addHeadersToList(items);
        return itemsAndHeaders;
    }

    public static class ItemAdapter extends RecyclerView.Adapter<CustomHolder> {
        private static final int TYPE_HEADER = 0;
        private static final int TYPE_ITEM = 1;

        private final Activity mActivity;
        private final List<Object> mObjects;

        public ItemAdapter(List<Object> objects, Activity activity) {
            mObjects = objects;
            mActivity = activity;
        }

        @NonNull
        @Override
        public CustomHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            if (viewType == TYPE_ITEM) {
                return new ItemHolder(mActivity.getLayoutInflater().inflate(R.layout.item, parent, false));
            } else if (viewType == TYPE_HEADER) {
                return new TitleHolder(mActivity.getLayoutInflater().inflate(R.layout.header, parent, false));
            }
            throw new RuntimeException("there is no type that matches the type " + viewType + " + make sure your using types correctly");
        }

        @Override
        public int getItemViewType(int position) {
            if (mObjects.get(position) instanceof Header)
                return TYPE_HEADER;
            return TYPE_ITEM;
        }

        @Override
        public void onBindViewHolder(@NonNull CustomHolder holder, int position) {
            holder.bindHolder(mObjects.get(position));
        }

        @Override
        public int getItemCount() {
            return mObjects.size();
        }
    }

    private abstract static class CustomHolder<T> extends RecyclerView.ViewHolder {
        public CustomHolder(@NonNull View itemView) {
            super(itemView);
        }

        abstract void bindHolder(T t);
    }

    private static class ItemHolder extends CustomHolder<Item> {
        public TextView idTextView;
        public TextView nameTextView;

        public ItemHolder(View itemView) {
            super(itemView);

            idTextView = (TextView) itemView.findViewById(R.id.id);
            nameTextView = (TextView) itemView.findViewById(R.id.name);
        }

        public void bindHolder(Item item) {
            idTextView.setText(Integer.toString(item.getId()));
            nameTextView.setText(item.getName());
        }

    }

    private static class TitleHolder extends CustomHolder<Header> {
        private final TextView listIdTextView;

        public TitleHolder(View itemView) {
            super(itemView);
            listIdTextView = (TextView) itemView.findViewById(R.id.list_id);
        }

        public void bindHolder(Header header) {
            listIdTextView.setText(header.getText());
        }
    }
}
