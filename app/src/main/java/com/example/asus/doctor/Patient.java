package com.example.asus.doctor;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.ContentFrameLayout;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;
import android.support.v4.app.FragmentTransaction;

import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.List;


public class Patient extends AppCompatActivity  {


    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;
    String[]titles = {"History", "Pill", "Report", "Chat"};
    private CharSequence mTitle;
    private CharSequence mDrawerTitle;
    private ActionBarDrawerToggle mDrawerToggle;
    private Toolbar topToolBar;
    FirebaseAuth auth;

NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient);


//catching user id from the PatientList.java
        Intent intent = getIntent();
        final String user = intent.getStringExtra("user");
       // Toast.makeText(getApplicationContext(),"The user id is: "+ user, Toast.LENGTH_SHORT).show();



        final Bundle bundle = new Bundle();
        bundle.putString("UID",user);



        mTitle = mDrawerTitle = getTitle();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout  mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
         mDrawerList = (ListView) findViewById(R.id.left_drawer);



        List<ItemObject> listViewItems = new ArrayList<ItemObject>();
        listViewItems.add(new ItemObject("History", R.drawable.imageone));
        listViewItems.add(new ItemObject("Pill Schedule", R.drawable.imagetwo));
        listViewItems.add(new ItemObject("Submit Report", R.drawable.imagethree));
        listViewItems.add(new ItemObject("Chat", R.drawable.imagefour));

        mDrawerList.setAdapter(new CustomAdapter(this, listViewItems));



        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.drawer_open, R.string.drawer_close) {

            /** Called when a drawer has settled in a completely closed state. */
            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
                getSupportActionBar().setTitle(mTitle);
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }


            /** Called when a drawer has settled in a completely open state. */
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                mDrawerList.bringToFront();
            //    mDrawerLayout.requestLayout();

                getSupportActionBar().setTitle(mDrawerTitle);
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }
        };


        // Set the drawer toggle as the DrawerListener
        mDrawerLayout.addDrawerListener(mDrawerToggle);
        mDrawerLayout.setDrawerListener(mDrawerToggle);
        mDrawerToggle.setDrawerIndicatorEnabled(true);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);


        mDrawerToggle.syncState();


       navigationView = (NavigationView) findViewById(R.id.nav_view);



        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {

         @Override
         public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        if (item.isChecked()) item.setChecked(false);
        else item.setChecked(true);



        //Check to see which item was being clicked and perform appropriate action
        switch (item.getItemId()) {



            case R.id.medical_hitory:

                Fragment fragment = new Fragment();

                fragment = new Menu1();

                //TO PASS USER ID FROM THIS ACTIVITY TO FRAGMENT MENU1
                Menu1 menu1obj = new Menu1();
                menu1obj.setArguments(bundle);

                android.support.v4.app.FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.frame, menu1obj);
                fragmentTransaction.commit();

                return true;



            case R.id.get_appointment:
                Toast.makeText(getApplicationContext(), "Appointment Selected", Toast.LENGTH_SHORT).show();
                return true;


            case R.id.pill_schedule:
                Fragment fragment1 = new Fragment();
                fragment1 = new Menu2();
                Menu2 menu2obj = new Menu2();
                menu2obj.setArguments(bundle);
                android.support.v4.app.FragmentTransaction fragmentTransaction1 = getSupportFragmentManager().beginTransaction();
                fragmentTransaction1.replace(R.id.frame, menu2obj);
                fragmentTransaction1.commit();
                return true;


            case R.id.view_reports:
                Fragment fragment3 = new Fragment();
                fragment1 = new Report();
                Report robj = new Report();
                robj.setArguments(bundle);
                android.support.v4.app.FragmentTransaction fragmentTransaction3 = getSupportFragmentManager().beginTransaction();
                fragmentTransaction3.replace(R.id.frame, robj);
                fragmentTransaction3.commit();
              //  Toast.makeText(getApplicationContext(), "Report Selected", Toast.LENGTH_SHORT).show();
                return true;


            case R.id.chat:
                Intent intent = new Intent(Patient.this,Chat_Activity.class);
                Bundle b =  new Bundle();
                String inter = user.replace('.','-');
                String USERID = inter.replace('@','-');
                b.putString("UID",USERID);
                 //Chat_Activity obj = new Chat_Activity();


          //      Toast.makeText(getApplicationContext(), "Chat Selected"+ USERID, Toast.LENGTH_SHORT).show();
               intent.putExtras(b);
                startActivity(intent);
                return true;
            case R.id.Sign_out:
                auth = FirebaseAuth.getInstance();
                auth.signOut();
                finish();
                Intent intent2 = new Intent(Patient.this,LoginActivity.class);
                startActivity(intent2);

          }

                    return false;
         }
                });

      // onNavigationItemSelected(MenuItem item);
         }



      private void selectItemFragment(int position){

        Fragment fragment = null;
        FragmentManager fragmentManager = getSupportFragmentManager();
        switch(position) {
            default:
            case 0:
                fragment = new DefaultFragment();
                break;
            case 1:
                fragment = new DefaultFragment();
                break;
            case 2:
                fragment = new DefaultFragment();
                break;
            case 3:
                fragment = new DefaultFragment();
                break;
            case 4:
                fragment = new DefaultFragment();
                break;
        }
        fragmentManager.beginTransaction().replace(R.id.main_fragment_container, fragment).commit();

        mDrawerList.setItemChecked(position, true);
        setTitle(titles[position]);
        mDrawerLayout.closeDrawer(mDrawerList);
        }

        @Override
        public void setTitle(CharSequence title) {
        mTitle = title;
        getSupportActionBar().setTitle(mTitle);
        }

      @Override
     protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        mDrawerToggle.syncState();
         }




    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.patient, menu);
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



   /* private void displaySelectedScreen(int itemId) {

        //creating fragment object
        Fragment fragment = null;

        //initializing the fragment object which is selected
        switch (itemId) {
            case R.id.medical_hitory:
                fragment = new Menu1();
                break;
            case R.id.get_appointment:

                break;
            case R.id.pill_schedule:

                break;
            case R.id.view_reports:
                break;
            case R.id.chat:
                break;
        }

        if (fragment != null) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.main_fragment_container, fragment);
            ft.commit();
            }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
    } */



}
