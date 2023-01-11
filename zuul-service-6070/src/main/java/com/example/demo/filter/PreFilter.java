package com.example.demo.filter;

import javax.servlet.http.HttpServletRequest;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

public class PreFilter extends ZuulFilter {

	@Override
	public boolean shouldFilter() {
		return true;
	}

	@Override
	public Object run() throws ZuulException {
		
		System.out.println("PreFilter");
		// 取得 request 物件
		RequestContext ctx = RequestContext.getCurrentContext();
		HttpServletRequest request = ctx.getRequest();
		// 分析 request 的 URL
		//request.getRequestURL();
		// 取得 request 的 參數
		//request.getParameter("xxxx");
		// 停止路由
		//ctx.setSendZuulResponse(false);
		//ctx.setResponseStatusCode(500); // 若不加此行網頁會變空白
		// 傳遞參數給下一個 filter
		ctx.set("key", "hello filter");
		return null;
	}

	@Override
	public String filterType() {
		return "pre";
	}

	@Override
	public int filterOrder() {
		// TODO Auto-generated method stub
		return 0;
	}

}
