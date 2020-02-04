package com.pigtom.diary.systemtest;

import com.baomidou.mybatisplus.core.MybatisPlusVersion;
import com.pigtom.diary.model.bean.ObjectMethodField;
import com.pigtom.diary.model.bean.SystemUser;
import com.pigtom.diary.util.ScanPkgUtil;
import com.pigtom.diary.util.SpringUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternUtils;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.core.type.ClassMetadata;
import org.springframework.core.type.classreading.CachingMetadataReaderFactory;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.stereotype.Component;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.*;

/**
 * @author tangdunhong
 * @blame tangdunhong
 * @module test
 * @since 2020/1/3 11:11 AM
 **/
@SpringBootTest
@RunWith(SpringRunner.class)
public class TestScan {
    @Autowired
    private ResourceLoader resourceLoader;

    @Test
    public void test() throws IOException, ClassNotFoundException {
        ResourcePatternResolver resolver = ResourcePatternUtils.getResourcePatternResolver(resourceLoader);
        MetadataReaderFactory metadataReaderFactory = new CachingMetadataReaderFactory(resourceLoader);
        String path = "classpath*:com/pigtom/diary/**/*.class";
        Resource[] resources = resolver.getResources( path);
        Set<Class<?>> set = new HashSet<>();
        for (Resource r : resources) {
            MetadataReader reader = metadataReaderFactory.getMetadataReader(r);
            ClassMetadata classMetadata = reader.getAnnotationMetadata();
            AnnotationMetadata annotationMetadata = reader.getAnnotationMetadata();
            if (annotationMetadata.hasAnnotation("org.springframework.web.bind.annotation.RestController")) {
                System.out.println(classMetadata.getClassName());

            }
        }
    }


    @Test
    public void testSplit() throws Exception {
        ResourcePatternResolver resourceLoader = new PathMatchingResourcePatternResolver();
        Resource[] result2 = resourceLoader.getResources("classpath*:/com/pigtom/diary/*/**/*.class");
        SystemUser systemUser = new SystemUser();
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        URL url = classLoader.getResource("com/pigtom/diary/model/bean/SystemUser.class");
        System.out.println(url);
        System.out.println(Arrays.toString(result2));
    }

    @Test
    public void testPrint () {
        System.out.println(" _ _   |_  _ _|_. ___ _ |    _ ");
        System.out.println("| | |\\/|_)(_| | |_\\  |_)||_|_\\ ");
        System.out.println("     /               |         ");
        System.out.println("                        " + MybatisPlusVersion.getVersion() + " ");
    }


    @Test
    public void testScan() throws Exception{
        ScanPkgUtil scanPkgUtil = new ScanPkgUtil(new String[]{"/com/pigtom/diary"}, Component.class);
        Map<Class<?>, List<ObjectMethodField>> classMap = scanPkgUtil.getClassMap();
        for (Class<?> aClass : classMap.keySet()) {
            System.out.println(aClass.getName());
        }
        System.out.println();
        classMap.entrySet().forEach(entry -> {
            ApplicationContext context = SpringUtil.getApplicationContext();
            Object object = context.getBean(entry.getKey());
            List<ObjectMethodField> list = entry.getValue();
            for (ObjectMethodField methodField : list) {
//                methodField.getValueName();
                Field field = methodField.getField();
                if (field != null) {
                    String value = "Test";
                    try {
                        field.setAccessible(true);
                        field.set(object, value);
                    } catch (Exception e) {
                        System.out.println("error while set vaue");
                    }
                }

                else {
                    Method method = methodField.getMethod();
                    String value = "Test";
                    try {
                        method.setAccessible(true);
                        method.invoke(object, value);

                    } catch (Exception e) {
                        System.out.println("error while set vaue");
                    }
                }
            }
        });

        for (Class<?> aClass : classMap.keySet()) {
            System.out.println(aClass.getName());
        }
    }
}
