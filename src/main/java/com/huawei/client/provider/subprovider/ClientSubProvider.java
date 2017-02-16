package com.huawei.client.provider.subprovider;

import com.huawei._1_fw.component.distribute_lock.DistributeLockUtils;
import com.huawei._1_fw.component.distribute_lock.subprovider.debug.DistributeLockDebugProvider.ConcurrentTask;
import com.huawei._1_fw.component.distribute_lock.subprovider.lock.DistributeLockProvider;
import com.huawei._1_fw.component.latch.ILatchCallback;
import com.huawei._1_fw.component.latch.LatchUtils;

public class ClientSubProvider {
	public static void hello() {
		ConcurrentTask[] lstTask = new ConcurrentTask[1];
		for (int i = 0; i < lstTask.length; i++) {
			ConcurrentTask oTask = new ConcurrentTask() {
				public void run() {
					DistributeLockProvider oLock = null;
					try {
						oLock = DistributeLockUtils.getProvider("127.0.0.1:2181", "getActor");
						oLock.lock();

						LatchUtils oLatchUtils = new LatchUtils();
						oLatchUtils.run(new ILatchCallback() {
							@Override
							public void run() {
								// #region Ä£ÄâÒµÎñ¿ò¼Ü

								try {
									Thread.sleep(1000);
								} catch (InterruptedException e) {
									e.printStackTrace();
								}
								System.out.println("heiheihei...");

								// #endregion
							}
						}, 3000);
					} catch (Exception e) {
						e.printStackTrace();
					} finally {
						oLock.unlock();
					}
				}
			};
			lstTask[i] = oTask;
		}
		DistributeLockUtils.getDebugProvider().init(lstTask);
	}
}
