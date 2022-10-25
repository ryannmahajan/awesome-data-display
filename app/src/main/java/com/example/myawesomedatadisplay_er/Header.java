package com.example.myawesomedatadisplay_er;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Header {
    private final String mText;

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Header)) return false;
        Header header = (Header) o;
        return mText.equals(header.mText);
    }

    @Override
    public int hashCode() {
        return Objects.hash(mText);
    }

    public Header(String text) {
        this.mText = "Group "+text;
    }

    public static List<Object> addHeadersToList(List<Item> items) {
        int MAX_VALID_INDEX = items.size()-1;

        List<Object> clone = new ArrayList<>(items);
        for (int i=MAX_VALID_INDEX; i>=0; i--) {
            if (i+1>MAX_VALID_INDEX) continue;
            if (isListIdChanged(items, i, i+1)) {
                insertHeaderAt(i + 1, clone);
            }
        }

        if (!clone.isEmpty()) insertHeaderAt(0, clone);
        return clone;
    }

    private static void insertHeaderAt(int insertAt, List<Object> listToInsertIn) {
        Header header = createHeaderFrom((Item) listToInsertIn.get(insertAt));
        listToInsertIn.add(insertAt, header);
    }

    private static boolean isListIdChanged(List<Item> items, int i, int j) {
        return items.get(i).getListId()!=items.get(j).getListId();
    }

    private static Header createHeaderFrom(Item item) {
        return new Header(Integer.toString(item.getListId()));
    }

    public String getText() {
        return mText;
    }
}
