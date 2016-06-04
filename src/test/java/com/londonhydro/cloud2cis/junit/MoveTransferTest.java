package com.londonhydro.cloud2cis.junit;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.google.inject.Inject;
import com.google.inject.Module;
import com.londonhydro.cloud2cis.job.GetMoveTransferJob;
import com.londonhydro.inject.JobsGuiceModule;
import com.londonhydro.junit.GuiceJUnitRunner;
import com.londonhydro.junit.GuiceJUnitRunner.GuiceModules;
import com.londonhydro.junit.GuiceModuleProvider;

@RunWith(GuiceJUnitRunner.class)
@GuiceModules({ JobsGuiceModule.class })
public class MoveTransferTest implements GuiceModuleProvider {

	@Inject
	private GetMoveTransferJob job;

	public Module getGuiceModule() {
		return new JobsGuiceModule();
	}

	@Before
	public void setup() {

	}

	@Test
	public void testMovein() {
		try {
			job.execute();
		} catch (Exception e) {
			Assert.fail(e.getMessage());
		}
	}
}
