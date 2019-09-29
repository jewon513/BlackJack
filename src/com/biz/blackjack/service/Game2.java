package com.biz.blackjack.service;

import java.util.Scanner;

public class Game2{

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
	
	public void start() {
		// 처음에 카드 2장씩 드로우
		// 1. 처음에 바로 버스트 될 일은 없음
		// 2. ACE 점수를 1로 했으므로 블랙잭이 나올 일은 없음
		us.drawCard();
		dr.drawCard();
		us.drawCard();
		dr.drawCard();
		
		// 처음 카드 세팅하고 자신의 카드리스트를 보여줌.
		us.viewCardList();
		
		// hit / stay의 입력에 따라 반복문의 종료 결정
		while(true) {
			System.out.print("(1:hit / 2:stay) >> ");
			
			String strinput = scan.nextLine();
			int input=0;
			try {
				input = Integer.valueOf(strinput);
			} catch (Exception e) {
				System.out.println("숫자만 입력해주세요.");
			}
			
			if(input==1) {
				// 1을 입력하면 hit
				// hit 하고 점수를 체크 ( 버스트인지, 21점인지 )
				// 버스트거나 21점이면 break 걸어서 반복문 종료 ( return값으로 false 반환)
				// 버스트이면 유저의 패배, 21점이면 유저의 승리
				if(this.hit()==false) {
					break;
				}
			}else if(input==2) {
				// 2를 입력하면 stay 
				this.stay();
				break;
				
			}else {
				// 이외의 숫자를 입력하면 다시 입력하라는 문구를 보여주고 다시 입력받기
				System.out.println("잘못 입력하셨습니다. 다시 입력해주세요");
				continue;
			}
		}
		
		System.out.println("게임종료");
		
	}
	
	// Stay 했을때
	// 1. 딜러가 17점 이상이 될때까지 드로우
	// 2. 누가 21점에 더 가까운지 비교
	// 3. 가까운 사람이 승자
	private void stay() {
		// 먼저 딜러의 점수를 확인해야함
		// 처음 세팅때 17점 이상이 되었다면 드로우를 하면 안되고 바로 점수체크
		int dealerScore = dr.getScore();
		int userScore = us.getScore();
		
		// 딜러의 점수가 17점 이상이면 드로우를 하지 않는다. ( break;로 while문 종료 )
		while(true) {
			if(dealerScore>16) {
				break;
			}
			
			dr.drawCard();
			dealerScore = dr.getScore();
			
			// 딜러가 버스트하면 딜러의 패배로 게임 종료 ( return; 으로 stay() method 종료 )
			if(dealerScore>21) {
				System.out.println("\t※딜러의 점수가 21점을 초과하였습니다.※");
				System.out.println("\t\t※유저의 승리입니다.※");
				dr.viewCardList();
				return;
			}
		}
		
		// 이제 점수를 체크해야함.
		if(userScore>dealerScore) {
			System.out.println("\t\t※유저의 승리입니다.※");
			us.viewCardList();
			dr.viewCardList();
			return;
		}else if(userScore<dealerScore) {
			System.out.println("\t\t※딜러의 승리입니다.※");
			us.viewCardList();
			dr.viewCardList();
			return;
		}else if(userScore==dealerScore) {
			System.out.println("\t\t※무승부입니다.※");
			us.viewCardList();
			dr.viewCardList();
			return;
		}
		
	}
	
	//
	
	// hit 했을때
	// 1. 21점을 초과하면 버스트로 패배
	// 2. 드로우 중에 21점이 되면 바로 유저 승리
	private boolean hit() {
		us.drawCard();
		us.viewCardList();
		
		// 버스트를 체크하는 부분
		int userScore = us.getScore();
		if(userScore >21) {
			System.out.println("21점을 초과하여 패배하였습니다.");
			return false;
		}
		// 21점을 제크하는 부분
		if(userScore ==21) {
			System.out.println("21점이 되어 승리하였습니다.");
			return false;
		}
		
		return true;
	}
	
	
	
	
	
}
