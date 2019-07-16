package service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import entity.User;
import mapper.UserMapper;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapperImpl;
    static Jedis jedis = new Jedis();


    public UserMapper getUserMapperImpl() {
        return userMapperImpl;
    }

    public void setUserMapperImpl(UserMapper userMapperImpl) {
        this.userMapperImpl = userMapperImpl;
    }

    @Override
    public String sel(String uname) {
        ObjectMapper mapper = new ObjectMapper();
        try{
            if (uname==""){
                return mapper.writeValueAsString(userMapperImpl.selAll());
            }else{
                return mapper.writeValueAsString(userMapperImpl.selByName(uname));
            }
        }catch (Exception e){
            System.out.println(e);
        }
        return "";
    }

    @Override
    public List<User> selAll() {
        return userMapperImpl.selAll();
    }

    @Override
    public String getCode(String phoneNum) {
        //调运短信的API；
        //向对应手机号发送短信验证码
        //
        String code = (int) ((Math.random() * 9 + 1) * 1000) + "";
        boolean flag = true;

        String appkey = jedis.get("appkey");

        String result = null;
        String url = "http://v.juhe.cn/sms/send";//请求接口地址
        Map params = new HashMap();//请求参数
        params.put("mobile", phoneNum);//接受短信的用户手机号码
        params.put("tpl_id", "169538");//您申请的短信模板ID，根据实际情况修改
        params.put("tpl_value", "#code#=" + code + "&#My#=1");//您设置的模板变量，根据实际情况修改
        params.put("key", appkey);//应用APPKEY(应用详细页查询)
        try {
//            result = MyUtil.net(url, params, "GET");
//            System.out.println(result);
//            JSONObject object = JSONObject.fromObject(result);

//            //将发送手机号作为key，验证码作为value保存在redis
            jedis.setex(phoneNum, 6000,code);

//            if (object.getInt("error_code") == 0) {
//                System.out.println(object.get("result"));
//            } else {
//                System.out.println(object.get("error_code") + ":" + object.get("reason"));
//            }
        } catch (Exception e) {
            flag = false;
            e.printStackTrace();
        }

        return code;
    }

    @Override
    public boolean checkCode(String ucode,String phoneNum) {
        String realCode = jedis.get(phoneNum);
        if (realCode==ucode){
            return true;
        }
        return false;
    }

    @Override
    public User selByPhone(String phoneNum) {
        return null;
    }

    @Override
    public User login(String phoneNum, String upwd) {

        return userMapperImpl.login(phoneNum,upwd);
    }

    @Override
    public boolean reg(HttpServletRequest req, String uname, String upwd, String phoneNum, String ucode){
        if ((jedis.get(phoneNum))!=null&&jedis.get(phoneNum).equals(ucode)){
            System.out.println("数据库验证码"+jedis.get(phoneNum));
            System.out.println("用户验证码"+ucode);
            System.out.println(uname+"   "+upwd+"   "+phoneNum);
            if(userMapperImpl.selByPhone(phoneNum)==null){
                userMapperImpl.insertBy(uname,upwd,phoneNum);
                return true;
            }else {
                req.getSession().setAttribute("regError","用户已存在");
                return false;
            }
        }else {
           req.getSession().setAttribute("regError","验证码错误");
           return false;
        }
    }


}
