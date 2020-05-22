package com.book.web;

import com.book.domain.Book;
import com.book.domain.LikeBook;
import com.book.domain.ReaderCard;
import com.book.service.BookService;
import com.book.service.LikeBookService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

@Controller
public class BookController {
    private BookService bookService;
    private LikeBookService likeBookService;
    
    @Autowired
    public void setLikeBookService(LikeBookService likeBookService) {
    	this.likeBookService = likeBookService;
    }

    @Autowired
    public void setBookService(BookService bookService) {
        this.bookService = bookService;
    }

    @RequestMapping("/querybook.html")
    public ModelAndView queryBookDo(HttpServletRequest request,String searchWord){
        boolean exist=bookService.matchBook(searchWord);
        if (exist){
            ArrayList<Book> books = bookService.queryBook(searchWord);
            ModelAndView modelAndView = new ModelAndView("admin_books");
            modelAndView.addObject("books",books);
            return modelAndView;
        }
        else{
            return new ModelAndView("admin_books","error","没有匹配的图书");
        }
    }
    @RequestMapping("/reader_querybook.html")
    public ModelAndView readerQueryBook(HttpServletRequest request){
       ModelAndView modelAndView =  new ModelAndView("reader_book_query");
	   if(request.getSession().getAttribute("ReflectionClassId") == null) {
		   ArrayList<Book> books = bookService.queryBookOrderByLikeNum();
    	   modelAndView.addObject("books",books);
	   }
	   else {
		   int bookClassId = (int)(request.getSession().getAttribute("BookClassId"));
	       if(bookClassId == -1) {
	    	   //查询全部,按likenum排序
	    	   ArrayList<Book> books = bookService.queryBookOrderByLikeNum();
	    	   modelAndView.addObject("books",books);
	       }
	       else{
	    	   //查询classId = classId 的，按likeNum排序
	    	   ArrayList<Book> books = bookService.queryBookByClassId(bookClassId);
	    	   modelAndView.addObject("books",books);
	       }
	   }
       
       return modelAndView;

    }
    @RequestMapping("/reader_querybook_do.html")
    public String readerQueryBookDo(HttpServletRequest request,String searchWord,RedirectAttributes redirectAttributes){
        boolean exist=bookService.matchBook(searchWord);
        if (exist){
            ArrayList<Book> books = bookService.queryBook(searchWord);
            redirectAttributes.addFlashAttribute("books", books);
            return "redirect:/reader_querybook.html";
        }
        else{
            redirectAttributes.addFlashAttribute("error", "没有匹配的图书！");
            return "redirect:/reader_querybook.html";
        }

    }

    @RequestMapping("/allbooks.html")
    public ModelAndView allBook(){
        ArrayList<Book> books=bookService.getAllBooks();
        ModelAndView modelAndView=new ModelAndView("admin_books");
        modelAndView.addObject("books",books);
        return modelAndView;
    }
    @RequestMapping("/deletebook.html")
    public String deleteBook(HttpServletRequest request,RedirectAttributes redirectAttributes){
        long bookId=Integer.parseInt(request.getParameter("bookId"));
        int res=bookService.deleteBook(bookId);

        if (res==1){
            redirectAttributes.addFlashAttribute("succ", "图书删除成功！");
            return "redirect:/allbooks.html";
        }else {
            redirectAttributes.addFlashAttribute("error", "图书删除失败！");
            return "redirect:/allbooks.html";
        }
    }

    @RequestMapping("/book_add.html")
    public ModelAndView addBook(HttpServletRequest request){

            return new ModelAndView("admin_book_add");

    }

    @RequestMapping("/book_add_do.html")
    public String addBookDo(BookAddCommand bookAddCommand,RedirectAttributes redirectAttributes){
        Book book=new Book();
        book.setBookId(0);
        book.setPrice(bookAddCommand.getPrice());
        book.setPublish(bookAddCommand.getPublish());
        book.setPubdate(bookAddCommand.getPubdate());
        book.setName(bookAddCommand.getName());
        book.setIsbn(bookAddCommand.getIsbn());
        book.setClassId(bookAddCommand.getClassId());
        book.setAuthor(bookAddCommand.getAuthor());
        book.setIntroduction(bookAddCommand.getIntroduction());
        book.setLanguage(bookAddCommand.getLanguage());
        book.setLikeNum(bookAddCommand.getLikeNum());

        boolean succ=bookService.addBook(book);
        ArrayList<Book> books=bookService.getAllBooks();
        if (succ){
            redirectAttributes.addFlashAttribute("succ", "图书添加成功！");
            return "redirect:/allbooks.html";
        }
        else {
            redirectAttributes.addFlashAttribute("succ", "图书添加失败！");
            return "redirect:/allbooks.html";
        }
    }

    @RequestMapping("/updatebook.html")
    public ModelAndView bookEdit(HttpServletRequest request){
        long bookId=Integer.parseInt(request.getParameter("bookId"));
        Book book=bookService.getBook(bookId);
        ModelAndView modelAndView=new ModelAndView("admin_book_edit");
        modelAndView.addObject("detail",book);
        return modelAndView;
    }

    @RequestMapping("/book_edit_do.html")
    public String bookEditDo(HttpServletRequest request,BookAddCommand bookAddCommand,RedirectAttributes redirectAttributes){
        long bookId=Integer.parseInt( request.getParameter("id"));
        Book book=new Book();
        book.setBookId(bookId);
        book.setPrice(bookAddCommand.getPrice());
        book.setPublish(bookAddCommand.getPublish());
        book.setPubdate(bookAddCommand.getPubdate());
        book.setName(bookAddCommand.getName());
        book.setIsbn(bookAddCommand.getIsbn());
        book.setClassId(bookAddCommand.getClassId());
        book.setAuthor(bookAddCommand.getAuthor());
        book.setIntroduction(bookAddCommand.getIntroduction());
        book.setLanguage(bookAddCommand.getLanguage());
        book.setLikeNum(bookAddCommand.getLikeNum());

        boolean succ=bookService.editBook(book);
        if (succ){
            redirectAttributes.addFlashAttribute("succ", "图书修改成功！");
            return "redirect:/allbooks.html";
        }
        else {
            redirectAttributes.addFlashAttribute("error", "图书修改失败！");
            return "redirect:/allbooks.html";
        }
    }


    @RequestMapping("/bookdetail.html")
    public ModelAndView bookDetail(HttpServletRequest request){
        long bookId=Integer.parseInt(request.getParameter("bookId"));
        Book book=bookService.getBook(bookId);
        ModelAndView modelAndView=new ModelAndView("admin_book_detail");
        modelAndView.addObject("detail",book);
        return modelAndView;
    }



    @RequestMapping("/readerbookdetail.html")
    public ModelAndView readerBookDetail(HttpServletRequest request){
        long bookId=Integer.parseInt(request.getParameter("bookId"));
        Book book=bookService.getBook(bookId);
        ModelAndView modelAndView=new ModelAndView("reader_book_detail");
        modelAndView.addObject("detail",book);
        if(request.getSession().getAttribute("readercard")==null) {
        	modelAndView.addObject("noReader","请登录");
        }
        else {
        	ReaderCard readercard = (ReaderCard) request.getSession().getAttribute("readercard");
        	int readerId = readercard.getReaderId();
        	if(likeBookService.matchLikeBook(readerId, bookId)) {
        		modelAndView.addObject("like","like");
        	}
        	else {
        		modelAndView.addObject("notlike","nolike");
        	}
        }
        return modelAndView;
    }

}
