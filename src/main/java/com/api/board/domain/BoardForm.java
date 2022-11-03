package com.api.board.domain;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import java.util.List;

@Data
public class BoardForm {

    @NotBlank
    @Size(min=1, max=10)
    private String boardWriter;

    @NotBlank
    @Size(min=1, max=75)
    private String boardSubject;

    @NotBlank
    @Size(min=1, max=1000)
    private String boardContent;

    private List<UploadFiles> uploadFilesList;

}
