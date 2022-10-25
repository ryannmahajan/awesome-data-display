package com.example.myawesomedatadisplay_er;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class DataParser {

    public static void main(String[] args) {
        String json = "[\n" +
                "{\"id\": 755, \"listId\": 2, \"name\": \"\"},\n" +
                "{\"id\": 203, \"listId\": 2, \"name\": \"\"},\n" +
                "{\"id\": 684, \"listId\": 1, \"name\": \"Item 684\"},\n" +
                "{\"id\": 276, \"listId\": 1, \"name\": \"Item 276\"},\n" +
                "{\"id\": 736, \"listId\": 3, \"name\": null}]";
        List<MainFragment.Item> items = generateItemsFromJson(json);
        System.out.println(items);
    }

    public static List<MainFragment.Item> generateItemsFromJson(String json) {
        List<MainFragment.Item> items = new ArrayList<>();

        try {
            JSONArray jsonItems = new JSONArray(json);
            for (int i = 0; i < jsonItems.length(); i++) {
                JSONObject jsonItem = jsonItems.getJSONObject(i);
                MainFragment.Item item = generateSingleItemFromJson(jsonItem);

                items.add(item);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return items;
    }

    private static MainFragment.Item generateSingleItemFromJson(JSONObject c) throws JSONException {
        int id = Integer.parseInt(c.getString("id"));
        int listId = Integer.parseInt(c.getString("listId"));
        String name = c.getString("name");

        return new MainFragment.Item(id, listId, name);
    }

//    private class GetContacts extends AsyncTask<Context, Void, Void> {
//        @Override
//        protected Void doInBackground(Context... contexts) {
//            super.onPreExecute(contexts);
//            Toast.makeText(contexts[0],"Json Data is downloading",Toast.LENGTH_LONG).show();
//        }
//
//
//        @Override
//        protected Void doInBackground(Void... arg0) {
//            HttpHandler sh = new HttpHandler();
//            // Making a request to url and getting response
//            String url = "http://api.androidhive.info/contacts/";
//            String jsonStr = sh.makeServiceCall(url);
//
//            Log.e(TAG, "Response from url: " + jsonStr);
//            if (jsonStr != null) {
//                try {
//                    JSONObject jsonObj = new JSONObject(jsonStr);
//
//                    // Getting JSON Array node
//                    JSONArray contacts = jsonObj.getJSONArray("contacts");
//
//                    // looping through All Contacts
//                    for (int i = 0; i < contacts.length(); i++) {
//                        JSONObject c = contacts.getJSONObject(i);
//                        String id = c.getString("id");
//                        String name = c.getString("name");
//                        String email = c.getString("email");
//                        String address = c.getString("address");
//                        String gender = c.getString("gender");
//
//                        // Phone node is JSON Object
//                        JSONObject phone = c.getJSONObject("phone");
//                        String mobile = phone.getString("mobile");
//                        String home = phone.getString("home");
//                        String office = phone.getString("office");
//
//                        // tmp hash map for single contact
//                        HashMap<String, String> contact = new HashMap<>();
//
//                        // adding each child node to HashMap key => value
//                        contact.put("id", id);
//                        contact.put("name", name);
//                        contact.put("email", email);
//                        contact.put("mobile", mobile);
//
//                        // adding contact to contact list
//                        contactList.add(contact);
//                    }
//                } catch (final JSONException e) {
//                    Log.e(TAG, "Json parsing error: " + e.getMessage());
//                    runOnUiThread(new Runnable() {
//                        @Override
//                        public void run() {
//                            Toast.makeText(getApplicationContext(),
//                                    "Json parsing error: " + e.getMessage(),
//                                    Toast.LENGTH_LONG).show();
//                        }
//                    });
//
//                }
//
//            } else {
//                Log.e(TAG, "Couldn't get json from server.");
//                runOnUiThread(new Runnable() {
//                    @Override
//                    public void run() {
//                        Toast.makeText(getApplicationContext(),
//                                "Couldn't get json from server. Check LogCat for possible errors!",
//                                Toast.LENGTH_LONG).show();
//                    }
//                });
//            }
//
//            return null;
//        }
//
//        @Override
//        protected void onPostExecute(Void result) {
//            super.onPostExecute(result);
//            ListAdapter adapter = new SimpleAdapter(MainActivity.this, contactList,
//                    R.layout.list_item, new String[]{ "email","mobile"},
//                    new int[]{R.id.email, R.id.mobile});
//            lv.setAdapter(adapter);
//        }
//    }

}
