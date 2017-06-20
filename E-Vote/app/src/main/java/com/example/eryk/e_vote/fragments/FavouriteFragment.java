package com.example.eryk.e_vote.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.eryk.e_vote.R;


/**
 * Created by Eryk on 29/10/2016.
 */

public class FavouriteFragment extends Fragment {
    public static FavouriteFragment getInstance(int position) {
        return new FavouriteFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.layout_listview, container, false);

        return rootView;
    }
}
