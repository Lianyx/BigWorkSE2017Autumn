package data.checkdata;

import dataService.checkdataService.ReceiptDataService;
import mapper.generic.ReceiptPOMapper;
import mybatis.MyBatisUtil;
import org.apache.ibatis.binding.BindingException;
import org.apache.ibatis.session.SqlSession;
import po.receiptPO.ReceiptPO;
import util.ReceiptState;
import util.ResultMessage;
import util.ReceiptSearchCondition;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;

public class ReceiptData<T extends ReceiptPO> extends UnicastRemoteObject implements ReceiptDataService<T> {
    private Class<? extends ReceiptPOMapper<T>> mapperClass = null;

    public ReceiptData(Class<? extends ReceiptPOMapper<T>> mapperClass) throws RemoteException{
        this.mapperClass = mapperClass;
    }

    @Override
    public int getDayId() throws RemoteException {
        LocalDateTime today = LocalDateTime.of(LocalDate.now(), LocalTime.MIN);

        int resultID;
        try (SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession()) {
            ReceiptPOMapper<T> mapper = session.getMapper(mapperClass);
            resultID = mapper.getDayId(today);
            session.commit();
        } catch (BindingException e) { // TODO 这样好像不好…
            return 0;
        }
        return resultID;
    }

    @Override
    public ResultMessage insert(T promotionPO) throws RemoteException {
        promotionPO.setCreateTime(LocalDateTime.now());
        promotionPO.setLastModifiedTime(LocalDateTime.now());

        try (SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession()) {
            ReceiptPOMapper<T> mapper = session.getMapper(mapperClass);
            mapper.insert(promotionPO);
            session.commit();
        }
        return ResultMessage.SUCCESS;
    }

    @Override
    // TODO 如果审批本来就通过，那么不应该改
    public ResultMessage update(T promotionPO) throws RemoteException{
        promotionPO.setLastModifiedTime(LocalDateTime.now());

        try (SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession()) {
            ReceiptPOMapper<T> mapper = session.getMapper(mapperClass);
            mapper.update(promotionPO);
            session.commit();
        }
        return ResultMessage.SUCCESS;
    }

    @Override
    public ResultMessage delete(T promotionPO) throws RemoteException{
        try (SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession()) {
            ReceiptPOMapper<T> mapper = session.getMapper(mapperClass);
            mapper.delete(promotionPO);
            session.commit();
        }
        return ResultMessage.SUCCESS;
    }

    @Override
    public ArrayList<T> selectBetween(LocalDateTime begin, LocalDateTime end) throws RemoteException{
        ArrayList<T> resultList;

        try (SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession()) {
            ReceiptPOMapper<T> mapper = session.getMapper(mapperClass);
            resultList = mapper.selectBetween(begin, end);
            // TODO 为什么这里返回就不是arraylist，下面就是
            session.commit();
        }
        return resultList;
    }

    @Override
    public ArrayList<T> selectByState(ReceiptState receiptState) throws RemoteException {
        ArrayList<T> resultList;

        try (SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession()) {
            ReceiptPOMapper<T> mapper = session.getMapper(mapperClass);
            resultList = mapper.selectByState(receiptState);
            session.commit();
        }
        return resultList;
    }

    @Override
    public ArrayList<T> search(ReceiptSearchCondition receiptSearchCondition) throws RemoteException {
        ArrayList<T> resultList;

        try (SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession()) {
            ReceiptPOMapper<T> mapper = session.getMapper(mapperClass);
            resultList = mapper.search(receiptSearchCondition);
            session.commit();
        }
        return resultList;
    }
}
