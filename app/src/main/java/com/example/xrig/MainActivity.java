package com.example.xrig;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

private NavigationView nav;
private ActionBarDrawerToggle toggle;
private DrawerLayout drawerLayout;
private Toolbar toolbar;
private GridLayoutManager gridLayoutManager;
private RecyclerView recycle;
private ArrayList<pc_model> pcList;
private FirebaseAuth firebaseAuth;
private FirebaseFirestore db;
private pc_build_adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recycle = findViewById(R.id.recycler);
        nav = findViewById(R.id.nav_menu);
        drawerLayout = findViewById(R.id.drawer);
        toolbar = findViewById(R.id.toolbar);
        firebaseAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();

        FirebaseUser currentUser = firebaseAuth.getCurrentUser();
        if (currentUser == null){
            startActivity(new Intent(this,XRiG_Login.class));
        }
    }

    @Override
    protected void onStart() {
        super.onStart();

        pcList = new ArrayList<>();
        recycle.setHasFixedSize(true);
        gridLayoutManager = new GridLayoutManager(this,2);
        recycle.setLayoutManager(gridLayoutManager);

        setSupportActionBar(toolbar);
        toggle =new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open,R.string.close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        loadRecyclerViewData();
        adapter = new pc_build_adapter(this, pcList);
        recycle.setAdapter(adapter);

        nav.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@androidx.annotation.NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.menu_profile:
                        Toast.makeText(MainActivity.this, "Profile Panel is Open", Toast.LENGTH_SHORT).show();
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;

                    case R.id.menu_cart:
                        Toast.makeText(MainActivity.this, "Cart Panel is Open", Toast.LENGTH_SHORT).show();
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;

                    case R.id.menu_wishlist:
                        Toast.makeText(MainActivity.this, "Wishlist Panel is Open", Toast.LENGTH_SHORT).show();
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;

                    case R.id.menu_logOut:
                        firebaseAuth.signOut();
                        startActivity(new Intent(MainActivity.this,XRiG_Login.class));
                        break;
                }
                return true;
            }
        });
    }

    private void loadRecyclerViewData() {
        db.collection("xrig").get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        if (!queryDocumentSnapshots.isEmpty()) {
                            List<DocumentSnapshot> list = queryDocumentSnapshots.getDocuments();
                            for (DocumentSnapshot d : list) {
                                pc_model modal = d.toObject(pc_model.class);
                                pcList.add(modal);
                            }
                            adapter.notifyDataSetChanged();
                        } else {
                            Toast.makeText(MainActivity.this, "No data found in Database", Toast.LENGTH_SHORT).show();
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(MainActivity.this, "Fail to get the data.", Toast.LENGTH_SHORT).show();
                    }
                });
    }
}