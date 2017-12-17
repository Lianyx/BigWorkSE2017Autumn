package vo;

import util.ReceiptState;

import java.time.LocalDateTime;

public abstract class ReceiptVO {
    private String id;
    private int operatorId;

    private LocalDateTime createTime;
    private LocalDateTime lastModifiedTime;

    private ReceiptState receiptState;
}
