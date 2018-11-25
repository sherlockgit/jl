package io.renren.modules.sys.service.impl;

import io.renren.modules.sys.vo.UserCneeVo;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;

import io.renren.modules.sys.dao.UserCneeDao;
import io.renren.modules.sys.entity.UserCneeEntity;
import io.renren.modules.sys.service.UserCneeService;


@Service("userCneeService")
public class UserCneeServiceImpl extends ServiceImpl<UserCneeDao, UserCneeEntity> implements UserCneeService {

    @Autowired
    UserCneeDao userCneeDao;

    @Override
    public PageUtils queryPage(Map<String, Object> map) throws ParseException {
        String startTime = (String)map.get("startTime");
        String endTime = (String)map.get("endTime");
        SimpleDateFormat sdf = new SimpleDateFormat("EEE MMM dd yyyy HH:mm:ss z", Locale.ENGLISH);
        Date startTimeDate = null;
        Date endTimeDate = null;
        if (StringUtils.isNotBlank(startTime)) {
            startTimeDate = sdf.parse(startTime.replace("GMT", "").replaceAll("\\(.*\\)", ""));
        }
        if (StringUtils.isNotBlank(endTime)) {
            endTimeDate = sdf.parse(endTime.replace("GMT", "").replaceAll("\\(.*\\)", ""));
        }
        map.put("startTimeDate",startTimeDate);
        map.put("endTimeDate",endTimeDate);
        List<UserCneeVo> list = userCneeDao.selectAll(map);
        String limit = (String)map.get("limit");
        String page = (String)map.get("page");
        Page<UserCneeVo> page1 = new Page<>(Integer.valueOf(page),Integer.valueOf(limit));
        page1.setRecords(list);

//        String userNo = (String)params.get("userNo");
//        String userName = (String)params.get("userName");
//        String phone = (String)params.get("phone");
//        String cneeAddr = (String)params.get("cneeAddr");
//        Page<UserCneeEntity> page = this.selectPage(
//                new Query<UserCneeEntity>(params).getPage(),
//                new EntityWrapper<UserCneeEntity>()
//                        .like(StringUtils.isNotBlank(userNo),"user_id", userNo)
//                        .like(StringUtils.isNotBlank(userName),"user_name", userName)
//                        .like(StringUtils.isNotBlank(phone),"phone", phone)
//                        .like(StringUtils.isNotBlank(cneeAddr),"cnee_addr", cneeAddr)
//        );
        return new PageUtils(page1);
    }

}
