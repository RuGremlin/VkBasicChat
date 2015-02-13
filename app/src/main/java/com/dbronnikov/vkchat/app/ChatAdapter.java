package com.dbronnikov.vkchat.app;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.perm.kate.api.Message;

import java.util.List;

/**
 * Created by dbronnikov on 13/02/15.
 * Copyright (c) 2014 Dmitriy Bronnikov. All rights reserved.
 */
public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.ChatViewHolder> {

    private final LayoutInflater inflater;
    private List<Message> data;

    public ChatAdapter(Context context, List<Message> data) {
        inflater = LayoutInflater.from(context);
        this.data = data;
    }

    @Override
    public ChatViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.chat_row, parent, false);
        return new ChatViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ChatViewHolder holder, int position) {
        Message current = data.get(position);
        holder.messageView.setText(current.body);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class ChatViewHolder extends RecyclerView.ViewHolder {
        private TextView messageView;

        public ChatViewHolder(View itemView) {
            super(itemView);
            messageView = (TextView) itemView.findViewById(R.id.messageView);
        }
    }
}
