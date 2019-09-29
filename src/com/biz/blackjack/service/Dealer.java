package com.biz.blackjack.service;

import java.util.ArrayList;
import java.util.List;

public class Dealer {

	private List<String> dealerCardList; // 뽑은 카드를 저장할 리스트
	private int dealerScore = 0;
	private CardDeck cd;
	
	public Dealer() {
		dealerCardList = new ArrayList<String>();
	}
	
	public void setCarddeck(CardDeck cd) {
		this.cd = cd;
	}
	
	public int getScore() {
		return dealerScore;
	}
	
	public void viewCardList() {
		System.out.println("=================================================");
		System.out.println("dealer's CardList");
		System.out.println("-------------------------------------------------");
		for (int i = 0; i < dealerCardList.size(); i++) {
			System.out.println(dealerCardList.get(i));
		}
		System.out.println("dealer' score : " + dealerScore);
		System.out.println("=================================================");
	}
	
	public void drawCard() {
		
		String card = cd.drawCard();
		dealerCardList.add(card);
		
		String[] list = card.split(":");
		String cardNumber = list[1];
		if(cardNumber.equals("A")) {
			dealerScore+=1;
		}else if(cardNumber.equals("J")) {
			dealerScore+=10;
		}else if(cardNumber.equals("Q")) {
			dealerScore+=10;
		}else if(cardNumber.equals("K")) {
			dealerScore+=10;
		}else {
			dealerScore+=Integer.valueOf(cardNumber);
		}
	}
	
		
		
	
}
