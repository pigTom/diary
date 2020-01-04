package com.pigtom.diary.systemtest;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternUtils;
import org.springframework.core.type.classreading.CachingMetadataReaderFactory;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;

import java.io.IOException;

/**
 * @author tangdunhong
 * @blame tangdunhong
 * @module test
 * @since 2020/1/3 11:11 AM
 **/
public class TestScan {
    @Autowired
    private ResourceLoader resourceLoader;

    @Test
    public void test() throws IOException {
        ResourcePatternResolver resolver = ResourcePatternUtils.getResourcePatternResolver(resourceLoader);
        MetadataReaderFactory metaReader = new CachingMetadataReaderFactory(resourceLoader);
        Resource[] resources = resolver.getResources("classpath*:com.pigtom.diary.service.LoginService.class");

        for (Resource r : resources) {
            MetadataReader reader = metaReader.getMetadataReader(r);
            System.out.println(reader.getClassMetadata().getClassName());
        }

    }


    @Test
    public void testSplit() {
        char[] chars = "123\t45666".toCharArray();
        System.out.println((int)chars[3]);
        System.out.println((int)chars[4]);
        System.out.println((int)chars[5]);
        System.out.println((int)chars[6]);
        System.out.println((int)chars[7]);
    }
}
