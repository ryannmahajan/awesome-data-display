package com.example.myawesomedatadisplay_er;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Item implements Comparable<Item>{
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

    @Override
    public int compareTo(Item item) {
        int relativeOrder = comparisonByListId(this, item);
        if (relativeOrder == 0) {
            return comparisonByName(this, item);
        }
        else return relativeOrder;
    }

    private static int comparisonByListId(Item item1, Item item2) {
        Integer l1 = item1.getListId();
        Integer l2 = item2.getListId();
        return l1.compareTo(l2);
    }

    private static int comparisonByName(Item item1, Item item2) {
        String l1 = item1.getName();
        String l2 = item2.getName();
        return l1.compareTo(l2);
    }
}
