package com.api.board.service.Impl;

import com.api.board.domain.Board;
import com.api.board.domain.UploadFiles;
import com.api.board.mapper.UploadMapper;
import com.api.board.service.UploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UploadServiceImpl implements UploadService {
    @Autowired
    private UploadMapper uploadMapper;

    @Override
    public List<UploadFiles> getUploadList(int boardSeq) throws Exception {
        return uploadMapper.getUploadList(boardSeq);
    }

    @Override
    public UploadFiles getUploadDetail(int imgSeq) throws Exception {
        return uploadMapper.getUploadDetail(imgSeq);
    }

    @Override
    public int insertUpload(UploadFiles uploadFiles){
        return uploadMapper.insertUpload(uploadFiles);
    }

    @Override
    public List<UploadFiles> getOldFiles() throws Exception {
        return uploadMapper.getOldFiles();
    }


}
