package io.renren.modules.sys.vo;

import com.baomidou.mybatisplus.annotations.TableId;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: sherlock
 * Date: 2018-10-15
 */
public class CourseNameList {

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    private String id;
    /**
     * 课程名称
     */
    private String courseName;

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }
}
