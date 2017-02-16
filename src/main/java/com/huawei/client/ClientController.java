package com.huawei.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ClientController {
	// #region Fields

	@Autowired
	private ClientService clientService;

	// #endregion

	// #region hello

	@RequestMapping("/hello")
	@ResponseBody
	public String hello() {
		return this.clientService.hello();
	}

	// #endregion
}
