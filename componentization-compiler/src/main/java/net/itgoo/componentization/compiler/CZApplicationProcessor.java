package net.itgoo.componentization.compiler;

import com.google.auto.service.AutoService;
import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeSpec;

import net.itgoo.componentization.annotation.CZApplication;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;
import javax.lang.model.util.Elements;

/**
 * Created by apple on 17/1/3.
 */

@AutoService(Processor.class)
public class CZApplicationProcessor extends AbstractProcessor {

    private Elements elementUtils;
    private List<String> targetModules = new ArrayList<>();

    @Override
    public synchronized void init(ProcessingEnvironment processingEnv) {
        super.init(processingEnv);
        elementUtils = processingEnv.getElementUtils();
        Map<String, String> map = processingEnv.getOptions();
        Set<String> keys = map.keySet();
        for (String key : keys) {
            if (key.contains("targetModule")) {
                targetModules.add(map.get(key));
            }
            System.out.println(key + " = " + map.get(key));
        }
    }

    @Override
    public Set<String> getSupportedAnnotationTypes() {
        return Collections.singleton(CZApplication.class.getCanonicalName());
    }

    @Override
    public SourceVersion getSupportedSourceVersion() {
        return SourceVersion.RELEASE_7;
    }

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        if (annotations.size() == 0) {
            return false;
        }

        Set<? extends Element> elements = roundEnv.getElementsAnnotatedWith(CZApplication.class);
        for (Element element : elements) {
            CZApplication componentizationApplicaiton = element.getAnnotation(CZApplication.class);
            ClassName applicationInitializer = ClassName.get("net.itgoo.componentization.application", "CZApplicationCreator");
            TypeSpec.Builder typeSpec = TypeSpec.classBuilder(componentizationApplicaiton.name() +  "ApplicationCreator")
                    .addSuperinterface(applicationInitializer)
                    .addModifiers(Modifier.PUBLIC);

            TypeElement applicationInitializerTypeElement = elementUtils.getTypeElement(applicationInitializer.toString());
            List<? extends Element> members = elementUtils.getAllMembers(applicationInitializerTypeElement);
            MethodSpec.Builder bindViewMethodSpecBuilder = null;
            for (Element member : members) {
                if ("init".equals(member.getSimpleName().toString())) {
                    bindViewMethodSpecBuilder = MethodSpec.overriding((ExecutableElement) member);
                    break;
                }
            }

            if (bindViewMethodSpecBuilder == null) {
                return false;
            }

            for (String module : targetModules) {
                try {
                    bindViewMethodSpecBuilder.addStatement("arg0.add($T.class)", Class.forName(module));
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }

            JavaFile javaFile = JavaFile.builder("net.itgoo.componentization.application", typeSpec.addMethod(bindViewMethodSpecBuilder.build()).build()).build();
            try {
                javaFile.writeTo(processingEnv.getFiler());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return false;
    }
}
