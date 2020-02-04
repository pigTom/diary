package com.pigtom.diary.util;

import com.pigtom.diary.model.bean.ObjectMethodField;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternUtils;
import org.springframework.core.type.classreading.CachingMetadataReaderFactory;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import org.springframework.core.type.filter.TypeFilter;
import org.springframework.util.ClassUtils;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.*;
import java.util.regex.Pattern;

/**
 * @author tangdunhong
 * @blame tangdunhong
 * @module config
 * @since 2020/1/3 11:01 AM
 **/
public class ScanPkgUtil implements ResourceLoaderAware {
    /**
     * Spring容器注入
     */
    @Autowired
    private ResourceLoader resourceLoader;

    @Override
    public void setResourceLoader(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    private void scan() throws Exception{

        ResourcePatternResolver resolver = ResourcePatternUtils.getResourcePatternResolver(resourceLoader);
        MetadataReaderFactory metadataReaderFactory = new CachingMetadataReaderFactory(resourceLoader);
        String path = "classpath*:com/pigtom/diary/**/*.class";
        Resource[] resources = resolver.getResources( path);
        Set<Class<?>> set = new HashSet<>();
        for (Resource r : resources) {
            MetadataReader reader = metadataReaderFactory.getMetadataReader(r);

            set.add(Class.forName(reader.getClassMetadata().getClassName()));
            System.out.println(reader.getClassMetadata());
            System.out.println(reader.getAnnotationMetadata());
        }
        System.out.println(set);
    }

    protected final Log logger = LogFactory.getLog(getClass());

    private static final String RESOURCE_PATTERN = "/**/*.class";

    private ResourcePatternResolver resourcePatternResolver = new PathMatchingResourcePatternResolver();

    private List<String> packagesList= new LinkedList<String>();

    private List<TypeFilter> typeFilters = new LinkedList<TypeFilter>();

    private Map<Class<?>, List<ObjectMethodField>> classMap= new HashMap<>();

    /**
     * 构造函数
     * @param packagesToScan 指定哪些包需要被扫描,支持多个包"package.a,package.b"并对每个包都会递归搜索
     * @param annotationFilter 指定扫描包中含有特定注解标记的bean,支持多个注解
     */
    public ScanPkgUtil(String[] packagesToScan, Class<? extends Annotation>... annotationFilter){
        if (packagesToScan != null) {
            for (String packagePath : packagesToScan) {
                this.packagesList.add(packagePath);
            }
        }
        if (annotationFilter != null){
            for (Class<? extends Annotation> annotation : annotationFilter) {
                typeFilters.add(new AnnotationTypeFilter(annotation, true));
            }
        }
    }

    /**
     * 将符合条件的Bean以Class集合的形式返回
     * @return
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public Map<Class<?>, List<ObjectMethodField>> getClassMap() throws IOException, ClassNotFoundException {
        this.classMap.clear();
        if (!this.packagesList.isEmpty()) {
            for (String pkg : this.packagesList) {
                String pattern = ResourcePatternResolver.CLASSPATH_ALL_URL_PREFIX +
                        ClassUtils.convertClassNameToResourcePath(pkg) + RESOURCE_PATTERN;
                Resource[] resources = this.resourcePatternResolver.getResources(pattern);
                MetadataReaderFactory readerFactory = new CachingMetadataReaderFactory(this.resourcePatternResolver);
                for (Resource resource : resources) {
                    if (resource.isReadable()) {
                        MetadataReader reader = readerFactory.getMetadataReader(resource);
                        String className = reader.getClassMetadata().getClassName();
                        if (matchesEntityTypeFilter(reader, readerFactory)) {
                            Class clazz = Class.forName(className);
                            Field[] fields = clazz.getDeclaredFields();
                            for (Field field : fields) {
                                Value value = field.getAnnotation(Value.class);
                                if (value == null) {
                                    continue;
                                }

                                String vaStr = value.value();
                                if (isApplicationEl(vaStr)) {
                                    List<ObjectMethodField> list = classMap.getOrDefault(clazz, new ArrayList<>());
                                    ObjectMethodField methodField = new ObjectMethodField();
                                    methodField.setField(field);
                                    methodField.setValueName(vaStr);
                                    list.add(methodField);
                                    classMap.put(clazz, list);
                                }
                            }

                            Method[] methods = clazz.getMethods();
                            for (Method method : methods) {
                                Value value = method.getAnnotation(Value.class);
                                if (value == null) {
                                    continue;
                                }

                                String vaStr = value.value();
                                if (isApplicationEl(vaStr)) {
                                    List<ObjectMethodField> list = classMap.getOrDefault(clazz, new ArrayList<>());
                                    ObjectMethodField methodField = new ObjectMethodField();
                                    methodField.setMethod(method);
                                    methodField.setValueName(vaStr);
                                    list.add(methodField);
                                    classMap.put(clazz, list);
                                }
                            }
                        }
                    }
                }
            }
        }
        //输出日志
        if (logger.isInfoEnabled()){
            for (Class<?> clazz : this.classMap.keySet()) {
                logger.info(String.format("Found class:%s", clazz.getName()));
            }
        }
        return this.classMap;
    }



    /**
     * 检查当前扫描到的Bean含有任何一个指定的注解标记
     * @param reader
     * @param readerFactory
     * @return
     * @throws IOException
     */
    private boolean matchesEntityTypeFilter(MetadataReader reader, MetadataReaderFactory readerFactory) throws IOException {
        if (!this.typeFilters.isEmpty()) {
            for (TypeFilter filter : this.typeFilters) {
                if (filter.match(reader, readerFactory)) {
                    return true;
                }
            }
        }
        return false;

    }

    /**
     * <code>@Value("${xxxx}")</code>
     * @param value
     * @return
     */
    private Pattern pattern = Pattern.compile("^\\$\\{.+}$");
    private boolean isApplicationEl(String value) {
        return pattern.matcher(value).matches();
    }
}
