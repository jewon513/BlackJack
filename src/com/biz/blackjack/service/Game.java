package com.biz.blackjack.service;

import java.util.Scanner;

public class Game {

	protected Dealer dr;
	protected User us;
	protected int userScore;
	protected int dealerScore;
	protected Scanner scan;
	
	public void setDealerUser(Dealer dr, User us) {
		this.dr = dr;
		this.us = us;
		scan = new Scanner(System.in);
		
	}
	
	public void start () {
		//처음 딜러 유저 카드 2개씩 뽑는다.
		us.drawCard();
		dr.drawCard();
		us.drawCard();
		dr.drawCard();
		us.viewCardList();
		this.checkScore();
	}
	
	// 1 = > 힛 2 = > 스테이
	public void hitStay() {
		
		while(true) {
			System.out.println("드로우를 하시겠습니까 ? (1 예 / 2 아니오) >> ");
			int menu= scan.nextInt();
			
			if(menu==1) {
				us.drawCard();
				us.viewCardList();
				if(userScore==21) {
					System.out.println("유저 BlackJack 승리");
					break;
				}
			}else {
				while(this.checkScore()) {
					dr.drawCard();
				}
				this.checkWinner();
				us.viewCardList();
				dr.viewCardList();
				break;
			}	
			
			if(this.checkScore()==false) {
				break;
			}
			
		}
		
	}
	
	// 버스트 체크
	public boolean checkScore() {
		
		this.dealerScore = dr.getScore();
		this.userScore = us.getScore();
				
		if(userScore>21) {
			System.out.println("user의 패배입니다.");
			return false;
		}
		
		if(dealerScore>=17) {
			return false;
		}
		return true;
	}
	
	public void checkWinner() {
		// 1 내가 이기는 경우는 ? 
		//		딱 21점이거나
		//			Ace를 1점으로 했으니 첫 세팅에서 바로 블랙잭은 안나옴
		//		21점에 가까운 경우
		//			hit을 안하고 멈추면 체크
		//				이제 딜러가 드로우를 하는데, 이때 딜러가 21점을 넘으면 딜러패배
		//					딜러가 21점을 안넘은 상태라면
		//					점수를 비교하여 체크
		
		this.dealerScore = dr.getScore();
		this.userScore = us.getScore();
		
		if(dealerScore==21) {
			System.out.println("딜러 BlackJack 승리");
			return;
		}
		
		if(dealerScore>21) { //딜러의 점수가 먼저 21점을 넘었다면 딜러의 패배
			System.out.println("딜러가 패배하였습니다.");
			return;
		}
		
		int num1 = 21-userScore;
		int num2 = 21-dealerScore;
		
		// 여기서부터 누가 21점에 더 가까운지 체크
		
		if(num1<num2) {
			System.out.println("유저의 승리");
			return;
		}
		if(num2<num1) {
			System.out.println("딜러의 승리");
			return;
		}
		
		if(num1==num2) {
			System.out.println("무승부");
		}
		
	}
}

// 먼저 카드를 2장씩 뽑는다.
// 딜러의 점수가 16점 이하이면 카드를 한번 더 뽑고 17점 이상이면 드로우를 하지 않는다.
// 유저는 드로우를 할지 안할지 선택할 수 있다
// 21점을 초과하면 무조건 패배

// 점수를 체크하는 메소드 구현
// 