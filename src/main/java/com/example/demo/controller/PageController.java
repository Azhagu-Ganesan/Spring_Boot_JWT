package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PageController {
	@RequestMapping("index")
	public String page()
	{
		System.out.println("index");
		return "index";
		
	}
	@RequestMapping("index1")
	public String page1()
	{
		System.out.println("index1");
		return "index_1";
		
	}

	@RequestMapping("index2")
	public String page2()
	{
		System.out.println("index2");
		return "index_2";
		
	}

	@RequestMapping("index3")
	public String page3()
	{
		System.out.println("index3");
		return "index_3";
		
	}


}
