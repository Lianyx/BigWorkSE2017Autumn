package data.businessdata;

import dataService.businessdataService.BusinessConditionDataService;
import mapper.BusinessConditionPOMapper;
import mybatis.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;
import po.BusinessConditionPO;

import java.time.LocalDateTime;
import java.util.List;

public class BusinessConditionData implements BusinessConditionDataService{

    @Override
    public List<BusinessConditionPO> select(LocalDateTime begin,LocalDateTime end){

        List<BusinessConditionPO> list = null;
        try(SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession()){
            BusinessConditionPOMapper mapper = sqlSession.getMapper(BusinessConditionPOMapper.class);
            list = mapper.select(begin,end);

        }
        return list;
    }


}
