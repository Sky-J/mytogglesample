# mytogglesample
![](https://github.com/Sky-J/mytogglesample/blob/master/jdfw.gif)
这个是一个安卓的togglebutton 使用方法~
'''Xml
   <com.example.mytogglesample.MyToggle
        android:id="@+id/MyToggle1"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content" >
    </com.example.mytogglesample.MyToggle>
'''
'''Java
myToggle=(MyToggle)findViewById(R.id.MyToggle1);
myToggle.setOnToggleChanged(new OnToggleChanged()
{
	@Override
	public void onToggle(boolean on)
	{
		if (on)
			Toast.makeText(getApplicationContext(), "yes", Toast.LENGTH_LONG).show();
		else
		{
			Toast.makeText(getApplicationContext(), "no", Toast.LENGTH_LONG).show();
		}
	}
});
'''
