package com.biz.blackjack.exec;

import com.biz.blackjack.service.CardDeck;
import com.biz.blackjack.service.Dealer;
import com.biz.blackjack.service.Game;
import com.biz.blackjack.service.User;

public class BlackJackEx_01 {

	public static void main(String[] args) {

		CardDeck cd = new CardDeck();
		Dealer dr = new Dealer();
		User us = new User();
		Game gs = new Game();
		
		try {
			cd.readFile(); // 카드 파일 읽기
			dr.setCarddeck(cd); //객체 전달
			us.setCarddeck(cd); //객체 전달
			gs.setDealerUser(dr, us); //객체 전달
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		gs.start();
		
		
	}

}
