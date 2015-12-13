package com.example.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

import com.example.main.MainActivity;

public class ChatService extends Thread {
	String IP = "http://yukiy.sinaapp.com/";
	int Port = 5123;;
	DataInputStream in;
	DataOutputStream out;
	Socket socket = null;
	JSONObject jsonObject;

	@Override
	public void run() {
		jsonObject = new JSONObject();

		try {
			socket = new Socket(IP, Port);
			out = new DataOutputStream(socket.getOutputStream());
			in = new DataInputStream(socket.getInputStream());
			
			Log.e("发送的消息asd", MainActivity.name);
			jsonObject.put("sender", MainActivity.name);
			String string = jsonObject.toString();
			
			out.writeUTF(string);
			
		} catch (IOException e) {
			e.printStackTrace();
		} catch (JSONException e) {
			e.printStackTrace();
		}
		Log.e("发送的消息", jsonObject.toString());
		String string = "未收到消息";
		while (true) {

			try {
				Thread.sleep(1000);
				Log.e("接收", "正在接收");
				string = in.readUTF();
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}

			Log.e("接收到数据", string);
		}
		
		
	}
	
}
