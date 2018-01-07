package ui.stockui;

import blService.blServiceFactory.ServiceFactory;
import blService.blServiceFactory.ServiceFactory_Stub;
import blService.stockblService.StockblService;
import com.jfoenix.controls.*;
import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIconView;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ListChangeListener;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import ui.util.*;
import util.ReceiptState;
import vo.ListGoodsItemVO;
import vo.receiptVO.StockReceiptVO;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.time.*;
import java.util.HashSet;
import java.util.function.Predicate;

import static ui.util.ValidatorDecorator.NumberValid;
import static ui.util.ValidatorDecorator.RequireValid;

/**
 * @Author: Lin Yuchao
 * @Description: 详细介面
 * @ModifyBy: Lin Yuchao
**/
public class StockReceiptPane extends ReceiptDetailPane<StockReceiptVO> {



    /**
    * 这里一定要有service和单据的除了delete和modify和save的其他组建
    **/

    @FXML
    StockListItemTreeTable stockListItemTreeTable;

    StockblService stockblService;
    @FXML
    TextField operator;
    @FXML
    TextField provider;
    @FXML
    JFXTextField sum;
    @FXML
    TextField stock;
    @FXML
    JFXDatePicker date;

    @FXML
    Label head;
    @FXML
    JFXButton member;
    @FXML
    JFXButton user;

    @FXML
    Label id;


    //根据id去数据库找vo
    int memberId = 0;



    @FXML
    TextArea comment;

    SimpleBooleanProperty isPur = new SimpleBooleanProperty();



    /**
     * @Author: Lin Yuchao
     * @Attention 单据查看 requrievalid就是要求textfield要有输入，numbervalid是要数字输入，doublevalid是double输入
     * @Param:
     * @Return:
    **/

    public StockReceiptPane(String id) {
        super("/stockui/stockreceipt.fxml",id);
        stockblService = ServiceFactory_Stub.getService(StockblService.class.getName());
        provider.setDisable(true);
        operator.setDisable(true);
        sum.setDisable(true);
        stock.disableProperty().bind(modifyState.not());
        date.disableProperty().bind(modifyState.not());
        member.disableProperty().bind(modifyState.not());
        user.disableProperty().bind(modifyState.not());

        RequireValid(operator);
        RequireValid(provider);
        RequireValid(stock);


        comment.disableProperty().bind(modifyState.not());

        if(id.split("-")[0].equals("JHD")){
            this.isPur.set(true);
        }else{
            this.isPur.set(false);
        }

        sum.setText("0");
        stockListItemTreeTable.sumProperty().addListener(t->{sum.setText(stockListItemTreeTable.getSum()+"");});
    }


    /**
     * @Author: Lin Yuchao
     * @Attention 单据新增界面  super的父类构造不一，父类有个updatestate的bool用来区别
     * @Param:
     * @Return:
    **/
    public StockReceiptPane(boolean isPur){
        super("/stockui/stockreceipt.fxml");
        stockblService = ServiceFactory_Stub.getService(StockblService.class.getName());
        provider.setDisable(true);
        operator.setDisable(true);
        sum.setDisable(true);
        operator.setText(UserInfomation.username);
        date.setValue(LocalDate.now());
        RequireValid(provider);
        RequireValid(stock);
        switchPane(true);
        this.isPur.set(isPur);
        if(isPur){
            head.setText("JHD-");
        }else{
            head.setText("JHTHD-");
        }
        id.setText("-"+ String.format("%05d", stockblService.getDayId()));
    }

    //接口未完成
    @FXML
    public void add() {
        stockListItemTreeTable.addGood(new ListGoodsItemVO("a", 1, "a", 1, 1, "a"));
    }

    //接口未完成
    @Override
    public void delete() {
        DoubleButtonDialog doubleButtonDialog = new DoubleButtonDialog(mainpane,"Delete","sabi","Yes","No");
        doubleButtonDialog.setButtonOne(()->{});
        doubleButtonDialog.setButtonTwo(()->{
            stockblService.delete(this.id.getText());

            //这里返回上个界面
            boardController.setRightAnimation();
            boardController.historicalSwitchTo((Refreshable) HistoricalRecord.pop());
            boardController.refresh();
            //removeAndPop是指pop出去后不能通过topbar的前进键前进
            HistoricalRecord.removeAndPop();
        });
        doubleButtonDialog.show();
    }

    /**
    * 这个是生成自己的名字
    **/
    @FXML
    public void currentUser() {
        operator.setText(UserInfomation.username);
    }


    /**
    * 这个是客户选择
    **/
    @FXML
    public void selectMember() {
        provider.setText("sabi");
        this.memberId = 5;
    }

    @Override
    public void refresh(boolean toSwitch) {
        boardController.Loading();
        try{

            /**
            * 查看
            **/
            if(!updateState.get()) {
                DoubleButtonDialog buttonDialog =
                        new DoubleButtonDialog(mainpane, "Wrong", "sabi", "Last", "Ret");
                buttonDialog.setButtonTwo(() -> boardController.Ret());
                buttonDialog.setButtonTwo(() -> refresh(false));
                Predicate<Integer> p = (i) -> {
                    if ((vo = stockblService.showDetail(receiptid)) != null) return true;
                    return false;
                };
                GetTask task =
                        new GetTask(() -> {
                            provider.setText(vo.getMemberName());
                            operator.setText(UserInfomation.username);
                            stock.setText(vo.getStockName());
                            date.setValue(vo.getCreateTime().toLocalDate());
                            comment.setText(vo.getComment());
                            id.setText("-" + vo.getId().split("-")[2]);
                            memberId = vo.getMemberId();
                            head.setText(vo.getId().split("-")[0] + "-");
                            sum.setText(vo.getSum() + "");
                            stockListItemTreeTable.setList(vo.getItems());
                            switchPane(toSwitch);
                        }, buttonDialog, p);

                new Thread(task).start();
            }else {

                /**
                 * 新建的跳转
                 **/
                switchPane(toSwitch);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }



    /**
     * 存储成pending的逻辑
     **/
    @Override
    public void savePendingReceipt(){
        DoubleButtonDialog doubleButtonDialog = new DoubleButtonDialog(mainpane, "Pending?", "sabi", "Yes", "No");
        doubleButtonDialog.setButtonTwo(() -> {
        });
        doubleButtonDialog.setButtonOne(() -> {


        this.receiptid = head.getText().replace("-","")+"-"+date.getValue().toString().replace("-","")+"-"+id.getText().replace("-","");
        this.vo = new StockReceiptVO(receiptid,
                UserInfomation.userid,
                LocalDateTime.now(),LocalDateTime.now(),
                ReceiptState.PENDING,
                this.memberId,provider.getText(),
                stock.getText(),
                Double.parseDouble(sum.getText()),
                stockListItemTreeTable.getList(),
                comment.getText(),
                isPur.get());
        try {

            /**
             * 根据是否为更新来判断是否是添加还是更新
             **/
            if (updateState.get())
                stockblService.insert(this.vo);
            else
                stockblService.update(this.vo);
            setBack();
        }catch (Exception e){
            e.printStackTrace();
        }
        });
        doubleButtonDialog.show();
    }

    @Override
    public void saveDraftReceipt() {
        DoubleButtonDialog doubleButtonDialog = new DoubleButtonDialog(mainpane, "Pending?", "sabi", "Yes", "No");
        doubleButtonDialog.setButtonTwo(() -> {
        });
        doubleButtonDialog.setButtonOne(() -> {
            if (sum.getText().equals("") || sum.getText() == null) {
                sum.setText("1");
            }
            if (date.getValue() == null)
                date.setValue(LocalDate.now());
            this.receiptid = head.getText().replace("-", "") + "-" + date.getValue().toString().replace("-", "") + "-" + id.getText().replace("-", "");
            this.vo = new StockReceiptVO(receiptid,
                    UserInfomation.userid,
                    LocalDateTime.now(),
                    LocalDateTime.now(),
                    ReceiptState.DRAFT,
                    this.memberId,
                    provider.getText(),
                    stock.getText(),
                    Double.parseDouble(sum.getText()),
                    stockListItemTreeTable.getList(),
                    comment.getText(),
                    isPur.get());
            try {
                if (updateState.get())
                    stockblService.insert(this.vo);
                else
                    stockblService.update(this.vo);
                setBack();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        doubleButtonDialog.show();
    }

    public boolean valid(){
        if(date.getValue()!=null&&!operator.getText().equals("")&&!provider.getText().equals("")&&!stock.getText().equals("")&&!sum.getText().equals(""))
            return true;
        return false;
    }

}
