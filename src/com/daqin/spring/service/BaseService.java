package com.daqin.spring.service;

import com.daqin.spring.domain.AppProxyResultDo;
import com.daqin.spring.util.JsonUtil;

public class BaseService {
	protected String newErrorResult() {
		return newErrorResult(null);
	}
	
	protected String newErrorResult(String message) {
		AppProxyResultDo apr = new AppProxyResultDo();
		apr.setError(true);
		apr.setErrorMessage(message);
		return JsonUtil.Object2Json(apr);
	}
	
	protected String newSuccessResult(Object result) {
		AppProxyResultDo apr = new AppProxyResultDo();
		apr.setError(false);
		apr.setResut(result);
		return JsonUtil.Object2Json(apr);
	}
}
