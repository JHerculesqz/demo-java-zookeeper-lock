package com.huawei._1_fw.component.threadpool.subprovider;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.springframework.stereotype.Component;

@Component
public class ThreadPoolProvider {
	// #region Fields

	private ExecutorService threadPool4Bu = Executors.newFixedThreadPool(1);
	private ExecutorService threadPool4Sys = Executors.newFixedThreadPool(1);

	// #endregion

	// #region submit4Bu

	public void submit4Bu(Runnable oTask) {
		this.threadPool4Bu.submit(oTask);
	}

	// #endregion

	// #region submit4Sys

	public void submit4Sys(Runnable oTask) {
		this.threadPool4Sys.submit(oTask);
	}

	// #endregion
}
