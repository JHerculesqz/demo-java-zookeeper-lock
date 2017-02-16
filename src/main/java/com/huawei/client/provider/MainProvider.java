package com.huawei.client.provider;

import com.huawei.client.provider.subprovider.ClientSubProvider;

public class MainProvider {
	// #region hello

	public static String hello() {
		ClientSubProvider.hello();

		return "ok";
	}

	// #endregion
}
