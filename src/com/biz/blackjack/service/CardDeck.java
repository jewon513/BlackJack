package com.biz.blackjack.service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CardDeck {

	private List<String> cardList;
	
	public CardDeck() {
		cardList = new ArrayList<String>();
	}
	
	// 카드 파일을 읽어서 cardList에 저장
	public void readFile() throws Exception {
		FileReader fileReader;
		BufferedReader buffer;
		
		String fileName = "src/com/biz/blackjack/domain/Card.txt";
		fileReader = new FileReader(fileName);
		buffer = new BufferedReader(fileReader);
		String reader = new String();
		while(true) {
			reader = buffer.readLine();
			if(reader==null) break;
			
			cardList.add(reader);
			
		}
		buffer.close();
		fileReader.close();
		
		System.out.println("카드파일을 읽고 리스트에 저장하였습니다.");
	}
	
	// 카드를 드로우
	// cardList를 shuffle하고 맨 앞장을 뽑은 뒤에 remove로 제거하고 
	// 메소드를 호출한 곳에 card를 전달
	public String drawCard() {
		Collections.shuffle(cardList);
		String card = cardList.get(0);
		cardList.remove(0);
		return card;
	}
}
