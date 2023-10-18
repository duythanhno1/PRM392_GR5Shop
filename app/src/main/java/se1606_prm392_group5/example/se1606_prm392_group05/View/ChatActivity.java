package se1606_prm392_group5.example.se1606_prm392_group05.View;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import se1606_prm392_group5.example.se1606_prm392_group05.Adapter.ChatAdapter;
import se1606_prm392_group5.example.se1606_prm392_group05.Model.Message;
import com.example.se1606_prm392_group05.R;

import java.util.ArrayList;
import java.util.List;

public class ChatActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private ChatAdapter chatAdapter;
    private List<Message> messageList;
    private EditText messageEditText;
    private ImageView sendImageView;
private  String username;
    private String userType; // Biến lưu trữ loại người dùng: "admin" hoặc "user"

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        messageList = new ArrayList<>();
        chatAdapter = new ChatAdapter(messageList);
        recyclerView.setAdapter(chatAdapter);

        messageEditText = findViewById(R.id.messageEditText);
        sendImageView = findViewById(R.id.imageView30);
        username = getIntent().getStringExtra("USERNAME");
        // Nhận loại người dùng từ Intent
        userType = getIntent().getStringExtra("USER_TYPE");

// Kiểm tra loại người dùng và thiết lập tên người gửi mặc định
        String defaultSender;
        if ("admin".equals(userType) && "admin".equals(username)) {
            defaultSender = "admin";
        } else {
            defaultSender = "user";
        }


        sendImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String message = messageEditText.getText().toString().trim();
                if (!message.isEmpty()) {
                    // Tạo một đối tượng tin nhắn mới với tên người gửi tương ứng
                    Message newMessage = new Message(defaultSender, message);
                    // Thêm tin nhắn vào danh sách tin nhắn
                    messageList.add(newMessage);
                    // Cập nhật giao diện
                    chatAdapter.notifyDataSetChanged();
                    // Xoá nội dung trong ô nhập tin nhắn
                    messageEditText.setText("");
                    // Cuộn RecyclerView xuống dòng tin nhắn mới nhất
                    recyclerView.scrollToPosition(messageList.size() - 1);
                }
            }
        });
    }
}
