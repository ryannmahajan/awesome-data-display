package com.example.myawesomedatadisplay_er;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ItemRecyclerView {
    static void setup_recycler_view(Activity activity, View v) {
        RecyclerView recyclerView = v.findViewById(R.id.recycler_view);

        recyclerView.setLayoutManager(new LinearLayoutManager(activity));
        recyclerView.setAdapter(new ItemAdapter(JsonParser.getValidatedItemsFromJson(), activity));
        recyclerView.addItemDecoration(new
                DividerItemDecoration(activity, DividerItemDecoration.VERTICAL));
        recyclerView.setHasFixedSize(true);
    }

    public static class ItemAdapter extends RecyclerView.Adapter<ItemHolder> {
        private final Activity mActivity;
        private List<Item> mItems;

        public ItemAdapter(List<Item> items, Activity activity) {
            mItems = items;
            mActivity = activity;
        }

        @NonNull
        @Override
        public ItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new ItemHolder(mActivity.getLayoutInflater().inflate(R.layout.songle_list_item, parent, false));
        }

        @Override
        public void onBindViewHolder(@NonNull ItemHolder holder, int position) {
            holder.bindHolder(mItems.get(position));
        }

        @Override
        public int getItemCount() {
            return mItems.size();
        }
    }

    private static class ItemHolder extends RecyclerView.ViewHolder {
        // Your holder should contain a member variable
        // for any view that will be set as you render a row
        public TextView idTextView;
        public TextView nameTextView;


        public ItemHolder(View itemView) {
            super(itemView);

            idTextView = (TextView) itemView.findViewById(R.id.mtrl_list_item_secondary_text);
            nameTextView = (TextView) itemView.findViewById(R.id.mtrl_list_item_text);
        }

        public void bindHolder(Item item) {
            idTextView.setText(Integer.toString(item.getId()));
            nameTextView.setText(item.getName());

        }
    }
}
