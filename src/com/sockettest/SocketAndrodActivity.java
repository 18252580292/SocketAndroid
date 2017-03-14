package com.sockettest;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class SocketAndrodActivity extends Activity {
	/** Called when the activity is first created. */
	private PrintWriter out;

	public void send(View v) {
		new Thread() {

			public void run() {

				out.println("hello,this is the android phone");
				System.out.println("i send the msg");

			};
		}.start();

	}

	private Socket socket;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		Button button = (Button) this.findViewById(R.id.btn);
		button.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				new Thread() {

					public void run() {
						try {

							System.out.println("Client：Connecting");
							socket = new Socket("192.168.43.225", 12345);
//							 socket = new Socket("10.64.45.181", 12345);
							if (socket != null) {
								out = new PrintWriter(
										new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), true);
							}
							// 发送给服务端的消息
							// String message = "Message from Android phone";
							//
							// System.out.println("Client Sending: '" + message
							// + "'");
							//
							// // 第二个参数为True则为自动flush
							// PrintWriter out = new PrintWriter(
							// new BufferedWriter(new
							// OutputStreamWriter(socket.getOutputStream())),
							// true);
							// for (int i = 0; i < 3; i++) {
							// out.println(message + i);
							// System.out.println("第"+i+"次发送");
							// Thread.sleep(3000);
							// }
							// out.println(message);
							// out.flush();
						} catch (Exception e1) {
							e1.printStackTrace();
						}
					};
				}.start();

			}
		});
	}
}