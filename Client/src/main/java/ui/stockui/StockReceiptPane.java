package ui.stockui;

import com.jfoenix.controls.*;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import ui.myAccountantui.common.ItemTreeTable;
import ui.myAccountantui.common.MyReceiptDetailPane;
import ui.util.*;
import vo.ListGoodsItemVO;
import vo.receiptVO.StockReceiptVO;

import java.time.*;

import static ui.util.ValidatorDecorator.RequireValid;
import static ui.util.ValidatorDecorator.isDouble;


public abstract class StockReceiptPane<T extends StockReceiptVO> extends MyReceiptDetailPane<T> {
    @FXML
    TextField operator;
    @FXML
    TextField provider;
    @FXML
    JFXTextField sum;
    @FXML
    TextField stock;

    @FXML
    JFXButton member;

    @FXML
    TextArea comment;

    @FXML
    ItemTreeTable itemTreeTable;

    public StockReceiptPane() {
    }

    public StockReceiptPane(T receiptVO) {
        super(receiptVO);
    }

    @Override
    public void initiate() {
        super.initiate();
        provider.setDisable(true);
        operator.setDisable(true);
        sum.setDisable(true);
        stock.disableProperty().bind(modifyState.not());
        member.disableProperty().bind(modifyState.not());

        RequireValid(operator);
        RequireValid(provider);
        RequireValid(stock);


        comment.disableProperty().bind(modifyState.not());
        sum.setText("0");
        itemTreeTable.sumProperty().addListener(t -> {
            sum.setText(itemTreeTable.getSum() + "");
        });

    }


    @Override
    protected String getURL() {
        return "/stockui/stockreceipt.fxml";
    }

    @Override
    protected void updateReceiptVO() {
        super.updateReceiptVO();
        receiptVO.setOperatorId(UserInfomation.userid);
        receiptVO.setLastModifiedTime(LocalDateTime.now());
        receiptVO.setMemberId(3);
        receiptVO.setMemberName(provider.getText());
        receiptVO.setStockName(stock.getText());
        receiptVO.setSum(Double.parseDouble(sum.getText()));
        receiptVO.setItems(itemTreeTable.getList());
        receiptVO.setComment(comment.getText());
    }

    @Override
    protected void reset() {
        super.reset();
        provider.setText(receiptVO.getMemberName());
        operator.setText(UserInfomation.username);
        stock.setText(receiptVO.getStockName());
        comment.setText(receiptVO.getComment());
        sum.setText(receiptVO.getSum() + "");
    }

    @Override
    public boolean validate() {
        if (super.validate() && isDouble(sum.getText())) {
            return true;
        }
        return false;
    }

    @FXML
    public void addTransfer() {
        itemTreeTable.add(new ListGoodsItemVO("a", "11", "a", 1,  1, "a"));
    }

    @FXML
    public void selectMember() {

    }
   /*

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


    //����idȥ���ݿ���vo
    int memberId = 0;



    @FXML
    TextArea comment;

    SimpleBooleanProperty isPur = new SimpleBooleanProperty();




    public StockReceiptPane(String id) {
        super("/stockui/stockreceipt.fxml");
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
      //  id.setText("-"+ String.format("%05d", stockblService.getDayId()));
    }




    //�ӿ�δ���
    @FXML
    public void add() {
        stockListItemTreeTable.addGood(new ListGoodsItemVO("a", 1, "a", 1, 1, "a"));
    }

    //�ӿ�δ���
    @Override
    public void delete() {
        DoubleButtonDialog doubleButtonDialog = new DoubleButtonDialog(mainpane,"Delete","sabi","Yes","No");
        doubleButtonDialog.setButtonOne(()->{});
        doubleButtonDialog.setButtonTwo(()->{

           // stockblService.delete(this.id.getText());

            //���ﷵ���ϸ�����
            boardController.setRightAnimation();
            boardController.historicalSwitchTo((Refreshable) HistoricalRecord.pop());
            boardController.refresh();
            //removeAndPop��ָpop��ȥ����ͨ��topbar��ǰ����ǰ��
            HistoricalRecord.removeAndPop();
        });
        doubleButtonDialog.show();
    }


    @FXML
    public void currentUser() {
        operator.setText(UserInfomation.username);
    }



    @FXML
    public void selectMember() {
        provider.setText("sabi");
        this.memberId = 5;
    }

    @Override
    public void refresh(boolean toSwitch) {
        boardController.Loading();
        try{


            if(!updateState.get()) {
                DoubleButtonDialog buttonDialog =
                        new DoubleButtonDialog(mainpane, "Wrong", "sabi", "Last", "Ret");
                buttonDialog.setButtonTwo(() -> boardController.Ret());
                buttonDialog.setButtonTwo(() -> refresh(false));
                Predicate<Integer> p = (i) -> {
            //        if ((vo = stockblService.showDetail(receiptid)) != null) return true;
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


                switchPane(toSwitch);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }



    @Override
    public void savePendingReceipt(){
        DoubleButtonDialog doubleButtonDialog = new DoubleButtonDialog(mainpane, "Pending?", "sabi", "Yes", "No");
        doubleButtonDialog.setButtonTwo(() -> {
        });
        doubleButtonDialog.setButtonOne(() -> {


        this.receiptid = head.getText().replace("-","")+"-"+date.getValue().toString().replace("-","")+"-"+id.getText().replace("-","");
        if(isPur.get())
        this.vo = new StockPurReceiptVO(receiptid,
                UserInfomation.userid,
                LocalDateTime.now(),LocalDateTime.now(),
                ReceiptState.PENDING,
                this.memberId,provider.getText(),
                stock.getText(),
                Double.parseDouble(sum.getText()),
                stockListItemTreeTable.getList(),
                comment.getText());
        else
            this.vo = new StockRetReceiptVO(receiptid,
                    UserInfomation.userid,
                    LocalDateTime.now(),LocalDateTime.now(),
                    ReceiptState.PENDING,
                    this.memberId,provider.getText(),
                    stock.getText(),
                    Double.parseDouble(sum.getText()),
                    stockListItemTreeTable.getList(),
                    comment.getText());
        try {

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
            if(isPur.get())
            this.vo = new StockPurReceiptVO(receiptid,
                    UserInfomation.userid,
                    LocalDateTime.now(),
                    LocalDateTime.now(),
                    ReceiptState.DRAFT,
                    this.memberId,
                    provider.getText(),
                    stock.getText(),
                    Double.parseDouble(sum.getText()),
                    stockListItemTreeTable.getList(),
                    comment.getText());
            else
                this.vo = new StockRetReceiptVO(receiptid,
                        UserInfomation.userid,
                        LocalDateTime.now(),
                        LocalDateTime.now(),
                        ReceiptState.DRAFT,
                        this.memberId,
                        provider.getText(),
                        stock.getText(),
                        Double.parseDouble(sum.getText()),
                        stockListItemTreeTable.getList(),
                        comment.getText());
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
    }*/

}
