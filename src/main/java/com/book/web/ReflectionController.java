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
import com.book.service.BookService;
import com.book.service.LikeReflectionService;
import com.book.service.ReflectionService;
@Controller
public class ReflectionController {
	private ReflectionService reflectionService;
	private LikeReflectionService likeReflectionService;
	

    public LikeReflectionService getLikeReflectionService() {
		return likeReflectionService;
	}
    @Autowired
	public void setLikeReflectionService(LikeReflectionService likeReflectionService) {
		this.likeReflectionService = likeReflectionService;
	}

	public ReflectionService getReflectionService() {
		return reflectionService;
	}
	@Autowired
    public void setReflectionService(ReflectionService reflectionService) {
		this.reflectionService = reflectionService;
	}

    @RequestMapping("/queryreflection.html")
    public ModelAndView queryBookDo(HttpServletRequest request,String searchWord){
        boolean exist=reflectionService.matchReflection(searchWord);
        if (exist){
            ArrayList<Reflection> reflections = reflectionService.queryReflection(searchWord);
            ModelAndView modelAndView = new ModelAndView("admin_reflections");
            modelAndView.addObject("reflections",reflections);
            return modelAndView;
        }
        else{
            return new ModelAndView("admin_reflections","error","没有匹配的读后感");
        }
    }

	@RequestMapping("/reader_queryreflection.html")
    public ModelAndView readerQueryReflection(HttpServletRequest request){
		ModelAndView modelAndView =  new ModelAndView("reader_reflection_query");
		   if(request.getSession().getAttribute("ReflectionClassId") == null) {
	    	   ArrayList<Reflection> reflections = reflectionService.queryReflectionOrderByLikeNum();
	    	   modelAndView.addObject("reflections",reflections);
		   }
		   else {
		       int reflectionClassId = (int)(request.getSession().getAttribute("ReflectionClassId"));
		       if(reflectionClassId == -1) {
		    	   //查询全部,按likenum排序
		    	   ArrayList<Reflection> reflections = reflectionService.queryReflectionOrderByLikeNum();
		    	   modelAndView.addObject("reflections",reflections);
		       }
		       else{
		    	   //查询classId = classId 的，按likeNum排序
		    	   ArrayList<Reflection> reflections = reflectionService.queryReflectionByClassId(reflectionClassId);
		    	   modelAndView.addObject("reflections",reflections);
		       }
		   }
	       return modelAndView;

    }
    @RequestMapping("/reader_queryreflection_do.html")
    public String readerQueryReflectionDo(HttpServletRequest request,String searchWord,RedirectAttributes redirectAttributes){
        boolean exist=reflectionService.matchReflection(searchWord);
        if (exist){
            ArrayList<Reflection> reflections = reflectionService.queryReflection(searchWord);
            redirectAttributes.addFlashAttribute("reflections", reflections);
            return "redirect:/reader_queryreflection.html";
        }
        else{
            redirectAttributes.addFlashAttribute("error", "没有匹配的读后感！");
            return "redirect:/reader_queryreflection.html";
        }

    }

    @RequestMapping("/allreflections.html")
    public ModelAndView allReflection(){
        ArrayList<Reflection> reflections=reflectionService.getAllReflections();
        ModelAndView modelAndView=new ModelAndView("admin_reflections");
        modelAndView.addObject("reflections",reflections);
        return modelAndView;
    }
    
    @RequestMapping("/reader_reflections.html")
    public ModelAndView readerReflection(){
        ArrayList<Reflection> reflections=reflectionService.getAllReflections();
        ModelAndView modelAndView=new ModelAndView("reader_reflections");
        modelAndView.addObject("reflections",reflections);
        return modelAndView;
    }
    @RequestMapping("/deletereflection.html")
    public String deleteReflection(HttpServletRequest request,RedirectAttributes redirectAttributes){
        long reflectionId=Integer.parseInt(request.getParameter("reflectionId"));
        int res=reflectionService.deleteReflection(reflectionId);

        if (res==1){
            redirectAttributes.addFlashAttribute("succ", "读后感删除成功！");
            return "redirect:/allreflections.html";
        }else {
            redirectAttributes.addFlashAttribute("error", "读后感删除失败！");
            return "redirect:/allreflections.html";
        }
    }

    @RequestMapping("/reflection_add.html")
    public ModelAndView addReflection(HttpServletRequest request){
    		ModelAndView modelAndView = new ModelAndView("reader_reflection_add");
            ReaderCard readercard =(ReaderCard) request.getSession().getAttribute("readercard");
            String bookName = request.getParameter("bookName");
            int classId = Integer.parseInt(request.getParameter("classId"));
            modelAndView.addObject("readerId", readercard.getReaderId());
            modelAndView.addObject("bookName",bookName);
            modelAndView.addObject("classId",classId);
    		return modelAndView;

    }

    @RequestMapping("/reflection_add_do.html")
    public String addReflectionDo(String name,String bookName, int readerId, int classId, String introduction,RedirectAttributes redirectAttributes){
    	Reflection reflection =new Reflection();
        reflection.setReflectionId(0);
        reflection.setName(name);
        reflection.setBookName(bookName);
        reflection.setReaderId(readerId);
        reflection.setIntroduction(introduction);
        reflection.setLikeNum(0);
        reflection.setClassId(classId);


        boolean succ=reflectionService.addReflection(reflection);
        ArrayList<Reflection> reflections=reflectionService.getAllReflections();
        if (succ){
            redirectAttributes.addFlashAttribute("succ", "读后感添加成功！");
            return "redirect:/reader_reflections.html";
        }
        else {
            redirectAttributes.addFlashAttribute("succ", "读后感添加失败！");
            return "redirect:/reader_reflections.html";
        }
    }

    @RequestMapping("/updatereflection.html")
    public ModelAndView reflectionEdit(HttpServletRequest request){
        long reflectionId=Integer.parseInt(request.getParameter("reflectionId"));
        Reflection reflection=reflectionService.getReflection(reflectionId);
        ModelAndView modelAndView=new ModelAndView("admin_reflection_edit");
        modelAndView.addObject("detail",reflection);
        return modelAndView;
    }

    @RequestMapping("/reflection_edit_do.html")
    public String reflectionEditDo(HttpServletRequest request,String name,int readerId,String bookName,int like,int classId, String introduction,RedirectAttributes redirectAttributes){
        long reflectionId=Integer.parseInt( request.getParameter("id"));
        Reflection reflection =new Reflection();
        reflection.setReflectionId(reflectionId);
        reflection.setName(name);
        reflection.setBookName(bookName);
        reflection.setReaderId(readerId);
        reflection.setIntroduction(introduction);
        reflection.setLikeNum(like);
        reflection.setClassId(classId);


        boolean succ=reflectionService.editReflection(reflection);
        if (succ){
            redirectAttributes.addFlashAttribute("succ", "读后感修改成功！");
            return "redirect:/allreflections.html";
        }
        else {
            redirectAttributes.addFlashAttribute("error", "读后感修改失败！");
            return "redirect:/allreflections.html";
        }
    }


    @RequestMapping("/reflectiondetail.html")
    public ModelAndView reflectionDetail(HttpServletRequest request){
        long reflectionId=Integer.parseInt(request.getParameter("reflectionId"));
        Reflection reflection = reflectionService.getReflection(reflectionId);
        ModelAndView modelAndView=new ModelAndView("admin_reflection_detail");
        modelAndView.addObject("detail",reflection);
        return modelAndView;
    }



    @RequestMapping("/readerreflectiondetail.html")
    public ModelAndView readerReflectionDetail(HttpServletRequest request){
    	long reflectionId=Integer.parseInt(request.getParameter("reflectionId"));
        Reflection reflection = reflectionService.getReflection(reflectionId);
        ModelAndView modelAndView=new ModelAndView("reader_reflection_detail");
        modelAndView.addObject("detail",reflection);
        
        if(request.getSession().getAttribute("readercard")==null) {
        	modelAndView.addObject("noReader","请登录");
        }
        else {
        	ReaderCard readercard = (ReaderCard) request.getSession().getAttribute("readercard");
        	int readerId = readercard.getReaderId();
        	if(likeReflectionService.matchLikeReflection(readerId, reflectionId)) {
        		modelAndView.addObject("like","like");
        	}
        	else {
        		modelAndView.addObject("notlike","nolike");
        	}
        }
        return modelAndView;
    }
}
