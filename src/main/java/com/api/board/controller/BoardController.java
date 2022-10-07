package com.api.board.controller;

import com.api.board.domain.Board;
import com.api.board.domain.BoardForm;
import com.api.board.domain.Boards;
import com.api.board.domain.UploadFiles;
import com.api.board.exception.ResourceNotFoundException;
import com.api.board.service.BoardService;
import com.api.board.service.UploadService;
import com.google.gson.Gson;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.log4j.Log4j2;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Api(tags = "게시글 API : Board", description = "게시글 목록 조회, 상세 조회, 등록, 삭제, 수정 API")
@RequestMapping("/boards")
@RestController
@Log4j2
public class BoardController {
 
    @Autowired
    private BoardService boardService;

    @Autowired
    private UploadService uploadService;
 
    /** 게시글 목록 조회 */
    @ApiOperation(value = "게시글 목록 조회", notes = "게시글 목록을 조회합니다.")
    @GetMapping
    public Boards getBoardList() throws Exception {
    	Boards boards = new Boards();
    	boards.setBoards(boardService.getBoardList());

    	return boards;
    }
 
    /** 게시글 상세 조회 */
    @ApiOperation(value = "게시글 상세 조회", notes = "게시글를 상세 조회합니다.")
    @GetMapping("/{boardSeq}")
    public Board getBoardDetail(@PathVariable("boardSeq") int boardSeq) throws Exception {
    	Board board = boardService.getBoardDetail(boardSeq);

        if(board == null) {
        	throw new ResourceNotFoundException();
        }
        
    	return board; 
    }

    @ResponseStatus(value = HttpStatus.CREATED)
    @PostMapping("/test")
    public String insertBoardV2(@Valid @RequestBody BoardForm boardForm) {

        Board board = new Board(
                boardForm.getBoardWriter(), boardForm.getBoardSubject(), boardForm.getBoardContent()
                );
        boardService.insertBoard(board);


        if (boardForm.getUploadFilesList() != null){
            int boardSeq = board.getBoardSeq();
            List<UploadFiles> uploadFilesList = boardForm.getUploadFilesList();
            log.info("boardSeq : {}", boardSeq);
            for (int i = 0; i < uploadFilesList.size(); i++) {
                UploadFiles uploadFiles = uploadFilesList.get(i);
                uploadFiles.setBoardSeq(boardSeq);
                uploadService.insertUpload(uploadFiles);
            }
        }
        return "success";
    }

    /** 게시글 등록  */
    @ApiOperation(value = "게시글 등록", notes = "게시글을 등록합니다.")
    @ResponseStatus(value = HttpStatus.CREATED)
    @PostMapping
    public String insertBoard(String jsonBoard, String uploadFilesList) throws Exception {

        Gson gson = new Gson();
        Board board = gson.fromJson(jsonBoard, Board.class );
        boardService.insertBoard(board);
        int boardSeq = board.getBoardSeq();

        JSONArray jsonArray = jsonParsing(uploadFilesList);

        for(int i = 0; i < jsonArray.size(); i++){
            JSONObject jsonObject = (JSONObject) jsonArray.get(i);
            jsonObject.put("boardSeq", boardSeq);
            log.info("jsonObject : " + jsonObject);
            UploadFiles uploadFiles = gson.fromJson(String.valueOf(jsonObject), UploadFiles.class);
            uploadFiles.setBoardSeq(boardSeq);
            uploadService.insertUpload(uploadFiles);
        }

        return "success";
    }

    public JSONArray jsonParsing(String param) throws Exception {
        JSONArray jsonArray = new JSONArray();
        JSONParser parser = new JSONParser();

        jsonArray = (JSONArray) parser.parse(param);
        return jsonArray;
    }
 
    /** 게시글 수정 */
    @ApiOperation(value = "게시글 수정", notes = "게시글을 수정합니다.")
    @ResponseStatus(value = HttpStatus.OK)
    @PutMapping("/{boardSeq}")
    public Board updateBoard(@PathVariable("boardSeq") int boardSeq, @RequestBody Board board) throws Exception {
 
        boardService.updateBoard(board);
 
        Board boardDetail = boardService.getBoardDetail(boardSeq);
 
        return boardDetail;
    }
 
    /** 게시글 삭제 */
    @ApiOperation(value = "게시글 삭제", notes = "게시글을 삭제합니다.")
    @ResponseStatus(value = HttpStatus.OK)
    @DeleteMapping("/{boardSeq}")
    public ResponseEntity<String> deleteBoard(@PathVariable("boardSeq") int boardSeq) throws Exception {

        return boardService.deleteBoard(boardSeq)? new ResponseEntity<>("success", HttpStatus.OK)
                :new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
