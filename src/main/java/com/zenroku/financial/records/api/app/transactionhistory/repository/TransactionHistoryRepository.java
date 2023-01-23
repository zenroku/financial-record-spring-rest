package com.zenroku.financial.records.api.app.transactionhistory.repository;

import com.zenroku.financial.records.api.app.transactionhistory.entity.TransactionHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Collection;

public interface TransactionHistoryRepository extends JpaRepository<TransactionHistory,Long>, JpaSpecificationExecutor<TransactionHistory> {
}
