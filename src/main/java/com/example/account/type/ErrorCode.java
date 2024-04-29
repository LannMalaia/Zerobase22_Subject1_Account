package com.example.account.type;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {
	INVALID_REQUEST("잘못된 요청이에요."),
	USER_NOT_FOUND("그런 사용자는 없어요."),
	ACCOUNT_NOT_FOUND("그런 계좌는 없어요."),
	AMOUNT_EXCEED_BALANCE("계좌 잔고보다 많은 금액을 입력했어요."),
	USER_ACCOUNT_UN_MATCH("사용자와 계좌의 소유주가 달라요."),
	ACCOUNT_ALREADY_UNREGISTERED("이미 해지된 계좌에요."),
	BALANCE_NOT_EMPTY("잔액이 남아있는 계좌는 해지할 수 없어요."),
	MAX_ACCOUNT_PER_USER_10("사용자 최대 계좌는 10개 까지에요."),
	TRANSACTION_NOT_FOUND("거래를 찾지 못했어요."),
	TRANSACTION_ACCOUNT_UN_MATCH("이 거래는 해당 계좌에서 발생한 거래가 아니에요."),
	CANCEL_MUST_FULLY("부분 취소는 허용할 수 없어요."),
	TOO_OLD_ORDER_TO_CANCEL("1년이 지나 취소할 수 없어요.");
	
	private final String description;
}
