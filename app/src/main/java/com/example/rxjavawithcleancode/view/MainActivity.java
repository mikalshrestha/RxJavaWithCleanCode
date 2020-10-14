package com.example.rxjavawithcleancode.view;

import android.os.Bundle;

import com.example.rxjavawithcleancode.R;
import com.example.rxjavawithcleancode.adapter.UserAdapter;
import com.example.rxjavawithcleancode.databinding.ActivityMainBinding;
import com.example.rxjavawithcleancode.domain.model.User;
import com.example.rxjavawithcleancode.vm.UserInfoVm;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private UserInfoVm userViewModel;
    UserAdapter adapter;
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        adapter = new UserAdapter();
        recyclerView.setAdapter(adapter);

        userViewModel = ViewModelProviders.of(this).get(UserInfoVm.class);
        /*adapter.setOnItemClickListener(user -> {
            Toast.makeText(getApplicationContext(),"You clicked : ", Toast.LENGTH_SHORT).show();
        });
*/
        getData();

    }

    private void getData() {
        userViewModel.fetchUserInfo().observe(this, userList -> {
            adapter.setUserList((ArrayList<User>) userList);
        });
    }
}