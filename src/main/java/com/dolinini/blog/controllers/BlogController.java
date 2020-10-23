package com.dolinini.blog.controllers;

import com.dolinini.blog.models.Post;
import com.dolinini.blog.repo.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Optional;

@Controller
public class BlogController {

    @Autowired
    private PostRepository postRepository;

    @GetMapping("/blog")
    public String blogMain(Model model){
        Iterable<Post> posts=postRepository.findAll();
        model.addAttribute("posts",posts);
        return "blog-main";
    }
    @GetMapping("/blog/add")
    public String addArticle(Model model){
        return "blog-add";
    }
    @PostMapping("/blog/add")
    public String postArticle(@RequestParam String title,@RequestParam String announce,@RequestParam String full_text, Model model){
        Post post=new Post(title, announce, full_text);
        postRepository.save(post);
        return"redirect:/blog";
    }
    @GetMapping("/blog/{id}")
    public String moreAboutArticle(@PathVariable(value="id") Long id, Model model){
        if(!postRepository.existsById(id)){
            return "redirect:/blog";
        }
        Optional<Post> post=postRepository.findById(id);
//       Post ordinaryPost=new Post();
//        ordinaryPost=post.get();
        ArrayList<Post> postList=new ArrayList<>();
        post.ifPresent(postList::add);
        model.addAttribute("post",postList);
        return "blog-details";
    }
    @GetMapping("/blog/{id}/edit")
    public String blogEdit(@PathVariable(value="id") Long id, Model model) {
        if(!postRepository.existsById(id)){
            return "redirect:/blog";
        }
        Optional<Post> post=postRepository.findById(id);
        ArrayList<Post> postList=new ArrayList<>();
        post.ifPresent(postList::add);
        model.addAttribute("post",postList);
        return "blog-edit";
    }
    @PostMapping("/blog/{id}/edit")
    public String BlogPostUpdate(@RequestParam String title,@RequestParam String announce, @RequestParam String full_text,@PathVariable(value="id") Long id, Model model){
        Post post=postRepository.findById(id).orElseThrow();
        post.setTitle(title);
        post.setAnnounce(announce);
        post.setFull_text(full_text);
        postRepository.save(post);
        //Optional<Post> OptionalPost=postRepository.findById(id);
        //Post post=OptionalPost.get();
        return "redirect:/blog";
    }
    @PostMapping("/blog/{id}/remove")
    public String blogPostDelete(@PathVariable(value="id") Long id, Model model) {
        Post post=postRepository.findById(id).orElseThrow();
        postRepository.delete(post);
        return "redirect:/blog";
    }
}
