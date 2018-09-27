package io.renren.common.enums;


/**
 * Created by liyou
 * 2017-06-11 18:56
 */

public enum ResultEnum {

    SUCCESS(0, "成功"),

    UPLOAD_ERROR(34,"文件上传异常"),

    FILE_ISNELL(35,"文件不能为空"),

    IMAGE_TYPE_ERROR(36,"图片文件只支持JPG，PNG格式")
    ;

    private Integer code;

    private String message;

    ResultEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
