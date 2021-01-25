package com.mystudy.service;

import com.mystudy.dao.DeleteDao;
import com.mystudy.dao.QueryDao;
import com.mystudy.dao.SaveDao;
import com.mystudy.entity.FileMeta;
import com.mystudy.util.ListUtil;

import java.util.List;
import java.util.stream.Collectors;

//主要提供给文件扫描时，处理扫描结果和DB中结果差集时使用
public class FileService {
    private SaveDao saveDao = new SaveDao();
    private DeleteDao deleteDao = new DeleteDao();
    private QueryDao queryDao = new QueryDao();

    public void saveFileList(List<FileMeta> list) {
        saveDao.save(list);
    }

    public void deleteFileList(List<FileMeta> list) {
        List<Integer> ids = list.stream().map(FileMeta::getId).collect(Collectors.toList());
        deleteDao.delete(ids);
    }

    public List<FileMeta> query(String keyWord) {
        List<FileMeta> query = queryDao.query(keyWord);
        return query;
    }

    public void service(String path,List<FileMeta> scanList) {
        List<FileMeta> queryList = queryDao.queryByPath(path);

        //1. queryList - scanList
        List<FileMeta> ds1 = ListUtil.differenceSet(queryList,scanList);
        deleteFileList(ds1);

        //2. scanList - queryist
        List<FileMeta> ds2 = ListUtil.differenceSet(scanList,queryList);
        saveFileList(ds2);
    }
}
