package net.itgoo.componentization.compiler;

import com.google.auto.service.AutoService;
import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.CodeBlock;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeSpec;

import net.itgoo.componentization.annotation.CZFragment;

import java.io.IOException;
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
public class CZFragmentProcessor extends AbstractProcessor {

    private Elements elementUtils;
    private String targetModuleName = "";

    @Override
    public synchronized void init(ProcessingEnvironment processingEnv) {
        super.init(processingEnv);
        elementUtils = processingEnv.getElementUtils();
        Map<String, String> map = processingEnv.getOptions();
        Set<String> keys = map.keySet();
        for (String key : keys) {
            if ("moduleName".equals(key)) {
                this.targetModuleName = map.get(key);
            }
            System.out.println(key + " = " + map.get(key));
        }
    }

    @Override
    public Set<String> getSupportedAnnotationTypes() {
        return Collections.singleton(CZFragment.class.getCanonicalName());
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

        Set<? extends Element> elements = roundEnv.getElementsAnnotatedWith(CZFragment.class);
        ClassName fragmentInitializer = ClassName.get("net.itgoo.componentization.fragment", "CZFragmentInitializer");
        TypeSpec.Builder typeSpec = TypeSpec.classBuilder((targetModuleName.length() == 0 ? "Apt" : targetModuleName) + "FragmentInitializer")
                .addSuperinterface(fragmentInitializer)
                .addModifiers(Modifier.PUBLIC)
                .addStaticBlock(CodeBlock.of(String.format("CZFragmentHelper.register(new %sFragmentInitializer());", (targetModuleName.length() == 0 ? "Apt" : targetModuleName))));

        TypeElement fragmentInitializerTypeElement = elementUtils.getTypeElement(fragmentInitializer.toString());
        List<? extends Element> members = elementUtils.getAllMembers(fragmentInitializerTypeElement);
        MethodSpec.Builder bindViewMethodSpecBuilder = null;
        for (Element element : members) {
            if ("init".equals(element.getSimpleName().toString())) {
                bindViewMethodSpecBuilder = MethodSpec.overriding((ExecutableElement) element);
                break;
            }
        }

        if (bindViewMethodSpecBuilder == null) {
            return false;
        }

        for (Element element : elements) {
            CZFragment componentizationFragment = element.getAnnotation(CZFragment.class);
            TypeElement typeElement = (TypeElement) element;
            bindViewMethodSpecBuilder.addStatement("arg0.put($S, new CZFragmentEntity($S, $S, $S, $T.class))",
                    componentizationFragment.name(), componentizationFragment.name(), componentizationFragment.titleName(), componentizationFragment.iconName(), typeElement.asType());
        }

        JavaFile javaFile = JavaFile.builder("net.itgoo.componentization.fragment", typeSpec.addMethod(bindViewMethodSpecBuilder.build()).build()).build();
        try {
            javaFile.writeTo(processingEnv.getFiler());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return false;
    }
}
