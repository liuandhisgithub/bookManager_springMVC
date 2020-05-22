package com.book.web;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.book.domain.Book;
import com.book.domain.ReaderCard;
import com.book.domain.Reflection;
import com.book.service.LikeBookService;
import com.book.service.LikeReflectionService;
import com.book.service.ReflectionService;

@Controller
public class LikeReflectionController {
	private LikeReflectionService likeReflectionService;
	private ReflectionService reflectionService;
	@Autowired
	public void setReflectionService(ReflectionService reflectionService) {
		this.reflectionService = reflectionService;
	}
	
	public LikeReflectionService getLikeBookService() {
		return likeReflectionService;
	}
	@Autowired
	public void setLikeBookService(LikeReflectionService likeReflectionService) {
		this.likeReflectionService = likeReflectionService;
	}
	
	
	@RequestMapping("/reader_like_reflection.html")
	public String likeReflection(HttpServletRequest request,RedirectAttributes redirectAttributes) {
		if(request.getSession().getAttribute("readercard")==null) {
			redirectAttributes.addFlashAttribute("error", "请先登录");
            return "redirect:/reader_main.html";
		}else {
			ReaderCard readercard =(ReaderCard) request.getSession().getAttribute("readercard");
			Long reflectionId = (long) Integer.parseInt(request.getParameter("reflectionId"));
			int readerId = readercard.getReaderId();
			int classId = Integer.parseInt(request.getParameter("classId"));
			int succ = likeReflectionService.addLikeReflection(readerId, reflectionId,classId);
			if(succ == 1) {
				redirectAttributes.addFlashAttribute("succ", "收藏成功");
	            return "redirect:/reader_main.html";
			}
			else {
				redirectAttributes.addFlashAttribute("error", "收藏失败");
	            return "redirect:/reader_main.html";
			}
		}
		
	}
	
	@RequestMapping("/reader_not_like_reflection.html")
	public String notLikeReflection(HttpServletRequest request,RedirectAttributes redirectAttributes) {
		if(request.getSession().getAttribute("readercard")==null) {
			redirectAttributes.addFlashAttribute("error", "请先登录");
            return "redirect:/reader_main.html";
		}else {
			ReaderCard readercard =(ReaderCard) request.getSession().getAttribute("readercard");
			Long reflectionId = (long) Integer.parseInt(request.getParameter("reflectionId"));
			int readerId = readercard.getReaderId();
			int succ = likeReflectionService.deleteLikeReflection(readerId, reflectionId);
			if(succ == 1) {
				redirectAttributes.addFlashAttribute("succ", "取消收藏成功");
	            return "redirect:/reader_main.html";
			}
			else {
				redirectAttributes.addFlashAttribute("error", "取消收藏失败");
	            return "redirect:/reader_main.html";
			}
		}
		
	}
	
	@RequestMapping("/likeReflection.html")
	public ModelAndView LikeBook(HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView("reader_like_reflection");
		if(request.getSession().getAttribute("readercard")==null) {
			modelAndView.addObject("error", "请先登录");
            return modelAndView;
		}
		else {
			ReaderCard readercard =(ReaderCard) request.getSession().getAttribute("readercard");
			int readerId = readercard.getReaderId();
			ArrayList<Long> reflectionIds = likeReflectionService.getReflectionIdByReaderId(readerId);
	    	ArrayList<Reflection> reflections = new ArrayList<Reflection>();
	    	for(Long reflectionId : reflectionIds) {
	    		reflections.add(reflectionService.getReflection(reflectionId));
	    	}
	    	modelAndView.addObject("reflections",reflections);
			return modelAndView;
		}
	}
}
