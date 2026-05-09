package com.example.team7;

import android.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.team7.database.WardrobeRepository;
import com.example.team7.database.entities.User;
import com.example.team7.databinding.UniqueUserBinding;

import java.util.ArrayList;
import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserViewHolder> {

    private final ArrayList<User> users = new ArrayList<>();
    private final WardrobeRepository repository;

    public UserAdapter(WardrobeRepository repository) {
        this.repository = repository;
    }

    public void setUsers(List<User> newUsers) {
        users.clear();
        users.addAll(newUsers);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        UniqueUserBinding binding = UniqueUserBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new UserViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(UserViewHolder holder, int position) {
        User user = users.get(position);
        holder.show(user);
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    class UserViewHolder extends RecyclerView.ViewHolder {
        private final UniqueUserBinding binding;

        public UserViewHolder(UniqueUserBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void show(User user) {
            binding.itemUsernameTextView.setText(user.getUsername());
            binding.removeUserButton.setOnClickListener(v -> {
                new AlertDialog.Builder(v.getContext())
                        .setTitle("Remove User")
                        .setMessage("Remove " + user.getUsername() + "?")
                        .setPositiveButton("Remove", ((dialog, which) -> repository.deleteUser(user)))
                        .setNegativeButton("Cancel", null)
                        .show();
                    });
        }
    }



}
