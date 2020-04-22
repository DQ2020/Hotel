package com.open.hotel;

import com.open.hotel.model.Account;
import com.open.hotel.model.Code;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface AccountMapper {

    @Select("select * from account where account like CONCAT('%',#{search},'%')")
    public List<Account> accounts(@Param("search") String search);

    @Select("select * from account where account = #{account} and pwd = #{pwd}")
    public List<Account> login(@Param("account") String acount, @Param("pwd")String pwd);

    @Insert("insert into code(tel,code) values(#{tel},#{code})")
    public void initCode(@Param("tel") String tel, @Param("code")String code);

    @Select("select * from code where tel = #{tel}")
    public List<Code> getCode(@Param("tel") String tel);

    @Select("select * from code where tel = #{tel} and code = #{code}")
    public List<Code> checkCode(@Param("tel") String tel, @Param("code") String code);

    @Select("select * from account where tel = #{tel}")
    public List<Account> loginByTel(@Param("tel") String tel);

    @Update("update account set enable = 0 where id = #{id}")
    public int delAcc(@Param("id") int tel);

    @Update("update account set account = #{account},name = #{name},tel = #{tel},age = #{age},sex = #{sex},score = #{score},pwd = #{pwd},enable = #{enable} where id = #{id}")
    public int updateAcc(@Param("id") int id,@Param("account") String account,@Param("name") String name,@Param("tel") String tel,@Param("age") int age,@Param("sex") int sex,@Param("score") int score,@Param("pwd") String pwd,@Param("enable") int enable);

    @Select("select id from account where id = #{id}")
    public String checkExist(@Param("id") int id);

    @Update("insert into account(account,name,tel,age,sex,score,pwd,enable) values(#{account},#{name},#{tel},#{age},#{sex},#{score},#{pwd},#{enable})")
    public int insertAcc(@Param("account") String account,@Param("name") String name,@Param("tel") String tel,@Param("age") int age,@Param("sex") int sex,@Param("score") int score,@Param("pwd") String pwd,@Param("enable") int enable);
}
