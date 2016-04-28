package com.example.sandra.tuperfiltestgimapp;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.Query;

public class TuPerfil extends AppCompatActivity {
    private TextView nom;
    private TextView numSoci;
    private TextView telefon;
    private TextView direccio ;
    private TextView email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tu_perfil);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        nom = (TextView)findViewById(R.id.nomPerfil);
        numSoci = (TextView)findViewById(R.id.numSoci);
        telefon = (TextView)findViewById(R.id.telfPerfil);
        direccio = (TextView)findViewById(R.id.direccioPerfil);
        email = (TextView)findViewById(R.id.emailPerfil);

        Firebase.setAndroidContext(this);

        Firebase ref = new Firebase("https://testgimmapp.firebaseio.com/");
        Firebase ref2 = ref.child("Clientes");

        Query queryRef = ref2.orderByChild("uid").equalTo("19dba67c-d8f0-4d7c-afc4-aebe236f84a4");

        queryRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot snapshot, String previousChild) {
                Cliente a=  snapshot.getValue(Cliente.class);
                nom.setText(a.getNombre()+" "+a.getApellido());
                numSoci.setText(a.getnSocio());
                telefon.setText(a.getTelf());
                direccio.setText(a.getDireccion());
                email.setText(a.getEmail());
                System.out.println("-------------------------------"+a.getNombre());
            }
            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_tu_perfil, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
