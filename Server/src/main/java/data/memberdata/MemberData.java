package data.memberdata;

import annotations.RMIRemote;
import dataService.memberdataService.MemberdataService;
import mapper.MemberPOMapper;
import mybatis.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;
import po.MemberPO;
import util.ImageConvertor;
import util.MemberCategory;
import util.MemberSearchCondition;
import util.ResultMessage;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.stream.Collectors;
@RMIRemote
public class MemberData extends UnicastRemoteObject implements MemberdataService{
    public MemberData() throws RemoteException {
    }

    @Override
    public MemberPO getNew() throws RemoteException {
        try (SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession()) {
            MemberPOMapper mapper = session.getMapper(MemberPOMapper.class);
            int i = mapper.getId();
            System.out.println(i);
            MemberPO memberPO = new MemberPO();
            memberPO.setMemberId(i);
            memberPO.setIsDeleted(0);
            memberPO.setMemberCategory(MemberCategory.SELLER);
            insert(memberPO);
            return memberPO;
        }
    }
    private ResultMessage insert(MemberPO memberPO) throws RemoteException{
        try (SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession()) {
            MemberPOMapper mapper = session.getMapper(MemberPOMapper.class);
            mapper.insert(memberPO);
            session.commit();
            return ResultMessage.SUCCESS;
        }
    }


    @Override
    public ResultMessage delete(int id) throws RemoteException {
        try (SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession()) {
            MemberPOMapper mapper = session.getMapper(MemberPOMapper.class);
            MemberPO memberPO = showDetail(id);
            memberPO.setIsDeleted(1);
            update(memberPO);
            session.commit();
            return ResultMessage.SUCCESS;
        }
    }

    @Override
    public ResultMessage update(MemberPO memberPO) throws RemoteException {
        try (SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession()) {
            MemberPOMapper mapper = session.getMapper(MemberPOMapper.class);
            mapper.update(memberPO);
            session.commit();
            return ResultMessage.SUCCESS;
        }
    }

    @Override
    public MemberPO showDetail(int id) throws RemoteException {
        try (SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession()) {
            MemberPOMapper mapper = session.getMapper(MemberPOMapper.class);
            MemberPO memberPO = mapper.showDetail(id);
            return memberPO;
        }
    }

    @Override
    public ArrayList<MemberPO> search(MemberSearchCondition memberSearchCondition) throws RemoteException {
        try (SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession()) {
            MemberPOMapper mapper = session.getMapper(MemberPOMapper.class);
            ArrayList<MemberPO> memberPOS = mapper.search(memberSearchCondition);
            memberPOS = memberPOS.stream().filter(userPO -> userPO.getIsDeleted()==0).collect(Collectors.toCollection(ArrayList::new));
            System.out.println(memberPOS.size());
            session.commit();
            return memberPOS;
        }
    }
}
