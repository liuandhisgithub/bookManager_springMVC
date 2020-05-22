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
import com.book.service.BookService;
import com.book.service.LikeBookService;

@Controller
public class LikeBookController {
	private LikeBookService likeBookService;
	private BookService bookService;
	@Autowired
	public void setBookService(BookService bookService) {
		this.bookService = bookService;
	}

	public LikeBookService getLikeBookService() {
		return likeBookService;
	}
	@Autowired
	public void setLikeBookService(LikeBookService likeBookService) {
		this.likeBookService = likeBookService;
	}
	
	
	@RequestMapping("/reader_like_book.html")
	public String likeBook(HttpServletRequest request,RedirectAttributes redirectAttributes) {
		if(request.getSession().getAttribute("readercard")==null) {
			redirectAttributes.addFlashAttribute("error", "请先登录");
            return "redirect:/reader_main.html";
		}else {
			ReaderCard readercard =(ReaderCard) request.getSession().getAttribute("readercard");
			Long bookId = (long) Integer.parseInt(request.getParameter("bookId"));
			int classId = Integer.parseInt(request.getParameter("classId"));
			int readerId = readercard.getReaderId();
			//System.out.println(readerId+"|"+bookId);
			int succ = likeBookService.addLikeBook(readerId, bookId,classId);
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
	
	@RequestMapping("/reader_not_like_book.html")
	public String notLikeBook(HttpServletRequest request,RedirectAttributes redirectAttributes) {
		if(request.getSession().getAttribute("readercard")==null) {
			redirectAttributes.addFlashAttribute("error", "请先登录");
            return "redirect:/allreflections.html";
		}else {
			ReaderCard readercard =(ReaderCard) request.getSession().getAttribute("readercard");
			Long bookId = (long) Integer.parseInt(request.getParameter("bookId"));
			int readerId = readercard.getReaderId();
			int succ = likeBookService.deleteLikeBook(readerId, bookId);
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
	@RequestMapping("/likeBook.html")
	public ModelAndView LikeBook(HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView("reader_like_book");
		if(request.getSession().getAttribute("readercard")==null) {
			modelAndView.addObject("error", "请先登录");
            return modelAndView;
		}
		else {
			ReaderCard readercard =(ReaderCard) request.getSession().getAttribute("readercard");
			int readerId = readercard.getReaderId();
			ArrayList<Long> bookIds = likeBookService.getBookIdByReaderId(readerId);
	    	ArrayList<Book> books = new ArrayList<Book>();
	    	for(Long bookId : bookIds) {
	    		books.add(bookService.getBook(bookId));
	    	}
	    	modelAndView.addObject("books",books);
			return modelAndView;
		}
	}
}
