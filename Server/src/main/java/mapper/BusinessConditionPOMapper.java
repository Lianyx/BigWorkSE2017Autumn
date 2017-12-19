package mapper;

import org.apache.ibatis.annotations.Param;
import po.BusinessConditionPO;

import java.time.LocalDateTime;
import java.util.List;

public interface BusinessConditionPOMapper {


    public List<BusinessConditionPO> select(@Param("begin") LocalDateTime begin, @Param("end") LocalDateTime end);

}
