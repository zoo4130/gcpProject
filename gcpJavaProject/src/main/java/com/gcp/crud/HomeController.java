package com.gcp.crud;


import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


import com.gcp.crud.CRUDService;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	@Autowired
	private CRUDService crudService;
	
	/**
	 * home.jsp VIEW
	 * @throws Exception 
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(HttpServletRequest request, HttpServletResponse response) throws Exception {
	
		System.out.println("===============================");
		System.out.println("Java CRUD test");
		
		
		return "home";
	}
	
	/**
	 * java Select
	 */ 
	@RequestMapping(value = "/select", method = RequestMethod.POST)
	public void javaSelect(HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		System.out.println("===============================");
		System.out.println("Java CRUD Select");
		
		Map<String,Object> param = null;
		crudService.select(param);
	}
	
	/**
	 * java Insert
	 */ 
	@RequestMapping(value = "/insert", method = RequestMethod.POST)
	public void javaInsert(HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		System.out.println("===============================");
		System.out.println("Java CRUD Insert");
		
		Map<String,Object> param = new HashMap<String,Object>();
		param.put("no", "004");
		param.put("name", "김철수");
		param.put("age", "15");
		
		crudService.insert(param);
		
		//재조회
		crudService.select(null);
	}
	
	/**
	 * java Update
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public void javaUpdate(HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		System.out.println("===============================");
		System.out.println("Java CRUD Update");
		
		Map<String,Object> param = new HashMap<String,Object>();
		param.put("no", "002");
		param.put("age", "100");
		
		crudService.update(param);
		
		//재조회
		crudService.select(null);
	}
	
	/**
	 * java Delete
	 */ 
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public void javaDelete(HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		System.out.println("===============================");
		System.out.println("Java CRUD Delete");
		
		Map<String,Object> param = new HashMap<String,Object>();
		param.put("no", "004");
		
		crudService.delete(param);
		
		//재조회
		crudService.select(null);
	}	
}
