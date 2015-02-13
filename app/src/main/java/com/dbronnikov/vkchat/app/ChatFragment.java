package com.dbronnikov.vkchat.app;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.perm.kate.api.Api;
import com.perm.kate.api.KException;
import com.perm.kate.api.Message;
import com.perm.kate.api.SearchDialogItem;
import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by dbronnikov on 11/02/15.
 * Copyright (c) 2014 Dmitriy Bronnikov. All rights reserved.
 */
public class ChatFragment extends Fragment {

    RecyclerView chatView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        String token = getArguments().getString("token");
        long userID = getArguments().getLong("user_id");
        new ChatMessagesTask(token, userID).execute();
        View layout = inflater.inflate(R.layout.chat_fragment, container, false);
        chatView = (RecyclerView) layout.findViewById(R.id.chatView);
        chatView.setLayoutManager(new LinearLayoutManager(getActivity()));
        return layout;
    }


    class ChatMessagesTask extends AsyncTask<Void, Void, List<Message>> {

        Api vkApi;
        long userID;

        public ChatMessagesTask(String token, long userID) {
            vkApi = new Api(token, Constants.APP_ID);
            this.userID = userID;
        }

        @Override
        protected void onPreExecute() {

        }

        @Override
        protected List<Message> doInBackground(Void... params) {
            try {
                List<SearchDialogItem> chats = vkApi.searchDialogs("тестчат", "", 20);
                return vkApi.getMessagesHistory(userID, chats.get(0).chat.chat_id, 0, 0L, 20);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            } catch (KException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(List<Message> result) {
            List<Message> messages = new ArrayList<Message>(result);
            chatView.setAdapter(new ChatAdapter(getActivity(), messages));
        }
    }
}
