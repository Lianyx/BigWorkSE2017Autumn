package blService;

public interface CheckblService {
    public void init();
    public void showDetail(Receipt receipt);
    public void approve(Receipt receipt);
    public void reject(Receipt receipt);

}
