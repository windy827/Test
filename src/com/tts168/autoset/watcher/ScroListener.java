package com.tts168.autoset.watcher;

import com.tts168.autoset.adapter.alart.ChooseRingAdapter;

import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;

/**
 * 针对闹铃选择的ListView的Adaper
 * @author 袁剑
 *
 */
public class ScroListener implements OnScrollListener{
	private ChooseRingAdapter adapter;
	public ScroListener(ChooseRingAdapter adapter){
		this.adapter=adapter;
	}
	
		@Override
		public void onScrollStateChanged(AbsListView view, int scrollState) {
			switch (scrollState) {
			case OnScrollListener.SCROLL_STATE_FLING:
				 adapter.setFlagBusy(true);
				break;
			case OnScrollListener.SCROLL_STATE_IDLE:
				adapter.setFlagBusy(false);
				break;
			case OnScrollListener.SCROLL_STATE_TOUCH_SCROLL:
				adapter.setFlagBusy(false);
				break;
			default:
				break;
			}
			adapter.notifyDataSetChanged();
		}
	
		@Override
		public void onScroll(AbsListView view, int firstVisibleItem,
				int visibleItemCount, int totalItemCount) {
	
		}
	
}

