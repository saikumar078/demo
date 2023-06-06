package com.jsp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.jsp.dao.EmployeeDao;
import com.jsp.dto.Employee;
import com.jsp.service.Service;

@Controller
public class EmployeeController {

	@Autowired
	Service service;

	@RequestMapping("/loadEmployee")
	public ModelAndView load() {

		ModelAndView andView = new ModelAndView("saveEmployee.jsp");
		andView.addObject("load", new Employee());
		return andView;

	}

	@RequestMapping("/saveEmployee")
	public ModelAndView saveEmployee(@ModelAttribute Employee employee) {
		Employee employee2 = service.save(employee);
		ModelAndView andView = new ModelAndView("/displayemployee");
//		andView.addObject("save", "data is saved");

		return andView;
	}

	@RequestMapping("/displayemployee")
	public ModelAndView displayall() {
		List<Employee> list = service.getall();
		ModelAndView andView = new ModelAndView("displayemployees.jsp");
		andView.addObject("list", list);
		return andView;

	}

	@RequestMapping("/editemployee")
	public ModelAndView edit(@RequestParam int id) {
		Employee employee = service.getbyid(id);
		ModelAndView andView = new ModelAndView("updateemployee.jsp");
		andView.addObject("emp", employee);

		return andView;
	}

	@RequestMapping("/updateemployee")
	public ModelAndView update(@ModelAttribute Employee employee) {
		Employee employee2=service.updateEmployee(employee);
		ModelAndView andView=new ModelAndView("/displayemployee");
		return andView;
	}
	@RequestMapping("/deleteemployee")
	public ModelAndView delete(@RequestParam int id) {
		
		service.deleteEmployee(id);
		ModelAndView andView=new ModelAndView("/displayemployee");
		
		return andView;
	}

}
