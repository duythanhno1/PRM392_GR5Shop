package com.example.se1606_prm392_group05.View;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.se1606_prm392_group05.Adapter.ChatAdapter;
import com.example.se1606_prm392_group05.Model.Message;
import com.example.se1606_prm392_group05.R;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ChatActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private EditText messageEditText;
    private List<Message> messageList;
    private ChatAdapter chatAdapter;
    private DatabaseReference messagesRef;
    private String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        // Nhận tên người đăng nhập từ Intent
        username = getIntent().getStringExtra("username");

        // Khởi tạo Firebase và tham chiếu đến nút "messages" trong Realtime Database
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        messagesRef = database.getReference("messages");

        // Khởi tạo các view và adapter
        recyclerView = findViewById(R.id.recyclerView);
        messageEditText = findViewById(R.id.messageEditText);

        messageList = new ArrayList<>();
        chatAdapter = new ChatAdapter(messageList);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(chatAdapter);

        // Lắng nghe sự thay đổi của dữ liệu tin nhắn trong Realtime Database
        messagesRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String previousChildName) {
                Message message = dataSnapshot.getValue(Message.class);
                messageList.add(message);
                chatAdapter.notifyItemInserted(messageList.size() - 1);
                recyclerView.scrollToPosition(messageList.size() - 1);
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String previousChildName) {
                // Không cần xử lý
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {
                // Không cần xử lý
            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String previousChildName) {
                // Không cần xử lý
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.e("ChatActivity", "Failed to read messages", databaseError.toException());
            }
        });

        // Xử lý sự kiện khi người dùng nhấn nút gửi tin nhắn
        Button sendButton = findViewById(R.id.sendButton);
        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String content = messageEditText.getText().toString().trim();
                if (!content.isEmpty()) {
                    String sender = username;
                    String timestamp = DateFormat.getDateTimeInstance().format(new Date());
                    Message message = new Message(sender, content, timestamp);

                    // Gửi tin nhắn lên Realtime Database
                    String messageId = messagesRef.push().getKey();
                    messagesRef.child(messageId).setValue(message);

                    messageEditText.setText("");
                }
            }
        });
    }
}
