package com.harismexis.breakingbad.presentation.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.AutoCompleteTextView;
import android.widget.SearchView;

public class CustomSearchView extends SearchView {

    OnQueryTextListener listener;

    public CustomSearchView(Context context) {
        super(context);
    }

    public CustomSearchView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomSearchView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void setOnQueryTextListener(OnQueryTextListener listener) {
        super.setOnQueryTextListener(listener);
        this.listener = listener;
        int srcTextId = getContext().getResources()
                .getIdentifier("android:id/search_src_text", null, null);
        AutoCompleteTextView searchTxtv = this.findViewById(srcTextId);
        searchTxtv.setOnEditorActionListener((textView, i, keyEvent) -> {
            if (listener != null) {
                listener.onQueryTextSubmit(getQuery().toString());
            }
            return true;
        });
    }
}