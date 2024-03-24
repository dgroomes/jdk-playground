package dgroomes.compiler_plugin;

import com.sun.source.tree.BinaryTree;
import com.sun.source.tree.ExpressionTree;
import com.sun.source.tree.Tree;
import com.sun.source.util.*;

/**
 * Please see the README for more information.
 */
public class OverflowDetectorPlugin implements Plugin {

    @Override
    public String getName() {
        return "OverflowDetectorPlugin";
    }

    @Override
    public void init(JavacTask task, String... args) {
        task.addTaskListener(new TaskListener() {
            @Override
            public void finished(TaskEvent e) {
                if (e.getKind() == TaskEvent.Kind.ANALYZE) {
                    new OverflowDetectorInner().scan(e.getCompilationUnit(), Trees.instance(task));
                }
            }
        });
    }

    static class OverflowDetectorInner extends TreePathScanner<Void, Trees> {

        @Override
        public Void visitBinary(BinaryTree node, Trees trees) {
            if (node.getKind() == Tree.Kind.PLUS) {
                ExpressionTree left = node.getLeftOperand();
                ExpressionTree right = node.getRightOperand();

                if (left.getKind() == Tree.Kind.INT_LITERAL && right.getKind() == Tree.Kind.INT_LITERAL) {
                    try {
                        int leftValue = Integer.parseInt(left.toString());
                        int rightValue = Integer.parseInt(right.toString());

                        // Check for integer overflow
                        if (Integer.MAX_VALUE - rightValue < leftValue) {
                            System.err.println("Integer overflow detected at " + trees.getSourcePositions().getStartPosition(
                                    getCurrentPath().getCompilationUnit(), node));
                        }
                    } catch (NumberFormatException ignored) {
                    }
                }
            }

            return super.visitBinary(node, trees);
        }
    }
}



