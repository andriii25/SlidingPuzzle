package com.fandras.slidingpuzzle;

import android.content.res.Configuration;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

public class MainActivity extends AppCompatActivity
{

    private  Controller controller;

    public void InitializeUI()
    {

        //Initializing UI elements, positioning the layouts, and adding the PuzzleBoardLayout to the Relative Layout.
        RelativeLayout relativeLayout = (RelativeLayout) findViewById(R.id.main_rellayout);
        PuzzleBoardLayout puzzleBoardLayout = controller.getPuzzleBoardLayout();
        ViewGroup parent = ((ViewGroup)puzzleBoardLayout.getParent());
        if (parent != null)
        {
            parent.removeView(puzzleBoardLayout);
        }

        relativeLayout.addView(puzzleBoardLayout, 0);
        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) puzzleBoardLayout.getLayoutParams();
        params.addRule(RelativeLayout.CENTER_IN_PARENT, RelativeLayout.TRUE);
        puzzleBoardLayout.setLayoutParams(params);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Instantiating the Controller, shuffling the board
        this.controller = new Controller(MainActivity.this, 4);
        controller.shuffle();


        InitializeUI();



    }

    @Override
    public void onConfigurationChanged(Configuration newConfig)
    {
        //Reinitializing the UI on config changes
        super.onConfigurationChanged(newConfig);
        setContentView(R.layout.activity_main);

        InitializeUI();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        //Inflating the menu
        super.onCreateOptionsMenu(menu);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.game_menu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item)
    {
        //Handling the clicks on menu items
        switch (item.getItemId())
        {
            case R.id.menu_new_game:
                controller.newGameDialog();
                return true;
            case R.id.menu_help:
                controller.help();
                return true;
            default:
                return super.onOptionsItemSelected(item);

        }
    }



}
