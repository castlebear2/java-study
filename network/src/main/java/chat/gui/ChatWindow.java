package chat.gui;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Frame;
import java.awt.Panel;
import java.awt.TextArea;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.Socket;

import chat.ChatClient;

public class ChatWindow {
	private Frame frame;
	private Panel pannel;
	private Button buttonSend;
	private TextField textField;
	private TextArea textArea;
	private Socket socket;
	private BufferedReader bufferedReader;
	private PrintWriter printWriter;
	private String nickname;

	public ChatWindow(String name, Socket socket) {
		frame = new Frame(name);
		pannel = new Panel();
		buttonSend = new Button("Send");
		textField = new TextField();
		textArea = new TextArea(30, 80);
		this.socket = socket;
		nickname = name;
	}

	public void show() {
		// Button
		buttonSend.setBackground(Color.GRAY);
		buttonSend.setForeground(Color.WHITE);
		buttonSend.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				sendMessage();
			}
		});

		// Textfield
		textField.setColumns(80);
		textField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				char keyCode = e.getKeyChar();
				if (keyCode == KeyEvent.VK_ENTER) {
					sendMessage();
				}
			}
		});

		// Pannel
		pannel.setBackground(Color.LIGHT_GRAY);
		pannel.add(textField);
		pannel.add(buttonSend);
		frame.add(BorderLayout.SOUTH, pannel);

		// TextArea
		textArea.setEditable(false);
		frame.add(BorderLayout.CENTER, textArea);

		// Frame
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				finish();
			}
		});
		frame.setVisible(true);
		frame.pack();

		// IOStream 받아오기
		// ChatClientThread 생성하고 실행
		try {
			printWriter = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), "utf-8"), true);
			bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream(), "utf-8"));
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			ChatClientApp.log("error : "+e1);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			ChatClientApp.log("error : "+e1);
		}
		new ChatClientThread().start();
	}

	private void finish() {
		// quit protocol 구현
		printWriter.println("quit");
		// exit java(Application)
		System.exit(0);
		try {
			if (socket != null && !socket.isClosed()) {
				socket.close();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void sendMessage() {
		String message = textField.getText();
		System.out.println("메세지 보내는 프로토콜 구현!!:" + message);

		textField.setText("");
		textField.requestFocus();

		if (message.equals("quit")) {
			printWriter.println("quit");
			finish();

		} else if (!message.equals("")) {
			printWriter.println("message:" + message);

		}

		// ChatClientThread 에서 서버로 부터 받은 메세지가 있다 치고~~~
	}

	private void updateTextArea(String message) {
		textArea.append(message);
		textArea.append("\n");
	}

	private class ChatClientThread extends Thread {
		@Override
		public void run() {
			String text = null;
			try {
				while (true) {
					text = bufferedReader.readLine();
					updateTextArea(text);
					if (text == null) {
						break;
					} 
				}

			} catch (IOException e) {
				updateTextArea(ChatClient.name + "님이 나갔습니다");
				ChatClientApp.log("error : "+e);
			}
		}
	}
}