package com.example.myawesomedatadisplay_er;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Item {
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

    public static List<Item> generateRandomItems(int count, int listCount) {
        List<Item> items = new ArrayList<>(count);

        while (count!=0) {
            int listId = ThreadLocalRandom.current().nextInt(1, listCount + 1);
            items.add(new Item(count, listId, "name"));
            count--;
        }
        return items;
    }
}
