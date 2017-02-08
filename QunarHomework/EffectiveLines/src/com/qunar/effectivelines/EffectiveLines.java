package com.qunar.effectivelines;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;

public abstract class EffectiveLines {
    public int countLine(File file, Charset charset) throws IOException {
        checkFile(file);
        return Files.readLines(file, charset, new LineProcessor<Integer>() {
            private int count = 0;

            public boolean processLine(String line) throws IOException {
                if (isValidLine(line)) {
                    count++;
                }
                return true;
            }

            public Integer getResult() {
                return count;
            }
        });
    }

    protected abstract void checkFile(File file);

    protected abstract boolean isValidLine(String line);
}

