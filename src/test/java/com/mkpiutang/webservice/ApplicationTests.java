package com.mkpiutang.webservice;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.mkpiutang.webservice.MkpiutangApplication;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = MkpiutangApplication.class)
public class ApplicationTests {

	@Test
	public void contextLoads() {
	}

}

/*@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = ApimkaApplication.class)
@WebAppConfiguration
public class ApplicationTests {

	@Test
	public void contextLoads() {
	}

}
*/