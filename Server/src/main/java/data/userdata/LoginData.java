package data.userdata;

import annotations.RMIRemote;
import com.sun.org.apache.regexp.internal.RE;
import dataService.userdataService.LoginDataService;
import dataService.userdataService.UserDataService;
import mapper.UserDataPOMapper;
import mybatis.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;
import po.UserPO;
import util.ResultMessage;
import util.UserCategory;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

@RMIRemote
public class LoginData extends UnicastRemoteObject implements LoginDataService {
    public LoginData() throws RemoteException {
    }

    @Override
    public ResultMessage login(String username, String password) {
        try (SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession()) {
            UserDataPOMapper mapper = session.getMapper(UserDataPOMapper.class);
            UserPO userPO = mapper.getPassword(username);
            if(userPO.getPassword().equals(password)){
                return ResultMessage.SUCCESS;
            }else{
                return ResultMessage.FAIL;
            }
        }
    }

    @Override
    public UserPO getCategory(String username) {
            try (SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession()) {
                UserDataPOMapper mapper = session.getMapper(UserDataPOMapper.class);
                UserPO userPO = mapper.getPassword(username);
                return userPO;
            }
    }
}
