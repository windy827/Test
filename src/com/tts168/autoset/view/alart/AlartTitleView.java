package com.tts168.autoset.view.alart;

import android.app.Activity;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnFocusChangeListener;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;

import com.larkiv.larksmart7618.R;
import com.tools.sortlistview.ClearEditText;
import com.tts168.autoset.activity.MyBaseActivity;
import com.tts168.autoset.listener.edittext.ClearHintOnFoucusChangeListener;
import com.tts168.autoset.tools.Tools;
import com.tts168.autoset.view.MyBaseActivityView;
import com.tts168.autoset.watcher.MaxLengthWatcher;

/**
 * 闹铃的标题
 * 
 * @author 袁剑
 * 
 */
public class AlartTitleView {

	public static final int TEXTLEN_MAX10 = 10;
	public static final int TEXTLEN_MAX20 = 20;
	View view;
	Activity activity;
	View rl_editView;// 可编辑的标题对应的RelativeLayout
	public TextView tv_alart_title;// 闹铃标题
	View include_edittext;// 包含字数限制的Edittext界面
	public ClearEditText et_title;// 编辑标题
	TextView tv_watcher;// 总字数显示
	int maxLength;

	public AlartTitleView(View view, int maxLength) {
		this.view = view;
		this.maxLength = maxLength;
		staticFindViewByView();
	}

	public AlartTitleView(Activity activity, int maxLength) {
		this.activity = activity;
		this.maxLength = maxLength;
		staticFindViewByActivity();
	}

	public void setTitle(String title) {
		tv_alart_title.setText(title);
	}

	public void setViewGone() {
		rl_editView.setVisibility(View.GONE);
	}

	/**
	 * 可编辑的标题
	 * 
	 * @param title
	 */
	public void setEitTextContent(String title) {
		et_title.setText(title);
		/**
		 * 设置光标到字符串尾部
		 */
		et_title.setSelection(et_title.getText().length());
		include_edittext.setVisibility(View.VISIBLE);
		tv_alart_title.setVisibility(View.GONE);
	}

	/**
	 * EditText的hinit
	 * 
	 * @param title
	 */
	public void setEitTextHinit(String hinit) {
		et_title.setHint(hinit);
		include_edittext.setVisibility(View.VISIBLE);
		tv_alart_title.setVisibility(View.GONE);
	}

	protected void getDataRefresh() {
		// TODO Auto-generated method stub

	}

	/**
	 * 根据View去初始化控件
	 */
	public void staticFindViewByView() {
		// TODO Auto-generated method stub
		rl_editView = view.findViewById(R.id.rl_view_model_alart_title);
		tv_alart_title = (TextView) view
				.findViewById(R.id.tv_activity_awakealart_title);
		et_title = (ClearEditText) view
				.findViewById(R.id.et_view_model_alart_title);
		include_edittext = view
				.findViewById(R.id.include_view_model_edittext_alrttitle);
		tv_watcher = (TextView) view.findViewById(R.id.tv_view_model_wordtotal);
		et_title.addTextChangedListener(new MaxLengthWatcher(this.maxLength,
				et_title, tv_watcher));
		// et_title焦点监听事件
		et_title.setOnFocusChangeListener(new ClearHintOnFoucusChangeListener());
		et_title.setOnEditorActionListener(new OnEditorActionListener() {
			@Override
			public boolean onEditorAction(TextView v, int actionId,
					KeyEvent event) {
				return (event.getKeyCode() == KeyEvent.KEYCODE_ENTER);
			}
		});
	}

	/**
	 * 根据Activity去初始化控件
	 */
	public void staticFindViewByActivity() {
		// TODO Auto-generated method stub

		rl_editView = activity.findViewById(R.id.rl_view_model_alart_title);
		tv_alart_title = (TextView) activity
				.findViewById(R.id.tv_activity_awakealart_title);
		include_edittext = activity
				.findViewById(R.id.include_view_model_edittext_alrttitle);
		et_title = (ClearEditText) activity
				.findViewById(R.id.et_view_model_alart_title);
		tv_watcher = (TextView) activity
				.findViewById(R.id.tv_view_model_wordtotal);
		et_title.addTextChangedListener(new MaxLengthWatcher(this.maxLength,
				et_title, tv_watcher));
		// et_title焦点监听事件
		et_title.setOnFocusChangeListener(new ClearHintOnFoucusChangeListener());
		et_title.setOnEditorActionListener(new OnEditorActionListener() {
			@Override
			public boolean onEditorAction(TextView v, int actionId,
					KeyEvent event) {
				return (event.getKeyCode() == KeyEvent.KEYCODE_ENTER);
			}
		});
	}

}
