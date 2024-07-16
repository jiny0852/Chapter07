package echo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;

//출장가서 일해야함
public class ServerThread extends Thread {

	//필드 
	private Socket socket;
	
	//생성자
	public ServerThread(Socket socket) {
		super();
		this.socket = socket;
	}
	
	
	//메소드 gs
	
	//메소드
	
	@Override
	public void run() {
		
		try {
			
			System.out.println("[클라이언트가 연결 되었습니다]");
			
			InputStream fr = socket.getInputStream();
			InputStreamReader isr = new InputStreamReader(fr, "UTF-8");
			BufferedReader br  = new BufferedReader(isr);
			
			OutputStream out  = socket.getOutputStream();
			OutputStreamWriter osw = new OutputStreamWriter(out, "UTF-8");
			BufferedWriter bw = new BufferedWriter(osw);
			
			
			while (true) {
				
				//메세지 받기
				String msg = br.readLine();
				System.out.println("받은 메세지 : " + msg);
				//System.out.println(br.readLine());
				
				if ( msg == null) {
					break;
				}
				
				//메세지 보내기
				bw.write(msg);
				bw.newLine();
				bw.flush();
				
			}
			
			br.close();
			bw.close();
			
			
		} catch (IOException e) {
			System.out.println(e.toString());
		}
		
		
		
	}

	
	
	

}
