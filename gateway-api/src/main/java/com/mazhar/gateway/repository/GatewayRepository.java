/**
 * 
 */
package com.mazhar.gateway.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mazhar.gateway.model.Gateway;

/**
 * @author mazhar
 *
 * Dec 12, 2020
 */
@Repository
public interface GatewayRepository extends JpaRepository<Gateway, String>{

}
