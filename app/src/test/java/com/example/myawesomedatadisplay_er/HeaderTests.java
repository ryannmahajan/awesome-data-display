package com.example.myawesomedatadisplay_er;

import static org.junit.Assert.*;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class HeaderTests {

    @Test
    public void headersAreInsertedCorrectly(){
        List<Item> items = new ArrayList<>();
        items.add(new Item(345, 3, "Item 345"));
        items.add(new Item(684, 1, "Item 684"));

        List<Object> gotResult = Header.addHeadersToList(items);
        List<Object> expectedHeaders = List.of(new Header("3"), new Header("1"));

        // todo: yes, this bunches up index, size and equality checks
        assertTrue(gotResult.get(0).equals(expectedHeaders.get(0)) &&
                gotResult.get(2).equals(expectedHeaders.get(1)));
        assertEquals(4, gotResult.size());
    }
}
