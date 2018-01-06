package blService.blServiceFactory;


import blService.stockblService.StockblService;

import java.lang.reflect.Field;

public class ServiceFactory {
    public static StockblService stockblService;


    public Object getClass(String clazz) throws Exception{
        Object obj = null;
        Field field = this.getClass().getDeclaredField(clazz);
        Class<?> c = null;
        if(field.get(this.getClass())==null){
            c = Class.forName("blServiceStub."+field.getName().toLowerCase()+"_Stub."+Character.toUpperCase(field.getName().charAt(0))+field.getName().substring(1)+"_Stub");
            obj = c.newInstance();
            field.set(this.getClass(),obj);
        }else{
            obj=field.get(this.getClass());
        }
        return obj;
    }

    public StockblService getStockblService() throws Exception{
        return (StockblService) getClass(Character.toLowerCase(StockblService.class.getName().split("\\.")[2].charAt(0))+StockblService.class.getName().split("\\.")[2].substring(1));
    }

    public static void main(String args[]){


        ServiceFactory serviceFactory = new ServiceFactory();
        try{
        StockblService stockblService = serviceFactory.getStockblService();
        System.out.println(stockblService);
        stockblService = serviceFactory.getStockblService();
        System.out.println(stockblService);
        }catch (Exception e){
            e.printStackTrace();
        }
    }


}
