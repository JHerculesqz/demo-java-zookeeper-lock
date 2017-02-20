package com.huawei._1_fw.component.latch;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import com.huawei._1_fw.component.threadpool.ThreadPoolUtils;

public class LatchUtils {
	// #region Fields

	private CountDownLatch latch = new CountDownLatch(1);

	// #endregion

	// #region run

	public boolean run(ILatchCallback oILatchCallback, int iTimeoutMs) {
		LatchTask oTask = new LatchTask(oILatchCallback, latch);
		ThreadPoolUtils.getProvider().init(2, 4, 3, 3, 2, 4, 3, 3);
		ThreadPoolUtils.getProvider().submit4Bu(oTask);
		boolean bInTime = wait(iTimeoutMs);
		if (!bInTime) {
			System.out.println("latch timeout...");
			oTask.stop();
		}

		return bInTime;
	}

	// #region _wait

	private boolean wait(int iTimeoutMs) {
		boolean bInTime = false;

		try {
			bInTime = latch.await(iTimeoutMs, TimeUnit.MILLISECONDS);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		return bInTime;
	}

	// #endregion

	// #endregion
}
