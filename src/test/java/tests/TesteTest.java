package tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import bootwildfly.ProblemaRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = bootwildfly.Application.class)
public class TesteTest {

	@Autowired
	ProblemaRepository PR; 
	
	@Before
	public void setUp() throws Exception {
	
	}

	@Test
	public void test() {
		fail("Not yet implemented");
	}

}
