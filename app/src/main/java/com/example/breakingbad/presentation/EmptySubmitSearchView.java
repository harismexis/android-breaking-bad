package com.example.breakingbad.presentation;

import android.content.Context;
import android.util.AttributeSet;
import androidx.appcompat.widget.SearchView;

/*
 * Created by: Jens Klingenberg (jensklingenberg.de)
 * GPLv3
 *   //This SearchView gets triggered even when the query submit is empty
 * */

public class EmptySubmitSearchView extends SearchView {

    SearchView.SearchAutoComplete mSearchSrcTextView;
    OnQueryTextListener listener;

    public EmptySubmitSearchView(Context context) {
        super(context);
    }

    public EmptySubmitSearchView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public EmptySubmitSearchView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void setOnQueryTextListener(OnQueryTextListener listener) {
        super.setOnQueryTextListener(listener);
        this.listener = listener;
        mSearchSrcTextView = this.findViewById(androidx.appcompat.R.id.search_src_text);
        mSearchSrcTextView.setOnEditorActionListener((textView, i, keyEvent) -> {
            if (listener != null) {
                listener.onQueryTextSubmit(getQuery().toString());
            }
            return true;
        });
    }
}