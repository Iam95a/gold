package com.cn.chen.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cn.chen.log.Log;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/chen")
public class ChenController {
	@Log
	@RequestMapping("/getName")
	@ResponseBody
	public String getName(HttpServletRequest request,HttpServletResponse response){
		
		return "chen";
	}

}
