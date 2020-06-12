package com.chengjiaqi.beijing.subway.dao;

import com.chengjiaqi.beijing.subway.model.vo.SubwayLine;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SubwayLineMapperTest {
    private static SubwayLineMapper mapper;

    @BeforeClass
    public static void setUpMybatisDatabase() {
        SqlSessionFactory builder = new SqlSessionFactoryBuilder().build(SubwayLineMapperTest.class.getClassLoader().getResourceAsStream("mybatisTestConfiguration/SubwayLineMapperTestConfiguration.xml"));
        mapper = builder.getConfiguration().getMapper(SubwayLineMapper.class, builder.openSession(true));
    }

    @Test
    public void testUpdateStation() throws FileNotFoundException {

    }
}
