package com.example.main;

import com.example.login.R;
import com.example.server.JSONHttpUtil;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

public class ChatActivity extends Activity {
	private String name;
	private TextView chatShow;
	private ListView chatList;
	private EditText chatText;
	private Button chatSend;
	private Message msg;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.chat_activity);
		name = getIntent().getStringExtra("name");

		// 修改软键盘设置
		getWindow().setSoftInputMode(
				WindowManager.LayoutParams.SOFT_INPUT_MASK_ADJUST
						| WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

		initView();

	}

	// 初始化控件
	private void initView() {
		chatShow = (TextView) findViewById(R.id.chat_show);
		chatList = (ListView) findViewById(R.id.chat_list);
		chatText = (EditText) findViewById(R.id.chat_text);
		chatSend = (Button) findViewById(R.id.chat_send);

		chatSend.setOnClickListener(new SendClickListener());

		chatShow.setText(name);
	}

	// 发送按钮事件监听
	private class SendClickListener implements OnClickListener {

		@Override
		public void onClick(View v) {
			new Thread(new Runnable() {

				@Override
				public void run() {

					msg = new Message();
					msg.obj = new JSONHttpUtil().SendNotice(MainActivity.name,
							name, chatText.getText().toString());
					handler.sendMessage(msg);
				}
			}).start();
		}

	}

	Handler handler = new Handler() {
		public void handleMessage(Message msg) {

			if (msg.obj.toString().equals("Ok")) {
				// 发送成功
			} else {
				// 发送失败
			}
			chatText.setText("");

		}
	};

	@Override
	protected void onStop() {
		super.onStop();
		finish();
	}

}
