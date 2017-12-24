package data.accountdata;


import dataService.accountDataService.AccountDataService;
import mapper.AccountPOMapper;
import mybatis.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;
import po.AccountPO;
import util.ResultMessage;

import java.util.List;

public class AccountData implements AccountDataService{

    /*private AccountPOMapper mapperClass = null;


    public AccountData(AccountPOMapper mapperClass){
        this.mapperClass = mapperClass;
    }*/

    @Override
    public int getID(String name){
        int result=0;

        try (SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession()) {
            AccountPOMapper mapper = session.getMapper(AccountPOMapper.class);
            mapper.getID(name);
        }
        return result;
    }

    @Override
    public AccountPO getAccountByName(String name){
        AccountPO accountPO;
        try(SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession()){
            AccountPOMapper mapper = sqlSession.getMapper(AccountPOMapper.class);
            accountPO = mapper.getAccountByName(name);
        }
        return accountPO;
    }

    @Override
    public ResultMessage insert(AccountPO accountPO) {

        try (SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession()) {
            AccountPOMapper mapper = session.getMapper(AccountPOMapper.class);
            mapper.insert(accountPO);
            session.commit();
        }
        return ResultMessage.SUCCESS;
    }

    @Override
    public ResultMessage update(AccountPO accountPO) {

        try (SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession()) {
            AccountPOMapper mapper = session.getMapper(AccountPOMapper.class);
            mapper.update(accountPO);
            session.commit();
        }
        return ResultMessage.SUCCESS;
    }

    @Override
    public ResultMessage delete(int ID) {
        try (SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession()) {
            AccountPOMapper mapper = session.getMapper(AccountPOMapper.class);
            mapper.delete(ID);
            session.commit();
        }
        return ResultMessage.SUCCESS;
    }

    @Override
    public List<AccountPO> selectInEffect(String keyword) {

        List<AccountPO> resultList;

        try (SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession()) {
            AccountPOMapper mapper = session.getMapper(AccountPOMapper.class);
            resultList = mapper.selectInEffect(keyword);
            session.commit();
        }
        return resultList;
    }

    @Override
    public List<AccountPO> getAll(){

        List<AccountPO> resultList;

        try (SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession()) {
            AccountPOMapper mapper = session.getMapper(AccountPOMapper.class);
            resultList = mapper.getAll();
            session.commit();
        }
        return resultList;
    }



}
