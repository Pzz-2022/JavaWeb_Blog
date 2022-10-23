package service.Impl;

import mapper.BlogLabelMapper;
import mapper.LabelMapper;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import pojo.BlogLabel;
import pojo.Label;
import service.LabelService;
import util.SqlSessionFactoryUtils;

import java.util.Arrays;

public class LabelServiceImpl implements LabelService {
    private static final SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtils.getSqlSessionFactory();

    @Override
    public Label[] selectAll() {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        LabelMapper labelMapper = sqlSession.getMapper(LabelMapper.class);
        Label[] labels = labelMapper.selectAll();

        sqlSession.close();
        return labels;
    }

    @Override
    public Label selectLabelByName(String name) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        LabelMapper labelMapper = sqlSession.getMapper(LabelMapper.class);
        Label label = labelMapper.selectLabelByName(name);

        sqlSession.close();
        return label;
    }

    @Override
    public Label[] selectAllByBlogId(int blog) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        LabelMapper mapper = sqlSession.getMapper(LabelMapper.class);
        Label[] labels = mapper.selectAllByBlogId(blog);

        System.out.println(Arrays.toString(labels));

        sqlSession.close();
        return labels;
    }

    @Override
    public void updateLabelDescription(int labelId, String description) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        LabelMapper mapper = sqlSession.getMapper(LabelMapper.class);
        mapper.updateLabelDescription(labelId, description);

        sqlSession.commit();
        sqlSession.close();
    }

    @Override
    public int selectCountByLabelId(int labelId) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        LabelMapper mapper = sqlSession.getMapper(LabelMapper.class);
        int count = mapper.selectCountByLabelId(labelId);

        sqlSession.close();
        return count;
    }

    @Override
    public void addLabel(Label label) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        LabelMapper labelMapper = sqlSession.getMapper(LabelMapper.class);
        labelMapper.addLabel(label);

        sqlSession.commit();
        sqlSession.close();
    }

    @Override
    public void addBlogLabel(long blogId, String[] labels) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        LabelMapper labelMapper = sqlSession.getMapper(LabelMapper.class);
        BlogLabelMapper blogLabelMapper = sqlSession.getMapper(BlogLabelMapper.class);

        for (String labelStr : labels) {
            Label label = labelMapper.selectLabelByName(labelStr);
            if (label == null){
                label = new Label(labelStr);
                labelMapper.addLabel(label);
            }

            blogLabelMapper.addBlogLabel(new BlogLabel((int) blogId, label.getLabelId()));
            sqlSession.commit();
        }

        sqlSession.close();
    }
}
