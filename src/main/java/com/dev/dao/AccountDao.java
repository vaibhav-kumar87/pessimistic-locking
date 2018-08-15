package com.dev.dao;
import javax.persistence.LockModeType;
import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Propagation;

import com.dev.domain.Account;

/**
 * A DAO for the entity User is simply created by extending the CrudRepository
 * interface provided by spring. The following methods are some of the ones
 * available from such interface: save, delete, deleteAll, findOne and findAll.
 * The magic is that such methods must not be implemented, and moreover it is
 * possible create new query methods working only by defining their signature!
 */
@Transactional
public interface AccountDao extends CrudRepository<Account, Long>, JpaSpecificationExecutor<Account> {

  public Account findByName(String name);
  
  @org.springframework.transaction.annotation.Transactional(propagation =Propagation.REQUIRES_NEW)
  @Lock(LockModeType.PESSIMISTIC_READ)
  @Query("select a from Account a where a.id = :id")
  public Account findOneForUpdate(@Param("id") Long id);
  
  @Override
  @Lock(LockModeType.PESSIMISTIC_WRITE)
  public Account save(Account a);
  
  

} 