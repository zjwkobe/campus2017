package mweb;

import com.google.common.base.Charsets;
import org.mozilla.intl.chardet.nsDetector;
import org.mozilla.intl.chardet.nsPSMDetector;

import java.nio.charset.Charset;
import java.util.Optional;

public class Tools {
  private static class Box<T> {
    private T val;

    boolean isEmpty() {
      return val == null;
    }

    void set(T val) {
      this.val = val;
    }
    T get() {
      return val;
    }
  }

  public static Charset getCharsets(byte[] bytes) {
    Box<String> res = new Box<>();
    nsDetector detector = new nsDetector(nsPSMDetector.CHINESE);
    detector.Init(res::set);
    int size = bytes.length;
    if (detector.isAscii(bytes, size))
      return Charsets.US_ASCII;

    detector.DoIt(bytes, size, false);
    detector.DataEnd();
    System.out.println(res.get());
    return Charsets.UTF_8;
  }
}
