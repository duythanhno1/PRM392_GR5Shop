package se1606_prm392_group5.example.se1606_prm392_group05.Adapter;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import se1606_prm392_group5.example.se1606_prm392_group05.Data.RegisterHelper;
import se1606_prm392_group5.example.se1606_prm392_group05.Model.User;
import com.example.se1606_prm392_group05.R;
import se1606_prm392_group5.example.se1606_prm392_group05.View.AdminActivity;
import se1606_prm392_group5.example.se1606_prm392_group05.View.HomeActivity;

import java.util.ArrayList;
import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserViewHolder> {
    private List<User> userList;
    private Context context;
    private RegisterHelper databaseHelper;

    public UserAdapter(Context context) {
        this.context = context;
        this.userList = new ArrayList<>();
        this.databaseHelper = new RegisterHelper(context);

    }
    public boolean authenticateUser(String username, String password) {
        boolean authenticated = false;

        if (username.equals("admin") && password.equals("123")) {
            authenticated = true; // Tài khoản admin
            // Chuyển đến trang AdminActivity
            Intent intent = new Intent(context, AdminActivity.class);
            context.startActivity(intent);
        } else {
            SQLiteDatabase db = databaseHelper.getReadableDatabase();
            String query = "SELECT * FROM " + RegisterHelper.TABLE_USER +
                    " WHERE " + RegisterHelper.COLUMN_USERNAME + " = ?" +
                    " AND " + RegisterHelper.COLUMN_PASSWORD + " = ?";
            Cursor cursor = db.rawQuery(query, new String[]{username, password});
            authenticated = cursor.getCount() > 0;

            if (authenticated) {
                // Chuyển đến trang chính
                Intent intent = new Intent(context, HomeActivity.class);
                intent.putExtra("USERNAME", username);
                context.startActivity(intent);
            }

            cursor.close();
            db.close(); // Đóng kết nối database
        }

        return authenticated;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
        notifyDataSetChanged();
    }

    public void addUser(User user) {
        userList.add(user);
        notifyItemInserted(userList.size() - 1);
    }

    public boolean checkEmailExist(String email) {
        for (User user : userList) {
            if (user.getEmail().equals(email)) {
                return true;
            }
        }
        return false;
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_user, parent, false);
        return new UserViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        User user = userList.get(position);
        holder.bind(user);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Kiểm tra xem người dùng có là tài khoản admin hay không
                if (user.getUsername().equals("admin") && user.getPassword().equals("123")) {
                    // Chuyển đến trang AdminActivity
                    Intent intent = new Intent(context, AdminActivity.class);
                    context.startActivity(intent);
                } else {
                    // Xử lý logic cho người dùng không phải là tài khoản admin
                    // ...
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    public class UserViewHolder extends RecyclerView.ViewHolder {
        private TextView usernameTextView;
        private TextView emailTextView;
        private TextView phoneTextView;
        private TextView addressTextView;

        public UserViewHolder(@NonNull View itemView) {
            super(itemView);
            usernameTextView = itemView.findViewById(R.id.usernameTextView);
            emailTextView = itemView.findViewById(R.id.emailTextView);
            phoneTextView = itemView.findViewById(R.id.phoneNumTextView);
            addressTextView = itemView.findViewById(R.id.addressTextView);
        }

        public void bind(User user) {
            usernameTextView.setText(user.getUsername());
            emailTextView.setText(user.getEmail());
            phoneTextView.setText(user.getPhone());
            addressTextView.setText(user.getAddress());
        }

    }
}

