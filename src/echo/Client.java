package echo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Scanner;

public class Client {

	public static void main(String[] args) throws IOExcep   tion {
		
		//종이컵 전화기
		Socket socket = new Socket();
		
		System.out.println("<클라이언트 시작>");
		System.out.println("============================");
		
		System.out.println("[서버에 연결을 요청합니다]");
		socket.connect(new InetSocketAddress("192.168.0.101", 10001));
		
		System.out.println("[서버에 연결 되었습니다]");
		
		/*
		//쓰기 스트림
		OutputStream out = new FileOutputStream("C:\\javaStudy\\PhoneDB_copy.txt");
		OutputStreamWriter osw = new OutputStreamWriter(out, "MS949");
		BufferedWriter bw = new BufferedWriter(osw);
		*/
		OutputStream out = socket.getOutputStream(); //socket 주스트림을 만들어서 달라고 하면됨
		OutputStreamWriter osw = new OutputStreamWriter(out, "UTF-8");
		BufferedWriter bw = new BufferedWriter(osw);
		
		//읽기 스트림
		InputStream in = socket.getInputStream();
		InputStreamReader isr = new InputStreamReader(in, "UTF-8");
		BufferedReader br = new BufferedReader(isr);
		
		//입력 스캐너 사용
		Scanner sc = new Scanner(System.in);
		//InputStream scin = System.in;
		//InputStreamReader scisr= new InputStreamReader(scin, "UTF-8");
		//BufferedReader scbr = new BufferedReader(scisr);
		
		
		
		
		while (true) {
			
			System.out.print("입력: ");
			String str = sc.nextLine();
			//String str = scbr.readLine();

			if ("/q".equals(str)) { //순서를 바꾸면 잘 처리된다 ex처리에 굿
				break;
			}			
			
			//메세지 보내기
			bw.write(str);
			bw.newLine(); //보낸거 확인 겸용 플러쉬?
			bw.flush(); //버퍼드 꽉 안차도 보내기
			
			
			//메세지 받기
			String remsg = br.readLine();
			System.out.println("from ServerMsg : " + remsg);
			//System.out.println(br.readLine()); //왜 한번에 안받아짐? 선언 필수??
			
			
		}
		
		System.out.println("======================");
		System.out.println("<클라이언트 종료>");
		
		
		
		//닫기
		br.close();
		bw.close();
		socket.close();
		sc.close();
		//scbr.close();

	}

}
