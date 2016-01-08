package com.example.mytogglesample;

import com.example.mytogglesample.MyToggle.OnToggleChanged;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.Toast;

public class MainActivity extends Activity
{

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		MyToggle myToggle=(MyToggle)findViewById(R.id.MyToggle1);
		myToggle.setOnToggleChanged(new OnToggleChanged()
		{
			@Override
			public void onToggle(boolean on)
			{
				if (on)
					Toast.makeText(getApplicationContext(), "yes", Toast.LENGTH_SHORT).show();
				else
				{
					Toast.makeText(getApplicationContext(), "no", Toast.LENGTH_SHORT).show();
				}
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
