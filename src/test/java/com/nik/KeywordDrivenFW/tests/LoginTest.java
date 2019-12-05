package com.nik.KeywordDrivenFW.tests;



import org.testng.annotations.Test;

import com.nik.KeywordDrivenFW.ExecutionEngine.executionEngine;

public class LoginTest {
	
	public executionEngine en;
	
	@Test
	public void login() {
		en=new executionEngine();
		en.startExecution("login");
	}

	@Test(priority=1)
	public void forgotacc() {
		en=new executionEngine();
		en.startExecution("forgotaccount");
	}
}
