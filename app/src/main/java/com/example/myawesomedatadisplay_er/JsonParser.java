package com.example.myawesomedatadisplay_er;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class JsonParser {

    public static List<Item> getValidatedItemsFromJson() {
        String json = "[\n" +
                "{\"id\": 755, \"listId\": 2, \"name\": \"\"},\n" +
                "{\"id\": 684, \"listId\": 1, \"name\": \"Item 684\"},\n" +
                "{\"id\": 276, \"listId\": 1, \"name\": \"Item 276\"},\n" +
                "{\"id\": 345, \"listId\": 3, \"name\": \"Item 345\"}]";
        List<Item> items = parseItemsFromJson(json);
        removeInvalidNamedItems(items);
        sortItems(items);
        return items;
    }

    public static List<Item> parseItemsFromJson(String json) {
        List<Item> items = new ArrayList<>();

        try {
            JSONArray jsonItems = new JSONArray(json);
            for (int i = 0; i < jsonItems.length(); i++) {
                JSONObject jsonItem = jsonItems.getJSONObject(i);
                Item item = parseSingleItemFromJson(jsonItem);

                items.add(item);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return items;
    }

    private static Item parseSingleItemFromJson(JSONObject c) throws JSONException {
        int id = c.getInt("id");
        int listId = c.getInt("listId");
        String name = c.getString("name");

        return new Item(id, listId, name);
    }

    public static void removeInvalidNamedItems(List<Item> items) {
        items.removeIf(item -> (item.getName() == null || item.getName().isEmpty()));
    }

    public static void sortItems(List<Item> items) {
        Collections.sort(items);
    }



}
