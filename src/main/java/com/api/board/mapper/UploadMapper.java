package com.api.board.mapper;

import com.api.board.domain.Board;
import com.api.board.domain.UploadFiles;

import java.util.List;

public interface UploadMapper {

    public List<UploadFiles> getUploadList(int boardSeq) throws Exception;

    public UploadFiles getUploadDetail(int imgSeq) throws Exception;

    public int insertUpload(UploadFiles uploadFiles);

    public List<UploadFiles> getOldFiles();
}
