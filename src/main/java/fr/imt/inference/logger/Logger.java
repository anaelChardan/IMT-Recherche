package fr.imt.inference.logger;

import io.vavr.collection.List;

import java.util.function.IntPredicate;

public class Logger {

    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_BLACK = "\u001B[30m";
    private static final String ANSI_RED = "\u001B[31m";
    private static final String ANSI_GREEN = "\u001B[32m";
    private static final String ANSI_YELLOW = "\u001B[33m";
    private static final String ANSI_BLUE = "\u001B[34m";
    private static final String ANSI_PURPLE = "\u001B[35m";
    private static final String ANSI_CYAN = "\u001B[36m";
    private static final String ANSI_WHITE = "\u001B[37m";

    private static final String ANSI_BLACK_BOLD_BRIGHT = "\033[1;90m";

    private String className;

    public Logger() {
        this.className = this.prettyClassName(new Exception().getStackTrace()[1].getClassName());
    }

    private String prettyClassName(String className) {
        List<String> exploded = List.of(className.split("\\."));

        final IntPredicate isLastElement = (int i) -> i == exploded.size() - 1;

        final List<ClassNameElement> classNameElementStream = List
                .range(0, exploded.size())
                .map(element -> new ClassNameElement(isLastElement.test(element), exploded.get(element)));

        List<String> readyToPrintClassNameParts = classNameElementStream.map(ClassNameElement::getPrettyValue);

        return readyToPrintClassNameParts.mkString(".");
    }

    private void printMessage(String level, String color, String message) {
        String spaces = List.range(0, 28 - this.className.length()).map(e -> " ").mkString("");

        System.out.println(String.format("%s%5s%s %s[%s]%s %s %s", color, level, ANSI_RESET, ANSI_BLACK_BOLD_BRIGHT, this.className, ANSI_RESET, spaces, message));
    }

    public void debug(String msg) {
        this.printMessage("DEBUG", ANSI_GREEN, msg);
    }

    public void trace(String msg) {
        this.printMessage("TRACE", ANSI_BLUE, msg);
    }

    private class ClassNameElement {
        public final boolean isLastElement;
        public final String element;

        public ClassNameElement(boolean isLastElement, String element) {

            this.isLastElement = isLastElement;
            this.element = element;
        }

        public String getPrettyValue() {
            return this.isLastElement ? this.element : String.valueOf(this.element.charAt(0));
        }
    }
}
