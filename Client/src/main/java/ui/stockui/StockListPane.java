package ui.stockui;

import ui.myAccountantui.common.MyReceiptListPane;
import vo.receiptVO.StockReceiptListVO;
import vo.receiptVO.StockReceiptVO;

/**
 * @Author: Lin Yuchao
 * @Description: listpane  注意所有见面跳转职责都分给自己，要跳到谁，由后者自己改，因为刷新也是这样
 * @ModifyBy: Lin Yuchao
**/

public abstract class StockListPane<T extends StockReceiptListVO<T>,S extends StockReceiptVO> extends MyReceiptListPane<T,S> {

}

/*
    /*

    StockblService stockblService;

    SimpleBooleanProperty isPur = new SimpleBooleanProperty();

    SimpleStringProperty match = new SimpleStringProperty("");


    public StockListPane(boolean isPur) throws Exception {
        super("/stockui/stocklistpane.fxml");
        this.stockblService = ServiceFactory_Stub.getService(StockblService.class.getName());
        this.isPur.set(isPur);

        receiptTreeTable = new StockTreeTable();
        receiptTreeTable.setPrefSize(600, 435);
        receiptTreeTable.keywordProperty().bind(match);
        borderpane.setTop(new BorderPane(receiptTreeTable));
/*

        for (ReceiptState receiptState : ReceiptState.values()) {
            stockSearchVO.getReceiptStates().add(receiptState);
        }
        StockFilterPane slp = new StockFilterPane(filterPopOver, stockSearchVO);
        filterPopOver.setDetachable(false);
        filterPopOver.setArrowLocation(PopOver.ArrowLocation.TOP_RIGHT);
        filterPopOver.setContentNode(slp);
        filter.setOnMouseClicked(e -> filterPopOver.show(filter));*/


/*

    @Override
    public void deleteList() {
        DoubleButtonDialog doubleButtonDialog = new DoubleButtonDialog(mainpane,"Delete","sabi","Yes","No");
        doubleButtonDialog.setButtonOne(()->{receiptTreeTable.delete(pagination); });
        doubleButtonDialog.setButtonTwo(()->{});
        doubleButtonDialog.show();
    }



    @Override
    public void search() {
        if (!searchField.getText().equals("")) {
            match.setValue(searchField.getText().toLowerCase());
            Set<StockReceiptListVO> hashSet;
            hashSet = set.stream().filter(
                    s -> s.getReceiptState().name().contains(match.get()) ||
                            s.getId().contains(match.get())||
                            s.getMemberName().contains(match.get())||
                            s.getStockName().contains(match.get())
            ).collect(Collectors.toSet());
            receiptTreeTable.setReceipts(hashSet);
            pagination.setPageCount(receiptTreeTable.getObservableList().size() / receiptTreeTable.getRowsPerPage() + 1);
            receiptTreeTable.createPage(0);
            borderpane.setBottom(pagination);
            switchPane(false);
        }
    }


    @Override
    public void add() {
        StockReceiptPane stockReceiptPane = new StockReceiptPane(isPur.get());
    }

    @Override
    public void refresh(boolean toSwitch) {
        boardController.Loading();
        try {

            DoubleButtonDialog buttonDialog =
                    new DoubleButtonDialog(mainpane, "Wrong", "sabi", "Last", "Ret");
            buttonDialog.setButtonTwo(() -> boardController.Ret());
            buttonDialog.setButtonTwo(() -> refresh(false));

            Predicate<Integer> p = (s) -> {
      /*          if ((set = stockblService.search(stockSearchVO, isPur.get())) != null) {
                    System.out.println(set.size());
                    return true;
                }
                retrn false;
            };

            GetTask getTask =
                    new GetTask(() -> {
                        receiptTreeTable.setReceipts(set);
                        pagination.setPageCount(receiptTreeTable.getObservableList().size() / receiptTreeTable.getRowsPerPage() + 1);
                        receiptTreeTable.createPage(0);
                        borderpane.setBottom(pagination);
                        switchPane(toSwitch);
                    }, buttonDialog, p);

            new Thread(getTask).start();
        } catch (Exception e) {

            e.printStackTrace();

        }
    }
*/


