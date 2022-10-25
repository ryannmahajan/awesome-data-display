package com.example.myawesomedatadisplay_er;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class MainFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_with_just_recyclerview, container, false);
        ItemRecyclerView.setup_recycler_view(getActivity(), v);
        return v;
    }


    // todo: save and restore things: scroll state


}
