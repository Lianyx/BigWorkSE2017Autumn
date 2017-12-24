package data.establishdata;

import dataService.establishDataService.EstablishDataService;
import mapper.EstablishMapper;
import mybatis.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;
import util.ResultMessage;

import java.util.Calendar;

public class EstablishData implements EstablishDataService{

    public ResultMessage init(){

        Calendar c = Calendar.getInstance();
        String time = String.valueOf(c.get(Calendar.YEAR));

        try (SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession()) {
            EstablishMapper mapper = session.getMapper(EstablishMapper.class);
            mapper.transfer(time);
        }

        return ResultMessage.SUCCESS;
    }

}
