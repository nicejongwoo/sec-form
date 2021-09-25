package com.sec.form.controller;

import com.sec.form.common.security.domain.CustomUser;
import com.sec.form.domain.Board;
import com.sec.form.domain.Member;
import com.sec.form.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@RequiredArgsConstructor
@Controller
@RequestMapping("/board")
public class BoardController {

    private final BoardService boardService;

    @GetMapping("/register")
    public void registerForm(Model model, Authentication authentication) {
        CustomUser user = (CustomUser) authentication.getPrincipal();
        Member member = user.getMember();

        Board board = new Board();
        board.setWriter(member.getUserId());

        model.addAttribute(board);
    }

    @PostMapping("/register")
    public String register(Board board, RedirectAttributes redirectAttributes) {
        boardService.register(board);
        redirectAttributes.addFlashAttribute("msg", "success");
        return "redirect:/board/list";
    }

    @GetMapping("/list")
    public void list(Board board, Model model) {
        List<Board> boards = boardService.list();
        model.addAttribute("list", boards);
    }

    @GetMapping("/read")
    public void read(int boardNo, Model model) {
        Board board = boardService.read(boardNo);
        model.addAttribute("board", board);
    }

    @GetMapping("/modify")
    public void modifyForm(int boardNo, Model model) {
        Board board = boardService.read(boardNo);
        model.addAttribute(board);
    }

    @PostMapping("/modify")
    public String modify(Board board, RedirectAttributes redirectAttributes) {
        boardService.modify(board);
        redirectAttributes.addFlashAttribute("msg", "success");
        return "redirect:/board/list";
    }

    @PostMapping("/remove")
    public String remove(int boardNo, RedirectAttributes redirectAttributes) {
        boardService.remove(boardNo);
        redirectAttributes.addFlashAttribute("msg", "success");
        return "redirect:/board/list";
    }

}
