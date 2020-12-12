/**
 * 
 */
package com.mazhar.gateway.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mazhar.gateway.model.Peripheral;

/**
 * @author mazhar
 *
 *         Dec 13, 2020
 */
public interface PeripheralRepository extends JpaRepository<Peripheral, Integer> {
	//List<Peripheral> findByPeripheralId(String postId);

	//Optional<Peripheral> findByIdAndGatewayId(Long id, Long postId);
}
