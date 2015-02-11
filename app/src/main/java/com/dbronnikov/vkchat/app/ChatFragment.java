package com.dbronnikov.vkchat.app;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by dbronnikov on 11/02/15.
 * Copyright (c) 2014 Dmitriy Bronnikov. All rights reserved.
 */
public class ChatFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.chat_fragment, container, false);
    }
}
