package com.zenroku.financial.records.api.app.transaction_history.repository;

import com.zenroku.financial.records.api.app.transaction_history.entity.TransactionHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionHistoryRepository extends JpaRepository<TransactionHistory,Long> {
}
