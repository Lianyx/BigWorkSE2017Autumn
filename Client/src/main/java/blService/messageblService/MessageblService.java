package blService.messageblService;

import po.MessagePO;
import util.ResultMessage;
import vo.MessageListVO;
import vo.MessageVO;

import java.util.ArrayList;
import java.util.Set;

public interface MessageblService {
    public ResultMessage send(MessageVO messageVO);
    public Set<MessageListVO> getViewed();
    public Set<MessageListVO> getNotViewed();
    public ResultMessage viewed(MessageVO messageVO);
    public ResultMessage delete(int id);
    public ResultMessage delete(ArrayList<Integer> list);
}

