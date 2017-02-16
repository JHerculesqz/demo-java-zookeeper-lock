package com.huawei._1_fw.component.threadpool;

import com.huawei._1_fw.component.threadpool.subprovider.ThreadPoolProvider;
import com.huawei._1_fw.core.ioc.IOCUtils;

public class ThreadPoolUtils {
	// #region getProvider

	public static ThreadPoolProvider getProvider() {
		return IOCUtils.getInstance().getBean(ThreadPoolProvider.class);
	}

	// #endregion
}
