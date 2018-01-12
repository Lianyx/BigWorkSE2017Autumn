package data.memberdata;

import annotations.RMIRemote;
import dataService.memberdataService.MemberdataService;
import mapper.MemberPOMapper;
import mybatis.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;
import po.MemberPO;
import util.ImageConvertor;
import util.MemberCategory;
import util.MemberSearchCondition;
import util.ResultMessage;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.stream.Collectors;
@RMIRemote
public class MemberData extends UnicastRemoteObject implements MemberdataService{
    public MemberData() throws RemoteException {
    }

    @Override
    public MemberPO getNew() throws RemoteException {
        try (SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession()) {
            MemberPOMapper mapper = session.getMapper(MemberPOMapper.class);
            int i = mapper.getId();
            System.out.println(i);
            MemberPO memberPO = new MemberPO();
            memberPO.setMemberId(i);
            memberPO.setIsDeleted(0);
            memberPO.setMemberCategory(MemberCategory.SELLER);
           /*
            UserPO userPO = new UserPO();
            userPO.setUserId(id);
            userPO.setCreateTime(LocalDateTime.now());
            userPO.setIsDeleted(0);
            userPO.setUsertype(UserCategory.UserManager);*/
            insert(memberPO);
            return memberPO;
        }
    }
    private ResultMessage insert(MemberPO memberPO) throws RemoteException{
        try (SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession()) {
            MemberPOMapper mapper = session.getMapper(MemberPOMapper.class);
            mapper.insert(memberPO);
            session.commit();
            return ResultMessage.SUCCESS;
        }
    }


    @Override
    public ResultMessage delete(int id) throws RemoteException {
        try (SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession()) {
            MemberPOMapper mapper = session.getMapper(MemberPOMapper.class);
            MemberPO memberPO = showDetail(id);
            memberPO.setIsDeleted(1);
            update(memberPO);
            session.commit();
            return ResultMessage.SUCCESS;
        }
    }

    @Override
    public ResultMessage update(MemberPO memberPO) throws RemoteException {
        try (SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession()) {
            MemberPOMapper mapper = session.getMapper(MemberPOMapper.class);
            mapper.update(memberPO);
            System.out.println(mapper);

            File file = null;
            String files[] = null;
            try{
                file = new File(getUserImageURL());
                files = file.list();
                System.out.println(files[0]);
            }catch (Exception e){
                e.printStackTrace();
            }
            try{
                ImageIO.write(ImageConvertor.getImage(memberPO.getImage()),"png",(new File(file.getPath() + "\\" + memberPO.getMemberId()+".png")));

            }catch (Exception e){
                e.printStackTrace();

            }
            session.commit();
            return ResultMessage.SUCCESS;
        }
    }

    @Override
    public MemberPO showDetail(int id) throws RemoteException {
        try (SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession()) {
            MemberPOMapper mapper = session.getMapper(MemberPOMapper.class);
            MemberPO memberPO = mapper.showDetail(id);
            if(memberPO==null)
                return null;
            if(memberPO.getMemberCategory()==null){
                memberPO.setMemberCategory(MemberCategory.SUPPLIER);
            }
            session.commit();
            File file = null;
            String files[] = null;
            try{
                file = new File(getUserImageURL());
                files = file.list();
            }catch (Exception e){
                e.printStackTrace();
            }
            if(files!=null) {
                boolean exist = false;
                for (String f : files) {
                    try {
                        if (memberPO.getMemberId() == Integer.parseInt(f.split("\\.")[0])) {
                            exist = true;
                            BufferedImage image = ImageIO.read((new File(file.getPath() + "\\" + f)));
                            memberPO.setImage(ImageConvertor.getByte(image));
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }

                if (!exist) {
                    try {
                        BufferedImage image = ImageIO.read((new File(getClass().getResource("/default/timg.jpg").toURI())));
                        memberPO.setImage(ImageConvertor.getByte(image));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
            return memberPO;
        }
    }

    @Override
    public ArrayList<MemberPO> search(MemberSearchCondition memberSearchCondition) throws RemoteException {
        try (SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession()) {
            MemberPOMapper mapper = session.getMapper(MemberPOMapper.class);
            ArrayList<MemberPO> memberPOS = mapper.search(memberSearchCondition);
            for(MemberPO userPO:memberPOS){
                if(userPO.getMemberCategory()==null){
                    userPO.setMemberCategory(MemberCategory.SUPPLIER);
                }
            }
            System.out.println(memberPOS+"???");
            memberPOS = memberPOS.stream().filter(userPO -> userPO.getIsDeleted()==0).collect(Collectors.toCollection(ArrayList::new));

            File file = null;
            String files[] = null;
            try{
                file = new File(getUserImageURL());
                files = file.list();
            }catch (Exception e){
                e.printStackTrace();
            }
            for(MemberPO userPO:memberPOS){
                for(String f:files) {
                    try {
                        if (userPO.getMemberId() == Integer.parseInt(f.split("\\.")[0])) {
                            BufferedImage image = ImageIO.read((new File(file.getPath() + "\\" + f)));
                            userPO.setImage(ImageConvertor.getByte(image));
                        }else {
                            BufferedImage image = ImageIO.read((new File(getClass().getResource("/default/timg.jpg").toURI())));
                            userPO.setImage(ImageConvertor.getByte(image));
                        }
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
            }
            System.out.println("sabi");
            System.out.println(memberPOS.size());
            session.commit();
            return memberPOS;
        }
    }
    private static String getUserImageURL(){
        return "E:\\ERPnju\\Server\\src\\main\\resources\\memberImage";
    }

}
