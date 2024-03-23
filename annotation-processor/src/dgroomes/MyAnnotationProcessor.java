package dgroomes;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.TypeElement;
import java.util.Set;

import static java.lang.System.out;

/**
 * Please see the README for more information.
 */
@SupportedAnnotationTypes("*")
@SupportedSourceVersion(SourceVersion.RELEASE_21)
public class MyAnnotationProcessor extends AbstractProcessor {

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        for (Element elem : roundEnv.getRootElements()) {
            if (elem.getKind() == ElementKind.CLASS) {
                out.printf("Found class: %s%n", elem.getSimpleName());
                for (Element enclosed : elem.getEnclosedElements()) {
                    if (enclosed.getKind() == ElementKind.METHOD) {
                        out.printf("    Found method: %s%n", enclosed.getSimpleName());
                    }
                }
            }
        }
        return true;
    }
}
