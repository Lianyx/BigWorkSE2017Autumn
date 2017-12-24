package data.goodsdata;

import dataService.goodsdataService.GoodsDataService;
import mapper.GoodsPOMapper;
import mybatis.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;
import po.GoodsPO;
import util.ResultMessage;

import java.util.ArrayList;

public class GoodsData implements GoodsDataService {
    @Override
    public ResultMessage insert(GoodsPO po) {
        SqlSession session = null;
        try {
            session = MyBatisUtil.getSqlSessionFactory().openSession();
            GoodsPOMapper mapper = session.getMapper(GoodsPOMapper.class);
            mapper.insert(po);
            session.commit();
        }finally {
            session.close();
        }
        return ResultMessage.SUCCESS;
    }

    @Override
    public ResultMessage delete(String id) {
        SqlSession session = null;
        try {
            session = MyBatisUtil.getSqlSessionFactory().openSession();
            GoodsPOMapper mapper = session.getMapper(GoodsPOMapper.class);
            mapper.delete(id);
        }finally {
            if(session != null)
                session.commit();
            session.close();
        }
        return ResultMessage.SUCCESS;
    }

    @Override
    public ResultMessage update(GoodsPO po) {
        SqlSession session = null;
        try {
            session = MyBatisUtil.getSqlSessionFactory().openSession();
            GoodsPOMapper mapper = session.getMapper(GoodsPOMapper.class);
            mapper.update(po);
        }finally {
            if(session != null)
                session.commit();
            session.close();
        }
        return ResultMessage.SUCCESS;
    }

    @Override
    public ArrayList<GoodsPO> show() {
        SqlSession session = null;
        ArrayList<GoodsPO> list;
        try {
            session = MyBatisUtil.getSqlSessionFactory().openSession();
            GoodsPOMapper mapper = session.getMapper(GoodsPOMapper.class);
            list = mapper.show();
        }finally {
            if(session != null)
                session.commit();
            session.close();
        }
        return list;
    }

    @Override
    public ArrayList<GoodsPO> select(String keywords) {
        SqlSession session = null;
        ArrayList<GoodsPO> list;
        try {
            session = MyBatisUtil.getSqlSessionFactory().openSession();
            GoodsPOMapper mapper = session.getMapper(GoodsPOMapper.class);
            list = mapper.select(keywords);
        }finally {
            session.close();
        }
        return list;
    }
}
