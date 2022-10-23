import mapper.LabelMapper;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import pojo.Label;
import util.SqlSessionFactoryUtils;

public class test1 {
    public static void main(String[] args) {
        SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtils.getSqlSessionFactory();
        SqlSession sqlSession = sqlSessionFactory.openSession();
        LabelMapper mapper = sqlSession.getMapper(LabelMapper.class);
        Label java = new Label("", "");
        mapper.addLabel(java);
        System.out.println(java.getLabelId());
        sqlSession.commit();
        sqlSession.close();
    }
}
