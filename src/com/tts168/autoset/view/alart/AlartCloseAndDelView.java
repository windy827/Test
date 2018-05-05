package com.tts168.autoset.view.alart;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;

import com.larkiv.larksmart7618.R;
import com.tts168.autoset.activity.MyBaseActivity;
import com.tts168.autoset.tools.alart.AlartNotifyDialog;
import com.tts168.autoset.tools.alart.AlartTools;
import com.tts168.autoset.view.MyBaseActivityView;
/**
 * �رպ�ɾ������
 * @author Ԭ��
 *
 */
public class AlartCloseAndDelView extends MyBaseActivityView{
	private View view;
	private ImageView iv_isclose;//��������Ƿ�ر�
	private View view_isclose;
	private ImageView iv_del;//ɾ������
	public AlartCloseAndDelView(MyBaseActivity activity) {
		super(activity);
		// TODO Auto-generated constructor stub
	}

/**
 * ���ظý���
 */
	public void setViewGone(){
		view.setVisibility(View.GONE);
	}
	
	/**
	 * ���ؿ�����ť
	 */
		public void setIsOpenViewGone(){
			view_isclose.setVisibility(View.GONE);
		}
		/**
		 * ��ǰ״̬��Ӧ��ͼƬ������
		 */
		public void setOpenAlartTextAndDrawable(boolean isOpen){
			if(isOpen){
				
				iv_isclose.setImageResource(R.drawable.close_selector);
			}else{
				/**
				 * ��ǰ״̬Ϊ�رն�Ӧ��ͼƬ������
				 */
			
				iv_isclose.setImageResource(R.drawable.open_selector);
			}
			
		}
	
		
	@Override
	protected void getDataRefresh() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void staticFindView() {
		// TODO Auto-generated method stub
		view=activity.findViewById(R.id.ll_view_model_del_and_close);
		iv_isclose=(ImageView) activity.findViewById(R.id.iv_acticity_awakealart_isopen);
		view_isclose=activity.findViewById(R.id.rl_isclose);
		iv_del=(ImageView) activity.findViewById(R.id.iv_acticity_awakealart_del);
	}

	@Override
	public void staticListener() {
		// TODO Auto-generated method stub
		/**
		 * �ô�������Activityȥʵ�ֵ������
		 */
		iv_del.setOnClickListener((OnClickListener) activity);
		iv_isclose.setOnClickListener((OnClickListener) activity);
	}

	@Override
	public void FindMyView() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void FindMyListener() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
	}

}
