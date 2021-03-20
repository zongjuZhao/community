package life.nefu.community.dto;

import life.nefu.community.model.Question;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author juran
 * @create 2021-03-20 13:27
 */
@Data
public class PaginationDTO {
    //解决页面展示的问题
    private List<QuestionDTO> questions;
    private boolean showPrevious;
    private boolean showFirstPage;
    private boolean showNext;
    private boolean showEndPage;

    private Integer page;
    private List<Integer> pages = new ArrayList<>();

    private Integer totalPage;

    public void setPagination(Integer totalCount, Integer page, Integer size) {

        if (totalCount % size == 0) {
            totalPage = totalCount / size;
        } else {
            totalPage = totalCount / size + 1;
        }

//      目的：当前页码高亮显示；给属性赋值
        if(page<1){
            page=1;
        }

        if(page>totalPage){
            page=totalPage;
        }

        this.page=page;

        pages.add(page);
        for (int i = 1; i <= 3; i++) {
//          在头部添加
            if (page - i > 0) {
                pages.add(0,page - i);
            }
//           在尾部添加
            if (page + i <= totalPage) {
                pages.add(page + i);
            }
        }

        //是否展示上一页
        if (page == 1) {
            showPrevious = false;
        } else {
            showNext = true;
        }

        //是否展示下一页
        if (page == totalPage) {
            showNext = false;
        } else {
            showPrevious = true;
        }

        //是否展示第一页
        if (pages.contains(1)) {
            showFirstPage = false;
        } else {
            showFirstPage = true;
        }

        //是否展示最后一页
        if (pages.contains(totalCount)) {
            showEndPage = false;
        } else {
            showEndPage = true;
        }
    }
}
