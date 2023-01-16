package com.zenroku.financial.records.api.app.transactionhistory.repository;

import com.zenroku.financial.records.api.app.transactionhistory.entity.TransactionHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionHistoryRepository extends JpaRepository<TransactionHistory,Long> {
}
