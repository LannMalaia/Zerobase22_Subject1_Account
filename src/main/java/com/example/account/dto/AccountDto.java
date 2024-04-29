package com.example.account.dto;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.example.account.domain.Account;
import com.example.account.domain.AccountUser;
import com.example.account.type.AccountStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AccountDto {
	private Long userId;
	private String accountNumber;
	private Long balance;

	private LocalDateTime registeredAt;
	private LocalDateTime unRegisteredAt;
	
	public static AccountDto fromEntity(Account account) {
		return AccountDto.builder()
				.userId(account.getAccountUser().getId())
				.accountNumber(account.getAccountNumber())
				.balance(account.getBalance())
				.registeredAt(account.getRegisteredAt())
				.unRegisteredAt(account.getUnRegisteredAt())
				.build();
	}
}
