package com.example.se1606_prm392_group05.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.se1606_prm392_group05.Model.Message;
import com.example.se1606_prm392_group05.R;

import java.util.List;

public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.MessageViewHolder> {
    private List<Message> messageList;

    public ChatAdapter(List<Message> messageList) {
        this.messageList = messageList;
    }

    @NonNull
    @Override
    public MessageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Tạo một View từ layout XML để hiển thị một mục tin nhắn trong RecyclerView
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_messager, parent, false);
        return new MessageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MessageViewHolder holder, int position) {
        // Lấy tin nhắn ở vị trí hiện tại trong danh sách
        Message message = messageList.get(position);

        // Gán tên người gửi, nội dung và thời gian vào các TextView trong ViewHolder
        holder.senderTextView.setText(message.getSender());
        holder.contentTextView.setText(message.getContent());
        holder.timestampTextView.setText(message.getTimestamp());
    }

    @Override
    public int getItemCount() {
        // Trả về số lượng mục tin nhắn trong danh sách
        return messageList.size();
    }

    public class MessageViewHolder extends RecyclerView.ViewHolder {
        public TextView senderTextView;
        public TextView contentTextView;
        public TextView timestampTextView;

        public MessageViewHolder(@NonNull View itemView) {
            super(itemView);

            // Liên kết các thành phần UI với phần tử trong layout XML
            senderTextView = itemView.findViewById(R.id.senderTextView);
            contentTextView = itemView.findViewById(R.id.contentTextView);
            timestampTextView = itemView.findViewById(R.id.timestampTextView);
        }
    }
}
