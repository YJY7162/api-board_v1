package com.api.board.service;

import com.api.board.domain.Board;
import com.api.board.domain.UploadFiles;

import java.util.List;

public interface UploadService {

    public List<UploadFiles> getUploadList(int boardSeq) throws Exception;

    public UploadFiles getUploadDetail(int imgSeq) throws Exception;

    /** 게시글 등록 */
    public int insertUpload(UploadFiles uploadFiles);

    public List<UploadFiles> getOldFiles() throws Exception;
}
