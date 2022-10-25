package com.example.myawesomedatadisplay_er;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    List<Item> items;
    // todo: how to test private methods. answer: don't

    @Before
    public void initialize() {
        items = new ArrayList<>();
        items.add(new Item(345, 3, "Item 345"));
        items.add(new Item(684, 1, "Item 684"));
        items.add(new Item(276, 1, "Item 276"));
    }

    @Test
    public void itemsWithInvalidNamesAreRemoved() {
        List<Item> clonedItems = new ArrayList<>(items);

        Item invalidItem = new Item(755, 2, "");
        clonedItems.add(invalidItem);

        assertTrue(clonedItems.contains(invalidItem));
        JsonParser.removeInvalidNamedItems(clonedItems);
        assertTrue(!clonedItems.contains(invalidItem) && clonedItems.size()==3);
    }

    @Test
    public void itemsAreSortedByListIdAndName() {
        List<Item> clonedItems = new ArrayList<>(items);

        Collections.shuffle(clonedItems);
        JsonParser.sortItems(clonedItems);

        assertTrue(
                clonedItems.get(0).getId()==276 &&
                        clonedItems.get(1).getId()==684 &&
                        clonedItems.get(2).getId()==345
        );
    }
    // todo: how to debug app

}