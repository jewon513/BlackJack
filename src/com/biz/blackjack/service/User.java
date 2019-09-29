package com.biz.blackjack.service;

import java.util.ArrayList;
import java.util.List;

public class User {

	private List<String>userCardList; // 뽑은 카드를 저장할 리스트
	private int userScore=0;
	private CardDeck cd;
	
	public User() {
		userCardList = new ArrayList<String>();
	}
	
	public void setCarddeck(CardDeck cd) {
		this.cd = cd;
	}
	
	public int getScore() {
		return userScore;
	}
	
	public void viewCardList() {
		System.out.println("=================================================");
		System.out.println("User CardList");
		System.out.println("-------------------------------------------------");
		for (int i = 0; i < userCardList.size(); i++) {
			System.out.println(userCardList.get(i));
		}
		System.out.println("\t\t\t\t현재 점수 : " + userScore);
		System.out.println("=================================================");
	}
	
	public void drawCard() {
		
		String card = cd.drawCard(); //카드를 뽑고
		userCardList.add(card); //리스트에 카드를 저장
		
		//뽑은 카드의 점수를 계산하는 부분
		String[] list = card.split(":"); //split을 써서 패턴과 넘버를 분리
		String cardNumber = list[1]; //점수를 정하는 넘버부분은 list[1]에 저장됨
		if(cardNumber.equals("A")) {
			userScore+=1;
		}else if(cardNumber.equals("J")) {
			userScore+=10;
		}else if(cardNumber.equals("Q")) {
			userScore+=10;
		}else if(cardNumber.equals("K")) {
			userScore+=10;
		}else {
			userScore+=Integer.valueOf(cardNumber);
		}
	}

}
