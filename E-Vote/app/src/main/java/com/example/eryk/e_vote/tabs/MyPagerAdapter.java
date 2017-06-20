package com.example.eryk.e_vote.tabs;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.content.ContextCompat;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ImageSpan;
import android.util.Log;

import com.example.eryk.e_vote.fragments.FavouriteFragment;

/**
 * Created by Eryk on 31/10/2016.
 */

public class MyPagerAdapter extends FragmentStatePagerAdapter {

    int icons[] = {android.R.drawable.ic_menu_search, android.R.drawable.ic_input_add, android.R.drawable.ic_menu_recent_history, android.R.drawable.ic_dialog_map};
    //String tabs[];
    private Context context;

    public MyPagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.context = context;
        //tabs = getResources().getStringArray(R.array.tabs);
    }


    @Override
    public Fragment getItem(int position) {
        //return SearchFragment.getInstance(position);
        // TODO: Change Return fragments
        Log.i("Pos: ", Integer.toString(position));
        switch (position) {
            case 0:
                return FavouriteFragment.getInstance(position);
            case 1:
                return FavouriteFragment.getInstance(position);
            case 2:
                return FavouriteFragment.getInstance(position);
            case 3:
                return FavouriteFragment.getInstance(position);
            default:
                return FavouriteFragment.getInstance(position);
        }
    }

    @Override
    public int getCount() {
        return icons.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        Drawable drawable = ContextCompat.getDrawable(context, icons[position]);
        drawable.setBounds(0, 0, 120, 120);
        ImageSpan imageSpan = new ImageSpan(drawable);
        SpannableString spannableString = new SpannableString(" ");
        spannableString.setSpan(imageSpan, 0, spannableString.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        return spannableString;
    }
}
