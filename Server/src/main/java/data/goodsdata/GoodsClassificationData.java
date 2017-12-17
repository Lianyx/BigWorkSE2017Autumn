package data.goodsdata;

import dataService.goodsdataService.GoodsClassificationDataService;
import mybatis.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;
import po.GoodsClassificationPO;
import util.ResultMessage;


import java.util.ArrayList;
import java.util.List;

public class GoodsClassificationData implements GoodsClassificationDataService {
    @Override
    public ResultMessage insert(GoodsClassificationPO po) {
        SqlSession session = null;
        try {
            session = MyBatisUtil.getSqlSessionFactory().openSession();
            GoodsClassficationPOMapper mapper = session.getMapper(GoodsClassficationPOMapper.class);
            mapper.insert(po);
        }finally {
            if(session != null)
                session.commit();
            session.close();
        }
        return ResultMessage.SUCCESS;
    }

    @Override
    public ResultMessage delete(String id) {
        SqlSession session = null;
        try {
            session = MyBatisUtil.getSqlSessionFactory().openSession();
            GoodsClassficationPOMapper mapper = session.getMapper(GoodsClassficationPOMapper.class);
            mapper.delete(id);
        }finally {
            if(session != null)
                session.commit();
            session.close();
        }
        return ResultMessage.SUCCESS;
    }

    @Override
    public ResultMessage update(GoodsClassificationPO po) {
        SqlSession session = null;
        try {
            session = MyBatisUtil.getSqlSessionFactory().openSession();
            GoodsClassficationPOMapper mapper = session.getMapper(GoodsClassficationPOMapper.class);
            mapper.update(po);
        }finally {
            if(session != null)
                session.commit();
            session.close();
        }
        return ResultMessage.SUCCESS;
    }

    @Override
    public ArrayList<GoodsClassificationPO> show() {
        SqlSession session = null;
        ArrayList<GoodsClassificationPO> list;
        try {
            session = MyBatisUtil.getSqlSessionFactory().openSession();
            GoodsClassficationPOMapper mapper = session.getMapper(GoodsClassficationPOMapper.class);
            list =  mapper.show();
        }finally {
            if(session != null)
                session.commit();
            session.close();
        }
        return list;
    }

    @Override
    public GoodsClassificationPO getFather(String fatherId) {
        SqlSession session = null;
        GoodsClassificationPO father;
        try {
            session = MyBatisUtil.getSqlSessionFactory().openSession();
            GoodsClassficationPOMapper mapper = session.getMapper(GoodsClassficationPOMapper.class);
            father = mapper.getFather(fatherId);
        }finally {
            if(session != null)
                session.commit();
            session.close();
        }
        return father;
    }

}
