package com.sdp.project.controller;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.sdp.project.model.Cart;
import com.sdp.project.model.Category;
import com.sdp.project.model.Services;
import com.sdp.project.model.SubCategory;
import com.sdp.project.model.User;
import com.sdp.project.model.UserQuery;
import com.sdp.project.repository.CartRepository;
import com.sdp.project.repository.QueryRepository;
import com.sdp.project.repository.ServicesRepository;
import com.sdp.project.repository.UserRepository;

@Controller
public class UserController 
{

	@Autowired
	QueryRepository queryRepo;
	
	@Autowired
	UserRepository userRepo;
	
	@Autowired
	CartRepository cartRepo;
	
	@Autowired
	ServicesRepository serviceRepo;
	
	@RequestMapping(value={"/user", "/user/home"})
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
				if (role.equals("User") && userRepo.findById(email).isPresent())
				{
					mv.setViewName("/User/home");
					mv.addObject("name", userRepo.findById(email).get().getName());
					if (cartRepo.findById(email).isPresent())
						mv.addObject("cartCount", cartRepo.findById(email).get().getCartCount());
					else
						mv.addObject("cartCount", 0);
				}
				else
				{
					mv.setViewName("redirect:/login");
					session.setAttribute("login", "false");
				}
			}
			else
			{
				mv.setViewName("redirect:/login");
				session.setAttribute("login", "false");
			}
		}
		else
		{
			session = request.getSession();
			mv.setViewName("redirect:/login");
			session.setAttribute("login", "false");
		}
		return mv;
	}
	
	@RequestMapping(value="/user/about")
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
				if (role.equals("User") && userRepo.findById(email).isPresent())
				{
					mv.setViewName("/User/about");
					mv.addObject("name", userRepo.findById(email).get().getName());
					if (cartRepo.findById(email).isPresent())
						mv.addObject("cartCount", cartRepo.findById(email).get().getCartCount());
					else
						mv.addObject("cartCount", 0);
				}
				else
				{
					mv.setViewName("redirect:/login");
					session.setAttribute("login", "false");
				}
			}
			else
			{
				mv.setViewName("redirect:/login");
				session.setAttribute("login", "false");
			}
		}
		else
		{
			session = request.getSession();
			mv.setViewName("redirect:/login");
			session.setAttribute("login", "false");
		}
		return mv;
	}
	
	@RequestMapping(value="/user/contact", method=RequestMethod.GET)
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
				if (role.equals("User") && userRepo.findById(email).isPresent())
				{
					mv.setViewName("/User/contact");
					mv.addObject("name", userRepo.findById(email).get().getName());
					mv.addObject("email", email);
					if (cartRepo.findById(email).isPresent())
						mv.addObject("cartCount", cartRepo.findById(email).get().getCartCount());
					else
						mv.addObject("cartCount", 0);
				}
				else
				{
					mv.setViewName("redirect:/login");
					session.setAttribute("login", "false");
				}
			}
			else
			{
				mv.setViewName("redirect:/login");
				session.setAttribute("login", "false");
			}
		}
		else
		{
			session = request.getSession();
			mv.setViewName("redirect:/login");
			session.setAttribute("login", "false");
		}
		return mv;
	}
	
	@RequestMapping(value="/user/contact", method=RequestMethod.POST)
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
				if (role.equals("User") && userRepo.findById(email).isPresent())
				{
					mv.setViewName("/User/contact");
					try
					{
						UserQuery q = new UserQuery(userRepo.findById(email).get().getName(), email, subject, message);
						queryRepo.save(q);
						mv.addObject("status", "true");
						mv.addObject("message", "Query Sent Successfully!");
						mv.addObject("button", "OK");
						mv.addObject("url", "/user/contact");
					}
					catch (Exception e)
					{
						mv.addObject("status", "false");
						mv.addObject("message", "Failed to send Query!");
						mv.addObject("button", "Retry");
						mv.addObject("url", "/user/contact");
					}
				}
				else
				{
					mv.setViewName("redirect:/login");
					session.setAttribute("login", "false");
				}
			}
			else
			{
				mv.setViewName("redirect:/login");
				session.setAttribute("login", "false");
			}
		}
		else
		{
			session = request.getSession();
			mv.setViewName("redirect:/login");
			session.setAttribute("login", "false");
		}
		return mv;
	}
	
	@RequestMapping(value={"/user/logout"})
	public ModelAndView logout(HttpServletRequest request)
	{
		ModelAndView mv = new ModelAndView();
		HttpSession session = request.getSession(false);
		if (session != null)
		{
			if (session.getAttribute("role") != null && session.getAttribute("email") != null)
			{
				String role = session.getAttribute("role").toString();
				String email = session.getAttribute("email").toString();
				if (role.equals("User") && userRepo.findById(email).isPresent())
				{
					session.invalidate();
					
					if (cartRepo.findById(email).isPresent())
					{
						Cart cart = cartRepo.findById(email).get();
						cartRepo.delete(cart);
					}
					
					mv.setViewName("/Home/home");
					mv.addObject("status", "true");
					mv.addObject("message", "Logged Out Successfully!");
					mv.addObject("button", "OK");
					mv.addObject("url", "/");
				}
				else
				{
					mv.setViewName("redirect:/login");
					session.setAttribute("login", "false");
				}
			}
			else
			{
				mv.setViewName("redirect:/login");
				session.setAttribute("login", "false");
			}
		}
		else
		{
			session = request.getSession();
			mv.setViewName("redirect:/login");
			session.setAttribute("login", "false");
		}
		return mv;
	}
	
	@RequestMapping(value={"/user/profile"})
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
				if (role.equals("User") && userRepo.findById(email).isPresent())
				{
					mv.setViewName("/User/profile");
					mv.addObject("name", userRepo.findById(email).get().getName());
					if (cartRepo.findById(email).isPresent())
						mv.addObject("cartCount", cartRepo.findById(email).get().getCartCount());
					else
						mv.addObject("cartCount", 0);
					mv.addObject("obj", userRepo.findById(email).get());
				}
				else
				{
					mv.setViewName("redirect:/login");
					session.setAttribute("login", "false");
				}
			}
			else
			{
				mv.setViewName("redirect:/login");
				session.setAttribute("login", "false");
			}
		}
		else
		{
			session = request.getSession();
			mv.setViewName("redirect:/login");
			session.setAttribute("login", "false");
		}
		return mv;
	}
	
	@RequestMapping(value={"/user/reset"}, method=RequestMethod.GET)
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
				if (role.equals("User") && userRepo.findById(email).isPresent())
				{
					mv.setViewName("/User/reset");
					mv.addObject("name", userRepo.findById(email).get().getName());
					if (cartRepo.findById(email).isPresent())
						mv.addObject("cartCount", cartRepo.findById(email).get().getCartCount());
					else
						mv.addObject("cartCount", 0);
				}
				else
				{
					mv.setViewName("redirect:/login");
					session.setAttribute("login", "false");
				}
			}
			else
			{
				mv.setViewName("redirect:/login");
				session.setAttribute("login", "false");
			}
		}
		else
		{
			session = request.getSession();
			mv.setViewName("redirect:/login");
			session.setAttribute("login", "false");
		}
		return mv;
	}
	
	@RequestMapping(value={"/user/reset"}, method=RequestMethod.POST)
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
				if (role.equals("User") && userRepo.findById(email).isPresent())
				{
					User u = userRepo.findById(email).get();
					if (u.getPassword().equals(u.hash(old)))
					{
						if (u.getPassword().equals(u.hash(password)))
						{
							mv.setViewName("/User/reset");
							mv.addObject("name", userRepo.findById(email).get().getName());
							if (cartRepo.findById(email).isPresent())
								mv.addObject("cartCount", cartRepo.findById(email).get().getCartCount());
							else
								mv.addObject("cartCount", 0);
							mv.addObject("status", "false");
							mv.addObject("message", "New Password same as Old Password!");
							mv.addObject("button", "Retry");
							mv.addObject("url", "/user/reset");
						}
						else
						{
							u.setPassword(password);
							userRepo.save(u);
							mv.setViewName("/User/reset");
							mv.addObject("name", userRepo.findById(email).get().getName());
							if (cartRepo.findById(email).isPresent())
								mv.addObject("cartCount", cartRepo.findById(email).get().getCartCount());
							else
								mv.addObject("cartCount", 0);
							mv.addObject("status", "true");
							mv.addObject("message", "Password Reset Successfully!");
							mv.addObject("button", "OK");
							mv.addObject("url", "/user/profile");
						}
					}
					else
					{
						mv.setViewName("/User/reset");
						mv.addObject("name", userRepo.findById(email).get().getName());
						if (cartRepo.findById(email).isPresent())
							mv.addObject("cartCount", cartRepo.findById(email).get().getCartCount());
						else
							mv.addObject("cartCount", 0);
						mv.addObject("status", "false");
						mv.addObject("message", "Wrong Old Password!");
						mv.addObject("button", "Retry");
						mv.addObject("url", "/user/reset");
					}
				}
				else
				{
					mv.setViewName("redirect:/login");
					session.setAttribute("login", "false");
				}
			}
			else
			{
				mv.setViewName("redirect:/login");
				session.setAttribute("login", "false");
			}
		}
		else
		{
			session = request.getSession();
			mv.setViewName("redirect:/login");
			session.setAttribute("login", "false");
		}
		return mv;
	}
	
	@RequestMapping(value={"/user/update"}, method=RequestMethod.GET)
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
				if (role.equals("User") && userRepo.findById(email).isPresent())
				{
					mv.setViewName("/User/update");
					mv.addObject("name", userRepo.findById(email).get().getName());
					if (cartRepo.findById(email).isPresent())
						mv.addObject("cartCount", cartRepo.findById(email).get().getCartCount());
					else
						mv.addObject("cartCount", 0);
					mv.addObject("obj", userRepo.findById(email).get());
				}
				else
				{
					mv.setViewName("redirect:/login");
					session.setAttribute("login", "false");
				}
			}
			else
			{
				mv.setViewName("redirect:/login");
				session.setAttribute("login", "false");
			}
		}
		else
		{
			session = request.getSession();
			mv.setViewName("redirect:/login");
			session.setAttribute("login", "false");
		}
		return mv;
	}
	
	@RequestMapping(value={"/user/update"}, method=RequestMethod.POST)
	public ModelAndView updateProfilePost(@RequestParam("name") String name, @RequestParam("contact") long contact, HttpServletRequest request)
	{
		ModelAndView mv = new ModelAndView();
		HttpSession session = request.getSession(false);
		if (session != null)
		{
			if (session.getAttribute("role") != null && session.getAttribute("email") != null)
			{
				String role = session.getAttribute("role").toString();
				String email = session.getAttribute("email").toString();
				if (role.equals("User") && userRepo.findById(email).isPresent())
				{
					try
					{
						User u = userRepo.findById(email).get();
						u.setName(name);
						u.setContact(contact);
						userRepo.save(u);
						
						mv.setViewName("/User/profile");
						mv.addObject("status", "true");
						mv.addObject("message", "Profile Updated Successfully!");
						mv.addObject("button", "OK");
						mv.addObject("url", "/user/profile");
					}
					catch (Exception e)
					{
						mv.setViewName("/User/profile");
						mv.addObject("status", "false");
						mv.addObject("message", "Failed to Update Profile!");
						mv.addObject("button", "Retry");
						mv.addObject("url", "/user/profile");
					}
				}
				else
				{
					mv.setViewName("redirect:/login");
					session.setAttribute("login", "false");
				}
			}
			else
			{
				mv.setViewName("redirect:/login");
				session.setAttribute("login", "false");
			}
		}
		else
		{
			session = request.getSession();
			mv.setViewName("redirect:/login");
			session.setAttribute("login", "false");
		}
		return mv;
	}
	
	@RequestMapping(value={"/user/browse"})
	public ModelAndView browse(HttpServletRequest request)
	{
		ModelAndView mv = new ModelAndView();
		HttpSession session = request.getSession(false);
		if (session != null)
		{
			if (session.getAttribute("role") != null && session.getAttribute("email") != null)
			{
				String role = session.getAttribute("role").toString();
				String email = session.getAttribute("email").toString();
				if (role.equals("User") && userRepo.findById(email).isPresent())
				{
					mv.setViewName("/User/browse");
					mv.addObject("name", userRepo.findById(email).get().getName());
					if (cartRepo.findById(email).isPresent())
						mv.addObject("cartCount", cartRepo.findById(email).get().getCartCount());
					else
						mv.addObject("cartCount", 0);
					mv.addObject("services", serviceRepo.findAll());
				}
				else
				{
					mv.setViewName("redirect:/login");
					session.setAttribute("login", "false");
				}
			}
			else
			{
				mv.setViewName("redirect:/login");
				session.setAttribute("login", "false");
			}
		}
		else
		{
			session = request.getSession();
			mv.setViewName("redirect:/login");
			session.setAttribute("login", "false");
		}
		return mv;
	}
	
	@RequestMapping(value={"/user/browse/{service}"})
	public ModelAndView browse(@PathVariable("service") String service, HttpServletRequest request)
	{
		ModelAndView mv = new ModelAndView();
		HttpSession session = request.getSession(false);
		if (session != null)
		{
			if (session.getAttribute("role") != null && session.getAttribute("email") != null)
			{	
				String role = session.getAttribute("role").toString();
				String email = session.getAttribute("email").toString();
				if (role.equals("User") && userRepo.findById(email).isPresent())
				{
					if (serviceRepo.findById(service).isPresent())
					{
						LinkedHashMap<String, List<SubCategory>> category = new LinkedHashMap<String, List<SubCategory>>();
						for (Category c : serviceRepo.findById(service).get().getCategory())
						{
							category.put(c.getCategoryName(), c.getSubCategory());
						}
						mv.setViewName("/User/service");
						mv.addObject("name", userRepo.findById(email).get().getName());
						if (cartRepo.findById(email).isPresent())
						{
							mv.addObject("cartCount", cartRepo.findById(email).get().getCartCount());
							mv.addObject("selected", cartRepo.findById(email).get().getCategory());
						}
						else
						{
							mv.addObject("cartCount", 0);
							mv.addObject("selected", new LinkedHashMap<String, List<String>>());
						}
						if (session.getAttribute("message") != null)
						{
							mv.addObject("status", "false");
							mv.addObject("message", "You can book professionals from only one service at a time!");
							mv.addObject("button", "OK");
							mv.addObject("url", "/user/browse/" + service);
							session.removeAttribute("message");
						}
						mv.addObject("service", service);
						mv.addObject("category", category);
					}
					else
					{
						mv.setViewName("redirect:/user/browse");
					}
				}
				else
				{
					mv.setViewName("redirect:/login");
					session.setAttribute("login", "false");
				}
			}
			else
			{
				mv.setViewName("redirect:/login");
				session.setAttribute("login", "false");
			}
		}
		else
		{
			session = request.getSession();
			mv.setViewName("redirect:/login");
			session.setAttribute("login", "false");
		}
		return mv;
	}
	
	@RequestMapping(value={"/user/service/add", "/user/service/delete"}, method=RequestMethod.GET)
	public ModelAndView serviceGet(HttpServletRequest request)
	{
		ModelAndView mv = new ModelAndView();
		HttpSession session = request.getSession(false);
		if (session != null)
		{
			if (session.getAttribute("role") != null && session.getAttribute("email") != null)
			{	
				String role = session.getAttribute("role").toString();
				String email = session.getAttribute("email").toString();
				if (role.equals("User") && userRepo.findById(email).isPresent())
				{
					mv.setViewName("redirect:/user/browse");
				}
				else
				{
					mv.setViewName("redirect:/login");
					session.setAttribute("login", "false");
				}
			}
			else
			{
				mv.setViewName("redirect:/login");
				session.setAttribute("login", "false");
			}
		}
		else
		{
			session = request.getSession();
			mv.setViewName("redirect:/login");
			session.setAttribute("login", "false");
		}
		return mv;
	}
	
	@RequestMapping(value={"/user/service/add"}, method=RequestMethod.POST)
	public ModelAndView serviceAddPost(HttpServletRequest request, @RequestParam("service") String service, @RequestParam("category") String category, @RequestParam("subcategory") String subcategory)
	{
		ModelAndView mv = new ModelAndView();
		HttpSession session = request.getSession(false);
		if (session != null)
		{
			if (session.getAttribute("role") != null && session.getAttribute("email") != null)
			{	
				String role = session.getAttribute("role").toString();
				String email = session.getAttribute("email").toString();
				if (role.equals("User") && userRepo.findById(email).isPresent())
				{
					if (serviceRepo.findById(service).isPresent())
					{
						Services s = serviceRepo.findById(service).get();
						if (s.getCategory().stream().filter(serv -> serv.getCategoryName().equals(category)).collect(Collectors.toList()).size() == 0)
						{
							mv.setViewName("redirect:/user/browse/" + service);
						}
						else
						{
							Category c = s.getCategory().stream().filter(serv -> serv.getCategoryName().equals(category)).collect(Collectors.toList()).get(0);
							if (c.getSubCategory().stream().filter(cat -> cat.getSubCategoryName().equals(subcategory)).collect(Collectors.toList()).size() == 0)
							{
								mv.setViewName("redirect:/user/browse/" + service);
							}
							else
							{
								if (cartRepo.findById(email).isPresent())
								{
									Cart cart = cartRepo.findById(email).get();
									if (cart.getService().equals(service))
									{
										if (cart.getCategory().keySet().contains(category))
										{
											cart.getCategory().get(category).add(subcategory);
										}
										else
										{
											cart.getCategory().put(category, Arrays.asList(subcategory));
										}
										cart.setCartCount(cart.getCartCount()+1);
										cartRepo.save(cart);
										
										mv.setViewName("redirect:/user/browse/" + service);
									}
									else
									{
										mv.setViewName("redirect:/user/browse/" + service);
										session.setAttribute("message", "false");
									}
								}
								else
								{
									Cart cart = new Cart();
									cart.setUserEmail(email);
									cart.setService(service);
									cart.getCategory().put(category, Arrays.asList(subcategory));
									cart.setCartCount(cart.getCartCount()+1);
									cartRepo.save(cart);
									
									mv.setViewName("redirect:/user/browse/" + service);
								}
							}
						}
					}
					else
					{
						mv.setViewName("redirect:/user/browse");
					}
				}
				else
				{
					mv.setViewName("redirect:/login");
					session.setAttribute("login", "false");
				}
			}
			else
			{
				mv.setViewName("redirect:/login");
				session.setAttribute("login", "false");
			}
		}
		else
		{
			session = request.getSession();
			mv.setViewName("redirect:/login");
			session.setAttribute("login", "false");
		}
		return mv;
	}
	
	@RequestMapping(value={"/user/service/delete"}, method=RequestMethod.POST)
	public ModelAndView serviceDeletePost(HttpServletRequest request, @RequestParam("service") String service, @RequestParam("category") String category, @RequestParam("subcategory") String subcategory)
	{
		ModelAndView mv = new ModelAndView();
		HttpSession session = request.getSession(false);
		if (session != null)
		{
			if (session.getAttribute("role") != null && session.getAttribute("email") != null)
			{	
				String role = session.getAttribute("role").toString();
				String email = session.getAttribute("email").toString();
				if (role.equals("User") && userRepo.findById(email).isPresent())
				{
					if (serviceRepo.findById(service).isPresent())
					{
						Services s = serviceRepo.findById(service).get();
						if (s.getCategory().stream().filter(serv -> serv.getCategoryName().equals(category)).collect(Collectors.toList()).size() == 0)
						{
							mv.setViewName("redirect:/user/browse/" + service);
						}
						else
						{
							Category c = s.getCategory().stream().filter(serv -> serv.getCategoryName().equals(category)).collect(Collectors.toList()).get(0);
							if (c.getSubCategory().stream().filter(cat -> cat.getSubCategoryName().equals(subcategory)).collect(Collectors.toList()).size() == 0)
							{
								mv.setViewName("redirect:/user/browse/" + service);
							}
							else
							{
								if (cartRepo.findById(email).isPresent())
								{
									Cart cart = cartRepo.findById(email).get();
									if (cart.getService().equals(service))
									{
										if (cart.getCategory().keySet().contains(category))
										{
											if (cart.getCategory().get(category).contains(subcategory))
											{
												cart.getCategory().get(category).remove(subcategory);
												cart.setCartCount(cart.getCartCount()-1);
											}
											else
											{
												mv.setViewName("redirect:/user/browse/" + service);
											}
											if (cart.getCategory().get(category).size() == 0)
												cart.getCategory().remove(category);
											cartRepo.save(cart);
											mv.setViewName("redirect:/user/browse/" + service);
										}
										else
										{
											mv.setViewName("redirect:/user/browse/" + service);
										}
										if (cart.getCategory().size() == 0)
										{
											cartRepo.delete(cart);
											mv.setViewName("redirect:/user/browse");
										}
									}
									else
									{
										mv.setViewName("redirect:/user/browse/" + service);
									}
								}
								else
								{
									mv.setViewName("redirect:/user/browse");
								}
							}
						}
					}
					else
					{
						mv.setViewName("redirect:/user/browse");
					}
				}
				else
				{
					mv.setViewName("redirect:/login");
					session.setAttribute("login", "false");
				}
			}
			else
			{
				mv.setViewName("redirect:/login");
				session.setAttribute("login", "false");
			}
		}
		else
		{
			session = request.getSession();
			mv.setViewName("redirect:/login");
			session.setAttribute("login", "false");
		}
		return mv;
	}
	
	@RequestMapping(value="/user/cart")
	public ModelAndView cart(HttpServletRequest request)
	{
		ModelAndView mv = new ModelAndView();
		HttpSession session = request.getSession(false);
		if (session != null)
		{
			if (session.getAttribute("role") != null && session.getAttribute("email") != null)
			{
				String role = session.getAttribute("role").toString();
				String email = session.getAttribute("email").toString();
				if (role.equals("User") && userRepo.findById(email).isPresent())
				{
					mv.setViewName("/User/cart");
					mv.addObject("name", userRepo.findById(email).get().getName());
					mv.addObject("email", email);
					if (cartRepo.findById(email).isPresent())
					{
						mv.addObject("cartCount", cartRepo.findById(email).get().getCartCount());
						Cart cart = cartRepo.findById(email).get();
						mv.addObject("service", cart.getService());
						mv.addObject("selected", cart.getCategory());
						mv.addObject("category", serviceRepo.findById(cart.getService()).get().getCategory());
					}
					else
						mv.addObject("cartCount", 0);
				}
				else
				{
					mv.setViewName("redirect:/login");
					session.setAttribute("login", "false");
				}
			}
			else
			{
				mv.setViewName("redirect:/login");
				session.setAttribute("login", "false");
			}
		}
		else
		{
			session = request.getSession();
			mv.setViewName("redirect:/login");
			session.setAttribute("login", "false");
		}
		return mv;
	}
	
	@RequestMapping(value="/user/cart/delete", method=RequestMethod.GET)
	public ModelAndView cartDeleteGet(HttpServletRequest request)
	{
		ModelAndView mv = new ModelAndView();
		HttpSession session = request.getSession(false);
		if (session != null)
		{
			if (session.getAttribute("role") != null && session.getAttribute("email") != null)
			{
				String role = session.getAttribute("role").toString();
				String email = session.getAttribute("email").toString();
				if (role.equals("User") && userRepo.findById(email).isPresent())
				{
					mv.setViewName("redirect:/user/cart");
				}
				else
				{
					mv.setViewName("redirect:/login");
					session.setAttribute("login", "false");
				}
			}
			else
			{
				mv.setViewName("redirect:/login");
				session.setAttribute("login", "false");
			}
		}
		else
		{
			session = request.getSession();
			mv.setViewName("redirect:/login");
			session.setAttribute("login", "false");
		}
		return mv;
	}
	
	@RequestMapping(value={"/user/cart/delete"}, method=RequestMethod.POST)
	public ModelAndView cartDeletePost(HttpServletRequest request, @RequestParam("service") String service, @RequestParam("category") String category, @RequestParam("subcategory") String subcategory)
	{
		ModelAndView mv = new ModelAndView();
		HttpSession session = request.getSession(false);
		if (session != null)
		{
			if (session.getAttribute("role") != null && session.getAttribute("email") != null)
			{	
				String role = session.getAttribute("role").toString();
				String email = session.getAttribute("email").toString();
				if (role.equals("User") && userRepo.findById(email).isPresent())
				{
					if (serviceRepo.findById(service).isPresent())
					{
						Services s = serviceRepo.findById(service).get();
						if (s.getCategory().stream().filter(serv -> serv.getCategoryName().equals(category)).collect(Collectors.toList()).size() == 0)
						{
							mv.setViewName("redirect:/user/cart/");
						}
						else
						{
							Category c = s.getCategory().stream().filter(serv -> serv.getCategoryName().equals(category)).collect(Collectors.toList()).get(0);
							if (c.getSubCategory().stream().filter(cat -> cat.getSubCategoryName().equals(subcategory)).collect(Collectors.toList()).size() == 0)
							{
								mv.setViewName("redirect:/user/cart/");
							}
							else
							{
								if (cartRepo.findById(email).isPresent())
								{
									Cart cart = cartRepo.findById(email).get();
									if (cart.getService().equals(service))
									{
										if (cart.getCategory().keySet().contains(category))
										{
											if (cart.getCategory().get(category).contains(subcategory))
											{
												cart.getCategory().get(category).remove(subcategory);
												cart.setCartCount(cart.getCartCount()-1);
											}
											else
											{
												mv.setViewName("redirect:/user/cart");
											}
											if (cart.getCategory().get(category).size() == 0)
												cart.getCategory().remove(category);
											cartRepo.save(cart);
											mv.setViewName("redirect:/user/cart");
										}
										else
										{
											mv.setViewName("redirect:/user/cart");
										}
										if (cart.getCategory().size() == 0)
										{
											cartRepo.delete(cart);
											mv.setViewName("redirect:/user/cart");
										}
									}
									else
									{
										mv.setViewName("redirect:/user/cart");
									}
								}
								else
								{
									mv.setViewName("redirect:/user/cart");
								}
							}
						}
					}
					else
					{
						mv.setViewName("redirect:/user/cart");
					}
				}
				else
				{
					mv.setViewName("redirect:/login");
					session.setAttribute("login", "false");
				}
			}
			else
			{
				mv.setViewName("redirect:/login");
				session.setAttribute("login", "false");
			}
		}
		else
		{
			session = request.getSession();
			mv.setViewName("redirect:/login");
			session.setAttribute("login", "false");
		}
		return mv;
	}
	
}
