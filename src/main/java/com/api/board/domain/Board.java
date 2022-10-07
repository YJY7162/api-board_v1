package com.api.board.domain;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.apache.tomcat.util.json.JSONParser;
import org.springframework.boot.configurationprocessor.json.JSONArray;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

@ApiModel(value = "게시글 정보 : Board", description = "게시글 정보")
@XmlRootElement(name = "board")
@XmlType(propOrder = {"boardSeq", "boardReRef", "boardReLev", "boardReSeq", "boardWriter", "boardSubject",
        "boardContent", "boardHits", "delYn", "insUserId", "insDate", "updUserId", "updDate"})
@Getter @Setter @ToString
public class Board {

	@ApiModelProperty(value = "게시글 번호")
    int boardSeq;
    @ApiModelProperty(value = "게시글 그룹 번호")
    int boardReRef;
    @ApiModelProperty(value = "게시글 답변 글의 깊이")
    int boardReLev;
    @ApiModelProperty(value = "게시글 답변 글의 순서")
    int boardReSeq;
    @ApiModelProperty(value = "게시글 작성자")
    String boardWriter;
    @ApiModelProperty(value = "게시글 제목")
    String boardSubject;
    @ApiModelProperty(value = "게시글 내용")
    String boardContent;
    @ApiModelProperty(value = "게시글 조회수")
    int boardHits;
    @ApiModelProperty(value = "게시글 삭제 유무")
    String delYn;
    @ApiModelProperty(value = "게시글 입력자 ID")
    String insUserId;
    @ApiModelProperty(value = "게시글 입력 일시")
    String insDate;
    @ApiModelProperty(value = "게시글 수정자 ID")
    String updUserId;
    @ApiModelProperty(value = "게시글 수정 일시")
    String updDate;

    public Board(String boardWriter, String boardSubject, String boardContent) {
        this.boardWriter = boardWriter;
        this.boardSubject = boardSubject;
        this.boardContent = boardContent;
    }

    public Board() {
    }
}
