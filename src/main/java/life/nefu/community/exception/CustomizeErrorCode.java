package life.nefu.community.exception;

/**
 * @author juran
 * @create 2021-03-21 19:58
 */

// 不同业务继承同一个ICustomizeErrorCode：避免类爆炸
public enum CustomizeErrorCode implements ICustomizeErrorCode {

    QUESTION_NOT_FOUND("你找的问题不在了，要不要换个试试？");

    private String message;

    CustomizeErrorCode(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
