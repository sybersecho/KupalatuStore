package com.ta.toko.module.common;

import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ta.toko.config.RootConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { RootConfig.class })
public abstract class AbstractServiceTest {

	@Before
	public abstract void setup();

	@After
	public abstract void tearDown();
}
