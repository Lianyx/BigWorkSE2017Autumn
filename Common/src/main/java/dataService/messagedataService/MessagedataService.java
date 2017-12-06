package dataService.messagedataService;

import po.MessagePO;
import util.ResultMessage;


import java.util.ArrayList;
import java.util.Set;

public interface MessagedataService {
    public ResultMessage insert(MessagePO messagePO);
    public ArrayList<MessagePO> getViewed();
    public ArrayList<MessagePO> getNotViewed();
    public ResultMessage viewed(MessagePO messagePO);
    public ResultMessage delete(int id);
    public ResultMessage delete(ArrayList<Integer> list);
}
