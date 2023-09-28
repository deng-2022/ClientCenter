package generator.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import generator.domain.CommentChild;
import generator.service.CommentChildService;
import generator.mapper.CommentChildMapper;
import org.springframework.stereotype.Service;

/**
* @author Lenovo
* @description 针对表【comment_child】的数据库操作Service实现
* @createDate 2023-09-28 23:57:34
*/
@Service
public class CommentChildServiceImpl extends ServiceImpl<CommentChildMapper, CommentChild>
    implements CommentChildService{

}




