package com.nevexis.services;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.nevexis.model.Crypto;
@Repository
public interface CryptoRepo extends JpaRepository<Crypto, Long>{
	@Query(value = "SELECT * FROM CRYPTO c WHERE c.crypto_currency_id = ?1", nativeQuery = true)
	Collection<Crypto> findByCurrency(String currencyName);
}
