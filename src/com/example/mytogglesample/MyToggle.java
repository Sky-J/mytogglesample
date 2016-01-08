package com.example.mytogglesample;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.animation.TranslateAnimation;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;
import android.widget.ToggleButton;
import android.widget.CompoundButton.OnCheckedChangeListener;

public class MyToggle extends RelativeLayout
{

	public Boolean toggleOn = false;
	private OnToggleChanged listener;
	// my
	private ToggleButton toggleButton;
	private ImageView imageButton;
	private Context c;
	AttributeSet attrs;
	private Boolean dolistener = true;
	private Boolean playani = true;

	public MyToggle(Context context)
	{
		super(context);
		c = context;
		// TODO Auto-generated constructor stub
	}

	public MyToggle(Context context, AttributeSet attrs, int defStyleAttr)
	{
		super(context, attrs, defStyleAttr);
		c = context;
		this.attrs = attrs;
	}

	public MyToggle(Context context, AttributeSet attrs)
	{
		super(context, attrs);
		c = context;
		this.attrs = attrs;
	}

	@Override
	protected void onFinishInflate()
	{
		setup(attrs);
		setListeners();
		super.onFinishInflate();
	}

	public void setup(AttributeSet attrs)
	{
		toggleButton = new ToggleButton(c);
		imageButton = new ImageView(c);
		if (toggleButton == null || imageButton == null)
			return;

		RelativeLayout.LayoutParams params1 = new RelativeLayout.LayoutParams(
				LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);

		toggleButton.setLayoutParams(params1);
		toggleButton.setId(Integer.MAX_VALUE - 1000);
		toggleButton.setBackgroundResource(R.drawable.toggle_selector);
		toggleButton.setGravity(Gravity.LEFT | Gravity.CENTER_VERTICAL);
		toggleButton.setPadding(DisplayUtils.dip2px(c, 14), 0, DisplayUtils.dip2px(c, 14), 0);
		toggleButton.setTextColor(Color.WHITE);
		toggleButton.setTextOff("OFF");
		toggleButton.setTextOn("ON");

		RelativeLayout.LayoutParams params2 = new RelativeLayout.LayoutParams(
				LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		// (RelativeLayout.LayoutParams)imageButton.getLayoutParams();
		imageButton.setLayoutParams(params2);
		imageButton.setImageResource(R.drawable.progress_thumb_selector);

		toggleButton.setChecked(toggleOn);
		RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) imageButton
				.getLayoutParams();
		if (toggleOn)
		{

			// 调整位置
			params.addRule(RelativeLayout.ALIGN_RIGHT, -1);
			params.addRule(RelativeLayout.ALIGN_LEFT, toggleButton.getId());
			imageButton.setLayoutParams(params);
			imageButton.setImageResource(R.drawable.progress_thumb_selector);
			toggleButton.setGravity(Gravity.RIGHT | Gravity.CENTER_VERTICAL);
		}
		else
		{
			// 调整位置
			params.addRule(RelativeLayout.ALIGN_RIGHT, toggleButton.getId());
			params.addRule(RelativeLayout.ALIGN_LEFT, -1);
			imageButton.setLayoutParams(params);
			imageButton.setImageResource(R.drawable.progress_thumb_off_selector);
			toggleButton.setGravity(Gravity.LEFT | Gravity.CENTER_VERTICAL);
		}
		toggleButton.invalidate();
		imageButton.invalidate();
		// this.removeAllViews();
		this.addView(toggleButton);
		this.addView(imageButton);
	}

	private void setListeners()
	{
		// my
		OnClickListener clickToToggleListener = new OnClickListener()
		{
			public void onClick(View v)
			{
				toggle();
			}
		};
		toggleButton.setOnCheckedChangeListener(new ToggleListener(c, toggleButton, imageButton));
		imageButton.setOnClickListener(clickToToggleListener);
		this.setOnClickListener(clickToToggleListener);
	}

	// 执行切换
	public void toggle()
	{
		toggle(true);
	}

	public void toggle(boolean animate)
	{
		toggleOn = !toggleOn;
		dolistener = true;
		toggleButton.toggle();
	}

	/**
	 * 设置显示成打开样式，不会触发toggle事件
	 */
	public void setToggleOn()
	{
		toggleOn = true;
		dolistener = false;
		toggleButton.setChecked(true);
		dolistener = true;
	}

	/**
	 * 设置显示成关闭样式，不会触发toggle事件
	 */
	public void setToggleOff()
	{
		toggleOn = false;
		dolistener = false;
		toggleButton.setChecked(false);
		dolistener = true;
	}
	public void setPlayAnimation(Boolean ok)
	{
		playani=ok;
	}
	public interface OnToggleChanged
	{
		/**
		 * @param on
		 */
		public void onToggle(boolean on);
	}
	public void setOnToggleChanged(OnToggleChanged onToggleChanged)
	{
		listener = onToggleChanged;
	}

	class ToggleListener implements OnCheckedChangeListener
	{
		private Context context;
		private ToggleButton toggle;
		private ImageView toggle_Button;

		public ToggleListener(Context context, ToggleButton toggle, ImageView toggle_Button)
		{
			this.context = context;
			this.toggle = toggle;
			this.toggle_Button = toggle_Button;
		}

		@Override
		public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
		{
			// 执行用户函数
			if (listener != null && dolistener == true)
				listener.onToggle(isChecked);
			// 播放动画
			RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) toggle_Button
					.getLayoutParams();
			if (isChecked)
			{
				Log.d("BUG", "on");
				// 调整位置
				params.addRule(RelativeLayout.ALIGN_RIGHT, -1);
				params.addRule(RelativeLayout.ALIGN_LEFT, toggle.getId());
				toggle_Button.setLayoutParams(params);
				toggle_Button.setImageResource(R.drawable.progress_thumb_selector);
				toggle.setGravity(Gravity.RIGHT | Gravity.CENTER_VERTICAL);
				// 播放动画
				if (playani)
				{
					TranslateAnimation animation = new TranslateAnimation(DisplayUtils.dip2px(
							context, 40), 0, 0, 0);
					animation.setDuration(200);
					toggle_Button.startAnimation(animation);
				}

			}
			else
			{
				Log.d("BUG", "off");
				params.addRule(RelativeLayout.ALIGN_RIGHT, toggle.getId());
				params.addRule(RelativeLayout.ALIGN_LEFT, -1);
				toggle_Button.setLayoutParams(params);
				toggle_Button.setImageResource(R.drawable.progress_thumb_off_selector);

				toggle.setGravity(Gravity.LEFT | Gravity.CENTER_VERTICAL);
				// 播放动画
				if (playani)
				{
					TranslateAnimation animation = new TranslateAnimation(DisplayUtils.dip2px(
							context, -40), 0, 0, 0);
					animation.setDuration(200);
					toggle_Button.startAnimation(animation);
				}
			}
		}
	}
}

//myToggle=(MyToggle)findViewById(R.id.MyToggle1);
//myToggle.setOnToggleChanged(new OnToggleChanged()
//{
//	@Override
//	public void onToggle(boolean on)
//	{
//		if (on)
//			Toast.makeText(getApplicationContext(), "yes", Toast.LENGTH_LONG).show();
//		else
//		{
//			Toast.makeText(getApplicationContext(), "no", Toast.LENGTH_LONG).show();
//		}
//	}
//});
