package life.nefu.community.exception;

/**
 * CustomizeException：处理上下文异常：比如查数据库，没有找到id
 * @author juran
 * @create 2021-03-21 19:41
 */
public class CustomizeException extends RuntimeException{
    private String message;

    public CustomizeException(ICustomizeErrorCode errorCode){
        this.message= errorCode.getMessage();
    }

    public CustomizeException(String message){
        this.message= message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
