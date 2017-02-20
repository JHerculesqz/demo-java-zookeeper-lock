package com.huawei._1_fw.component.threadpool.subprovider;

import java.util.UUID;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.springframework.stereotype.Component;

@Component
public class ThreadPoolProvider {
	// #region Fields

	private ThreadPoolExecutor threadPool4Bu;

	private ThreadPoolExecutor threadPool4Sys;

	// #endregion

	// #region init

	public void init(int iCorePoolSize4Bu, int iMaxPoolSize4Bu, long iKeepAliveSecond4Bu, int iQueueSize4Bu,
			int iCorePoolSize4Sys, int iMaxPoolSize4Sys, long iKeepAliveSecond4Sys, int iQueueSize4Sys) {
		// 1.init threadPool4Bu
		this.threadPool4Bu = new ThreadPoolExecutor(iCorePoolSize4Bu, iMaxPoolSize4Bu, iKeepAliveSecond4Bu,
				TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(iQueueSize4Bu), new ThreadFactory() {
					@Override
					public Thread newThread(Runnable r) {
						Thread oThread = new Thread(r);
						oThread.setName("BuPool-" + UUID.randomUUID());
						return oThread;
					}
				});

		// 2.init threadPool4Sys
		this.threadPool4Sys = new ThreadPoolExecutor(iCorePoolSize4Sys, iMaxPoolSize4Sys, iKeepAliveSecond4Sys,
				TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(iQueueSize4Sys), new ThreadFactory() {
					@Override
					public Thread newThread(Runnable r) {
						Thread oThread = new Thread(r);
						oThread.setName("SysPool-" + UUID.randomUUID());
						return oThread;
					}
				});
	}

	// #endregion

	// #region submit4Bu

	public void submit4Bu(Runnable oTask) {
		if (this.threadPool4Bu != null) {
			this.threadPool4Bu.submit(oTask);
		}
	}

	// #endregion

	// #region submit4Sys

	public void submit4Sys(Runnable oTask) {
		if (this.threadPool4Sys != null) {
			this.threadPool4Sys.submit(oTask);
		}
	}

	// #endregion
}
