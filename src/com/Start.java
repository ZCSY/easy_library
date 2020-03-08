package com;

import java.util.Scanner;

public class Start {

	public static void main(String[] args) {
		
		Scanner input = new Scanner(System.in);
	
		for(int i=0;i<3;i++) {
			System.out.print("请输入用户名：");
			String userName = input.next();
			System.out.print("请输入密码：");
			String userPassword = input.next();
			if(userName.equals("suoyue_zhan") && userPassword.equals("12345")) {
				BookMgr dm = new BookMgr();
				dm.initial();
				dm.startMenu();
				break;
			}else if(i<2){
				System.out.println("用户名或密码有误，请重新输入...");
			}
		}		
		input.close();
		System.out.println("谢谢 使 用 !!!");
	}
	
}
