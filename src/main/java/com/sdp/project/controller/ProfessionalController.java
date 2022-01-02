package com.sdp.project.controller;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.sdp.project.model.Category;
import com.sdp.project.model.Professional;
import com.sdp.project.model.Services;
import com.sdp.project.model.UserQuery;
import com.sdp.project.repository.ProfessionalRepo;
import com.sdp.project.repository.QueryRepository;
import com.sdp.project.repository.ServicesRepository;
import com.sdp.project.util.AmazonClient;

@Controller
public class ProfessionalController {

	@Autowired
	QueryRepository queryRepo;
	
	@Autowired
	ProfessionalRepo professionalRepo;
	
	@Autowired
	ServicesRepository serviceRepo;
	
	@Autowired
	MongoOperations mongoOperations;
	
	@Autowired
	AmazonClient amazonClient;
	
	@RequestMapping(value={"/professional", "/professional/home"})
	public ModelAndView home(HttpServletRequest request)
	{
		ModelAndView mv = new ModelAndView();
		HttpSession session = request.getSession(false);
		if (session != null)
		{
			if (session.getAttribute("role") != null && session.getAttribute("email") != null)
			{
				String role = session.getAttribute("role").toString();
				String email = session.getAttribute("email").toString();
				if (role.equals("Professional") && professionalRepo.findById(email).isPresent())
				{
					mv.setViewName("/Professional/home");
					mv.addObject("name", professionalRepo.findById(email).get().getName());
				}
				else
				{
					mv.setViewName("redirect:/professionalLogin");
					session.setAttribute("login", "false");
				}
			}
			else
			{
				mv.setViewName("redirect:/professionalLogin");
				session.setAttribute("login", "false");
			}
		}
		else
		{
			session = request.getSession();
			mv.setViewName("redirect:/professionalLogin");
			session.setAttribute("login", "false");
		}
		return mv;
	}
	
	@RequestMapping(value="/professional/about")
	public ModelAndView about(HttpServletRequest request)
	{
		ModelAndView mv = new ModelAndView();
		HttpSession session = request.getSession(false);
		if (session != null)
		{
			if (session.getAttribute("role") != null && session.getAttribute("email") != null)
			{
				String role = session.getAttribute("role").toString();
				String email = session.getAttribute("email").toString();
				if (role.equals("Professional") && professionalRepo.findById(email).isPresent())
				{
					mv.setViewName("/Professional/about");
					mv.addObject("name", professionalRepo.findById(email).get().getName());
				}
				else
				{
					mv.setViewName("redirect:/professionalLogin");
					session.setAttribute("login", "false");
				}
			}
			else
			{
				mv.setViewName("redirect:/professionalLogin");
				session.setAttribute("login", "false");
			}
		}
		else
		{
			session = request.getSession();
			mv.setViewName("redirect:/professionalLogin");
			session.setAttribute("login", "false");
		}
		return mv;
	}
	
	@RequestMapping(value="/professional/contact", method=RequestMethod.GET)
	public ModelAndView contactGet(HttpServletRequest request)
	{
		ModelAndView mv = new ModelAndView();
		HttpSession session = request.getSession(false);
		if (session != null)
		{
			if (session.getAttribute("role") != null && session.getAttribute("email") != null)
			{
				String role = session.getAttribute("role").toString();
				String email = session.getAttribute("email").toString();
				if (role.equals("Professional") && professionalRepo.findById(email).isPresent())
				{
					mv.setViewName("/Professional/contact");
					mv.addObject("name", professionalRepo.findById(email).get().getName());
					mv.addObject("email", email);
				}
				else
				{
					mv.setViewName("redirect:/professionalLogin");
					session.setAttribute("login", "false");
				}
			}
			else
			{
				mv.setViewName("redirect:/professionalLogin");
				session.setAttribute("login", "false");
			}
		}
		else
		{
			session = request.getSession();
			mv.setViewName("redirect:/professionalLogin");
			session.setAttribute("login", "false");
		}
		return mv;
	}
	
	@RequestMapping(value="/professional/contact", method=RequestMethod.POST)
	public ModelAndView contactPost(@RequestParam("subject") String subject, @RequestParam("message") String message, HttpServletRequest request)
	{
		ModelAndView mv = new ModelAndView();
		HttpSession session = request.getSession(false);
		if (session != null)
		{
			if (session.getAttribute("role") != null && session.getAttribute("email") != null)
			{
				String role = session.getAttribute("role").toString();
				String email = session.getAttribute("email").toString();
				if (role.equals("Professional") && professionalRepo.findById(email).isPresent())
				{
					mv.setViewName("/Professional/contact");
					try
					{
						UserQuery q = new UserQuery(professionalRepo.findById(email).get().getName(), email, subject, message);
						queryRepo.save(q);
						mv.addObject("status", "true");
						mv.addObject("message", "Query Sent Successfully!");
						mv.addObject("button", "OK");
						mv.addObject("url", "/professional/contact");
					}
					catch (Exception e)
					{
						mv.addObject("status", "false");
						mv.addObject("message", "Failed to send Query!");
						mv.addObject("button", "Retry");
						mv.addObject("url", "/professional/contact");
					}
				}
				else
				{
					mv.setViewName("redirect:/professionalLogin");
					session.setAttribute("login", "false");
				}
			}
			else
			{
				mv.setViewName("redirect:/professionalLogin");
				session.setAttribute("login", "false");
			}
		}
		else
		{
			session = request.getSession();
			mv.setViewName("redirect:/professionalLogin");
			session.setAttribute("login", "false");
		}
		return mv;
	}
	
	@RequestMapping(value="/professional/profile")
	public ModelAndView profile(HttpServletRequest request)
	{
		ModelAndView mv = new ModelAndView();
		HttpSession session = request.getSession(false);
		if (session != null)
		{
			if (session.getAttribute("role") != null && session.getAttribute("email") != null)
			{
				String role = session.getAttribute("role").toString();
				String email = session.getAttribute("email").toString();
				if (role.equals("Professional") && professionalRepo.findById(email).isPresent())
				{
					Professional p = professionalRepo.findById(email).get();
					List<Services> services = serviceRepo.findAll();
					String s = "";
					for (Services i : services)
					{
						if (i.getProfessional().contains(email))
							s = i.getServiceName();
					}
					mv.setViewName("/Professional/profile");
					mv.addObject("name", professionalRepo.findById(email).get().getName());
					mv.addObject("obj", p);
					mv.addObject("service", s);
				}
				else
				{
					mv.setViewName("redirect:/professionalLogin");
					session.setAttribute("login", "false");
				}
			}
			else
			{
				mv.setViewName("redirect:/professionalLogin");
				session.setAttribute("login", "false");
			}
		}
		else
		{
			session = request.getSession();
			mv.setViewName("redirect:/professionalLogin");
			session.setAttribute("login", "false");
		}
		return mv;
	}
	
	@RequestMapping(value="/professional/reset", method=RequestMethod.GET)
	public ModelAndView resetGet(HttpServletRequest request)
	{
		ModelAndView mv = new ModelAndView();
		HttpSession session = request.getSession(false);
		if (session != null)
		{
			if (session.getAttribute("role") != null && session.getAttribute("email") != null)
			{
				String role = session.getAttribute("role").toString();
				String email = session.getAttribute("email").toString();
				if (role.equals("Professional") && professionalRepo.findById(email).isPresent())
				{
					mv.setViewName("/Professional/reset");
					mv.addObject("name", professionalRepo.findById(email).get().getName());
				}
				else
				{
					mv.setViewName("redirect:/professionalLogin");
					session.setAttribute("login", "false");
				}
			}
			else
			{
				mv.setViewName("redirect:/professionalLogin");
				session.setAttribute("login", "false");
			}
		}
		else
		{
			session = request.getSession();
			mv.setViewName("redirect:/professionalLogin");
			session.setAttribute("login", "false");
		}
		return mv;
	}
	
	@RequestMapping(value="/professional/reset", method=RequestMethod.POST)
	public ModelAndView resetPost(@RequestParam("old_password") String old, @RequestParam("re_password") String password, HttpServletRequest request)
	{
		ModelAndView mv = new ModelAndView();
		HttpSession session = request.getSession(false);
		if (session != null)
		{
			if (session.getAttribute("role") != null && session.getAttribute("email") != null)
			{
				String role = session.getAttribute("role").toString();
				String email = session.getAttribute("email").toString();
				if (role.equals("Professional") && professionalRepo.findById(email).isPresent())
				{
					Professional p = professionalRepo.findById(email).get();
					if (p.getPassword().equals(p.hash(old)))
					{
						if (p.getPassword().equals(p.hash(password)))
						{
							mv.setViewName("/Professional/reset");
							mv.addObject("name", professionalRepo.findById(email).get().getName());
							mv.addObject("status", "false");
							mv.addObject("message", "New Password same as Old Password!");
							mv.addObject("button", "Retry");
							mv.addObject("url", "/professional/reset");
						}
						else
						{
							p.setPassword(password);
							professionalRepo.save(p);
							mv.setViewName("/Professional/profile");
							mv.addObject("name", professionalRepo.findById(email).get().getName());
							mv.addObject("status", "true");
							mv.addObject("message", "Password Reset Successfully!");
							mv.addObject("button", "OK");
							mv.addObject("url", "/professional/profile");
						}
					}
					else
					{
						mv.setViewName("/Professional/reset");
						mv.addObject("name", professionalRepo.findById(email).get().getName());
						mv.addObject("status", "false");
						mv.addObject("message", "Wrong Old Password!");
						mv.addObject("button", "Retry");
						mv.addObject("url", "/professional/reset");
					}
				}
				else
				{
					mv.setViewName("redirect:/professionalLogin");
					session.setAttribute("login", "false");
				}
			}
			else
			{
				mv.setViewName("redirect:/professionalLogin");
				session.setAttribute("login", "false");
			}
		}
		else
		{
			session = request.getSession();
			mv.setViewName("redirect:/professionalLogin");
			session.setAttribute("login", "false");
		}
		return mv;
	}
	
	@RequestMapping(value="/professional/update", method=RequestMethod.GET)
	public ModelAndView updateProfileGet(HttpServletRequest request)
	{
		ModelAndView mv = new ModelAndView();
		HttpSession session = request.getSession(false);
		if (session != null)
		{
			if (session.getAttribute("role") != null && session.getAttribute("email") != null)
			{
				String role = session.getAttribute("role").toString();
				String email = session.getAttribute("email").toString();
				if (role.equals("Professional") && professionalRepo.findById(email).isPresent())
				{
					Professional p = professionalRepo.findById(email).get();
					List<Services> services = serviceRepo.findAll();
					String service = "";
					for (Services i : services)
					{
						if (i.getProfessional().contains(email))
							service = i.getServiceName();
					}
					List<String> experiences = Arrays.asList("< 1 Year", "1 Year", "2 Years", "3 Years", "4 Years", "5 Years", "6 Years", "7 Years", "8 Years", "9 Years", "10 Years", "> 10 Years");
					mv.setViewName("/Professional/update");
					mv.addObject("name", professionalRepo.findById(email).get().getName());
					mv.addObject("obj", p);
					mv.addObject("service", service);
					mv.addObject("experiences", experiences);
					mv.addObject("experience", p.getExperience());
				}
				else
				{
					mv.setViewName("redirect:/professionalLogin");
					session.setAttribute("login", "false");
				}
			}
			else
			{
				mv.setViewName("redirect:/professionalLogin");
				session.setAttribute("login", "false");
			}
		}
		else
		{
			session = request.getSession();
			mv.setViewName("redirect:/professionalLogin");
			session.setAttribute("login", "false");
		}
		return mv;
	}
	
	@RequestMapping(value="/professional/update", method=RequestMethod.POST)
	public ModelAndView updateProfilePost(HttpServletRequest request, @RequestParam("name") String name, @RequestParam("address") String address, @RequestParam("city") String city, @RequestParam("zip") int zip, @RequestParam("contact") long contact, @RequestParam("profilePic") MultipartFile profilePic, @RequestParam("experience") String experience) throws IOException
	{
		ModelAndView mv = new ModelAndView();
		HttpSession session = request.getSession(false);
		if (session != null)
		{
			if (session.getAttribute("role") != null && session.getAttribute("email") != null)
			{
				String role = session.getAttribute("role").toString();
				String email = session.getAttribute("email").toString();
				if (role.equals("Professional") && professionalRepo.findById(email).isPresent())
				{
					try
					{
						Professional p = professionalRepo.findById(email).get();
						p.setName(name);
						p.setAddress(address);
						p.setCity(city);
						p.setZip(zip);
						p.setContact(contact);
						p.setExperience(experience);
						String url = "";
						if (!profilePic.isEmpty())
						{
							File file = new File(System.getProperty("java.io.tmpdir")+"/"+p.getProfessionalId()+p.getExtension(profilePic.getOriginalFilename()));
							profilePic.transferTo(file);
							url = amazonClient.uploadFile(file, file.getName());
							p.setProfilePic(url);
						}
						professionalRepo.save(p);
						
						mv.setViewName("/Professional/profile");
						mv.addObject("status", "true");
						mv.addObject("message", "Profile Updated Successfully!");
						mv.addObject("button", "OK");
						mv.addObject("url", "/professional/profile");
					}
					catch (Exception e)
					{
						mv.setViewName("/Professional/profile");
						mv.addObject("status", "false");
						mv.addObject("message", "Failed to Update Profile!");
						mv.addObject("button", "Retry");
						mv.addObject("url", "/professional/profile");
					}
				}
				else
				{
					mv.setViewName("redirect:/professionalLogin");
					session.setAttribute("login", "false");
				}
			}
			else
			{
				mv.setViewName("redirect:/professionalLogin");
				session.setAttribute("login", "false");
			}
		}
		else
		{
			session = request.getSession();
			mv.setViewName("redirect:/professionalLogin");
			session.setAttribute("login", "false");
		}
		return mv;
	}
	
}
