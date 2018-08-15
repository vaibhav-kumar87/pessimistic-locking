package com.dev.dao.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.dev.dao.AccountDao;
import com.dev.domain.Account;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AccountDaoTest {
	
	@Autowired
	private  AccountDao accountDao;

	@Test
	public void testCreate() throws InterruptedException {
		Account account = new Account(0l,"Foo", "New York");
		accountDao.save(account);
		
		
		
		Thread t = new Thread(() -> {
		    for(int i=1;i<=1001;i++)
		    {
		    	Account fooAccount = accountDao.findOneForUpdate(1l);
		    	System.out.println("get amount for Credit>>"+fooAccount.getAmount());
				fooAccount.credit(1l);;
				accountDao.save(fooAccount);
		    }
		});
		Thread t2 = new Thread(() -> {
		    for(int i=1;i<=1000;i++)
		    {
		    	Account fooAccount2 = accountDao.findOneForUpdate(1l);
		    	System.out.println("get amount for Debit>>"+fooAccount2.getAmount());
		    	fooAccount2.debit(1l);
				accountDao.save(fooAccount2);
		    }
		});
		t.start();
		t2.start();
		t.join();
		t2.join();
		
	}

}