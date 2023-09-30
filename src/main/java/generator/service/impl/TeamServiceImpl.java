package generator.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import generator.domain.Team;
import generator.service.TeamService;
import generator.mapper.TeamMapper;
import org.springframework.stereotype.Service;

/**
* @author Lenovo
* @description 针对表【team(队伍)】的数据库操作Service实现
* @createDate 2023-09-27 12:46:27
*/
@Service
public class TeamServiceImpl extends ServiceImpl<TeamMapper, Team>
    implements TeamService{

}



