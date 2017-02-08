package com.qunar.effectivelines;

import java.io.File;

public class LineCounter extends EffectiveLines {

    private static final String JAVA_FILE_SUFFIX = ".java";
    private static final String SINGLE_LINE_COMMENT = "//";
    private static final String MULTI_LINE_COMMENT_PREFIX = "/*";
    private static final String MULTI_LINE_COMMENT_SUFFIX = "*/";

    protected void checkFile(File file) {
        Preconditions.checkArgument(file.getName().endsWith(JAVA_FILE_SUFFIX), "文件必须以" + JAVA_FILE_SUFFIX + "结尾");
    }

    protected boolean isValidLine(String line) {
        if (StringUtils.isBlank(line)) {
            return false;
        }

        String trimmedLine = StringUtils.trimToEmpty(line);
        if (trimmedLine.startsWith(SINGLE_LINE_COMMENT)
                || (trimmedLine.startsWith(MULTI_LINE_COMMENT_PREFIX) && trimmedLine.endsWith(MULTI_LINE_COMMENT_SUFFIX))) {
            return false;
        }

        return true;
    }
}