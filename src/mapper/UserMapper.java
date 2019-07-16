package mapper;

import entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface UserMapper {
    @Select("select * from user where uname like concat('%',#{uname},'%')")
    List<User> selByName (String uname);

    @Select("select * from user where identity='员工'")
    List<User> selAll();

    @Select("select * from user where phoneNum=#{phoneNum} and upwd=#{upwd}")
    User login(@Param("phoneNum") String PhoneNum,@Param("upwd") String upwd);

    @Insert("insert user(uname,upwd,phoneNum) values(#{uname},#{upwd},#{phoneNum})")
    int insertBy(@Param("uname") String uname, @Param("upwd") String upwd, @Param("phoneNum") String phoneNum);

    @Select("select * from user where phoneNum=#{phoneNum}")
    User selByPhone(String phoneNum);
}
