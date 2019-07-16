package service;

import entity.User;
import net.sf.json.JSONObject;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface UserService {
    String sel(String uname);

    List<User> selAll();

    String getCode(String phoneNum);

    boolean checkCode(String ucode,String phoneNum);

    boolean reg(HttpServletRequest req, String uname, String upwd, String phoneNum, String ucode);

    User selByPhone(String phoneNum);

    User login(String phoneNum,String upwd);
}
