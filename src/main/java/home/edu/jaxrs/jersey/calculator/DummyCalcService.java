package home.edu.jaxrs.jersey.calculator;


import org.springframework.stereotype.Service;

import java.util.List;

import static org.springframework.util.CollectionUtils.isEmpty;


/**
 * Simple dummy calculator, able to perform basic arithmetic operations
 */
@Service
public class DummyCalcService {

    /**
     * Performs @param operation with provided args.
     * @param operation {@link Operation}
     * @param args should be only positive numbers
     * @return calculation result, applied to passed in parameters
     */
    public int calculate(Operation operation, List<Integer> args) {
        validateInputArguments(args);
        Integer result = args.remove(0);
        switch(operation) {
            case PLUS:
                for (Integer arg : args) {
                    result += arg;
                }
                return result;
            case MINUS:
                for (Integer arg : args) {
                    result -= arg;
                }
                return result;
            case MULTIPLY:
                for (Integer arg : args) {
                    result *= arg;
                }
                return result;
            case DIVIDE:
                for (Integer arg : args) {
                    result /= arg;
                }
                return result;
            default:
                throw new IllegalArgumentException("Unrecognized operator");
        }
    }

    private void validateInputArguments(List<Integer> args) {
        if (isEmpty(args)) {
            throw new IllegalArgumentException("Arguments list shouldn't be empty");
        }
        boolean containsNegative = args.stream()
                .filter( arg -> arg < 0).findAny()
                .isPresent();
        if (containsNegative) {
            throw new IllegalArgumentException("Arguments should be only positive numbers");
        }
    }

}