package com.huawei._1_fw.component.distribute_lock;

import com.huawei._1_fw.component.distribute_lock.subprovider.debug.DistributeLockDebugProvider;
import com.huawei._1_fw.component.distribute_lock.subprovider.lock.DistributeLockProvider;

public class DistributeLockUtils {
	// #region getProvider

	public static DistributeLockProvider getProvider(String strZkConfig, String strLockName) {
		DistributeLockProvider oProvider = new DistributeLockProvider(strZkConfig, strLockName);
		return oProvider;
	}

	// #endregion

	// #region lock

	public static void lock(DistributeLockProvider oProvider) {
		oProvider.lock();
	}

	// #endregion

	// #region unlock

	public static void unlock(DistributeLockProvider oProvider) {
		oProvider.unlock();
	}

	// #endregion

	// #region getDebugProvider

	public static DistributeLockDebugProvider getDebugProvider() {
		return new DistributeLockDebugProvider();
	}

	// #endregion
}
