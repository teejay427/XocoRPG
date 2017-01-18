package com.xocogames.xocorpg;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

	Random random = new Random();
	NameGenerator nameGenerator = new NameGenerator();

	@Override
	protected void onCreate( Bundle savedInstanceState ){
		super.onCreate( savedInstanceState );
		setContentView( R.layout.activity_main );

		generateNewName();
	}

	void generateNewName(){
		setNameLabel( nameGenerator.newName( random ) );
	}

	public void onRefreshButtonClick( View view ){
		generateNewName();
	}

	TextView getNameLabel(){
		return ( TextView ) findViewById( R.id.nameLabel );
	}

	void setNameLabel( String newText ){
		getNameLabel().setText( newText );
	}

}
