package cloud.spring.my.study.gof23.behaviorPatten.interpreter;

import org.springframework.expression.Expression;
import org.springframework.expression.spel.standard.SpelExpressionParser;

/**
 * 解释器模式，例如 Spring 框架中 SpelExpressionParser 使用了解释器模式
 */
public class InterpreterPatten {

//    SpelExpressionParser
//    interpreter

    public static void main(String[] args) {
        SpelExpressionParser parser = new SpelExpressionParser();
        Expression expression = parser.parseExpression("5 + 8 * 3");
        Integer value = (Integer) expression.getValue();
        System.out.println(value);
    }

}
