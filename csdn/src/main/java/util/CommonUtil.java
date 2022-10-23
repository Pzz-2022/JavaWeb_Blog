package util;

import mapper.CommonMapper;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import pojo.Common;

import java.util.Arrays;

public class CommonUtil {
    // 将子评论加在父评论的 list 上
    public static void addChildCommon(Common[] commons){
        SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtils.getSqlSessionFactory();
        SqlSession sqlSession = sqlSessionFactory.openSession();
        CommonMapper mapper = sqlSession.getMapper(CommonMapper.class);

        for (Common common : commons) {
            Common[] childs = mapper.selectChildByRootId(common.getId());
            common.setChilds(Arrays.asList(childs));
        }
    }
}
