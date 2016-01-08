# mytogglesample
![](https://github.com/Sky-J/mytogglesample/blob/master/jdfw.gif)
![](https://github.com/Sky-J/mytogglesample/blob/master/look.PNG)
这个是一个安卓的togglebutton<br>
使用方法~<br>

在layout中添加xml 
```Xml
   <com.example.mytogglesample.MyToggle
        android:id="@+id/MyToggle1"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content" >
    </com.example.mytogglesample.MyToggle>
```
设置它的触发器
```Java
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
```
