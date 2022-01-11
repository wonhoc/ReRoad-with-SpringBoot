package com.example.member.service;

import com.example.member.dao.UserDao;
import com.example.member.vo.UserAccount;
import com.example.member.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronizationManager;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.*;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {
    @Autowired
    private UserDao userDao;


    //Security Login - ID 전송 후 DB에서 ID,Password,Role 값 가져옴
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserVo selectedUser = this.userDao.getUserByID(username);

        Collection<SimpleGrantedAuthority> roles = new ArrayList<SimpleGrantedAuthority>();
        roles.add(new SimpleGrantedAuthority(selectedUser.getRole()));
//        String password = selectedUser.getUserPwd();
//        UserDetails user = new User(username, password, roles);
        UserAccount user = new UserAccount(selectedUser, roles);

        return user;
    }
    // 로그인 후 아이디로 회원 정보 반환(ID, NickName, Role)
    @Override
    public UserVo getInfo(String username) {
        return this.userDao.getUserInfo(username);
    }


    //회원가입
    @Override
    public void registUser(UserVo user) { this.userDao.insertUser(user);}

    //아이디 중복 검사
    @Override
    public int checkId(String userId) {
        return this.userDao.existId(userId);
    }

    //닉네임 중복 검사
    @Override
    public int checkNick(String userNick) {return this.userDao.existNick(userNick);}

    //임시 비밀번호 DB로 업데이트
    @Override
    public void updateTempPwd(UserVo user) { this.userDao.insertTempPwd(user);}

    //회원가입 과정에서 Validation 검증
    @Override
    public Map <String, String> validate(BindingResult bindingResult) {
        Map<String, String> map = new HashMap<String, String>();
        for(FieldError error: bindingResult.getFieldErrors()) {
            map.put(String.format("valid_%s", error.getField()), error.getDefaultMessage());
        }
        return map;
    }

    @Override
    public List<UserVo> retrieveUserList() {
        return this.userDao.selectUserList();
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void removeUserForce(String userId) {
        this.userDao.deleteUserForce(userId);
    }

    //비밀번호 일치 확인
    @Override
    public String checkPwd(String userId) {
        return this.userDao.confirmPwd(userId);
    }


    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void removeUser(String userId) {
        this.userDao.deleteUser(userId);
    }

    @Override
    public UserVo retrieveUser(String userId) {
        return this.userDao.selectUser(userId);

    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void modifyUser(UserVo user) {
        this.userDao.update(user);
    }


}