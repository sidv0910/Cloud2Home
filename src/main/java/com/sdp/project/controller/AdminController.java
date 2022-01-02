package com.sdp.project.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.sdp.project.model.Category;
import com.sdp.project.model.Services;
import com.sdp.project.model.SubCategory;
import com.sdp.project.repository.ServicesRepository;
import com.sdp.project.util.AmazonClient;

@Controller
public class AdminController {

	@Autowired
	AmazonClient amazonClient;
	
	@Autowired
	ServicesRepository serviceRepo;
	
	@RequestMapping(value={"/admin", "/admin/home"}, method=RequestMethod.GET)
	public ModelAndView homeGet(HttpServletRequest request)
	{
		ModelAndView mv = new ModelAndView();
		HttpSession session = request.getSession(false);
		if (session != null)
		{
			if (session.getAttribute("role") != null && session.getAttribute("username") != null)
			{
				String role = session.getAttribute("role").toString();
				String username = session.getAttribute("username").toString();
				if (role.equals("admin") && username.equals("ProjectAdmin"))
				{
					mv.setViewName("/Admin/home");
				}
				else
				{
					session.invalidate();
					mv.setViewName("/Admin/login");
					mv.addObject("status", "false");
					mv.addObject("message", "Wrong Admin Credentials!");
					mv.addObject("button", "Retry");
					mv.addObject("url", "/admin");
				}
			}
			else
			{
				session.invalidate();
				mv.setViewName("/Admin/login");
			}
		}
		else
		{
			mv.setViewName("/Admin/login");
		}
		return mv;
	}
	
	@RequestMapping(value={"/admin", "/admin/home"}, method=RequestMethod.POST)
	public ModelAndView homePost(HttpServletRequest request, @RequestParam("username") String username, @RequestParam("password") String password)
	{
		
		ModelAndView mv = new ModelAndView();
		if (username.equals("ProjectAdmin") && password.equals("ProjectPassword"))
		{
			HttpSession session = request.getSession();
			session.setAttribute("role", "admin");
			session.setAttribute("username", "ProjectAdmin");
			mv.setViewName("/Admin/login");
			mv.addObject("status", "true");
			mv.addObject("message", "Login Successful!");
			mv.addObject("button", "OK");
			mv.addObject("url", "/admin");
		}
		else
		{
			mv.setViewName("/Admin/login");
			mv.addObject("status", "false");
			mv.addObject("message", "Wrong Admin Credentials!");
			mv.addObject("button", "Retry");
			mv.addObject("url", "/admin");
		}
		return mv;
	}
	
	@RequestMapping(value="/admin/addServices", method=RequestMethod.GET)
	public ModelAndView adminAddServicesGet(HttpServletRequest request)
	{
		ModelAndView mv = new ModelAndView();
		HttpSession session = request.getSession(false);
		if (session != null)
		{
			if (session.getAttribute("role") != null && session.getAttribute("username") != null)
			{
				String role = session.getAttribute("role").toString();
				String username = session.getAttribute("username").toString();
				if (role.equals("admin") && username.equals("ProjectAdmin"))
				{
					mv.setViewName("/Admin/addServices");
				}
				else
				{
					session.invalidate();
					mv.setViewName("redirect:/Admin");
				}
			}
			else
			{
				session.invalidate();
				mv.setViewName("redirect:/Admin");
			}
		}
		else
		{
			mv.setViewName("redirect:/Admin");
		}
		return mv;
	}
	
	@RequestMapping(value="/admin/addServices", method=RequestMethod.POST)
	public ModelAndView adminAddServicesPost(HttpServletRequest request, @RequestParam("serviceimage") MultipartFile serviceimage, @RequestParam("service") String service, @RequestParam("category") String category, @RequestParam("subcategory") String subcategory, @RequestParam("price") double price) throws Exception, IOException
	{
		ModelAndView mv = new ModelAndView();
		HttpSession session = request.getSession(false);
		if (session != null)
		{
			if (session.getAttribute("role") != null && session.getAttribute("username") != null)
			{
				String role = session.getAttribute("role").toString();
				String username = session.getAttribute("username").toString();
				if (role.equals("admin") && username.equals("ProjectAdmin"))
				{
					if (serviceRepo.findById(service).isPresent())
					{
						mv.setViewName("/Admin/addServices");
						mv.addObject("status", "false");
						mv.addObject("message", "Service Already Exists!");
						mv.addObject("button", "Retry");
						mv.addObject("url", "/admin/addServices");
					}
					else
					{
						Services s = new Services();
						
						File file = new File(System.getProperty("java.io.tmpdir")+"/"+service+s.getExtension(serviceimage.getOriginalFilename()));
						serviceimage.transferTo(file);
						
						SubCategory sc = new SubCategory(subcategory, price);
						Category c = new Category(category, Arrays.asList(sc));
						Services serv = new Services(service, amazonClient.uploadServiceImage(file, file.getName()), Arrays.asList(c));
						
						serviceRepo.save(serv);
						
						mv.setViewName("/Admin/home");
						mv.addObject("status", "true");
						mv.addObject("message", "Service Added Successfully!");
						mv.addObject("button", "OK");
						mv.addObject("url", "/admin");
					}
				}
				else
				{
					session.invalidate();
					mv.setViewName("redirect:/Admin");
				}
			}
			else
			{
				session.invalidate();
				mv.setViewName("redirect:/Admin");
			}
		}
		else
		{
			mv.setViewName("redirect:/Admin");
		}
		return mv;
	}
	
	@RequestMapping(value="/admin/addCategory", method=RequestMethod.GET)
	public ModelAndView adminAddCategoryGet(HttpServletRequest request)
	{
		ModelAndView mv = new ModelAndView();
		HttpSession session = request.getSession(false);
		if (session != null)
		{
			if (session.getAttribute("role") != null && session.getAttribute("username") != null)
			{
				String role = session.getAttribute("role").toString();
				String username = session.getAttribute("username").toString();
				if (role.equals("admin") && username.equals("ProjectAdmin"))
				{
					List<String> services = new ArrayList<String>();
					for (Services s : serviceRepo.findAll())
					{
						services.add(s.getServiceName());
					}
					mv.setViewName("/Admin/addCategory");
					mv.addObject("services", services);
				}
				else
				{
					session.invalidate();
					mv.setViewName("redirect:/Admin");
				}
			}
			else
			{
				session.invalidate();
				mv.setViewName("redirect:/Admin");
			}
		}
		else
		{
			mv.setViewName("redirect:/Admin");
		}
		return mv;
	}
	
}
