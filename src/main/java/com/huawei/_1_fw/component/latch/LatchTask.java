package com.huawei._1_fw.component.latch;

import java.util.concurrent.CountDownLatch;

public class LatchTask implements Runnable {
	// #region Fields

	private ILatchCallback iLatchCallback;
	private CountDownLatch latch;
	private Thread currentThread;

	// #endregion

	// #region Construciton

	public LatchTask(ILatchCallback oILatchCallback, CountDownLatch oLatch) {
		this.iLatchCallback = oILatchCallback;
		this.latch = oLatch;
	}

	// #endregion

	// #region run

	@Override
	public void run() {
		try {
			currentThread = Thread.currentThread();
			iLatchCallback.run();
		} finally {
			latch.countDown();
		}
	}

	// #endregion

	// #region stop

	public void stop() {
		currentThread.stop();
	}

	// #endregion
}
