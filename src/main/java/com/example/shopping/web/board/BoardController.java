package com.example.shopping.web.board;

import com.example.shopping.SessionConst;
import com.example.shopping.domain.board.BoardServices;
import com.example.shopping.domain.board.BoardsEntity;
import com.example.shopping.domain.member.MemberEntity;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.session.StandardSessionFacade;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;


@RequestMapping("/board")
@RequiredArgsConstructor
@Controller
public class BoardController {
    private final BoardServices boardServices;


    @GetMapping
    public String boardList(Model model) {
        List<BoardsEntity> boards = boardServices.findAllBoard();
        model.addAttribute("boards", boards);
        return "/board/boards";
    }


    @GetMapping("/add")
    public String boards(@ModelAttribute("board") BoardSaveForm boardSaveForm) {

        return "board/addForm";
    }
    @PostMapping("/add")
    public String newBoard(@Validated @ModelAttribute("board") BoardSaveForm boardSaveForm, BindingResult bindingResult
            , RedirectAttributes redirectAttributes, HttpServletRequest request) {
        if (bindingResult.hasErrors()) {
            return "/board/addForm";
        }
        HttpSession session = request.getSession(false);
        MemberEntity MemberAttribute = (MemberEntity) session.getAttribute(SessionConst.LOGIN_MEMBER);
        Long Member_id = MemberAttribute.getId();


        BoardsEntity newBoard = new BoardsEntity().CreateBoard
                (MemberAttribute.getUserName(), boardSaveForm.getTitle(),
                boardSaveForm.getText());


        Long id = boardServices.saveBoard(newBoard, Member_id);
        redirectAttributes.addAttribute("board_id", id);
        redirectAttributes.addAttribute("status", true);

        return "redirect:/board/{board_id}";

    }


    @GetMapping("/{board_id}")
    public String board(@PathVariable Long board_id, Model model) {
        BoardsEntity findBoad = boardServices.findBoard(board_id);
        model.addAttribute("board", findBoad);
        return "board/board";
    }


    @GetMapping("/{board_id}/edit")
    public String editBoard(@PathVariable Long board_id, Model model) {
        BoardsEntity board = boardServices.findBoard(board_id);
        model.addAttribute("board", board);
        return "board/editForm";
    }

    @PostMapping("/{board_id}/edit")
    public String editBoard(@PathVariable Long board_id,
                            @Validated @ModelAttribute("board")BoardUpdateForm form,
                            BindingResult bindingResult,HttpServletRequest request){

   /*     HttpSession session = request.getSession(false);
        MemberEntity MemberAttribute = (MemberEntity) session.getAttribute(SessionConst.LOGIN_MEMBER);
*/
//        BoardsEntity board = boardServices.findBoard(board_id);

/*
        if (!board.getMember().equals(MemberAttribute.getUserName())) {
            bindingResult.reject("editFail", "글 수정은 본인만 가능합니다.");
            return "/board/board";
        }*/


        if(bindingResult.hasErrors()){
            return "board/editForm";
        }

     HttpSession session = request.getSession(false);
        MemberEntity MemberAttribute = (MemberEntity) session.getAttribute(SessionConst.LOGIN_MEMBER);

        BoardsEntity boards = new  BoardsEntity().CreateBoard
                (MemberAttribute.getUserName(), form.getTitle(), form.getText());
        Long editBoard_id = boardServices.updateBoard(boards);

        return "redirect:/board/"+editBoard_id;
    }
}
