package dgroomes.compiler_plugin;

import com.sun.source.tree.*;
import com.sun.source.util.*;

import static java.lang.System.err;

/**
 * Please see the README for more information.
 */
public class OverflowDetectorPlugin implements Plugin {

    @Override
    public String getName() {
        return "OverflowDetector";
    }

    @Override
    public void init(JavacTask task, String... args) {
        task.addTaskListener(new TaskListener() {
            @Override
            public void finished(TaskEvent e) {
                if (e.getKind() == TaskEvent.Kind.ANALYZE) {
                    new Scanner().scan(e.getCompilationUnit(), Trees.instance(task));
                }
            }
        });
    }
}

class Scanner extends TreePathScanner<Void, Trees> {

    @Override
    public Void visitBinary(BinaryTree node, Trees trees) {
        if (node.getKind() == Tree.Kind.PLUS) {
            ExpressionTree left = node.getLeftOperand();
            ExpressionTree right = node.getRightOperand();

            if (left.getKind() == Tree.Kind.INT_LITERAL && right.getKind() == Tree.Kind.INT_LITERAL) {
                int leftValue = Integer.parseInt(left.toString());
                int rightValue = Integer.parseInt(right.toString());

                if (Integer.MAX_VALUE - rightValue < leftValue) {

                    long lineNumber;
                    String fileName;
                    {
                        SourcePositions sourcePositions = trees.getSourcePositions();
                        CompilationUnitTree compilationUnit = getCurrentPath().getCompilationUnit();
                        LineMap lineMap = compilationUnit.getLineMap();
                        long startPosition = sourcePositions.getStartPosition(compilationUnit, node);
                        lineNumber = lineMap.getLineNumber(startPosition);
                        fileName = compilationUnit.getSourceFile().getName();
                    }

                    err.printf("Integer overflow detected in file '%s' at line %d for expression %s%n", fileName, lineNumber, node);
                }
            }
        }

        return super.visitBinary(node, trees);
    }
}



