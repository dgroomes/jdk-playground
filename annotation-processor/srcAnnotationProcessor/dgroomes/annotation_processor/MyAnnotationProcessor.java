package dgroomes.annotation_processor;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import java.util.ArrayDeque;
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
        out.printf("Processing %s%n", roundEnv);
        var stack = new ArrayDeque<Element>(roundEnv.getRootElements());
        while (!stack.isEmpty()) {
            var elem = stack.pop();
            out.printf("[kind=%s]: %s%n", elem.getKind(), elem);
            elem.getEnclosedElements().forEach(stack::push);
        }
        return true;
    }
}
