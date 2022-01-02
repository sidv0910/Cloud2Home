package com.sdp.project.controller;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.sdp.project.model.Category;
import com.sdp.project.model.Mail;
import com.sdp.project.model.Professional;
import com.sdp.project.model.Services;
import com.sdp.project.model.User;
import com.sdp.project.model.UserQuery;
import com.sdp.project.repository.ProfessionalRepo;
import com.sdp.project.repository.QueryRepository;
import com.sdp.project.repository.ServicesRepository;
import com.sdp.project.repository.UserRepository;
import com.sdp.project.util.AmazonClient;

@Controller
public class HomeController 
{

	@Autowired
	UserRepository userRepo;
	
	@Autowired
	QueryRepository queryRepo;
	
	@Autowired
	ServicesRepository serviceRepo;
	
	@Autowired
	MongoOperations mongoOperations;
	
	@Autowired
	ProfessionalRepo professionalRepo;
	
	@Autowired
	AmazonClient amazonClient;
	
	HashMap<String, String> otp = new HashMap<String, String>();
	HashMap<String, Date> timeout = new HashMap<String, Date>();
	Mail m = new Mail();
	
	@RequestMapping(value={"/", "/home"})
	public ModelAndView home()
	{
		ModelAndView mv = new ModelAndView();
		mv.setViewName("/Home/home");
		return mv;
	}
	
	@RequestMapping(value="/signup", method = RequestMethod.GET)
	public ModelAndView signupGet()
	{
		ModelAndView mv = new ModelAndView();
		mv.setViewName("/Home/signup");
		mv.addObject("status", "none");
		return mv;
	}
	
	@RequestMapping(value="/signup", method = RequestMethod.POST)
	public ModelAndView signupPost(User u)
	{
		ModelAndView mv = new ModelAndView();
		try
		{
			if (userRepo.findById(u.getEmail()).isPresent())
			{
				mv.setViewName("/Home/signup");
				mv.addObject("status", "false");
				mv.addObject("message", "Account Already Exists!");
				mv.addObject("button", "OK");
				mv.addObject("url", "/signup");
			}
			else
			{
				userRepo.save(u);
				mv.setViewName("/Home/login");
				mv.addObject("status", "true");
				mv.addObject("message", "Sign Up Successful!");
				mv.addObject("button", "OK");
				mv.addObject("url", "/login");
			}
		}
		catch (Exception e)
		{
			mv.setViewName("/Home/signup");
			mv.addObject("status", "false");
			mv.addObject("message", "Sign Up Failed!");
			mv.addObject("button", "Retry");
			mv.addObject("url", "/signup");
		}
		return mv;
	}
	
	@RequestMapping(value="/login", method = RequestMethod.GET)
	public ModelAndView loginGet(HttpServletRequest request)
	{
		ModelAndView mv = new ModelAndView();
		mv.setViewName("/Home/login");
		
		HttpSession session = request.getSession(false);
		if (session != null)
		{
			if (session.getAttribute("login") != null)
			{
				mv.addObject("status", "false");
				mv.addObject("message", "Please Login First!");
				mv.addObject("button", "OK");
				mv.addObject("url", "/login");
				session.removeAttribute("login");
			}
		}
		
		return mv;
	}
	
	@RequestMapping(value="/login", method = RequestMethod.POST)
	public ModelAndView loginPost(@RequestParam("email") String email, @RequestParam("password") String password, HttpServletRequest request)
	{
		ModelAndView mv = new ModelAndView();
		try
		{
			if (userRepo.findById(email).isPresent())
			{
				User u = userRepo.findById(email).get();
				if (u.getPassword().equals(User.hash(password)))
				{
					HttpSession session = request.getSession();
					session.setAttribute("role", "User");
					session.setAttribute("email", email);
					
					mv.setViewName("/User/home");
					mv.addObject("status", "true");
					mv.addObject("message", "Login Successful!");
					mv.addObject("button", "OK");
					mv.addObject("url", "/user");
				}
				else
				{
					mv.setViewName("/Home/login");
					mv.addObject("status", "false");
					mv.addObject("message", "Wrong Password!");
					mv.addObject("button", "Retry");
					mv.addObject("url", "/login");
				}
			}
			else
			{
				mv.setViewName("/Home/login");
				mv.addObject("status", "false");
				mv.addObject("message", "No User Account found for the given Email");
				mv.addObject("button", "Retry");
				mv.addObject("url", "/login");
			}
		}
		catch (Exception e)
		{
			mv.setViewName("/Home/login");
			mv.addObject("status", "false");
			mv.addObject("message", "Login Failed!");
			mv.addObject("button", "Retry");
			mv.addObject("url", "/login");
		}
		return mv;
	}
	
	@RequestMapping(value="/contact", method = RequestMethod.GET)
	public ModelAndView contactGet()
	{
		ModelAndView mv = new ModelAndView();
		mv.setViewName("/Home/contact");
		mv.addObject("status", "none");
		return mv;
	}
	
	@RequestMapping(value="/contact", method = RequestMethod.POST)
	public ModelAndView contactPost(UserQuery q)
	{
		ModelAndView mv = new ModelAndView();
		mv.setViewName("/Home/contact");
		try
		{
			queryRepo.save(q);
			mv.addObject("status", "true");
			mv.addObject("message", "Query Sent Successfully!");
			mv.addObject("button", "OK");
			mv.addObject("url", "/contact");
		}
		catch (Exception e)
		{
			mv.addObject("status", "false");
			mv.addObject("message", "Failed to send Query!");
			mv.addObject("button", "Retry");
			mv.addObject("url", "/contact");
		}
		return mv;
	}
	
	@RequestMapping(value="/about")
	public String about()
	{
		return "/Home/about";
	}
	
	@RequestMapping(value="/register", method = RequestMethod.GET)
	public ModelAndView registerGet()
	{
		ModelAndView mv = new ModelAndView();
		mv.setViewName("/Home/register");
		
		List<Services> s = mongoOperations.findAll(Services.class, "services");
		mv.addObject("services", s);
		return mv;
	}
	
	@RequestMapping(value="/register", method = RequestMethod.POST)
	public ModelAndView registerPost(@RequestParam("name") String name, @RequestParam("address") String address, @RequestParam("city") String city, @RequestParam("zip") int zip, @RequestParam("email") String email, @RequestParam("contact") long contact, @RequestParam("password") String password, @RequestParam("profilePic") MultipartFile profilePic, @RequestParam("service") String service, @RequestParam("experience") String experience) throws IOException
	{
		ModelAndView mv = new ModelAndView();
		List<Services> s = mongoOperations.findAll(Services.class, "services");
		mv.addObject("services", s);
		try
		{
			if (professionalRepo.findById(email).isPresent())
			{
				mv.setViewName("/Home/register");
				mv.addObject("status", "false");
				mv.addObject("message", "A Professional's Account already exists with the given Email Address!");
				mv.addObject("button", "Retry");
				mv.addObject("url", "/register");
			}
			else if (userRepo.findById(email).isPresent())
			{
				mv.setViewName("/Home/register");
				mv.addObject("status", "false");
				mv.addObject("message", "A User Account already exists with the given Email Address!");
				mv.addObject("button", "Retry");
				mv.addObject("url", "/register");
			}
			else
			{				
				Query query = new Query();
				query.fields().include("professionalId");
				List<String> prof = mongoOperations.find(query, Professional.class).stream().map(Professional::getProfessionalId).collect(Collectors.toList());
				
				Professional p = new Professional();
				String professionalId = p.generateProfessionalId();
				while (prof.contains(professionalId))
				{
					professionalId = p.generateProfessionalId();
				}
				
				File file = new File(System.getProperty("java.io.tmpdir")+"/"+professionalId+p.getExtension(profilePic.getOriginalFilename()));
				profilePic.transferTo(file);
				
				p.setProfessionalId(professionalId);
				p.setEmail(email);
				p.setName(name);
				p.setAddress(address);
				p.setCity(city);
				p.setZip(zip);
				p.setContact(contact);
				p.setPassword(password);
				p.setProfilePic(amazonClient.uploadFile(file, file.getName()));
				p.setExperience(experience);
				p.setApproved(false);
				
				professionalRepo.save(p);
				
				Services serv = serviceRepo.findById(service).get();
				List<String> professional = serv.getProfessional();
				professional.add(email);
				serv.setProfessional(professional);
				serviceRepo.save(serv);
				
				mv.setViewName("/Home/professionalLogin");
				mv.addObject("status", "true");
				mv.addObject("message", "Registration Successful!<br><br>Your profile is under approval. Our team will contact you soon for approving your details.<br><br>Once your account is approved, you can login to your account.");
				mv.addObject("button", "OK");
				mv.addObject("url", "/professionalLogin");
			}
		}
		catch (Exception e)
		{
			mv.setViewName("/Home/register");
			mv.addObject("status", "false");
			mv.addObject("message", "Registration Failed!");
			mv.addObject("button", "Retry");
			mv.addObject("url", "/register");
		}
		return mv;
	}
	
	@RequestMapping(value="/professionalLogin", method = RequestMethod.GET)
	public ModelAndView professionalLoginGet(HttpServletRequest request)
	{
		ModelAndView mv = new ModelAndView();
		mv.setViewName("/Home/professionalLogin");
		
		HttpSession session = request.getSession(false);
		if (session != null)
		{
			if (session.getAttribute("login") != null)
			{
				mv.addObject("status", "false");
				mv.addObject("message", "Please Login First!");
				mv.addObject("button", "OK");
				mv.addObject("url", "/professionalLogin");
				session.removeAttribute("login");
			}
		}
		
		return mv;
	}
	
	@RequestMapping(value="/professionalLogin", method = RequestMethod.POST)
	public ModelAndView professionalLoginPost(@RequestParam("professionalId") String professionalId, @RequestParam("email") String email, @RequestParam("password") String password, HttpServletRequest request)
	{
		ModelAndView mv = new ModelAndView();
		try
		{
			if (professionalRepo.findById(email).isPresent())
			{
				Professional p = professionalRepo.findById(email).get();
				if (p.getProfessionalId().equals(professionalId) && p.getPassword().equals(Professional.hash(password)))
				{
					if (p.isApproved())
					{
						HttpSession session = request.getSession();
						session.setAttribute("role", "Professional");
						session.setAttribute("email", email);
						
						mv.setViewName("/Professional/home");
						mv.addObject("status", "true");
						mv.addObject("message", "Login Successful!");
						mv.addObject("button", "OK");
						mv.addObject("url", "/professional");
					}
					else
					{
						mv.setViewName("/Home/professionalLogin");
						mv.addObject("status", "false");
						mv.addObject("message", "Your profile is under approval. Our team will contact you soon for approving your details.<br><br>Once your account is approved, you can login to your account.");
						mv.addObject("button", "OK");
						mv.addObject("url", "/professionalLogin");
					}
				}
				else
				{
					mv.setViewName("/Home/professionalLogin");
					mv.addObject("status", "false");
					mv.addObject("message", "Invalid Credentials!<br>Login Failed!");
					mv.addObject("button", "Retry");
					mv.addObject("url", "/professionalLogin");
				}
			}
			else
			{
				mv.setViewName("/Home/professionalLogin");
				mv.addObject("status", "false");
				mv.addObject("message", "No Professional Account found for the given Email");
				mv.addObject("button", "Retry");
				mv.addObject("url", "/professionalLogin");
			}
		}
		catch (Exception e)
		{
			mv.setViewName("/Home/professionalLogin");
			mv.addObject("status", "false");
			mv.addObject("message", "Login Failed!");
			mv.addObject("button", "Retry");
			mv.addObject("url", "/professionalLogin");
		}
		return mv;
	}
	
	@RequestMapping(value="/forgot", method = RequestMethod.GET)
	public ModelAndView forgotGet()
	{
		ModelAndView mv = new ModelAndView();
		mv.setViewName("/Home/forgot");
		mv.addObject("status", "none");
		return mv;
	}
	
	public int codeGenerator()
	{
		int code = (int) (Math.random()*(999999-100000+1)+100000);
		return code;
	}
	
	@RequestMapping(value="/forgot", method = RequestMethod.POST)
	public ModelAndView forgotPost(@RequestParam("email") String email, HttpServletRequest request) throws IOException
	{
		ModelAndView mv = new ModelAndView();
		
		Query query = new Query();
		query.fields().include("email");
		List<String> profEmails = mongoOperations.find(query, Professional.class).stream().map(Professional::getEmail).collect(Collectors.toList());
		List<String> userEmails = mongoOperations.find(query, User.class).stream().map(User::getEmail).collect(Collectors.toList());
		
		HttpSession session = request.getSession();
		if (profEmails.contains(email))
		{
			mv.setViewName("/Home/verify");
			mv.addObject("status", "none");
			session.setAttribute("role", "Reset");
			session.setAttribute("email", email);
			String code = Integer.toString(codeGenerator());
			String body = amazonClient.getFile("OtpMessage1.txt") + code + amazonClient.getFile("OtpMessage2.txt");
			m.sendOtpMail(email, body);
			otp.put(email, code);
			timeout.put(email, new Date());
		}
		else if (userEmails.contains(email))
		{
			mv.setViewName("/Home/verify");
			mv.addObject("status", "none");
			session.setAttribute("role", "Reset");
			session.setAttribute("email", email);
			String code = Integer.toString(codeGenerator());
			String body = amazonClient.getFile("OtpMessage1.txt") + code + amazonClient.getFile("OtpMessage2.txt");
			m.sendOtpMail(email, body);
			otp.put(email, code);
			timeout.put(email, new Date());
		}
		else
		{
			mv.setViewName("/Home/forgot");
			mv.addObject("status", "false");
			mv.addObject("message", "Sorry! No Account found for the given Email Address");
			mv.addObject("button", "Retry");
			mv.addObject("url", "/forgot");
		}
		return mv;
	}
	
	@RequestMapping(value="/verify", method = RequestMethod.GET)
	public String verifyGet(HttpServletRequest request)
	{
		HttpSession session = request.getSession(false);
		if (session != null)
		{
			if (session.getAttribute("role") != null && session.getAttribute("email") != null)
			{
				if (otp.containsKey(session.getAttribute("email").toString()) && timeout.containsKey(session.getAttribute("email").toString()))
				{
					return "/Home/verify";
				}
				else
				{
					return "redirect:/forgot";
				}
			}
			else
			{
				return "redirect:/forgot";
			}
		}
		else
		{
			return "redirect:/forgot";
		}
	}
	
	@RequestMapping(value="/verify", method = RequestMethod.POST)
	public ModelAndView verifyPost(@RequestParam("first") String first, @RequestParam("second") String second, @RequestParam("third") String third, @RequestParam("fourth") String fourth, @RequestParam("fifth") String fifth, @RequestParam("sixth") String sixth, HttpServletRequest request)
	{
		ModelAndView mv = new ModelAndView();
		HttpSession session = request.getSession(false);
		if (session != null)
		{
			if (session.getAttribute("role") != null && session.getAttribute("email") != null)
			{
				String code = first + second + third + fourth + fifth + sixth;
				String email = session.getAttribute("email").toString();
				if (otp.containsKey(email))
				{
					Date curr = new Date();
					long duration = curr.getTime() - timeout.get(email).getTime();
					if (TimeUnit.MILLISECONDS.toSeconds(duration) <= 360)
					{
						if (otp.get(email).equals(code))
						{
							otp.remove(email);
							timeout.remove(email);
							mv.setViewName("/Home/reset");
							mv.addObject("status", "none");
						}
						else
						{
							mv.setViewName("/Home/verify");
							mv.addObject("status", "false");
							mv.addObject("message", "Wrong Verification Code!");
							mv.addObject("button", "Retry");
						}
					}
					else
					{
						otp.remove(email);
						timeout.remove(email);
						mv.setViewName("/Home/forgot");
						mv.addObject("status", "false");
						mv.addObject("message", "Sorry! Verification Code expired.");
						mv.addObject("button", "Retry");
						mv.addObject("url", "/forgot");
					}
				}
				else
				{
					mv.setViewName("/Home/forgot");
					mv.addObject("status", "false");
					mv.addObject("message", "Password Reset Failed!");
					mv.addObject("button", "Retry");
					mv.addObject("url", "/forgot");
				}
			}
			else
			{
				mv.setViewName("/Home/forgot");
				mv.addObject("status", "false");
				mv.addObject("message", "Password Reset Failed!");
				mv.addObject("button", "Retry");
				mv.addObject("url", "/forgot");
			}
		}
		else
		{
			mv.setViewName("/Home/forgot");
			mv.addObject("status", "false");
			mv.addObject("message", "Password Reset Failed!");
			mv.addObject("button", "Retry");
			mv.addObject("url", "/forgot");
		}
        return mv;
	}
	
	@RequestMapping(value="/reset", method = RequestMethod.GET)
	public String resetGet(HttpServletRequest request)
	{
		HttpSession session = request.getSession(false);
		if (session != null)
		{
			if (session.getAttribute("role") != null && session.getAttribute("email") != null)
			{
				return "/Home/reset";
			}
			else
			{
				return "redirect:/forgot";
			}
		}
		else
		{
			return "redirect:/forgot";
		}
	}
	
	@RequestMapping(value="/reset", method = RequestMethod.POST)
	public ModelAndView resetPost(@RequestParam("password") String password, HttpServletRequest request)
	{
		ModelAndView mv = new ModelAndView();
		HttpSession session = request.getSession(false);
		if (session != null)
		{
			if (session.getAttribute("role") != null && session.getAttribute("email") != null)
			{
				String email = session.getAttribute("email").toString();
				
				Query query = new Query();
				query.fields().include("email");
				List<String> profEmails = mongoOperations.find(query, Professional.class).stream().map(Professional::getEmail).collect(Collectors.toList());
				List<String> userEmails = mongoOperations.find(query, User.class).stream().map(User::getEmail).collect(Collectors.toList());
				
				if (profEmails.contains(email))
				{
					query = new Query(Criteria.where("email").is(email));
					Professional p = mongoOperations.find(query, Professional.class).get(0);
					if (p.getPassword().equals(p.hash(password)))
					{
						mv.setViewName("/Home/reset");
						mv.addObject("status", "false");
						mv.addObject("message", "New Password same as Old Password!");
						mv.addObject("button", "Retry");
					}
					else
					{
						p.setPassword(password);
						mongoOperations.save(p);
						mv.setViewName("/Home/professionalLogin");
						mv.addObject("status", "true");
						mv.addObject("message", "Password Reset Successfully!");
						mv.addObject("button", "OK");
						mv.addObject("url", "/professionalLogin");
						session.invalidate();
					}
				}
				else if (userEmails.contains(email))
				{
					query = new Query(Criteria.where("email").is(email));
					User u = mongoOperations.find(query, User.class).get(0);
					if (u.getPassword().equals(u.hash(password)))
					{
						mv.setViewName("/Home/reset");
						mv.addObject("status", "false");
						mv.addObject("message", "New Password same as Old Password!");
						mv.addObject("button", "Retry");
					}
					else
					{
						u.setPassword(password);
						mongoOperations.save(u);
						mv.setViewName("/Home/login");
						mv.addObject("status", "true");
						mv.addObject("message", "Password Reset Successfully!");
						mv.addObject("button", "OK");
						mv.addObject("url", "/login");
						session.invalidate();
					}
				}
				else
				{
					mv.setViewName("/Home/forgot");
					mv.addObject("status", "false");
					mv.addObject("message", "Password Reset Failed!");
					mv.addObject("button", "Retry");
					mv.addObject("url", "/forgot");
				}
			}
			else 
			{
				mv.setViewName("/Home/forgot");
				mv.addObject("status", "false");
				mv.addObject("message", "Password Reset Failed!");
				mv.addObject("button", "Retry");
				mv.addObject("url", "/forgot");
			}
		}
		else
		{
			mv.setViewName("/Home/forgot");
			mv.addObject("status", "false");
			mv.addObject("message", "Password Reset Failed!");
			mv.addObject("button", "Retry");
			mv.addObject("url", "/forgot");
		}
        return mv;
	}
	
	@RequestMapping(value="/logout", method = RequestMethod.GET)
	public ModelAndView logout(HttpServletRequest request)
	{
		HttpSession session = request.getSession(false);
		session.invalidate();
		
		ModelAndView mv = new ModelAndView();
		mv.setViewName("/Home/home");
		mv.addObject("status", "true");
		mv.addObject("message", "Logged Out Successfully!");
		mv.addObject("button", "OK");
		mv.addObject("url", "/");
		
		return mv;
	}

}