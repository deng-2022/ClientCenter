package generator.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import generator.domain.Article;
import generator.service.ArticleService;
import generator.mapper.ArticleMapper;
import org.springframework.stereotype.Service;

/**
* @author Lenovo
* @description 针对表【article(博文)】的数据库操作Service实现
* @createDate 2023-09-27 12:02:17
*/
@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article>
    implements ArticleService{

}




