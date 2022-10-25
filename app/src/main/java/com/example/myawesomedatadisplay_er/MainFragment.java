package com.example.myawesomedatadisplay_er;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class MainFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_list, container, false);
        setup_recycler_view(v);
        return v;
    }

    private void setup_recycler_view(View v) {
        RecyclerView recyclerView = v.findViewById(R.id.recycler_view);

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        String json = "[\n" +
                "{\"id\": 755, \"listId\": 2, \"name\": \"\"},\n" +
                "{\"id\": 203, \"listId\": 2, \"name\": \"\"},\n" +
                "{\"id\": 684, \"listId\": 1, \"name\": \"Item 684\"},\n" +
                "{\"id\": 276, \"listId\": 1, \"name\": \"Item 276\"},\n" +
                "{\"id\": 736, \"listId\": 3, \"name\": null}]";
        recyclerView.setAdapter(new ItemAdapter(DataParser.generateItemsFromJson(json)));
        RecyclerView.ItemDecoration itemDecoration = new
                DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(itemDecoration);
        recyclerView.setHasFixedSize(true);
    }

    private List<Item> generateItems(int count, int listCount) {
        List<Item> items = new ArrayList<>(count);

        while (count!=0) {
            int listId = ThreadLocalRandom.current().nextInt(1, listCount + 1);
            items.add(new Item(count, listId, "name"));
            count--;
        }
        return items;
    }

    // todo: save and restore things: scroll state

    public static class Item {
        private int mId;
        private int mListId;
        private String mName;

        public Item(int id, int listId, String name) {
            mId = id;
            mListId = listId;
            mName = name;
        }

        public int getId() {
            return mId;
        }

        public int getListId() {
            return mListId;
        }

        public String getName() {
            return mName;
        }
    }

    public class ItemAdapter extends RecyclerView.Adapter<ItemHolder> {
        private List<Item> mItems;

        public ItemAdapter(List<Item> items) {
            mItems = items;
        }

        @NonNull
        @Override
        public ItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new ItemHolder(getActivity().getLayoutInflater().inflate(R.layout.songle_list_item, parent, false));
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

    private class ItemHolder extends RecyclerView.ViewHolder {
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
