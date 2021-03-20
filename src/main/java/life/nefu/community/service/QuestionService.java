package life.nefu.community.service;

import life.nefu.community.dto.PaginationDTO;
import life.nefu.community.dto.QuestionDTO;
import life.nefu.community.mapper.QuestionMapper;
import life.nefu.community.mapper.UserMapper;
import life.nefu.community.model.Question;
import life.nefu.community.model.User;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author juran
 * @create 2021-03-20 10:22
 */
@Service
public class QuestionService{

    @Autowired
    private QuestionMapper questionMapper;

    @Autowired
    private UserMapper userMapper;

    public PaginationDTO list(Integer page, Integer size) {

        PaginationDTO paginationDTO = new PaginationDTO();
        Integer totalCount =questionMapper.count();
        paginationDTO.setPagination(totalCount,page,size);

        if(page<1){
            page=1;
        }

        if(page>paginationDTO.getTotalPage()){
            page=paginationDTO.getTotalPage();
        }

        //size*(page-1)
        Integer offset = size * (page - 1);
        List<Question> questions = questionMapper.list(offset,size);
        ArrayList<QuestionDTO> questionDTOList = new ArrayList<>();

        for (Question question:questions){
            User user= userMapper.finById(question.getCreator());
            QuestionDTO questionDTO = new QuestionDTO();
//          优化：questionDTO.setXxx()
            BeanUtils.copyProperties(question, questionDTO);
            questionDTO.setUser(user);
            questionDTOList.add(questionDTO);
        }

        paginationDTO.setQuestions(questionDTOList);

        return paginationDTO;
    }
}