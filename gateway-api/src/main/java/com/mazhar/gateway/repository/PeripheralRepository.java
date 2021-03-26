/**
 * 
 */
package com.mazhar.gateway.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.mazhar.gateway.model.Peripheral;

/**
 * @author mazhar
 *
 *         Dec 13, 2020
 */
public interface PeripheralRepository extends JpaRepository<Peripheral, Integer> {
	@Transactional
	@Modifying
	@Query(value = "DELETE FROM peripheral WHERE id = ?1", nativeQuery = true)
	public void deleteCascade(int id);
	
}
