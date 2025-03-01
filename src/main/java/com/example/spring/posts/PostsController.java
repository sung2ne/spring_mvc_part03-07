package com.example.spring.posts;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class PostsController {
    
    @Autowired
    PostsService postsService;

    // 게시글 등록 (화면, GET)
    @GetMapping("/posts/create")
    public String create() {
        return "posts/create";
    }

    // 게시글 등록 (처리, POST)
    @PostMapping("/posts/create")
    public String createPost(PostsVo postsVo, RedirectAttributes redirectAttributes) {
        boolean created = postsService.create(postsVo);
        if (created) {
            redirectAttributes.addFlashAttribute("successMessage", "게시글이 등록되었습니다.");
            return "redirect:/posts/";
        }
        redirectAttributes.addFlashAttribute("errorMessage", "게시글 등록에 실패했습니다.");
        return "redirect:/posts/create";
    }

    // 게시글 목록
    @GetMapping("/posts/")
    public String listGet(Model model) {
        List<PostsVo> postsVoList = postsService.list();
        model.addAttribute("postsVoList", postsVoList);
        return "posts/list";
    }
}
