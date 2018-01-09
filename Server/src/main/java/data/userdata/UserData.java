package data.userdata;

import annotations.RMIRemote;
import dataService.userdataService.UserDataService;
import mapper.UserPOMapper;
import mybatis.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;
import po.UserPO;
import util.ResultMessage;
import util.UserSearchCondition;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
@RMIRemote
public class UserData extends UnicastRemoteObject implements UserDataService {

    public UserData() throws RemoteException {
    }

    @Override
    public int getId() throws RemoteException {
        try (SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession()) {
            UserPOMapper mapper = session.getMapper(UserPOMapper.class);
            int id = mapper.getId();
            return id;
        }
    }

    @Override
    public ResultMessage insert(UserPO userPO) throws RemoteException {
        try (SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession()) {
            UserPOMapper mapper = session.getMapper(UserPOMapper.class);
            mapper.insert(userPO);
            return ResultMessage.SUCCESS;
        }
    }

    @Override
    public ResultMessage delete(int id) throws RemoteException {
        try (SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession()) {
            UserPOMapper mapper = session.getMapper(UserPOMapper.class);
            mapper.delete(id);
            return ResultMessage.SUCCESS;
        }
    }

    @Override
    public ResultMessage update(UserPO userPO) throws RemoteException {
        try (SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession()) {
            UserPOMapper mapper = session.getMapper(UserPOMapper.class);
            mapper.update(userPO);
            return ResultMessage.SUCCESS;
        }
    }

    @Override
    public UserPO showDetail(int id) throws RemoteException {
        try (SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession()) {
            UserPOMapper mapper = session.getMapper(UserPOMapper.class);
            UserPO userPO = mapper.showDetail(id);
            return userPO;
        }
    }

    @Override
    public ArrayList<UserPO> search(UserSearchCondition userSearchCondition) throws RemoteException {
        try (SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession()) {
            UserPOMapper mapper = session.getMapper(UserPOMapper.class);
            ArrayList<UserPO> userPOs = mapper.search(userSearchCondition);
            System.out.println("sabi");
            System.out.println(userPOs.size());
            return userPOs;
        }
    }

    @Override
    public UserPO checkPassword(String username, String password) throws RemoteException {
        try (SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession()) {
            UserPOMapper mapper = session.getMapper(UserPOMapper.class);
            UserPO userPO = mapper.getPassword(username);
            if(userPO == null)
                return null;
            if(userPO.getPassword() == password)
                return userPO;


            session.commit();
        }
        return null;
    }
}
